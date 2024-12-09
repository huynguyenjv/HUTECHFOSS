import dayjs from 'dayjs';

export interface INguoiNhan {
  id?: number;
  tenNguoiNhan?: string;
  soDienThoai?: string | null;
  email?: string | null;
  diaChi?: string | null;
  vungId?: number | null;
  soNguoiNhan?: number | null;
  createdAt?: dayjs.Dayjs | null;
  updatedAt?: dayjs.Dayjs | null;
}

export const defaultValue: Readonly<INguoiNhan> = {};
