import dayjs from 'dayjs';

export interface ILoaiNhuYeuPham {
  id?: number;
  tenLoai?: string;
  createdAt?: dayjs.Dayjs | null;
  updatedAt?: dayjs.Dayjs | null;
}

export const defaultValue: Readonly<ILoaiNhuYeuPham> = {};
