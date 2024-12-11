import dayjs from 'dayjs';

export interface INhaCungCap {
  id?: number;
  tenNhaCungCap?: string;
  soDienThoai?: string | null;
  email?: string | null;
  diaChi?: string | null;
  createdAt?: dayjs.Dayjs | null;
  updatedAt?: dayjs.Dayjs | null;
}

export const defaultValue: Readonly<INhaCungCap> = {};
