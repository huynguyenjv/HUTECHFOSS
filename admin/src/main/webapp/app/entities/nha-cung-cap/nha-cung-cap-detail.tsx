import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { TextFormat, Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './nha-cung-cap.reducer';

export const NhaCungCapDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const nhaCungCapEntity = useAppSelector(state => state.nhaCungCap.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="nhaCungCapDetailsHeading">
          <Translate contentKey="adminApp.nhaCungCap.detail.title">NhaCungCap</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{nhaCungCapEntity.id}</dd>
          <dt>
            <span id="tenNhaCungCap">
              <Translate contentKey="adminApp.nhaCungCap.tenNhaCungCap">Ten Nha Cung Cap</Translate>
            </span>
          </dt>
          <dd>{nhaCungCapEntity.tenNhaCungCap}</dd>
          <dt>
            <span id="soDienThoai">
              <Translate contentKey="adminApp.nhaCungCap.soDienThoai">So Dien Thoai</Translate>
            </span>
          </dt>
          <dd>{nhaCungCapEntity.soDienThoai}</dd>
          <dt>
            <span id="email">
              <Translate contentKey="adminApp.nhaCungCap.email">Email</Translate>
            </span>
          </dt>
          <dd>{nhaCungCapEntity.email}</dd>
          <dt>
            <span id="diaChi">
              <Translate contentKey="adminApp.nhaCungCap.diaChi">Dia Chi</Translate>
            </span>
          </dt>
          <dd>{nhaCungCapEntity.diaChi}</dd>
          <dt>
            <span id="createdAt">
              <Translate contentKey="adminApp.nhaCungCap.createdAt">Created At</Translate>
            </span>
          </dt>
          <dd>
            {nhaCungCapEntity.createdAt ? <TextFormat value={nhaCungCapEntity.createdAt} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="updatedAt">
              <Translate contentKey="adminApp.nhaCungCap.updatedAt">Updated At</Translate>
            </span>
          </dt>
          <dd>
            {nhaCungCapEntity.updatedAt ? <TextFormat value={nhaCungCapEntity.updatedAt} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
        </dl>
        <Button tag={Link} to="/nha-cung-cap" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/nha-cung-cap/${nhaCungCapEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default NhaCungCapDetail;
