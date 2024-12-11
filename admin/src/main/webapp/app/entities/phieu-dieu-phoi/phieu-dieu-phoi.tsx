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

import { getEntities } from './phieu-dieu-phoi.reducer';

export const PhieuDieuPhoi = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [paginationState, setPaginationState] = useState(
    overridePaginationStateWithQueryParams(getPaginationState(pageLocation, ITEMS_PER_PAGE, 'id'), pageLocation.search),
  );

  const phieuDieuPhoiList = useAppSelector(state => state.phieuDieuPhoi.entities);
  const loading = useAppSelector(state => state.phieuDieuPhoi.loading);
  const totalItems = useAppSelector(state => state.phieuDieuPhoi.totalItems);

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
      <h2 id="phieu-dieu-phoi-heading" data-cy="PhieuDieuPhoiHeading">
        <Translate contentKey="adminApp.phieuDieuPhoi.home.title">Phieu Dieu Phois</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="adminApp.phieuDieuPhoi.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/phieu-dieu-phoi/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="adminApp.phieuDieuPhoi.home.createLabel">Create new Phieu Dieu Phoi</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {phieuDieuPhoiList && phieuDieuPhoiList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="adminApp.phieuDieuPhoi.id">ID</Translate> <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('nhaCungCapId')}>
                  <Translate contentKey="adminApp.phieuDieuPhoi.nhaCungCapId">Nha Cung Cap Id</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('nhaCungCapId')} />
                </th>
                <th className="hand" onClick={sort('nguoiNhanId')}>
                  <Translate contentKey="adminApp.phieuDieuPhoi.nguoiNhanId">Nguoi Nhan Id</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('nguoiNhanId')} />
                </th>
                <th className="hand" onClick={sort('tinhNguyenVienId')}>
                  <Translate contentKey="adminApp.phieuDieuPhoi.tinhNguyenVienId">Tinh Nguyen Vien Id</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('tinhNguyenVienId')} />
                </th>
                <th className="hand" onClick={sort('trangThaiId')}>
                  <Translate contentKey="adminApp.phieuDieuPhoi.trangThaiId">Trang Thai Id</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('trangThaiId')} />
                </th>
                <th className="hand" onClick={sort('thoiGianDen')}>
                  <Translate contentKey="adminApp.phieuDieuPhoi.thoiGianDen">Thoi Gian Den</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('thoiGianDen')} />
                </th>
                <th className="hand" onClick={sort('thoiGianCho')}>
                  <Translate contentKey="adminApp.phieuDieuPhoi.thoiGianCho">Thoi Gian Cho</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('thoiGianCho')} />
                </th>
                <th className="hand" onClick={sort('createdAt')}>
                  <Translate contentKey="adminApp.phieuDieuPhoi.createdAt">Created At</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('createdAt')} />
                </th>
                <th className="hand" onClick={sort('updatedAt')}>
                  <Translate contentKey="adminApp.phieuDieuPhoi.updatedAt">Updated At</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('updatedAt')} />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {phieuDieuPhoiList.map((phieuDieuPhoi, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/phieu-dieu-phoi/${phieuDieuPhoi.id}`} color="link" size="sm">
                      {phieuDieuPhoi.id}
                    </Button>
                  </td>
                  <td>{phieuDieuPhoi.nhaCungCapId}</td>
                  <td>{phieuDieuPhoi.nguoiNhanId}</td>
                  <td>{phieuDieuPhoi.tinhNguyenVienId}</td>
                  <td>{phieuDieuPhoi.trangThaiId}</td>
                  <td>
                    {phieuDieuPhoi.thoiGianDen ? (
                      <TextFormat type="date" value={phieuDieuPhoi.thoiGianDen} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>
                    {phieuDieuPhoi.thoiGianCho ? (
                      <TextFormat type="date" value={phieuDieuPhoi.thoiGianCho} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>
                    {phieuDieuPhoi.createdAt ? <TextFormat type="date" value={phieuDieuPhoi.createdAt} format={APP_DATE_FORMAT} /> : null}
                  </td>
                  <td>
                    {phieuDieuPhoi.updatedAt ? <TextFormat type="date" value={phieuDieuPhoi.updatedAt} format={APP_DATE_FORMAT} /> : null}
                  </td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/phieu-dieu-phoi/${phieuDieuPhoi.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/phieu-dieu-phoi/${phieuDieuPhoi.id}/edit?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
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
                          (window.location.href = `/phieu-dieu-phoi/${phieuDieuPhoi.id}/delete?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`)
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
              <Translate contentKey="adminApp.phieuDieuPhoi.home.notFound">No Phieu Dieu Phois found</Translate>
            </div>
          )
        )}
      </div>
      {totalItems ? (
        <div className={phieuDieuPhoiList && phieuDieuPhoiList.length > 0 ? '' : 'd-none'}>
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

export default PhieuDieuPhoi;
