import re
from execute_query import execute_query
import openai

class SQLAgent:
    def __init__(self):
        self.tables = self.fetch_metadata()

    def fetch_metadata(self):
        """Lấy metadata từ cơ sở dữ liệu"""
        query = """
        SELECT TABLE_NAME, COLUMN_NAME, DATA_TYPE
        FROM INFORMATION_SCHEMA.COLUMNS
        WHERE TABLE_SCHEMA = 'VRELIEF';
        """
        metadata = execute_query(query)
        formatted_tables = {}
        for row in metadata:
            table_name = row['TABLE_NAME']
            column_name = row['COLUMN_NAME']
            if table_name not in formatted_tables:
                formatted_tables[table_name] = []
            formatted_tables[table_name].append(column_name)
        return formatted_tables

    def parse_query(self, user_input):
        """Phân tích câu hỏi người dùng để tạo truy vấn SQL"""
        data = {
            "role": None,
            "details": None
        }
        if "tình nguyện viên" in user_input.lower():
            data["role"] = "tinh_nguyen_vien"
        elif "người nhận" in user_input.lower():
            data["role"] = "nguoi_nhan"
        elif "quyên góp" in user_input.lower():
            data["role"] = "nha_quyen_gop"
        elif "nhu yếu phẩm" in user_input.lower():
            data["role"] = "nhu_yeu_pham"
        elif "phiếu điều phối" in user_input.lower():
            data["role"] = "phieu_dieu_phoi"

        name_match = re.search(r"(tôi là|tên tôi là)\s+([\w\s]+)", user_input, re.IGNORECASE)
        if name_match:
            data["details"] = name_match.group(2).strip()

        return data

    def generate_sql(self, parsed_data):
        """Sinh truy vấn SQL từ dữ liệu đã phân tích"""
        if parsed_data["role"] == "tinh_nguyen_vien":
            return "SELECT * FROM TINHNGUYENVIEN;"
        elif parsed_data["role"] == "nguoi_nhan":
            return "SELECT * FROM THONGTINNGUOINHAN;"
        elif parsed_data["role"] == "nha_quyen_gop":
            return "SELECT * FROM THONGTINNGUOIQUYENGOP;"
        elif parsed_data["role"] == "nhu_yeu_pham":
            return "SELECT * FROM NHUYEUPHAM;"
        elif parsed_data["role"] == "phieu_dieu_phoi":
            return "SELECT * FROM PHIEUDIEUPHOI;"
        else:
            return None

    def execute_query_with_trigger(self, query):
        """Thực thi truy vấn SQL với Trigger trong DB"""
        try:
            result = execute_query(query)
            return {"success": True, "data": result}
        except Exception as e:
            return {"error": f"Lỗi từ Trigger: {str(e)}"}

    def allocate_supply(self, ma_nhu_yeu_pham, so_luong):
        """Phân bổ nhu yếu phẩm từ kho"""
        query = f"""
        INSERT INTO PHIEUDIEUPHOI (MaNhuYeuPham, SLYeuCau)
        VALUES ('{ma_nhu_yeu_pham}', {so_luong});
        """
        return self.execute_query_with_trigger(query)

    def execute(self, user_input):
        """Xử lý câu hỏi từ người dùng"""
        parsed_data = self.parse_query(user_input)
        query = self.generate_sql(parsed_data)
        if query:
            return execute_query(query)
        else:
            return {"error": "Không thể tạo truy vấn từ câu hỏi này."}

class SQLAgentWithAI(SQLAgent):
    def __init__(self, api_key):
        super().__init__()
        openai.api_key = api_key

    def use_ai_to_generate_query(self, user_input):
        """Sử dụng GPT để sinh truy vấn SQL"""
        prompt = f"""
        Bạn là một hệ thống hỗ trợ quản lý dữ liệu cứu trợ thiên tai. Dưới đây là cấu trúc bảng dữ liệu:
        {self.tables}

        Người dùng hỏi: "{user_input}"

        Yêu cầu:
        - Sinh truy vấn SQL phù hợp với câu hỏi.
        - Nếu câu hỏi liên quan đến cập nhật hoặc kiểm tra dữ liệu, hãy đảm bảo trigger trong cơ sở dữ liệu được sử dụng.
        """
        try:
            response = openai.Completion.create(
                engine="text-davinci-003",
                prompt=prompt,
                max_tokens=200,
                temperature=0
            )
            sql_query = response.choices[0].text.strip()
            return sql_query
        except Exception as e:
            return None

    def execute(self, user_input):
        """Xử lý câu hỏi với AI và fallback nếu không dùng được AI"""
        try:
            sql_query = self.use_ai_to_generate_query(user_input)
            if sql_query:
                print(f"Truy vấn AI sinh ra: {sql_query}")
                return self.execute_query_with_trigger(sql_query)
            else:
                print("Không sử dụng được AI, fallback sang logic cứng.")
                return super().execute(user_input)
        except Exception as e:
            print(f"Lỗi: {e}, sử dụng fallback logic.")
            return super().execute(user_input)

# # Ví dụ sử dụng
# if __name__ == "__main__":
#     agent = SQLAgentWithAI(api_key="YOUR_OPENAI_API_KEY")
#     user_query = "Hiển thị danh sách tình nguyện viên tại TP.HCM"
#     result = agent.execute(user_query)
#     print(result)
