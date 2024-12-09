import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { TextFormat, Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './vung.reducer';

export const VungDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const vungEntity = useAppSelector(state => state.vung.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="vungDetailsHeading">
          <Translate contentKey="adminApp.vung.detail.title">Vung</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{vungEntity.id}</dd>
          <dt>
            <span id="tenVung">
              <Translate contentKey="adminApp.vung.tenVung">Ten Vung</Translate>
            </span>
          </dt>
          <dd>{vungEntity.tenVung}</dd>
          <dt>
            <span id="lat">
              <Translate contentKey="adminApp.vung.lat">Lat</Translate>
            </span>
          </dt>
          <dd>{vungEntity.lat}</dd>
          <dt>
            <span id="lng">
              <Translate contentKey="adminApp.vung.lng">Lng</Translate>
            </span>
          </dt>
          <dd>{vungEntity.lng}</dd>
          <dt>
            <span id="loaiThienTaiId">
              <Translate contentKey="adminApp.vung.loaiThienTaiId">Loai Thien Tai Id</Translate>
            </span>
          </dt>
          <dd>{vungEntity.loaiThienTaiId}</dd>
          <dt>
            <span id="soDan">
              <Translate contentKey="adminApp.vung.soDan">So Dan</Translate>
            </span>
          </dt>
          <dd>{vungEntity.soDan}</dd>
          <dt>
            <span id="createdAt">
              <Translate contentKey="adminApp.vung.createdAt">Created At</Translate>
            </span>
          </dt>
          <dd>{vungEntity.createdAt ? <TextFormat value={vungEntity.createdAt} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="updatedAt">
              <Translate contentKey="adminApp.vung.updatedAt">Updated At</Translate>
            </span>
          </dt>
          <dd>{vungEntity.updatedAt ? <TextFormat value={vungEntity.updatedAt} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
        </dl>
        <Button tag={Link} to="/vung" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/vung/${vungEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default VungDetail;
