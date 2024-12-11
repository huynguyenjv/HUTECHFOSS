import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import NhaCungCap from './nha-cung-cap';
import NhaCungCapDetail from './nha-cung-cap-detail';
import NhaCungCapUpdate from './nha-cung-cap-update';
import NhaCungCapDeleteDialog from './nha-cung-cap-delete-dialog';

const NhaCungCapRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<NhaCungCap />} />
    <Route path="new" element={<NhaCungCapUpdate />} />
    <Route path=":id">
      <Route index element={<NhaCungCapDetail />} />
      <Route path="edit" element={<NhaCungCapUpdate />} />
      <Route path="delete" element={<NhaCungCapDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default NhaCungCapRoutes;
