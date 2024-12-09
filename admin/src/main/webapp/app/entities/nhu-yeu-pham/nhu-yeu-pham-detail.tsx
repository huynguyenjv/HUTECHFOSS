import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { TextFormat, Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './nhu-yeu-pham.reducer';

export const NhuYeuPhamDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const nhuYeuPhamEntity = useAppSelector(state => state.nhuYeuPham.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="nhuYeuPhamDetailsHeading">
          <Translate contentKey="adminApp.nhuYeuPham.detail.title">NhuYeuPham</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{nhuYeuPhamEntity.id}</dd>
          <dt>
            <span id="tenNhuYeuPham">
              <Translate contentKey="adminApp.nhuYeuPham.tenNhuYeuPham">Ten Nhu Yeu Pham</Translate>
            </span>
          </dt>
          <dd>{nhuYeuPhamEntity.tenNhuYeuPham}</dd>
          <dt>
            <span id="donViTinh">
              <Translate contentKey="adminApp.nhuYeuPham.donViTinh">Don Vi Tinh</Translate>
            </span>
          </dt>
          <dd>{nhuYeuPhamEntity.donViTinh}</dd>
          <dt>
            <span id="loaiNhuYeuPhamId">
              <Translate contentKey="adminApp.nhuYeuPham.loaiNhuYeuPhamId">Loai Nhu Yeu Pham Id</Translate>
            </span>
          </dt>
          <dd>{nhuYeuPhamEntity.loaiNhuYeuPhamId}</dd>
          <dt>
            <span id="mucCanhBao">
              <Translate contentKey="adminApp.nhuYeuPham.mucCanhBao">Muc Canh Bao</Translate>
            </span>
          </dt>
          <dd>{nhuYeuPhamEntity.mucCanhBao}</dd>
          <dt>
            <span id="createdAt">
              <Translate contentKey="adminApp.nhuYeuPham.createdAt">Created At</Translate>
            </span>
          </dt>
          <dd>
            {nhuYeuPhamEntity.createdAt ? <TextFormat value={nhuYeuPhamEntity.createdAt} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="updatedAt">
              <Translate contentKey="adminApp.nhuYeuPham.updatedAt">Updated At</Translate>
            </span>
          </dt>
          <dd>
            {nhuYeuPhamEntity.updatedAt ? <TextFormat value={nhuYeuPhamEntity.updatedAt} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
        </dl>
        <Button tag={Link} to="/nhu-yeu-pham" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/nhu-yeu-pham/${nhuYeuPhamEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default NhuYeuPhamDetail;
