import dayjs from 'dayjs';

export interface ILoaiThienTai {
  id?: number;
  tenLoaiThienTai?: string;
  moTa?: string | null;
  createdAt?: dayjs.Dayjs | null;
  updatedAt?: dayjs.Dayjs | null;
}

export const defaultValue: Readonly<ILoaiThienTai> = {};
