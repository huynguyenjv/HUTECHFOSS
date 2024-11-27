import os

# Đường dẫn đến thư mục chứa dữ liệu
dataset_dir = 'Comprehensive Disaster Dataset(CDD)'

# Liệt kê các thư mục con (labels) trong thư mục dataset
labels = os.listdir(dataset_dir)
print("Số lượng nhãn:", len(labels))
print("Các nhãn có sẵn:", labels)
