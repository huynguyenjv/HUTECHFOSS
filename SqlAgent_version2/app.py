import requests
from flask import Flask, request, jsonify
from sql_agent import SQLAgentWithAI

app = Flask(__name__)

# Khởi tạo SQL Agent với AI


# URL API của UI để nhận kết quả từ Flask App
UI_API_URL = ""

@app.route('/process', methods=['POST'])
def process_request():
    """
    Endpoint để nhận input từ API của n8n, xử lý thông qua agent,
    và gửi kết quả đến API của UI.
    """
    try:
        # Nhận dữ liệu JSON từ n8n
        data = request.json
        if not data or 'message' not in data:
            return jsonify({"error": "Thiếu trường 'message' trong yêu cầu"}), 400

        # Lấy câu hỏi từ n8n
        user_query = data['message']

        # Gọi agent để xử lý câu hỏi
        result = agent.execute(user_query)

        # Log kết quả từ agent
        print(f"Kết quả xử lý bởi agent: {result}")

        # Gửi kết quả tới API UI
        ui_response = requests.post(UI_API_URL, json=result)

        # Kiểm tra phản hồi từ API UI
        if ui_response.status_code == 200:
            return jsonify({"success": True, "message": "Kết quả đã gửi đến UI"}), 200
        else:
            return jsonify({
                "error": "Không thể gửi kết quả đến UI",
                "ui_response_status": ui_response.status_code,
                "ui_response_text": ui_response.text
            }), 500

    except Exception as e:
        # Bắt lỗi toàn bộ và trả về thông báo
        return jsonify({"error": f"Lỗi máy chủ: {str(e)}"}), 500

if __name__ == "__main__":
    # Chạy Flask app
    app.run(host="0.0.0.0", port=5000, debug=True)
