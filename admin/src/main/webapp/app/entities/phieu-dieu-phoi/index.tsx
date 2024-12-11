import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import PhieuDieuPhoi from './phieu-dieu-phoi';
import PhieuDieuPhoiDetail from './phieu-dieu-phoi-detail';
import PhieuDieuPhoiUpdate from './phieu-dieu-phoi-update';
import PhieuDieuPhoiDeleteDialog from './phieu-dieu-phoi-delete-dialog';

const PhieuDieuPhoiRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<PhieuDieuPhoi />} />
    <Route path="new" element={<PhieuDieuPhoiUpdate />} />
    <Route path=":id">
      <Route index element={<PhieuDieuPhoiDetail />} />
      <Route path="edit" element={<PhieuDieuPhoiUpdate />} />
      <Route path="delete" element={<PhieuDieuPhoiDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default PhieuDieuPhoiRoutes;
