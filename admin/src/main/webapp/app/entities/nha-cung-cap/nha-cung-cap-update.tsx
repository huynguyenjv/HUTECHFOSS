import React, { useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate, ValidatedField, ValidatedForm, translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { createEntity, getEntity, reset, updateEntity } from './nha-cung-cap.reducer';

export const NhaCungCapUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const nhaCungCapEntity = useAppSelector(state => state.nhaCungCap.entity);
  const loading = useAppSelector(state => state.nhaCungCap.loading);
  const updating = useAppSelector(state => state.nhaCungCap.updating);
  const updateSuccess = useAppSelector(state => state.nhaCungCap.updateSuccess);

  const handleClose = () => {
    navigate(`/nha-cung-cap${location.search}`);
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
    values.createdAt = convertDateTimeToServer(values.createdAt);
    values.updatedAt = convertDateTimeToServer(values.updatedAt);

    const entity = {
      ...nhaCungCapEntity,
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
          ...nhaCungCapEntity,
          createdAt: convertDateTimeFromServer(nhaCungCapEntity.createdAt),
          updatedAt: convertDateTimeFromServer(nhaCungCapEntity.updatedAt),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="adminApp.nhaCungCap.home.createOrEditLabel" data-cy="NhaCungCapCreateUpdateHeading">
            <Translate contentKey="adminApp.nhaCungCap.home.createOrEditLabel">Create or edit a NhaCungCap</Translate>
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
                  id="nha-cung-cap-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('adminApp.nhaCungCap.tenNhaCungCap')}
                id="nha-cung-cap-tenNhaCungCap"
                name="tenNhaCungCap"
                data-cy="tenNhaCungCap"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('adminApp.nhaCungCap.soDienThoai')}
                id="nha-cung-cap-soDienThoai"
                name="soDienThoai"
                data-cy="soDienThoai"
                type="text"
              />
              <ValidatedField
                label={translate('adminApp.nhaCungCap.email')}
                id="nha-cung-cap-email"
                name="email"
                data-cy="email"
                type="text"
              />
              <ValidatedField
                label={translate('adminApp.nhaCungCap.diaChi')}
                id="nha-cung-cap-diaChi"
                name="diaChi"
                data-cy="diaChi"
                type="text"
              />
              <ValidatedField
                label={translate('adminApp.nhaCungCap.createdAt')}
                id="nha-cung-cap-createdAt"
                name="createdAt"
                data-cy="createdAt"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('adminApp.nhaCungCap.updatedAt')}
                id="nha-cung-cap-updatedAt"
                name="updatedAt"
                data-cy="updatedAt"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/nha-cung-cap" replace color="info">
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

export default NhaCungCapUpdate;
