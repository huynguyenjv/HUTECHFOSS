import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { TextFormat, Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './phieu-dieu-phoi.reducer';

export const PhieuDieuPhoiDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const phieuDieuPhoiEntity = useAppSelector(state => state.phieuDieuPhoi.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="phieuDieuPhoiDetailsHeading">
          <Translate contentKey="adminApp.phieuDieuPhoi.detail.title">PhieuDieuPhoi</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{phieuDieuPhoiEntity.id}</dd>
          <dt>
            <span id="nhaCungCapId">
              <Translate contentKey="adminApp.phieuDieuPhoi.nhaCungCapId">Nha Cung Cap Id</Translate>
            </span>
          </dt>
          <dd>{phieuDieuPhoiEntity.nhaCungCapId}</dd>
          <dt>
            <span id="nguoiNhanId">
              <Translate contentKey="adminApp.phieuDieuPhoi.nguoiNhanId">Nguoi Nhan Id</Translate>
            </span>
          </dt>
          <dd>{phieuDieuPhoiEntity.nguoiNhanId}</dd>
          <dt>
            <span id="tinhNguyenVienId">
              <Translate contentKey="adminApp.phieuDieuPhoi.tinhNguyenVienId">Tinh Nguyen Vien Id</Translate>
            </span>
          </dt>
          <dd>{phieuDieuPhoiEntity.tinhNguyenVienId}</dd>
          <dt>
            <span id="trangThaiId">
              <Translate contentKey="adminApp.phieuDieuPhoi.trangThaiId">Trang Thai Id</Translate>
            </span>
          </dt>
          <dd>{phieuDieuPhoiEntity.trangThaiId}</dd>
          <dt>
            <span id="thoiGianDen">
              <Translate contentKey="adminApp.phieuDieuPhoi.thoiGianDen">Thoi Gian Den</Translate>
            </span>
          </dt>
          <dd>
            {phieuDieuPhoiEntity.thoiGianDen ? (
              <TextFormat value={phieuDieuPhoiEntity.thoiGianDen} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="thoiGianCho">
              <Translate contentKey="adminApp.phieuDieuPhoi.thoiGianCho">Thoi Gian Cho</Translate>
            </span>
          </dt>
          <dd>
            {phieuDieuPhoiEntity.thoiGianCho ? (
              <TextFormat value={phieuDieuPhoiEntity.thoiGianCho} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="createdAt">
              <Translate contentKey="adminApp.phieuDieuPhoi.createdAt">Created At</Translate>
            </span>
          </dt>
          <dd>
            {phieuDieuPhoiEntity.createdAt ? (
              <TextFormat value={phieuDieuPhoiEntity.createdAt} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="updatedAt">
              <Translate contentKey="adminApp.phieuDieuPhoi.updatedAt">Updated At</Translate>
            </span>
          </dt>
          <dd>
            {phieuDieuPhoiEntity.updatedAt ? (
              <TextFormat value={phieuDieuPhoiEntity.updatedAt} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
        </dl>
        <Button tag={Link} to="/phieu-dieu-phoi" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/phieu-dieu-phoi/${phieuDieuPhoiEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default PhieuDieuPhoiDetail;
