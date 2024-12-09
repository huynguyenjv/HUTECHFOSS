import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import ChiTietDieuPhoi from './chi-tiet-dieu-phoi';
import ChiTietDieuPhoiDetail from './chi-tiet-dieu-phoi-detail';
import ChiTietDieuPhoiUpdate from './chi-tiet-dieu-phoi-update';
import ChiTietDieuPhoiDeleteDialog from './chi-tiet-dieu-phoi-delete-dialog';

const ChiTietDieuPhoiRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<ChiTietDieuPhoi />} />
    <Route path="new" element={<ChiTietDieuPhoiUpdate />} />
    <Route path=":id">
      <Route index element={<ChiTietDieuPhoiDetail />} />
      <Route path="edit" element={<ChiTietDieuPhoiUpdate />} />
      <Route path="delete" element={<ChiTietDieuPhoiDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default ChiTietDieuPhoiRoutes;
