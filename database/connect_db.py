import pymysql
from project.setting import DATABASE_CONFIG  # Sử dụng từ cấu hình settings.py

def connect_to_db():
    """Hàm kết nối đến cơ sở dữ liệu sử dụng thông tin từ settings.py"""
    connection = pymysql.connect(
        host=DATABASE_CONFIG["host"],
        user=DATABASE_CONFIG["user"],
        password=DATABASE_CONFIG["password"],
        database=DATABASE_CONFIG["database"],
        charset='utf8mb4',
        cursorclass=pymysql.cursors.DictCursor
    )
    return connection
