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

import { getEntities } from './nhu-yeu-pham.reducer';

export const NhuYeuPham = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [paginationState, setPaginationState] = useState(
    overridePaginationStateWithQueryParams(getPaginationState(pageLocation, ITEMS_PER_PAGE, 'id'), pageLocation.search),
  );

  const nhuYeuPhamList = useAppSelector(state => state.nhuYeuPham.entities);
  const loading = useAppSelector(state => state.nhuYeuPham.loading);
  const totalItems = useAppSelector(state => state.nhuYeuPham.totalItems);

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
      <h2 id="nhu-yeu-pham-heading" data-cy="NhuYeuPhamHeading">
        <Translate contentKey="adminApp.nhuYeuPham.home.title">Nhu Yeu Phams</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="adminApp.nhuYeuPham.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/nhu-yeu-pham/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="adminApp.nhuYeuPham.home.createLabel">Create new Nhu Yeu Pham</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {nhuYeuPhamList && nhuYeuPhamList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="adminApp.nhuYeuPham.id">ID</Translate> <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('tenNhuYeuPham')}>
                  <Translate contentKey="adminApp.nhuYeuPham.tenNhuYeuPham">Ten Nhu Yeu Pham</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('tenNhuYeuPham')} />
                </th>
                <th className="hand" onClick={sort('donViTinh')}>
                  <Translate contentKey="adminApp.nhuYeuPham.donViTinh">Don Vi Tinh</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('donViTinh')} />
                </th>
                <th className="hand" onClick={sort('loaiNhuYeuPhamId')}>
                  <Translate contentKey="adminApp.nhuYeuPham.loaiNhuYeuPhamId">Loai Nhu Yeu Pham Id</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('loaiNhuYeuPhamId')} />
                </th>
                <th className="hand" onClick={sort('mucCanhBao')}>
                  <Translate contentKey="adminApp.nhuYeuPham.mucCanhBao">Muc Canh Bao</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('mucCanhBao')} />
                </th>
                <th className="hand" onClick={sort('createdAt')}>
                  <Translate contentKey="adminApp.nhuYeuPham.createdAt">Created At</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('createdAt')} />
                </th>
                <th className="hand" onClick={sort('updatedAt')}>
                  <Translate contentKey="adminApp.nhuYeuPham.updatedAt">Updated At</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('updatedAt')} />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {nhuYeuPhamList.map((nhuYeuPham, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/nhu-yeu-pham/${nhuYeuPham.id}`} color="link" size="sm">
                      {nhuYeuPham.id}
                    </Button>
                  </td>
                  <td>{nhuYeuPham.tenNhuYeuPham}</td>
                  <td>{nhuYeuPham.donViTinh}</td>
                  <td>{nhuYeuPham.loaiNhuYeuPhamId}</td>
                  <td>{nhuYeuPham.mucCanhBao}</td>
                  <td>{nhuYeuPham.createdAt ? <TextFormat type="date" value={nhuYeuPham.createdAt} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{nhuYeuPham.updatedAt ? <TextFormat type="date" value={nhuYeuPham.updatedAt} format={APP_DATE_FORMAT} /> : null}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/nhu-yeu-pham/${nhuYeuPham.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/nhu-yeu-pham/${nhuYeuPham.id}/edit?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
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
                          (window.location.href = `/nhu-yeu-pham/${nhuYeuPham.id}/delete?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`)
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
              <Translate contentKey="adminApp.nhuYeuPham.home.notFound">No Nhu Yeu Phams found</Translate>
            </div>
          )
        )}
      </div>
      {totalItems ? (
        <div className={nhuYeuPhamList && nhuYeuPhamList.length > 0 ? '' : 'd-none'}>
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

export default NhuYeuPham;
