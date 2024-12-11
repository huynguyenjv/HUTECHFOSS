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

import { getEntities } from './tinh-nguyen-vien.reducer';

export const TinhNguyenVien = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [paginationState, setPaginationState] = useState(
    overridePaginationStateWithQueryParams(getPaginationState(pageLocation, ITEMS_PER_PAGE, 'id'), pageLocation.search),
  );

  const tinhNguyenVienList = useAppSelector(state => state.tinhNguyenVien.entities);
  const loading = useAppSelector(state => state.tinhNguyenVien.loading);
  const totalItems = useAppSelector(state => state.tinhNguyenVien.totalItems);

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
      <h2 id="tinh-nguyen-vien-heading" data-cy="TinhNguyenVienHeading">
        <Translate contentKey="adminApp.tinhNguyenVien.home.title">Tinh Nguyen Viens</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="adminApp.tinhNguyenVien.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/tinh-nguyen-vien/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="adminApp.tinhNguyenVien.home.createLabel">Create new Tinh Nguyen Vien</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {tinhNguyenVienList && tinhNguyenVienList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="adminApp.tinhNguyenVien.id">ID</Translate> <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('tenTinhNguyenVien')}>
                  <Translate contentKey="adminApp.tinhNguyenVien.tenTinhNguyenVien">Ten Tinh Nguyen Vien</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('tenTinhNguyenVien')} />
                </th>
                <th className="hand" onClick={sort('soDienThoai')}>
                  <Translate contentKey="adminApp.tinhNguyenVien.soDienThoai">So Dien Thoai</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('soDienThoai')} />
                </th>
                <th className="hand" onClick={sort('diaChi')}>
                  <Translate contentKey="adminApp.tinhNguyenVien.diaChi">Dia Chi</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('diaChi')} />
                </th>
                <th className="hand" onClick={sort('trangThaiId')}>
                  <Translate contentKey="adminApp.tinhNguyenVien.trangThaiId">Trang Thai Id</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('trangThaiId')} />
                </th>
                <th className="hand" onClick={sort('createdAt')}>
                  <Translate contentKey="adminApp.tinhNguyenVien.createdAt">Created At</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('createdAt')} />
                </th>
                <th className="hand" onClick={sort('updatedAt')}>
                  <Translate contentKey="adminApp.tinhNguyenVien.updatedAt">Updated At</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('updatedAt')} />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {tinhNguyenVienList.map((tinhNguyenVien, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/tinh-nguyen-vien/${tinhNguyenVien.id}`} color="link" size="sm">
                      {tinhNguyenVien.id}
                    </Button>
                  </td>
                  <td>{tinhNguyenVien.tenTinhNguyenVien}</td>
                  <td>{tinhNguyenVien.soDienThoai}</td>
                  <td>{tinhNguyenVien.diaChi}</td>
                  <td>{tinhNguyenVien.trangThaiId}</td>
                  <td>
                    {tinhNguyenVien.createdAt ? <TextFormat type="date" value={tinhNguyenVien.createdAt} format={APP_DATE_FORMAT} /> : null}
                  </td>
                  <td>
                    {tinhNguyenVien.updatedAt ? <TextFormat type="date" value={tinhNguyenVien.updatedAt} format={APP_DATE_FORMAT} /> : null}
                  </td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/tinh-nguyen-vien/${tinhNguyenVien.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/tinh-nguyen-vien/${tinhNguyenVien.id}/edit?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
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
                          (window.location.href = `/tinh-nguyen-vien/${tinhNguyenVien.id}/delete?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`)
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
              <Translate contentKey="adminApp.tinhNguyenVien.home.notFound">No Tinh Nguyen Viens found</Translate>
            </div>
          )
        )}
      </div>
      {totalItems ? (
        <div className={tinhNguyenVienList && tinhNguyenVienList.length > 0 ? '' : 'd-none'}>
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

export default TinhNguyenVien;
