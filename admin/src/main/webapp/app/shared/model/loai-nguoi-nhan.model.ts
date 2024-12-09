import dayjs from 'dayjs';

export interface ILoaiNguoiNhan {
  id?: number;
  tenLoai?: string;
  moTa?: string | null;
  createdAt?: dayjs.Dayjs | null;
  updatedAt?: dayjs.Dayjs | null;
}

export const defaultValue: Readonly<ILoaiNguoiNhan> = {};
