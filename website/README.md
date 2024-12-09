# V-RELIEF FRONT-END 


Đây là một dự án React được tạo bằng Vite - một công cụ build hiện đại, nhanh và nhẹ dành cho các ứng dụng JavaScript.

## Yêu cầu hệ thống

- **Node.js**: Phiên bản từ `16.x` trở lên.
- **npm**: Phiên bản từ `8.x` trở lên (hoặc sử dụng **yarn/pnpm**).

## Cài đặt

1. Clone repository:
   ```bash
   git clone https://github.com/huynguyenjv/V-RELIEF.git
   cd project-name
   ```
2. Di chuyển tới folder website
   ```bash
   cd \website
   ```
3. Cài đặt các dependency
   ```bash
   npm install
   ```
## Chạy dự án 
1. Chạy server phát triển:
```bash
npm run dev
```
2. Mở trình duyệt và truy cập:
```
http://localhost:5173
```
## Build dự án 
1. Tạo bản build production:
```bash
npm run build
```
2. Các file build sẽ được lưu trong thư mục dist.
3. Để kiểm tra bản build:
```bash
npm run preview
```
## Cấu trúc dự án 
```
project-name/
├── public/          # Thư mục chứa các file tĩnh
├── src/             # Source code chính
│   ├── assets/      # Hình ảnh, file CSS, v.v.
│   ├── components/  # Các component React
|   ├── features     # Nơi chữa các pages
|   ├── shared       # Nơi chưa các file shared
│   ├── App.jsx      # Component gốc
│   └── main.jsx     # Entry point chính
├── package.json     # Cấu hình npm
├── vite.config.js   # Cấu hình Vite
└── README.md        # Tệp hướng dẫn
```

## Scripts hữu ích
  npm run dev: Chạy server phát triển.
  npm run build: Build ứng dụng cho production.
  npm run preview: Chạy bản build để kiểm tra.

## Công nghệ sử dụng
  Vite: Công cụ build siêu nhanh.
  React: Thư viện xây dựng giao diện người dùng.

## Đóng góp
 Nếu bạn muốn đóng góp vào dự án, hãy tạo pull request hoặc mở issue để thảo luận ý tưởng.
