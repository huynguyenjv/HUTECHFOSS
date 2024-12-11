import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { TextFormat, Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './loai-nhu-yeu-pham.reducer';

export const LoaiNhuYeuPhamDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const loaiNhuYeuPhamEntity = useAppSelector(state => state.loaiNhuYeuPham.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="loaiNhuYeuPhamDetailsHeading">
          <Translate contentKey="adminApp.loaiNhuYeuPham.detail.title">LoaiNhuYeuPham</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{loaiNhuYeuPhamEntity.id}</dd>
          <dt>
            <span id="tenLoai">
              <Translate contentKey="adminApp.loaiNhuYeuPham.tenLoai">Ten Loai</Translate>
            </span>
          </dt>
          <dd>{loaiNhuYeuPhamEntity.tenLoai}</dd>
          <dt>
            <span id="createdAt">
              <Translate contentKey="adminApp.loaiNhuYeuPham.createdAt">Created At</Translate>
            </span>
          </dt>
          <dd>
            {loaiNhuYeuPhamEntity.createdAt ? (
              <TextFormat value={loaiNhuYeuPhamEntity.createdAt} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="updatedAt">
              <Translate contentKey="adminApp.loaiNhuYeuPham.updatedAt">Updated At</Translate>
            </span>
          </dt>
          <dd>
            {loaiNhuYeuPhamEntity.updatedAt ? (
              <TextFormat value={loaiNhuYeuPhamEntity.updatedAt} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
        </dl>
        <Button tag={Link} to="/loai-nhu-yeu-pham" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/loai-nhu-yeu-pham/${loaiNhuYeuPhamEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default LoaiNhuYeuPhamDetail;
