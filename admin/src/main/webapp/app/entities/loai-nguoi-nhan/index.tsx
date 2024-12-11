import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import LoaiNguoiNhan from './loai-nguoi-nhan';
import LoaiNguoiNhanDetail from './loai-nguoi-nhan-detail';
import LoaiNguoiNhanUpdate from './loai-nguoi-nhan-update';
import LoaiNguoiNhanDeleteDialog from './loai-nguoi-nhan-delete-dialog';

const LoaiNguoiNhanRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<LoaiNguoiNhan />} />
    <Route path="new" element={<LoaiNguoiNhanUpdate />} />
    <Route path=":id">
      <Route index element={<LoaiNguoiNhanDetail />} />
      <Route path="edit" element={<LoaiNguoiNhanUpdate />} />
      <Route path="delete" element={<LoaiNguoiNhanDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default LoaiNguoiNhanRoutes;
