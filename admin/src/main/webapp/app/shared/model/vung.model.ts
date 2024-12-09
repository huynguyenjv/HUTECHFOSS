import dayjs from 'dayjs';

export interface IVung {
  id?: number;
  tenVung?: string;
  lat?: number | null;
  lng?: number | null;
  loaiThienTaiId?: number | null;
  soDan?: number | null;
  createdAt?: dayjs.Dayjs | null;
  updatedAt?: dayjs.Dayjs | null;
}

export const defaultValue: Readonly<IVung> = {};
