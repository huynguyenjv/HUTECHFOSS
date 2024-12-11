import React, { useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate, ValidatedField, ValidatedForm, translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { createEntity, getEntity, reset, updateEntity } from './vung.reducer';

export const VungUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const vungEntity = useAppSelector(state => state.vung.entity);
  const loading = useAppSelector(state => state.vung.loading);
  const updating = useAppSelector(state => state.vung.updating);
  const updateSuccess = useAppSelector(state => state.vung.updateSuccess);

  const handleClose = () => {
    navigate(`/vung${location.search}`);
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
    if (values.lat !== undefined && typeof values.lat !== 'number') {
      values.lat = Number(values.lat);
    }
    if (values.lng !== undefined && typeof values.lng !== 'number') {
      values.lng = Number(values.lng);
    }
    if (values.loaiThienTaiId !== undefined && typeof values.loaiThienTaiId !== 'number') {
      values.loaiThienTaiId = Number(values.loaiThienTaiId);
    }
    if (values.soDan !== undefined && typeof values.soDan !== 'number') {
      values.soDan = Number(values.soDan);
    }
    values.createdAt = convertDateTimeToServer(values.createdAt);
    values.updatedAt = convertDateTimeToServer(values.updatedAt);

    const entity = {
      ...vungEntity,
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
          ...vungEntity,
          createdAt: convertDateTimeFromServer(vungEntity.createdAt),
          updatedAt: convertDateTimeFromServer(vungEntity.updatedAt),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="adminApp.vung.home.createOrEditLabel" data-cy="VungCreateUpdateHeading">
            <Translate contentKey="adminApp.vung.home.createOrEditLabel">Create or edit a Vung</Translate>
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
                  id="vung-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('adminApp.vung.tenVung')}
                id="vung-tenVung"
                name="tenVung"
                data-cy="tenVung"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField label={translate('adminApp.vung.lat')} id="vung-lat" name="lat" data-cy="lat" type="text" />
              <ValidatedField label={translate('adminApp.vung.lng')} id="vung-lng" name="lng" data-cy="lng" type="text" />
              <ValidatedField
                label={translate('adminApp.vung.loaiThienTaiId')}
                id="vung-loaiThienTaiId"
                name="loaiThienTaiId"
                data-cy="loaiThienTaiId"
                type="text"
              />
              <ValidatedField label={translate('adminApp.vung.soDan')} id="vung-soDan" name="soDan" data-cy="soDan" type="text" />
              <ValidatedField
                label={translate('adminApp.vung.createdAt')}
                id="vung-createdAt"
                name="createdAt"
                data-cy="createdAt"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('adminApp.vung.updatedAt')}
                id="vung-updatedAt"
                name="updatedAt"
                data-cy="updatedAt"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/vung" replace color="info">
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

export default VungUpdate;
