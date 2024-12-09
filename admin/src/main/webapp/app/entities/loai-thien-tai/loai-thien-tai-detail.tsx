import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { TextFormat, Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './loai-thien-tai.reducer';

export const LoaiThienTaiDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const loaiThienTaiEntity = useAppSelector(state => state.loaiThienTai.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="loaiThienTaiDetailsHeading">
          <Translate contentKey="adminApp.loaiThienTai.detail.title">LoaiThienTai</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{loaiThienTaiEntity.id}</dd>
          <dt>
            <span id="tenLoaiThienTai">
              <Translate contentKey="adminApp.loaiThienTai.tenLoaiThienTai">Ten Loai Thien Tai</Translate>
            </span>
          </dt>
          <dd>{loaiThienTaiEntity.tenLoaiThienTai}</dd>
          <dt>
            <span id="moTa">
              <Translate contentKey="adminApp.loaiThienTai.moTa">Mo Ta</Translate>
            </span>
          </dt>
          <dd>{loaiThienTaiEntity.moTa}</dd>
          <dt>
            <span id="createdAt">
              <Translate contentKey="adminApp.loaiThienTai.createdAt">Created At</Translate>
            </span>
          </dt>
          <dd>
            {loaiThienTaiEntity.createdAt ? <TextFormat value={loaiThienTaiEntity.createdAt} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="updatedAt">
              <Translate contentKey="adminApp.loaiThienTai.updatedAt">Updated At</Translate>
            </span>
          </dt>
          <dd>
            {loaiThienTaiEntity.updatedAt ? <TextFormat value={loaiThienTaiEntity.updatedAt} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
        </dl>
        <Button tag={Link} to="/loai-thien-tai" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/loai-thien-tai/${loaiThienTaiEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default LoaiThienTaiDetail;
