import dayjs from 'dayjs';

export interface ITrangThai {
  id?: number;
  tenTrangThai?: string;
  moTa?: string | null;
  createdAt?: dayjs.Dayjs | null;
  updatedAt?: dayjs.Dayjs | null;
}

export const defaultValue: Readonly<ITrangThai> = {};
