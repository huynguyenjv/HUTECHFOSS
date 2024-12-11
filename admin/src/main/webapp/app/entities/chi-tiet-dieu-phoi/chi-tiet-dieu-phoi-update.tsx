import React, { useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate, ValidatedField, ValidatedForm, translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { createEntity, getEntity, reset, updateEntity } from './chi-tiet-dieu-phoi.reducer';

export const ChiTietDieuPhoiUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const chiTietDieuPhoiEntity = useAppSelector(state => state.chiTietDieuPhoi.entity);
  const loading = useAppSelector(state => state.chiTietDieuPhoi.loading);
  const updating = useAppSelector(state => state.chiTietDieuPhoi.updating);
  const updateSuccess = useAppSelector(state => state.chiTietDieuPhoi.updateSuccess);

  const handleClose = () => {
    navigate(`/chi-tiet-dieu-phoi${location.search}`);
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    if (values.id !== undefined && typeof values.id !== 'number') {
      values.id = Number(values.id);
    }
    if (values.phieuDieuPhoiId !== undefined && typeof values.phieuDieuPhoiId !== 'number') {
      values.phieuDieuPhoiId = Number(values.phieuDieuPhoiId);
    }
    if (values.nhuYeuPhamId !== undefined && typeof values.nhuYeuPhamId !== 'number') {
      values.nhuYeuPhamId = Number(values.nhuYeuPhamId);
    }
    if (values.soLuong !== undefined && typeof values.soLuong !== 'number') {
      values.soLuong = Number(values.soLuong);
    }
    values.createdAt = convertDateTimeToServer(values.createdAt);
    values.updatedAt = convertDateTimeToServer(values.updatedAt);

    const entity = {
      ...chiTietDieuPhoiEntity,
      ...values,
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {
          createdAt: displayDefaultDateTime(),
          updatedAt: displayDefaultDateTime(),
        }
      : {
          ...chiTietDieuPhoiEntity,
          createdAt: convertDateTimeFromServer(chiTietDieuPhoiEntity.createdAt),
          updatedAt: convertDateTimeFromServer(chiTietDieuPhoiEntity.updatedAt),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="adminApp.chiTietDieuPhoi.home.createOrEditLabel" data-cy="ChiTietDieuPhoiCreateUpdateHeading">
            <Translate contentKey="adminApp.chiTietDieuPhoi.home.createOrEditLabel">Create or edit a ChiTietDieuPhoi</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? (
                <ValidatedField
                  name="id"
                  required
                  readOnly
                  id="chi-tiet-dieu-phoi-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('adminApp.chiTietDieuPhoi.phieuDieuPhoiId')}
                id="chi-tiet-dieu-phoi-phieuDieuPhoiId"
                name="phieuDieuPhoiId"
                data-cy="phieuDieuPhoiId"
                type="text"
              />
              <ValidatedField
                label={translate('adminApp.chiTietDieuPhoi.nhuYeuPhamId')}
                id="chi-tiet-dieu-phoi-nhuYeuPhamId"
                name="nhuYeuPhamId"
                data-cy="nhuYeuPhamId"
                type="text"
              />
              <ValidatedField
                label={translate('adminApp.chiTietDieuPhoi.soLuong')}
                id="chi-tiet-dieu-phoi-soLuong"
                name="soLuong"
                data-cy="soLuong"
                type="text"
              />
              <ValidatedField
                label={translate('adminApp.chiTietDieuPhoi.createdAt')}
                id="chi-tiet-dieu-phoi-createdAt"
                name="createdAt"
                data-cy="createdAt"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('adminApp.chiTietDieuPhoi.updatedAt')}
                id="chi-tiet-dieu-phoi-updatedAt"
                name="updatedAt"
                data-cy="updatedAt"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/chi-tiet-dieu-phoi" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default ChiTietDieuPhoiUpdate;
