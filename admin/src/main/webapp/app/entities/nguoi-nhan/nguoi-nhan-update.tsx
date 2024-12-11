import React, { useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate, ValidatedField, ValidatedForm, translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { createEntity, getEntity, reset, updateEntity } from './nguoi-nhan.reducer';

export const NguoiNhanUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const nguoiNhanEntity = useAppSelector(state => state.nguoiNhan.entity);
  const loading = useAppSelector(state => state.nguoiNhan.loading);
  const updating = useAppSelector(state => state.nguoiNhan.updating);
  const updateSuccess = useAppSelector(state => state.nguoiNhan.updateSuccess);

  const handleClose = () => {
    navigate(`/nguoi-nhan${location.search}`);
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
    if (values.vungId !== undefined && typeof values.vungId !== 'number') {
      values.vungId = Number(values.vungId);
    }
    if (values.soNguoiNhan !== undefined && typeof values.soNguoiNhan !== 'number') {
      values.soNguoiNhan = Number(values.soNguoiNhan);
    }
    values.createdAt = convertDateTimeToServer(values.createdAt);
    values.updatedAt = convertDateTimeToServer(values.updatedAt);

    const entity = {
      ...nguoiNhanEntity,
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
          ...nguoiNhanEntity,
          createdAt: convertDateTimeFromServer(nguoiNhanEntity.createdAt),
          updatedAt: convertDateTimeFromServer(nguoiNhanEntity.updatedAt),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="adminApp.nguoiNhan.home.createOrEditLabel" data-cy="NguoiNhanCreateUpdateHeading">
            <Translate contentKey="adminApp.nguoiNhan.home.createOrEditLabel">Create or edit a NguoiNhan</Translate>
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
                  id="nguoi-nhan-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('adminApp.nguoiNhan.tenNguoiNhan')}
                id="nguoi-nhan-tenNguoiNhan"
                name="tenNguoiNhan"
                data-cy="tenNguoiNhan"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('adminApp.nguoiNhan.soDienThoai')}
                id="nguoi-nhan-soDienThoai"
                name="soDienThoai"
                data-cy="soDienThoai"
                type="text"
              />
              <ValidatedField
                label={translate('adminApp.nguoiNhan.email')}
                id="nguoi-nhan-email"
                name="email"
                data-cy="email"
                type="text"
              />
              <ValidatedField
                label={translate('adminApp.nguoiNhan.diaChi')}
                id="nguoi-nhan-diaChi"
                name="diaChi"
                data-cy="diaChi"
                type="text"
              />
              <ValidatedField
                label={translate('adminApp.nguoiNhan.vungId')}
                id="nguoi-nhan-vungId"
                name="vungId"
                data-cy="vungId"
                type="text"
              />
              <ValidatedField
                label={translate('adminApp.nguoiNhan.soNguoiNhan')}
                id="nguoi-nhan-soNguoiNhan"
                name="soNguoiNhan"
                data-cy="soNguoiNhan"
                type="text"
              />
              <ValidatedField
                label={translate('adminApp.nguoiNhan.createdAt')}
                id="nguoi-nhan-createdAt"
                name="createdAt"
                data-cy="createdAt"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('adminApp.nguoiNhan.updatedAt')}
                id="nguoi-nhan-updatedAt"
                name="updatedAt"
                data-cy="updatedAt"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/nguoi-nhan" replace color="info">
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

export default NguoiNhanUpdate;
