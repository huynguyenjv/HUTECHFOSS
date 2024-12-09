import dayjs from 'dayjs';

export interface INhuYeuPham {
  id?: number;
  tenNhuYeuPham?: string;
  donViTinh?: string | null;
  loaiNhuYeuPhamId?: number | null;
  mucCanhBao?: number | null;
  createdAt?: dayjs.Dayjs | null;
  updatedAt?: dayjs.Dayjs | null;
}

export const defaultValue: Readonly<INhuYeuPham> = {};
