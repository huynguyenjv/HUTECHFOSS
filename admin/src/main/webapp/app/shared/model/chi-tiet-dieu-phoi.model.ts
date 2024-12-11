import dayjs from 'dayjs';

export interface IChiTietDieuPhoi {
  id?: number;
  phieuDieuPhoiId?: number | null;
  nhuYeuPhamId?: number | null;
  soLuong?: number | null;
  createdAt?: dayjs.Dayjs | null;
  updatedAt?: dayjs.Dayjs | null;
}

export const defaultValue: Readonly<IChiTietDieuPhoi> = {};
