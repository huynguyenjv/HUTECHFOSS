import dayjs from 'dayjs';

export interface IPhieuDieuPhoi {
  id?: number;
  nhaCungCapId?: number | null;
  nguoiNhanId?: number | null;
  tinhNguyenVienId?: number | null;
  trangThaiId?: number | null;
  thoiGianDen?: dayjs.Dayjs | null;
  thoiGianCho?: dayjs.Dayjs | null;
  createdAt?: dayjs.Dayjs | null;
  updatedAt?: dayjs.Dayjs | null;
}

export const defaultValue: Readonly<IPhieuDieuPhoi> = {};
