import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { TextFormat, Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './loai-nguoi-nhan.reducer';

export const LoaiNguoiNhanDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const loaiNguoiNhanEntity = useAppSelector(state => state.loaiNguoiNhan.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="loaiNguoiNhanDetailsHeading">
          <Translate contentKey="adminApp.loaiNguoiNhan.detail.title">LoaiNguoiNhan</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{loaiNguoiNhanEntity.id}</dd>
          <dt>
            <span id="tenLoai">
              <Translate contentKey="adminApp.loaiNguoiNhan.tenLoai">Ten Loai</Translate>
            </span>
          </dt>
          <dd>{loaiNguoiNhanEntity.tenLoai}</dd>
          <dt>
            <span id="moTa">
              <Translate contentKey="adminApp.loaiNguoiNhan.moTa">Mo Ta</Translate>
            </span>
          </dt>
          <dd>{loaiNguoiNhanEntity.moTa}</dd>
          <dt>
            <span id="createdAt">
              <Translate contentKey="adminApp.loaiNguoiNhan.createdAt">Created At</Translate>
            </span>
          </dt>
          <dd>
            {loaiNguoiNhanEntity.createdAt ? (
              <TextFormat value={loaiNguoiNhanEntity.createdAt} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="updatedAt">
              <Translate contentKey="adminApp.loaiNguoiNhan.updatedAt">Updated At</Translate>
            </span>
          </dt>
          <dd>
            {loaiNguoiNhanEntity.updatedAt ? (
              <TextFormat value={loaiNguoiNhanEntity.updatedAt} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
        </dl>
        <Button tag={Link} to="/loai-nguoi-nhan" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/loai-nguoi-nhan/${loaiNguoiNhanEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default LoaiNguoiNhanDetail;
