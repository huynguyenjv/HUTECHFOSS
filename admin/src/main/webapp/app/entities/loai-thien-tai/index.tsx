import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import LoaiThienTai from './loai-thien-tai';
import LoaiThienTaiDetail from './loai-thien-tai-detail';
import LoaiThienTaiUpdate from './loai-thien-tai-update';
import LoaiThienTaiDeleteDialog from './loai-thien-tai-delete-dialog';

const LoaiThienTaiRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<LoaiThienTai />} />
    <Route path="new" element={<LoaiThienTaiUpdate />} />
    <Route path=":id">
      <Route index element={<LoaiThienTaiDetail />} />
      <Route path="edit" element={<LoaiThienTaiUpdate />} />
      <Route path="delete" element={<LoaiThienTaiDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default LoaiThienTaiRoutes;
