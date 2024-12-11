import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import TinhNguyenVien from './tinh-nguyen-vien';
import TinhNguyenVienDetail from './tinh-nguyen-vien-detail';
import TinhNguyenVienUpdate from './tinh-nguyen-vien-update';
import TinhNguyenVienDeleteDialog from './tinh-nguyen-vien-delete-dialog';

const TinhNguyenVienRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<TinhNguyenVien />} />
    <Route path="new" element={<TinhNguyenVienUpdate />} />
    <Route path=":id">
      <Route index element={<TinhNguyenVienDetail />} />
      <Route path="edit" element={<TinhNguyenVienUpdate />} />
      <Route path="delete" element={<TinhNguyenVienDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default TinhNguyenVienRoutes;
