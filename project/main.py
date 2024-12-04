import logging
from project.gpt.text2sql_gpt4 import generate_sql
from project.database.execute_query import execute_query
from project.utils.response_formatter import format_response
from project.utils.logger import logger


def main():
    logger.info("Application started!")
    print("Welcome to Supporter AI powered by GPT-4!")

    while True:
        user_query = input("Bạn cần hỗ trợ gì? ")
        if user_query.lower() in ["thoát", "exit"]:
            logger.info("Application exited.")
            print("Hẹn gặp lại!")
            break

        try:
            # Sinh câu SQL từ câu hỏi người dùng
            sql_query = generate_sql(user_query)
            print(f"Generated SQL: {sql_query}")

            # Thực thi câu SQL
            data = execute_query(sql_query)

            # Định dạng và hiển thị kết quả
            print(format_response(data))

        except Exception as e:
            logger.error(f"Lỗi khi xử lý câu hỏi: {e}")
            print(f"Đã xảy ra lỗi: {e}")


if __name__ == "__main__":
    main()
