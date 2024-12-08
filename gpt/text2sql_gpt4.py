import re
from project.database.execute_query import execute_query

class Text2SQLAssistant:
    def __init__(self):
        self.tables = """
        nha_cung_cap(nha_cung_cap_id, ten_nha_cung_cap, so_dien_thoai, email, dia_chi, created_at, updated_at)
        nhu_yeu_pham(nhu_yeu_pham_id, ten_nhu_yeu_pham, don_vi_tinh, loai_nhu_yeu_pham_id, muc_canh_bao, created_at, updated_at)
        ton_kho(nhu_yeu_pham_id, so_luong, updated_at)
        vung(vung_id, ten_vung, lat, lng, loai_thien_tai_id, so_dan, created_at, updated_at)
        nguoi_nhan(nguoi_nhan_id, ten_nguoi_nhan, so_dien_thoai, email, dia_chi, vung_id, so_nguoi_nhan, loai_nguoi_nhan_id, created_at, updated_at)
        """

    def parse_user_query(self, user_query):
        """Phân tích câu hỏi người dùng"""
        data = {
            "name": None,
            "role": None,
            "items": [],
            "location": None,
            "phone": None,
            "household_size": None,
        }

        # Phân tích vai trò
        if "cung cấp" in user_query.lower() or "góp" in user_query.lower():
            data["role"] = "supplier"
        elif "cần" in user_query.lower() or "nhận" in user_query.lower():
            data["role"] = "receiver"

        # Tìm thông tin cụ thể
        name_match = re.search(r"(tôi là|tôi tên là)\s+([\w\s]+)", user_query, re.IGNORECASE)
        if name_match:
            data["name"] = name_match.group(2).strip()

        loc_match = re.search(r"(ở|tại)\s+([\w\s]+)", user_query, re.IGNORECASE)
        if loc_match:
            data["location"] = loc_match.group(2).strip()

        phone_match = re.search(r"\b0\d{9,10}\b", user_query)
        if phone_match:
            data["phone"] = phone_match.group(0)

        items_pattern = re.findall(r"(\d+)\s*(kg|thùng|bao|gói)\s+([\w\s]+)", user_query.lower())
        for qty, unit, item_name in items_pattern:
            data["items"].append({
                "ten_nyp": item_name.strip(),
                "so_luong": int(qty),
                "don_vi": unit
            })

        return data

    def ensure_region_exists(self, location):
        """Đảm bảo vùng tồn tại"""
        try:
            check_region_query = f"SELECT vung_id FROM vung WHERE ten_vung = '{location}' LIMIT 1;"
            region = execute_query(check_region_query)
            if not region:
                insert_region_query = f"INSERT INTO vung (ten_vung, created_at, updated_at) VALUES ('{location}', NOW(), NOW());"
                execute_query(insert_region_query)
                region = execute_query(check_region_query)
            return region[0]['vung_id'] if region else None
        except Exception as e:
            raise ValueError(f"Lỗi khi kiểm tra hoặc thêm vùng: {e}")

    def handle_supplier_request(self, parsed_data):
        """Xử lý yêu cầu từ nhà cung cấp"""
        try:
            check_supplier_query = f"""
            SELECT nha_cung_cap_id FROM nha_cung_cap 
            WHERE ten_nha_cung_cap = '{parsed_data['name']}' AND so_dien_thoai = '{parsed_data['phone']}';
            """
            existing_supplier = execute_query(check_supplier_query)

            if existing_supplier:
                supplier_id = existing_supplier[0]['nha_cung_cap_id']
                update_supplier_query = f"""
                UPDATE nha_cung_cap SET dia_chi = '{parsed_data['location']}', updated_at = NOW() 
                WHERE nha_cung_cap_id = {supplier_id};
                """
                execute_query(update_supplier_query)
            else:
                insert_supplier_query = f"""
                INSERT INTO nha_cung_cap (ten_nha_cung_cap, so_dien_thoai, dia_chi, created_at, updated_at)
                VALUES ('{parsed_data['name']}', '{parsed_data['phone']}', '{parsed_data['location']}', NOW(), NOW());
                """
                execute_query(insert_supplier_query)

            for item in parsed_data["items"]:
                insert_item_query = f"""
                INSERT INTO ton_kho (nhu_yeu_pham_id, so_luong, updated_at)
                VALUES ((SELECT nhu_yeu_pham_id FROM nhu_yeu_pham WHERE ten_nhu_yeu_pham = '{item['ten_nyp']}' LIMIT 1), {item['so_luong']}, NOW())
                ON DUPLICATE KEY UPDATE so_luong = so_luong + {item['so_luong']}, updated_at = NOW();
                """
                execute_query(insert_item_query)

            return f"Thông tin nhà cung cấp {parsed_data['name']} đã được ghi nhận và tồn kho được cập nhật."
        except Exception as e:
            raise ValueError(f"Lỗi khi xử lý yêu cầu từ nhà cung cấp: {e}")

    def ensure_region_exists(self, location):
        """Đảm bảo vùng tồn tại"""
        try:
            # Kiểm tra vùng đã tồn tại chưa
            check_region_query = f"SELECT vung_id FROM vung WHERE ten_vung = '{location}' LIMIT 1;"
            region = execute_query(check_region_query)

            # Nếu chưa tồn tại, tự động thêm vùng mới
            if not region:
                insert_region_query = f"""
                INSERT INTO vung (ten_vung, created_at, updated_at)
                VALUES ('{location}', NOW(), NOW());
                """
                execute_query(insert_region_query)
                region = execute_query(check_region_query)

            # Trả về ID của vùng
            return region[0]['vung_id'] if region else None
        except Exception as e:
            raise ValueError(f"Lỗi khi kiểm tra hoặc thêm vùng: {e}")

    def handle_receiver_request(self, parsed_data):
        """Xử lý yêu cầu từ người nhận"""
        try:
            # Đảm bảo vùng tồn tại hoặc thêm mới
            region_id = self.ensure_region_exists(parsed_data["location"])
            if not region_id:
                return f"Không thể thêm vùng {parsed_data['location']}."

            # Tổng tồn kho
            check_total_inventory_query = """
            SELECT IFNULL(SUM(so_luong), 0) AS total_inventory FROM ton_kho;
            """
            total_inventory_result = execute_query(check_total_inventory_query)
            total_inventory = total_inventory_result[0]['total_inventory'] if total_inventory_result else 0

            # Tổng số lượng yêu cầu
            total_requested = sum(item['so_luong'] for item in parsed_data["items"])

            # So sánh tổng tồn kho với tổng số lượng yêu cầu
            if total_inventory >= total_requested:
                # Ghi nhận thông tin người nhận
                insert_receiver_query = f"""
                INSERT INTO nguoi_nhan (ten_nguoi_nhan, so_dien_thoai, dia_chi, vung_id, created_at, updated_at)
                VALUES ('{parsed_data['name']}', '{parsed_data['phone']}', '{parsed_data['location']}', {region_id}, NOW(), NOW());
                """
                execute_query(insert_receiver_query)

                return f"Thông tin người nhận {parsed_data['name']} đã được ghi nhận thành công."
            else:
                return f"Hiện không đủ số lượng nhu yếu phẩm trong kho."
        except Exception as e:
            raise ValueError(f"Lỗi khi xử lý yêu cầu từ người nhận: {e}")

    def process_request(self, user_query):
        """Xử lý yêu cầu từ người dùng"""
        parsed_data = self.parse_user_query(user_query)

        if parsed_data["role"] == "supplier":
            return self.handle_supplier_request(parsed_data)
        elif parsed_data["role"] == "receiver":
            return self.handle_receiver_request(parsed_data)
        else:
            return "Vai trò hoặc hành động này hiện chưa được hỗ trợ."
