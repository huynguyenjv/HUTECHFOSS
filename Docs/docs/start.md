---
sidebar_position: 2
---

# Bắt đầu với V-Relief

Hướng dẫn này sẽ giúp bạn bắt đầu sử dụng **V-Relief**, từ thiết lập ban đầu đến quản lý các chức năng chính của hệ thống. Hãy cùng khám phá các bước cơ bản để triển khai và sử dụng V-Relief một cách hiệu quả.

## Yêu cầu hệ thống

Trước khi bắt đầu, hãy đảm bảo rằng bạn đã chuẩn bị đầy đủ các yêu cầu cần thiết:

- **Node.js** phiên bản 18.0 trở lên: [Tải Node.js tại đây](https://nodejs.org/en/download/)  
  - Khi cài đặt, bạn nên đánh dấu vào tất cả các tùy chọn liên quan đến phụ thuộc.
- **N8N**: Công cụ tự động hóa quy trình (nếu bạn sử dụng backend dựa trên n8n). [Xem hướng dẫn cài đặt tại đây](https://n8n.io/docs).
- **Goong Maps API Key** hoặc **Windy API Key**: Để tích hợp bản đồ và theo dõi thiên tai. 
- Trình quản lý gói **npm** hoặc **yarn**.

---

## Thiết lập dự án

### Bước 1: Tải mã nguồn

Clone mã nguồn dự án V-Relief từ kho lưu trữ:

```bash
git clone https://github.com/huynguyenjv/v-relief.git
cd v-relief
```
### Bước 2: Cài đặt phụ thuộc

Cài đặt các phụ thuộc cần thiết:
```bash
npm install
```
## Khởi động hệ thống

### Bước 3: Chạy ứng dụng

Khởi động ứng dụng bằng lệnh:

```bash
npm run dev
```

Ứng dụng sẽ chạy trên môi trường cục bộ tại địa chỉ http://localhost:5673. Bạn có thể truy cập và bắt đầu kiểm tra các chức năng.

### Bước 4: Cấu hình API

Truy cập tệp cấu hình config.js hoặc .env trong thư mục gốc của dự án.
Thêm API key cho Goong Maps hoặc Windy để kích hoạt tính năng bản đồ và theo dõi thiên tai.

```env
GOONG_API_KEY=your-goong-maps-api-key
WINDY_API_KEY=your-windy-api-key
```

## Tích hợp n8n (Tùy chọn)
Nếu bạn sử dụng n8n để tự động hóa quy trình cứu trợ, hãy thực hiện các bước sau:

1. Triển khai n8n cục bộ hoặc trên máy chủ.
2. Thêm các workflow đã thiết kế cho V-Relief, như xử lý đăng ký nhu yếu phẩm hoặc điều phối tình nguyện viên.
3. Kết nối ứng dụng với n8n bằng API webhook đã được thiết lập.

Bây giờ, bạn đã sẵn sàng khám phá và sử dụng V-Relief để hỗ trợ công tác cứu trợ thiên tai một cách hiệu quả hơn!