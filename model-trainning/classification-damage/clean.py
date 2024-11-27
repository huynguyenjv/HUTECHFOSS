import tensorflow as tf
from tensorflow.keras.preprocessing.image import ImageDataGenerator
from tensorflow.keras.callbacks import ModelCheckpoint, EarlyStopping, TensorBoard
import matplotlib.pyplot as plt
import os

# Đường dẫn đến thư mục dữ liệu
train_dir = './data/train'
val_dir = './data/val'

# Custom generator để bỏ qua ảnh bị hỏng
def safe_load_img(file_path):
    try:
        img = load_img(file_path)  # Kiểm tra khả năng load ảnh
        return True  # Ảnh hợp lệ
    except:
        print(f"Ảnh bị hỏng: {file_path}")
        return False

# Duyệt qua tất cả ảnh và loại bỏ ảnh bị hỏng
def clean_dataset(directory):
    for root, _, files in os.walk(directory):
        for file in files:
            file_path = os.path.join(root, file)
            if not safe_load_img(file_path):
                os.remove(file_path)  # Xóa ảnh bị hỏng
                print(f"Đã xóa ảnh: {file_path}")

# Làm sạch dataset
clean_dataset(train_dir)
clean_dataset(val_dir)