def format_response(data):
    if not data:
        return "Không tìm thấy dữ liệu."
    response = "Kết quả:\n"
    for row in data:
        response += ", ".join([f"{key}: {value}" for key, value in row.items()]) + "\n"
    return response
