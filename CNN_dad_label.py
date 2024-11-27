import os
from tensorflow.keras.preprocessing import image

# Đường dẫn tới dữ liệu
dataset_dir = 'path_to_your_dataset'

image_paths = []
labels = []

# Quét qua các thư mục nhãn cha
for label in os.listdir(dataset_dir):
    label_dir = os.path.join(dataset_dir, label)
    
    # Kiểm tra xem có phải thư mục nhãn cha không
    if os.path.isdir(label_dir):
        # Quét qua các nhãn con trong thư mục nhãn cha
        for sub_label in os.listdir(label_dir):
            sub_label_dir = os.path.join(label_dir, sub_label)
            if os.path.isdir(sub_label_dir):
                # Quét tất cả các hình ảnh trong nhãn con
                for img_name in os.listdir(sub_label_dir):
                    if img_name.endswith('.jpg') or img_name.endswith('.png'):
                        # Lưu đường dẫn ảnh và nhãn
                        image_paths.append(os.path.join(sub_label_dir, img_name))
                        labels.append(f'{label}_{sub_label}')  # Kết hợp nhãn cha và con

# Kiểm tra số lượng hình ảnh và nhãn
print(f'Total images: {len(image_paths)}')
print(f'Total labels: {len(labels)}')
