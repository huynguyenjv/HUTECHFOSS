import React from 'react';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import ChiTietDieuPhoi from './chi-tiet-dieu-phoi';
import LoaiNguoiNhan from './loai-nguoi-nhan';
import LoaiNhuYeuPham from './loai-nhu-yeu-pham';
import LoaiThienTai from './loai-thien-tai';
import NguoiNhan from './nguoi-nhan';
import NhaCungCap from './nha-cung-cap';
import NhuYeuPham from './nhu-yeu-pham';
import PhieuDieuPhoi from './phieu-dieu-phoi';
import TinhNguyenVien from './tinh-nguyen-vien';
import TrangThai from './trang-thai';
import Vung from './vung';
/* jhipster-needle-add-route-import - JHipster will add routes here */

export default () => {
  return (
    <div>
      <ErrorBoundaryRoutes>
        {/* prettier-ignore */}
        <Route path="chi-tiet-dieu-phoi/*" element={<ChiTietDieuPhoi />} />
        <Route path="loai-nguoi-nhan/*" element={<LoaiNguoiNhan />} />
        <Route path="loai-nhu-yeu-pham/*" element={<LoaiNhuYeuPham />} />
        <Route path="loai-thien-tai/*" element={<LoaiThienTai />} />
        <Route path="nguoi-nhan/*" element={<NguoiNhan />} />
        <Route path="nha-cung-cap/*" element={<NhaCungCap />} />
        <Route path="nhu-yeu-pham/*" element={<NhuYeuPham />} />
        <Route path="phieu-dieu-phoi/*" element={<PhieuDieuPhoi />} />
        <Route path="tinh-nguyen-vien/*" element={<TinhNguyenVien />} />
        <Route path="trang-thai/*" element={<TrangThai />} />
        <Route path="vung/*" element={<Vung />} />
        {/* jhipster-needle-add-route-path - JHipster will add routes here */}
      </ErrorBoundaryRoutes>
    </div>
  );
};
