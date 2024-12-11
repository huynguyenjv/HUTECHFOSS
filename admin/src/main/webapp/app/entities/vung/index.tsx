import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import Vung from './vung';
import VungDetail from './vung-detail';
import VungUpdate from './vung-update';
import VungDeleteDialog from './vung-delete-dialog';

const VungRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<Vung />} />
    <Route path="new" element={<VungUpdate />} />
    <Route path=":id">
      <Route index element={<VungDetail />} />
      <Route path="edit" element={<VungUpdate />} />
      <Route path="delete" element={<VungDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default VungRoutes;
