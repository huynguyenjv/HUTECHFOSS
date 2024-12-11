import mysql.connector

def get_connection():
    return mysql.connector.connect(
        host="103.106.105.176",  # Hoặc địa chỉ IP của máy chủ MySQL
        user="phong@hutechfoss",       # Tên người dùng MySQL
        password="123123", # Mật khẩu MySQL
        database="VRELIEF"  # Tên cơ sở dữ liệu
    )
