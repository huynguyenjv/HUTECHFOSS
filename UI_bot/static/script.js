async function sendFile() {
    const fileInput = document.getElementById('fileInput');
    const file = fileInput.files[0];
    if (!file) return;

    const formData = new FormData();
    formData.append('file', file);

    appendMessage('user', `Uploaded file: ${file.name}`);

    try {
        const response = await fetch('/upload_image', {
            method: 'POST',
            body: formData,
        });

        const result = await response.json();

        // Hiển thị ảnh đã tải lên
        if (result.image_url) {
            appendImage('user', result.image_url);
        }

        // Hiển thị dự đoán từ Flask
        if (result.prediction) {
            appendMessage('bot', `Prediction (Flask): ${result.prediction}`);
        }

        // Hiển thị phản hồi từ N8n
        appendMessage('bot', `N8n Reply: ${result.reply}`);
    } catch (error) {
        appendMessage('bot', 'Error uploading file.');
    }
}

function appendImage(sender, imageUrl) {
    const chatBody = document.getElementById('chatBody');
    const div = document.createElement('div');
    div.className = sender === 'user' ? 'message user' : 'message bot';
    const img = document.createElement('img');
    img.src = imageUrl;
    img.alt = "Uploaded Image";
    img.style.maxWidth = "100%";
    img.style.borderRadius = "10px";
    div.appendChild(img);
    chatBody.appendChild(div);
    chatBody.scrollTop = chatBody.scrollHeight;
}

function appendMessage(sender, text) {
    const chatBody = document.getElementById('chatBody');
    const div = document.createElement('div');
    div.className = sender === 'user' ? 'message user' : 'message bot';
    div.textContent = text;
    chatBody.appendChild(div);
    chatBody.scrollTop = chatBody.scrollHeight;
}
