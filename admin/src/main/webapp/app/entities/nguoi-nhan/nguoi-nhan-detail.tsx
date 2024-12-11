import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { TextFormat, Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './nguoi-nhan.reducer';

export const NguoiNhanDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const nguoiNhanEntity = useAppSelector(state => state.nguoiNhan.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="nguoiNhanDetailsHeading">
          <Translate contentKey="adminApp.nguoiNhan.detail.title">NguoiNhan</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{nguoiNhanEntity.id}</dd>
          <dt>
            <span id="tenNguoiNhan">
              <Translate contentKey="adminApp.nguoiNhan.tenNguoiNhan">Ten Nguoi Nhan</Translate>
            </span>
          </dt>
          <dd>{nguoiNhanEntity.tenNguoiNhan}</dd>
          <dt>
            <span id="soDienThoai">
              <Translate contentKey="adminApp.nguoiNhan.soDienThoai">So Dien Thoai</Translate>
            </span>
          </dt>
          <dd>{nguoiNhanEntity.soDienThoai}</dd>
          <dt>
            <span id="email">
              <Translate contentKey="adminApp.nguoiNhan.email">Email</Translate>
            </span>
          </dt>
          <dd>{nguoiNhanEntity.email}</dd>
          <dt>
            <span id="diaChi">
              <Translate contentKey="adminApp.nguoiNhan.diaChi">Dia Chi</Translate>
            </span>
          </dt>
          <dd>{nguoiNhanEntity.diaChi}</dd>
          <dt>
            <span id="vungId">
              <Translate contentKey="adminApp.nguoiNhan.vungId">Vung Id</Translate>
            </span>
          </dt>
          <dd>{nguoiNhanEntity.vungId}</dd>
          <dt>
            <span id="soNguoiNhan">
              <Translate contentKey="adminApp.nguoiNhan.soNguoiNhan">So Nguoi Nhan</Translate>
            </span>
          </dt>
          <dd>{nguoiNhanEntity.soNguoiNhan}</dd>
          <dt>
            <span id="createdAt">
              <Translate contentKey="adminApp.nguoiNhan.createdAt">Created At</Translate>
            </span>
          </dt>
          <dd>
            {nguoiNhanEntity.createdAt ? <TextFormat value={nguoiNhanEntity.createdAt} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="updatedAt">
              <Translate contentKey="adminApp.nguoiNhan.updatedAt">Updated At</Translate>
            </span>
          </dt>
          <dd>
            {nguoiNhanEntity.updatedAt ? <TextFormat value={nguoiNhanEntity.updatedAt} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
        </dl>
        <Button tag={Link} to="/nguoi-nhan" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/nguoi-nhan/${nguoiNhanEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default NguoiNhanDetail;
