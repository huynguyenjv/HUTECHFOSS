# V-RELIEF DOCUMENT

Đây là dự án tài liệu được xây dựng bằng [Docusaurus](https://docusaurus.io/) - một công cụ hiện đại để tạo website tài liệu nhanh chóng và dễ dàng.

## Yêu cầu hệ thống

- **Node.js**: Phiên bản từ `16.x` trở lên.
- **npm**: Phiên bản từ `8.x` trở lên (hoặc sử dụng **yarn/pnpm**).

## Cài đặt

1. Clone repository:
   ```bash
   git clone https://github.com/huynguyenjv/V-RELIEF.git
   cd V-RELIEF
   ```
2. Di chuyển tới folder website
   ```bash
   cd \Docs
   ```
3. Cài đặt các dependency
   ```bash
   npm install
   ```
## Chạy dự án 
1. Chạy server phát triển:
```bash
npm start
```
2. Mở trình duyệt và truy cập:
```
http://localhost:3000
```
## Build dự án 
1. Tạo bản build production:
```bash
npm run build
```
2. Các file build sẽ được lưu trong thư mục dist.
3. Để kiểm tra bản build:
```bash
npm run serve
```
## Cấu trúc dự án 
```
project-docs/
├── docs/              # Tài liệu chính (Markdown files)
├── blog/              # Bài viết blog (tuỳ chọn)
├── src/               # Mã nguồn tuỳ chỉnh (nếu cần)
├── static/            # Các tệp tĩnh (hình ảnh, favicon, v.v.)
├── docusaurus.config.js # Cấu hình Docusaurus
├── package.json       # Cấu hình npm
└── README.md          # Tệp hướng dẫn

```

## Scripts hữu ích
  npm start: Chạy server phát triển.
  npm run build: Build ứng dụng cho production.
  npm run preview: Chạy bản build để kiểm tra.

## Tùy chỉnh tài liệu
  Thêm tài liệu mới: Thêm file Markdown vào thư mục docs/.
  Cập nhật sidebar: Sửa file sidebars.js để thêm tài liệu mới vào sidebar.
  Tuỳ chỉnh giao diện: Chỉnh sửa mã nguồn trong thư mục src/.

## Công nghệ sử dụng
  Docusaurus: Công cụ xây dựng tài liệu.
  React: Thư viện giao diện người dùng.

## Đóng góp
 Nếu bạn muốn đóng góp vào dự án, hãy tạo pull request hoặc mở issue để thảo luận ý tưởng.
