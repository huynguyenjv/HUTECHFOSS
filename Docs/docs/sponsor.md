---
sidebar_position: 5
---

# Công Nghệ Sử Dụng

Dự án **V-Relief** ứng dụng nhiều công nghệ hiện đại để đảm bảo hiệu quả trong việc hỗ trợ cứu trợ thiên tai. Dưới đây là các công nghệ và cách triển khai:

## 1. Hiển Thị Bản Đồ và Theo Dõi Thời Gian Thực
- **API Windy.com**: Cung cấp dữ liệu thời tiết, theo dõi bão và các hiện tượng thiên nhiên thời gian thực.
- **React/Next.js**: Tạo giao diện bản đồ và thông báo cảnh báo.
- **n8n**: Tự động gửi cảnh báo qua email hoặc SMS khi người dùng ở vùng nguy hiểm.

## 2. Quản Lý Trung Tâm Cứu Trợ
- **React/Next.js**: Giao diện tìm kiếm và hiển thị trung tâm cứu trợ.
- **n8n**: Workflow tự động cập nhật trạng thái trung tâm cứu trợ.

## 3. Đăng Ký Hỗ Trợ Nhu Yếu Phẩm
- **Goong Maps**: Định vị vị trí người dùng.
- **React/Next.js**: Form đăng ký nhu yếu phẩm.
- **n8n**: Tự động gửi thông báo nhu yếu phẩm đến trung tâm cứu trợ gần nhất.

## 4. Bản Đồ Cộng Đồng Hỗ Trợ
- **Goong Maps**: Hiển thị vị trí tình nguyện viên và các dịch vụ hỗ trợ.
- **n8n**: Tự động gửi thông báo khi có yêu cầu hỗ trợ gần tình nguyện viên.

## 5. Quản Lý Kho Cứu Trợ
- **Retool**: Dashboard giúp quản lý tình trạng kho và phân bổ nhu yếu phẩm.
- **n8n**: Workflow cập nhật tự động tình trạng kho cứu trợ.

## 6. Trung tâm điều phối thông minh 
- **OpenAI**: Cung cấp api cho quá trình tạo agent
- **n8n** : Workflow để quản lý thông tin input và output cho agent

## Công Nghệ Tổng Thể
- **Frontend**: 
  - React/Next.js: Phát triển giao diện người dùng.
  - Goong Maps và Windy.com: Hiển thị bản đồ và thông tin thời tiết.

- **Backend**: 
  - python: phát triển agent
  - java : trang quản lý trung tâm
  - n8n: Workflow tự động hóa các tác vụ backend.

- **Tự Động Hóa**: 
  - n8n: Kết nối API, xử lý dữ liệu đăng ký, cập nhật trạng thái kho.

---

Với sự kết hợp của các công nghệ trên, **V-Relief** sẽ là nền tảng mạnh mẽ, hiện đại và hiệu quả để hỗ trợ cứu trợ thiên tai, kết nối cộng đồng và chính quyền địa phương.
