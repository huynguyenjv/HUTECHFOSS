import logging
from project.gpt.text2sql_gpt4 import Text2SQLAssistant

# Cấu hình logging
logging.basicConfig(level=logging.INFO)

def main():
    assistant = Text2SQLAssistant()
    print("🎉 Chào mừng bạn đến với Supporter AI được hỗ trợ bởi GPT-4!")
    print("Hệ thống sẵn sàng giúp bạn điều phối nhu yếu phẩm và tình nguyện viên.")
    print("Gõ 'thoát' hoặc 'exit' để dừng chương trình bất kỳ lúc nào.")

    while True:
        try:
            user_input = input("\n👋 Bạn cần hỗ trợ gì hôm nay? ").strip()
            if user_input.lower() in ["thoát", "exit"]:
                print("Cảm ơn bạn đã sử dụng hệ thống. Hẹn gặp lại!")
                break

            result = assistant.process_request(user_input)
            print(f"\n📋 Kết quả:\n{result}\n")
        except Exception as e:
            logging.error(f"Lỗi khi xử lý câu hỏi: {e}")
            print("❌ Đã xảy ra lỗi khi xử lý yêu cầu của bạn.")
            print("🔍 Vui lòng kiểm tra lại câu hỏi hoặc cung cấp thông tin rõ ràng hơn.")

if __name__ == "__main__":
    main()
