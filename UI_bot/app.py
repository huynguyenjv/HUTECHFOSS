from flask import Flask, request, jsonify, render_template, send_from_directory
import requests
from PIL import Image
import numpy as np
from tensorflow.keras.models import load_model
import os

app = Flask(__name__)

# Đường dẫn đến mô hình
MODEL_PATH = "models/best_model.keras"
disaster_model = load_model(MODEL_PATH)

# Kích thước ảnh chuẩn
IMG_SIZE = 128

# Webhook URL của N8n
N8N_WEBHOOK_URL = "http://localhost:5678/webhook-test/46121572-1543-4d17-85b1-1d810a5a41f1"

# Tiền xử lý ảnh
def preprocess_image(img):
    img = img.resize((IMG_SIZE, IMG_SIZE))
    if img.mode != 'RGB':
        img = img.convert('RGB')
    img = np.array(img) / 255.0
    img = np.expand_dims(img, axis=0)
    return img

# Giao diện chính
@app.route('/')
def home():
    return render_template('index.html')

# Endpoint xử lý tin nhắn
@app.route('/chat', methods=['POST'])
def chat():
    user_message = request.json.get('message', '')

    # Gửi tin nhắn đến N8n
    try:
        response = requests.post(N8N_WEBHOOK_URL, json={"message": user_message})
        response.raise_for_status()
        n8n_reply = response.json().get("reply", "No response from N8n.")
    except requests.exceptions.RequestException as e:
        n8n_reply = f"Error sending request to N8n: {str(e)}"

    return jsonify({"reply": n8n_reply})

@app.route('/upload_image', methods=['POST'])
def upload_image():
    if 'file' not in request.files:
        return jsonify({"reply": "No file uploaded."}), 400

    file = request.files['file']
    try:
        # Lưu ảnh vào thư mục tạm
        file_path = os.path.join("uploads", file.filename)
        file.save(file_path)

        # Tiền xử lý ảnh và dự đoán
        img = Image.open(file_path)
        processed_img = preprocess_image(img)
        prediction = disaster_model.predict(processed_img)
        predicted_value = prediction[0][0]
        label = "Disaster" if predicted_value > 0.5 else "No Disaster"

        # Gửi file và kết quả dự đoán đến N8n webhook
        files = {
            'file': (file.filename, open(file_path, 'rb'), file.mimetype)
        }
        data = {
            'prediction': label  # Kết quả dự đoán từ Flask
        }
        response = requests.post(N8N_WEBHOOK_URL, files=files, data=data)
        response.raise_for_status()

        # Nhận phản hồi từ N8n
        n8n_reply = response.json().get("reply", "No response from N8n.")

        # Trả về URL ảnh, dự đoán từ Flask và phản hồi từ N8n
        file_url = f"/uploads/{file.filename}"
        return jsonify({"reply": n8n_reply, "image_url": file_url, "prediction": label})
    except requests.exceptions.RequestException as e:
        return jsonify({"reply": f"Error sending request to N8n: {str(e)}"}), 500

# Cấp quyền truy cập ảnh đã tải lên
@app.route('/uploads/<filename>')
def uploaded_file(filename):
    return send_from_directory("uploads", filename)

if __name__ == '__main__':
    if not os.path.exists("uploads"):
        os.makedirs("uploads")
    app.run(debug=True)
