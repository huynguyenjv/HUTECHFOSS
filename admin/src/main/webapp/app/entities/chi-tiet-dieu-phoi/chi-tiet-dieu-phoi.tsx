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

import { getEntities } from './chi-tiet-dieu-phoi.reducer';

export const ChiTietDieuPhoi = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [paginationState, setPaginationState] = useState(
    overridePaginationStateWithQueryParams(getPaginationState(pageLocation, ITEMS_PER_PAGE, 'id'), pageLocation.search),
  );

  const chiTietDieuPhoiList = useAppSelector(state => state.chiTietDieuPhoi.entities);
  const loading = useAppSelector(state => state.chiTietDieuPhoi.loading);
  const totalItems = useAppSelector(state => state.chiTietDieuPhoi.totalItems);

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
      <h2 id="chi-tiet-dieu-phoi-heading" data-cy="ChiTietDieuPhoiHeading">
        <Translate contentKey="adminApp.chiTietDieuPhoi.home.title">Chi Tiet Dieu Phois</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="adminApp.chiTietDieuPhoi.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link
            to="/chi-tiet-dieu-phoi/new"
            className="btn btn-primary jh-create-entity"
            id="jh-create-entity"
            data-cy="entityCreateButton"
          >
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="adminApp.chiTietDieuPhoi.home.createLabel">Create new Chi Tiet Dieu Phoi</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {chiTietDieuPhoiList && chiTietDieuPhoiList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="adminApp.chiTietDieuPhoi.id">ID</Translate> <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('phieuDieuPhoiId')}>
                  <Translate contentKey="adminApp.chiTietDieuPhoi.phieuDieuPhoiId">Phieu Dieu Phoi Id</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('phieuDieuPhoiId')} />
                </th>
                <th className="hand" onClick={sort('nhuYeuPhamId')}>
                  <Translate contentKey="adminApp.chiTietDieuPhoi.nhuYeuPhamId">Nhu Yeu Pham Id</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('nhuYeuPhamId')} />
                </th>
                <th className="hand" onClick={sort('soLuong')}>
                  <Translate contentKey="adminApp.chiTietDieuPhoi.soLuong">So Luong</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('soLuong')} />
                </th>
                <th className="hand" onClick={sort('createdAt')}>
                  <Translate contentKey="adminApp.chiTietDieuPhoi.createdAt">Created At</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('createdAt')} />
                </th>
                <th className="hand" onClick={sort('updatedAt')}>
                  <Translate contentKey="adminApp.chiTietDieuPhoi.updatedAt">Updated At</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('updatedAt')} />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {chiTietDieuPhoiList.map((chiTietDieuPhoi, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/chi-tiet-dieu-phoi/${chiTietDieuPhoi.id}`} color="link" size="sm">
                      {chiTietDieuPhoi.id}
                    </Button>
                  </td>
                  <td>{chiTietDieuPhoi.phieuDieuPhoiId}</td>
                  <td>{chiTietDieuPhoi.nhuYeuPhamId}</td>
                  <td>{chiTietDieuPhoi.soLuong}</td>
                  <td>
                    {chiTietDieuPhoi.createdAt ? (
                      <TextFormat type="date" value={chiTietDieuPhoi.createdAt} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>
                    {chiTietDieuPhoi.updatedAt ? (
                      <TextFormat type="date" value={chiTietDieuPhoi.updatedAt} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button
                        tag={Link}
                        to={`/chi-tiet-dieu-phoi/${chiTietDieuPhoi.id}`}
                        color="info"
                        size="sm"
                        data-cy="entityDetailsButton"
                      >
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/chi-tiet-dieu-phoi/${chiTietDieuPhoi.id}/edit?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
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
                          (window.location.href = `/chi-tiet-dieu-phoi/${chiTietDieuPhoi.id}/delete?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`)
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
              <Translate contentKey="adminApp.chiTietDieuPhoi.home.notFound">No Chi Tiet Dieu Phois found</Translate>
            </div>
          )
        )}
      </div>
      {totalItems ? (
        <div className={chiTietDieuPhoiList && chiTietDieuPhoiList.length > 0 ? '' : 'd-none'}>
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

export default ChiTietDieuPhoi;
