import pandas as pd

# Định nghĩa giới hạn khu vực Biển Đông
lat_min, lat_max = 0, 25    # Vĩ độ từ 0° đến 25°N
lon_min, lon_max = 100, 122  # Kinh độ từ 100°E đến 122°E

# Bước nhảy tính bằng độ
lat_step = 0.45  # Tương ứng ~50 km
lon_step = 0.52  # Tương ứng ~50 km

# Tạo danh sách tọa độ
coordinates = []
lat = lat_min
while lat <= lat_max:
    lon = lon_min
    while lon <= lon_max:
        coordinates.append((round(lat, 2), round(lon, 2)))
        lon += lon_step
    lat += lat_step

# Chuyển sang DataFrame để dễ lưu và xử lý
df = pd.DataFrame(coordinates, columns=["Latitude", "Longitude"])

# Lưu vào file CSV
df.to_csv("bien_dong_coordinates.csv", index=False)

print("Danh sách tọa độ đã được lưu vào file 'bien_dong_coordinates.csv'.")
