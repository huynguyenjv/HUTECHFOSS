import dayjs from 'dayjs';

export interface ITinhNguyenVien {
  id?: number;
  tenTinhNguyenVien?: string;
  soDienThoai?: string | null;
  diaChi?: string | null;
  trangThaiId?: number | null;
  createdAt?: dayjs.Dayjs | null;
  updatedAt?: dayjs.Dayjs | null;
}

export const defaultValue: Readonly<ITinhNguyenVien> = {};
