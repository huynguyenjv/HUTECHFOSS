import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { TextFormat, Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './trang-thai.reducer';

export const TrangThaiDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const trangThaiEntity = useAppSelector(state => state.trangThai.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="trangThaiDetailsHeading">
          <Translate contentKey="adminApp.trangThai.detail.title">TrangThai</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{trangThaiEntity.id}</dd>
          <dt>
            <span id="tenTrangThai">
              <Translate contentKey="adminApp.trangThai.tenTrangThai">Ten Trang Thai</Translate>
            </span>
          </dt>
          <dd>{trangThaiEntity.tenTrangThai}</dd>
          <dt>
            <span id="moTa">
              <Translate contentKey="adminApp.trangThai.moTa">Mo Ta</Translate>
            </span>
          </dt>
          <dd>{trangThaiEntity.moTa}</dd>
          <dt>
            <span id="createdAt">
              <Translate contentKey="adminApp.trangThai.createdAt">Created At</Translate>
            </span>
          </dt>
          <dd>
            {trangThaiEntity.createdAt ? <TextFormat value={trangThaiEntity.createdAt} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="updatedAt">
              <Translate contentKey="adminApp.trangThai.updatedAt">Updated At</Translate>
            </span>
          </dt>
          <dd>
            {trangThaiEntity.updatedAt ? <TextFormat value={trangThaiEntity.updatedAt} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
        </dl>
        <Button tag={Link} to="/trang-thai" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/trang-thai/${trangThaiEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default TrangThaiDetail;
