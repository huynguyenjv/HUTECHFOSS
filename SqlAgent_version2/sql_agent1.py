import openai
import json
from datetime import datetime
import mysql.connector
import re

# Kết nối tới GPT API

# Lớp quản lý cơ sở dữ liệu
class DatabaseManager:
    def __init__(self, host, user, password, database):
        try:
            self.db = mysql.connector.connect(
                host=host,
                user=user,
                password=password,
                database=database
            )
            self.cursor = self.db.cursor(dictionary=True)
            print("Kết nối cơ sở dữ liệu thành công.")
        except mysql.connector.Error as err:
            print(f"Không thể kết nối tới MySQL: {err}")
            exit(1)

    # Phương thức thực thi truy vấn SELECT
    def query(self, query, params=None):
        try:
            if params:
                self.cursor.execute(query, params)
            else:
                self.cursor.execute(query)
            return self.cursor.fetchall()
        except mysql.connector.Error as err:
            print(f"Lỗi truy vấn cơ sở dữ liệu: {err}")
            return None

    # Phương thức thực thi truy vấn INSERT, UPDATE, DELETE
    def execute(self, query, params=None):
        try:
            if params:
                self.cursor.execute(query, params)
            else:
                self.cursor.execute(query)
            self.db.commit()
        except mysql.connector.Error as err:
            print(f"Lỗi thực thi cơ sở dữ liệu: {err}")
            self.db.rollback()

    # Phương thức kiểm tra cấu trúc bảng
    def get_table_structure(self, table_name):
        try:
            query = f"DESCRIBE {table_name};"
            self.cursor.execute(query)
            return self.cursor.fetchall()
        except mysql.connector.Error as err:
            print(f"Lỗi khi kiểm tra cấu trúc bảng: {err}")
            return []

# Khởi tạo kết nối cơ sở dữ liệu
db_manager = DatabaseManager(
    host="103.106.105.176",
    user="phong@hutechfoss",
    password="123123",
    database="VRELIEF"
)

# Hàm chuyển đổi datetime thành chuỗi
def datetime_converter(o):
    if isinstance(o, datetime):
        return o.isoformat()

# Hàm sử dụng GPT-3.5 để phân tích yêu cầu
def analyze_with_gpt(user_input):
    prompt = f"""
        Bạn là một hệ thống hỗ trợ điều phối cứu trợ. Dựa trên yêu cầu sau đây, hãy trích xuất các thông tin cần thiết theo định dạng JSON bao gồm:
        - Tên người yêu cầu
        - Loại yêu cầu (cứu trợ, quyên góp, nhận)
        - Nhu yếu phẩm và số lượng
        - Khu vực liên quan

        Yêu cầu: "{user_input}"

        Định dạng JSON trả về:
        {{
            "user_name": "Tên người yêu cầu",
            "request_type": "Loại yêu cầu",
            "items": [
                {{
                    "item_name": "Tên nhu yếu phẩm",
                    "quantity": "Số lượng"
                }}
            ],
            "location": "Tên khu vực"
        }}
        """
    try:
        response = openai.ChatCompletion.create(
            model="gpt-3.5-turbo",
            messages=[{"role": "system", "content": "Bạn là một trợ lý hỗ trợ điều phối cứu trợ."},
                      {"role": "user", "content": prompt}],
            max_tokens=300,
            temperature=0.3,
        )
        gpt_output = response.choices[0].message['content'].strip()
        match = re.search(r'\{.*\}', gpt_output, re.DOTALL)
        if match:
            json_str = match.group(0)
            data = json.loads(json_str)
            return data
        else:
            print("GPT không trả về định dạng JSON hợp lệ.")
            print(f"Đầu ra từ GPT: {gpt_output}")
            return None
    except openai.error.OpenAIError as e:
        print(f"Lỗi OpenAI API: {e}")
        return None
    except json.JSONDecodeError:
        print("Lỗi phân tích JSON từ GPT.")
        print(f"Đầu ra từ GPT: {gpt_output}")
        return None
    except Exception as e:
        print(f"Lỗi trong quá trình xử lý GPT: {e}")
        return None

# Hàm kiểm tra và thêm khu vực nếu chưa có
def check_and_add_area(location):
    area = db_manager.query("SELECT * FROM KHUVUCTHIENTAI WHERE LOWER(TenKhuVuc) = LOWER(%s)", (location,))
    if not area:
        print(f"Khu vực '{location}' không tồn tại trong cơ sở dữ liệu. Đang tự động thêm khu vực này vào cơ sở dữ liệu.")
        db_manager.execute("INSERT INTO KHUVUCTHIENTAI (TenKhuVuc, TrangThai) VALUES (%s, %s)", (location, 1))
        print(f"Khu vực '{location}' đã được thêm vào cơ sở dữ liệu.")
    return True

# Hàm kiểm tra cấu trúc bảng và tạo truy vấn động
def handle_dynamic_query(table_name, filters):
    table_structure = db_manager.get_table_structure(table_name)
    if not table_structure:
        print(f"Không thể lấy cấu trúc bảng '{table_name}'.")
        return []

    available_columns = [column['Field'] for column in table_structure]

    # Tạo điều kiện động cho truy vấn
    conditions = []
    params = []
    for column, value in filters.items():
        if column in available_columns:
            conditions.append(f"{column} = %s")
            params.append(value)

    # Tạo câu truy vấn SQL động
    if conditions:
        query = f"SELECT * FROM {table_name} WHERE {' AND '.join(conditions)}"
        return db_manager.query(query, params)
    else:
        print("Không có cột hợp lệ trong bảng.")
        return []

# Hàm xử lý yêu cầu người dùng
def handle_user_request(request):
    extracted_data = analyze_with_gpt(request)

    if not extracted_data:
        print("Không thể trích xuất thông tin từ yêu cầu.")
        return

    user_name = extracted_data.get("user_name")
    request_type = extracted_data.get("request_type", "").lower()
    items = extracted_data.get("items", [])
    location = extracted_data.get("location")

    if not user_name or not request_type or not items or not location:
        print("Thông tin trích xuất không đầy đủ. Vui lòng kiểm tra lại yêu cầu.")
        return

    print(f"Đã trích xuất thông tin: {extracted_data}")

    check_and_add_area(location)  # Kiểm tra và thêm khu vực nếu cần

    # Xử lý các loại yêu cầu khác nhau
    if request_type == "quyên góp":
        print("Đang xử lý yêu cầu quyên góp...")
    elif request_type == "nhận":
        print("Đang xử lý yêu cầu nhận...")
    elif request_type == "cứu trợ":
        print("Đang xử lý yêu cầu cứu trợ...")
    else:
        print(f"Loại yêu cầu '{request_type}' không được hỗ trợ.")

# Hàm nhập yêu cầu từ người dùng
def input_data():
    request = input(">> ")
    handle_user_request(request)
if __name__ == "__main__":
    print("Hệ thống đang chạy...")
    while True:
        print("\n==============================")
        input_data()
        continue_input = input("\nBạn có muốn nhập lại không? (y/n): ")
        if continue_input.lower() != 'y':
            break
    print("Hệ thống đã kết thúc.")
