import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import LoaiNhuYeuPham from './loai-nhu-yeu-pham';
import LoaiNhuYeuPhamDetail from './loai-nhu-yeu-pham-detail';
import LoaiNhuYeuPhamUpdate from './loai-nhu-yeu-pham-update';
import LoaiNhuYeuPhamDeleteDialog from './loai-nhu-yeu-pham-delete-dialog';

const LoaiNhuYeuPhamRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<LoaiNhuYeuPham />} />
    <Route path="new" element={<LoaiNhuYeuPhamUpdate />} />
    <Route path=":id">
      <Route index element={<LoaiNhuYeuPhamDetail />} />
      <Route path="edit" element={<LoaiNhuYeuPhamUpdate />} />
      <Route path="delete" element={<LoaiNhuYeuPhamDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default LoaiNhuYeuPhamRoutes;
