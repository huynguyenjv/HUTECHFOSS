import logging
from SqlAgent_version2.connect_db import get_connection

# Cấu hình logging
logging.basicConfig(
    filename="query_logs.log",
    level=logging.INFO,
    format="%(asctime)s - %(levelname)s - %(message)s"
)

def execute_query(query, params=None):
    """
    Thực thi truy vấn SQL và trả về kết quả.
    - Xử lý cả truy vấn SELECT và các truy vấn thay đổi dữ liệu (INSERT, UPDATE, DELETE).
    - Hỗ trợ trigger trong cơ sở dữ liệu.
    """
    try:
        connection = get_connection()
        cursor = connection.cursor(dictionary=True)

        # Log thông tin truy vấn
        logging.info(f"Executing query: {query} with params: {params}")

        # Thực thi truy vấn
        cursor.execute(query, params or ())

        # Phân biệt SELECT và các truy vấn khác
        if query.strip().lower().startswith("select"):
            result = cursor.fetchall()
        else:
            connection.commit()
            result = {"affected_rows": cursor.rowcount}

        # Đóng kết nối
        cursor.close()
        connection.close()

        return {"success": True, "data": result}

    except Exception as e:
        # Log lỗi nếu xảy ra
        logging.error(f"Error executing query: {query} with params: {params}. Error: {str(e)}")

        # Đóng kết nối trong trường hợp lỗi
        if 'cursor' in locals() and cursor:
            cursor.close()
        if 'connection' in locals() and connection:
            connection.close()

        # Nếu lỗi từ trigger, trả về chi tiết lỗi
        if "SQLSTATE" in str(e):
            return {"error": "Lỗi từ Trigger", "details": str(e)}
        else:
            return {"error": str(e)}
