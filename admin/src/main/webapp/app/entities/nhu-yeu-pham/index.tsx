import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import NhuYeuPham from './nhu-yeu-pham';
import NhuYeuPhamDetail from './nhu-yeu-pham-detail';
import NhuYeuPhamUpdate from './nhu-yeu-pham-update';
import NhuYeuPhamDeleteDialog from './nhu-yeu-pham-delete-dialog';

const NhuYeuPhamRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<NhuYeuPham />} />
    <Route path="new" element={<NhuYeuPhamUpdate />} />
    <Route path=":id">
      <Route index element={<NhuYeuPhamDetail />} />
      <Route path="edit" element={<NhuYeuPhamUpdate />} />
      <Route path="delete" element={<NhuYeuPhamDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default NhuYeuPhamRoutes;
