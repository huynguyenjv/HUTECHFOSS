import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { TextFormat, Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './chi-tiet-dieu-phoi.reducer';

export const ChiTietDieuPhoiDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const chiTietDieuPhoiEntity = useAppSelector(state => state.chiTietDieuPhoi.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="chiTietDieuPhoiDetailsHeading">
          <Translate contentKey="adminApp.chiTietDieuPhoi.detail.title">ChiTietDieuPhoi</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{chiTietDieuPhoiEntity.id}</dd>
          <dt>
            <span id="phieuDieuPhoiId">
              <Translate contentKey="adminApp.chiTietDieuPhoi.phieuDieuPhoiId">Phieu Dieu Phoi Id</Translate>
            </span>
          </dt>
          <dd>{chiTietDieuPhoiEntity.phieuDieuPhoiId}</dd>
          <dt>
            <span id="nhuYeuPhamId">
              <Translate contentKey="adminApp.chiTietDieuPhoi.nhuYeuPhamId">Nhu Yeu Pham Id</Translate>
            </span>
          </dt>
          <dd>{chiTietDieuPhoiEntity.nhuYeuPhamId}</dd>
          <dt>
            <span id="soLuong">
              <Translate contentKey="adminApp.chiTietDieuPhoi.soLuong">So Luong</Translate>
            </span>
          </dt>
          <dd>{chiTietDieuPhoiEntity.soLuong}</dd>
          <dt>
            <span id="createdAt">
              <Translate contentKey="adminApp.chiTietDieuPhoi.createdAt">Created At</Translate>
            </span>
          </dt>
          <dd>
            {chiTietDieuPhoiEntity.createdAt ? (
              <TextFormat value={chiTietDieuPhoiEntity.createdAt} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="updatedAt">
              <Translate contentKey="adminApp.chiTietDieuPhoi.updatedAt">Updated At</Translate>
            </span>
          </dt>
          <dd>
            {chiTietDieuPhoiEntity.updatedAt ? (
              <TextFormat value={chiTietDieuPhoiEntity.updatedAt} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
        </dl>
        <Button tag={Link} to="/chi-tiet-dieu-phoi" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/chi-tiet-dieu-phoi/${chiTietDieuPhoiEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default ChiTietDieuPhoiDetail;
