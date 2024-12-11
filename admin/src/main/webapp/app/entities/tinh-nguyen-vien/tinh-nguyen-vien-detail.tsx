import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { TextFormat, Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './tinh-nguyen-vien.reducer';

export const TinhNguyenVienDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const tinhNguyenVienEntity = useAppSelector(state => state.tinhNguyenVien.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="tinhNguyenVienDetailsHeading">
          <Translate contentKey="adminApp.tinhNguyenVien.detail.title">TinhNguyenVien</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{tinhNguyenVienEntity.id}</dd>
          <dt>
            <span id="tenTinhNguyenVien">
              <Translate contentKey="adminApp.tinhNguyenVien.tenTinhNguyenVien">Ten Tinh Nguyen Vien</Translate>
            </span>
          </dt>
          <dd>{tinhNguyenVienEntity.tenTinhNguyenVien}</dd>
          <dt>
            <span id="soDienThoai">
              <Translate contentKey="adminApp.tinhNguyenVien.soDienThoai">So Dien Thoai</Translate>
            </span>
          </dt>
          <dd>{tinhNguyenVienEntity.soDienThoai}</dd>
          <dt>
            <span id="diaChi">
              <Translate contentKey="adminApp.tinhNguyenVien.diaChi">Dia Chi</Translate>
            </span>
          </dt>
          <dd>{tinhNguyenVienEntity.diaChi}</dd>
          <dt>
            <span id="trangThaiId">
              <Translate contentKey="adminApp.tinhNguyenVien.trangThaiId">Trang Thai Id</Translate>
            </span>
          </dt>
          <dd>{tinhNguyenVienEntity.trangThaiId}</dd>
          <dt>
            <span id="createdAt">
              <Translate contentKey="adminApp.tinhNguyenVien.createdAt">Created At</Translate>
            </span>
          </dt>
          <dd>
            {tinhNguyenVienEntity.createdAt ? (
              <TextFormat value={tinhNguyenVienEntity.createdAt} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="updatedAt">
              <Translate contentKey="adminApp.tinhNguyenVien.updatedAt">Updated At</Translate>
            </span>
          </dt>
          <dd>
            {tinhNguyenVienEntity.updatedAt ? (
              <TextFormat value={tinhNguyenVienEntity.updatedAt} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
        </dl>
        <Button tag={Link} to="/tinh-nguyen-vien" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/tinh-nguyen-vien/${tinhNguyenVienEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default TinhNguyenVienDetail;
