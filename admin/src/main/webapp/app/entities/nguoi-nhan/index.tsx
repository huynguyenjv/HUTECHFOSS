import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import NguoiNhan from './nguoi-nhan';
import NguoiNhanDetail from './nguoi-nhan-detail';
import NguoiNhanUpdate from './nguoi-nhan-update';
import NguoiNhanDeleteDialog from './nguoi-nhan-delete-dialog';

const NguoiNhanRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<NguoiNhan />} />
    <Route path="new" element={<NguoiNhanUpdate />} />
    <Route path=":id">
      <Route index element={<NguoiNhanDetail />} />
      <Route path="edit" element={<NguoiNhanUpdate />} />
      <Route path="delete" element={<NguoiNhanDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default NguoiNhanRoutes;
