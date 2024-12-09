import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import TrangThai from './trang-thai';
import TrangThaiDetail from './trang-thai-detail';
import TrangThaiUpdate from './trang-thai-update';
import TrangThaiDeleteDialog from './trang-thai-delete-dialog';

const TrangThaiRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<TrangThai />} />
    <Route path="new" element={<TrangThaiUpdate />} />
    <Route path=":id">
      <Route index element={<TrangThaiDetail />} />
      <Route path="edit" element={<TrangThaiUpdate />} />
      <Route path="delete" element={<TrangThaiDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default TrangThaiRoutes;
