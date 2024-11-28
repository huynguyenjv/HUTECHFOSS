import os
import numpy as np
from PIL import Image
from sklearn.model_selection import train_test_split
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Conv2D, MaxPooling2D, Flatten, Dense, Dropout, BatchNormalization
from tensorflow.keras.optimizers import Adam
from tensorflow.keras.callbacks import EarlyStopping
from tensorflow.keras.utils import to_categorical
from tensorflow.keras.preprocessing.image import ImageDataGenerator
from sklearn.metrics import classification_report, confusion_matrix

# Đường dẫn tới dữ liệu
image_dir = "V-RELIEF/Comprehensive Disaster Dataset(CDD)"

# Kích thước ảnh chuẩn hóa
IMG_SIZE = 128  # Kích thước chuẩn hóa ảnh về 128x128

# Danh sách lưu đường dẫn ảnh và nhãn
image_paths = []
labels = []

# Lấy danh sách các nhãn (labels) tự động từ thư mục
all_labels = os.listdir(image_dir)

# Duyệt qua các thư mục cha (thảm họa)
for label in all_labels:
    label_path = os.path.join(image_dir, label)
    if os.path.isdir(label_path):  # Kiểm tra nếu là thư mục
        for sub_label in os.listdir(label_path):
            sub_label_path = os.path.join(label_path, sub_label)
            if os.path.isdir(sub_label_path):
                for image_name in os.listdir(sub_label_path):
                    image_paths.append(os.path.join(sub_label_path, image_name))
                    labels.append(label)  # Gán nhãn cha

# Tạo từ điển `label_to_idx` tự động từ danh sách nhãn
label_to_idx = {label: idx for idx, label in enumerate(all_labels)}

# Chuyển nhãn thành chỉ số
labels = [label_to_idx[label] for label in labels]

# Xác định số lượng nhãn tự động từ `label_to_idx`
NUM_CLASSES = len(label_to_idx)

# Chia dữ liệu thành tập huấn luyện và kiểm tra
X_train, X_test, y_train, y_test = train_test_split(image_paths, labels, test_size=0.2, random_state=42)

# Tiền xử lý ảnh: Chuyển ảnh thành mảng numpy, thay đổi kích thước và chuẩn hóa
def preprocess_images(image_paths, labels):
    images = []
    filtered_labels = []  # List to hold corresponding labels for valid images
    error_count = 0
    for path, label in zip(image_paths, labels):
        try:
            img = Image.open(path)
            img = img.resize((IMG_SIZE, IMG_SIZE))  # Đảm bảo kích thước ảnh
            if img.mode != 'RGB':
                img = img.convert('RGB')  # Chuyển đổi ảnh thành RGB nếu không phải
            img = np.array(img)
            if img.shape == (IMG_SIZE, IMG_SIZE, 3):
                images.append(img)
                filtered_labels.append(label)
            else:
                error_count += 1
        except Exception as e:
            error_count += 1

    if error_count > 0:
        print(f"{error_count} images were skipped due to errors.")
    
    return np.array(images), np.array(filtered_labels)

X_train, y_train = preprocess_images(X_train, y_train)
X_test, y_test = preprocess_images(X_test, y_test)

# Kiểm tra dữ liệu
assert len(X_train) == len(y_train), f"Số lượng ảnh và nhãn không khớp: {len(X_train)} vs {len(y_train)}"
assert len(X_test) == len(y_test), f"Số lượng ảnh và nhãn không khớp: {len(X_test)} vs {len(y_test)}"

# Chuẩn hóa dữ liệu
X_train = X_train / 255.0
X_test = X_test / 255.0

# Chuyển nhãn thành dạng one-hot encoding
y_train = to_categorical(y_train, NUM_CLASSES)
y_test = to_categorical(y_test, NUM_CLASSES)

# Tạo đối tượng ImageDataGenerator để tăng cường dữ liệu
datagen = ImageDataGenerator(
    rotation_range=20,
    width_shift_range=0.2,
    height_shift_range=0.2,
    shear_range=0.2,
    zoom_range=0.2,
    horizontal_flip=True,
    fill_mode='nearest'
)

# Fit dữ liệu tăng cường trên tập huấn luyện
datagen.fit(X_train)

# Xây dựng mô hình CNN
model = Sequential([    
    Conv2D(32, (3, 3), activation='relu', input_shape=(IMG_SIZE, IMG_SIZE, 3)),
    BatchNormalization(),
    MaxPooling2D(pool_size=(2, 2)),
    
    Conv2D(64, (3, 3), activation='relu'),
    BatchNormalization(),
    MaxPooling2D(pool_size=(2, 2)),
    
    Conv2D(128, (3, 3), activation='relu'),
    BatchNormalization(),
    MaxPooling2D(pool_size=(2, 2)),
    
    Flatten(),
    Dense(128, activation='relu'),
    Dropout(0.5),
    Dense(NUM_CLASSES, activation='softmax')
])

# Biên dịch mô hình
model.compile(optimizer=Adam(), loss='categorical_crossentropy', metrics=['accuracy'])

# Sử dụng EarlyStopping để tránh overfitting
early_stopping = EarlyStopping(monitor='val_loss', patience=3, restore_best_weights=True)

# Huấn luyện mô hình
model.fit(datagen.flow(X_train, y_train, batch_size=32), 
          validation_data=(X_test, y_test), 
          epochs=50, 
          callbacks=[early_stopping])

# Đánh giá mô hình trên tập kiểm tra
y_pred = model.predict(X_test)
y_pred_labels = np.argmax(y_pred, axis=1)

# In kết quả phân loại
print(classification_report(np.argmax(y_test, axis=1), y_pred_labels, target_names=list(label_to_idx.keys())))

# In ma trận nhầm lẫn
print(confusion_matrix(np.argmax(y_test, axis=1), y_pred_labels))
