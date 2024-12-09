import React, { useEffect, useState } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { JhiItemCount, JhiPagination, TextFormat, Translate, getPaginationState } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSort, faSortDown, faSortUp } from '@fortawesome/free-solid-svg-icons';
import { APP_DATE_FORMAT } from 'app/config/constants';
import { ASC, DESC, ITEMS_PER_PAGE, SORT } from 'app/shared/util/pagination.constants';
import { overridePaginationStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities } from './nha-cung-cap.reducer';

export const NhaCungCap = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [paginationState, setPaginationState] = useState(
    overridePaginationStateWithQueryParams(getPaginationState(pageLocation, ITEMS_PER_PAGE, 'id'), pageLocation.search),
  );

  const nhaCungCapList = useAppSelector(state => state.nhaCungCap.entities);
  const loading = useAppSelector(state => state.nhaCungCap.loading);
  const totalItems = useAppSelector(state => state.nhaCungCap.totalItems);

  const getAllEntities = () => {
    dispatch(
      getEntities({
        page: paginationState.activePage - 1,
        size: paginationState.itemsPerPage,
        sort: `${paginationState.sort},${paginationState.order}`,
      }),
    );
  };

  const sortEntities = () => {
    getAllEntities();
    const endURL = `?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`;
    if (pageLocation.search !== endURL) {
      navigate(`${pageLocation.pathname}${endURL}`);
    }
  };

  useEffect(() => {
    sortEntities();
  }, [paginationState.activePage, paginationState.order, paginationState.sort]);

  useEffect(() => {
    const params = new URLSearchParams(pageLocation.search);
    const page = params.get('page');
    const sort = params.get(SORT);
    if (page && sort) {
      const sortSplit = sort.split(',');
      setPaginationState({
        ...paginationState,
        activePage: +page,
        sort: sortSplit[0],
        order: sortSplit[1],
      });
    }
  }, [pageLocation.search]);

  const sort = p => () => {
    setPaginationState({
      ...paginationState,
      order: paginationState.order === ASC ? DESC : ASC,
      sort: p,
    });
  };

  const handlePagination = currentPage =>
    setPaginationState({
      ...paginationState,
      activePage: currentPage,
    });

  const handleSyncList = () => {
    sortEntities();
  };

  const getSortIconByFieldName = (fieldName: string) => {
    const sortFieldName = paginationState.sort;
    const order = paginationState.order;
    if (sortFieldName !== fieldName) {
      return faSort;
    }
    return order === ASC ? faSortUp : faSortDown;
  };

  return (
    <div>
      <h2 id="nha-cung-cap-heading" data-cy="NhaCungCapHeading">
        <Translate contentKey="adminApp.nhaCungCap.home.title">Nha Cung Caps</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="adminApp.nhaCungCap.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/nha-cung-cap/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="adminApp.nhaCungCap.home.createLabel">Create new Nha Cung Cap</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {nhaCungCapList && nhaCungCapList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="adminApp.nhaCungCap.id">ID</Translate> <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('tenNhaCungCap')}>
                  <Translate contentKey="adminApp.nhaCungCap.tenNhaCungCap">Ten Nha Cung Cap</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('tenNhaCungCap')} />
                </th>
                <th className="hand" onClick={sort('soDienThoai')}>
                  <Translate contentKey="adminApp.nhaCungCap.soDienThoai">So Dien Thoai</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('soDienThoai')} />
                </th>
                <th className="hand" onClick={sort('email')}>
                  <Translate contentKey="adminApp.nhaCungCap.email">Email</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('email')} />
                </th>
                <th className="hand" onClick={sort('diaChi')}>
                  <Translate contentKey="adminApp.nhaCungCap.diaChi">Dia Chi</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('diaChi')} />
                </th>
                <th className="hand" onClick={sort('createdAt')}>
                  <Translate contentKey="adminApp.nhaCungCap.createdAt">Created At</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('createdAt')} />
                </th>
                <th className="hand" onClick={sort('updatedAt')}>
                  <Translate contentKey="adminApp.nhaCungCap.updatedAt">Updated At</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('updatedAt')} />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {nhaCungCapList.map((nhaCungCap, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/nha-cung-cap/${nhaCungCap.id}`} color="link" size="sm">
                      {nhaCungCap.id}
                    </Button>
                  </td>
                  <td>{nhaCungCap.tenNhaCungCap}</td>
                  <td>{nhaCungCap.soDienThoai}</td>
                  <td>{nhaCungCap.email}</td>
                  <td>{nhaCungCap.diaChi}</td>
                  <td>{nhaCungCap.createdAt ? <TextFormat type="date" value={nhaCungCap.createdAt} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{nhaCungCap.updatedAt ? <TextFormat type="date" value={nhaCungCap.updatedAt} format={APP_DATE_FORMAT} /> : null}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/nha-cung-cap/${nhaCungCap.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/nha-cung-cap/${nhaCungCap.id}/edit?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
                        color="primary"
                        size="sm"
                        data-cy="entityEditButton"
                      >
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        onClick={() =>
                          (window.location.href = `/nha-cung-cap/${nhaCungCap.id}/delete?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`)
                        }
                        color="danger"
                        size="sm"
                        data-cy="entityDeleteButton"
                      >
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="adminApp.nhaCungCap.home.notFound">No Nha Cung Caps found</Translate>
            </div>
          )
        )}
      </div>
      {totalItems ? (
        <div className={nhaCungCapList && nhaCungCapList.length > 0 ? '' : 'd-none'}>
          <div className="justify-content-center d-flex">
            <JhiItemCount page={paginationState.activePage} total={totalItems} itemsPerPage={paginationState.itemsPerPage} i18nEnabled />
          </div>
          <div className="justify-content-center d-flex">
            <JhiPagination
              activePage={paginationState.activePage}
              onSelect={handlePagination}
              maxButtons={5}
              itemsPerPage={paginationState.itemsPerPage}
              totalItems={totalItems}
            />
          </div>
        </div>
      ) : (
        ''
      )}
    </div>
  );
};

export default NhaCungCap;
