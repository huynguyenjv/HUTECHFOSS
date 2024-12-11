import React, { useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate, ValidatedField, ValidatedForm, translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { createEntity, getEntity, reset, updateEntity } from './nhu-yeu-pham.reducer';

export const NhuYeuPhamUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const nhuYeuPhamEntity = useAppSelector(state => state.nhuYeuPham.entity);
  const loading = useAppSelector(state => state.nhuYeuPham.loading);
  const updating = useAppSelector(state => state.nhuYeuPham.updating);
  const updateSuccess = useAppSelector(state => state.nhuYeuPham.updateSuccess);

  const handleClose = () => {
    navigate(`/nhu-yeu-pham${location.search}`);
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
    if (values.loaiNhuYeuPhamId !== undefined && typeof values.loaiNhuYeuPhamId !== 'number') {
      values.loaiNhuYeuPhamId = Number(values.loaiNhuYeuPhamId);
    }
    if (values.mucCanhBao !== undefined && typeof values.mucCanhBao !== 'number') {
      values.mucCanhBao = Number(values.mucCanhBao);
    }
    values.createdAt = convertDateTimeToServer(values.createdAt);
    values.updatedAt = convertDateTimeToServer(values.updatedAt);

    const entity = {
      ...nhuYeuPhamEntity,
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
          ...nhuYeuPhamEntity,
          createdAt: convertDateTimeFromServer(nhuYeuPhamEntity.createdAt),
          updatedAt: convertDateTimeFromServer(nhuYeuPhamEntity.updatedAt),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="adminApp.nhuYeuPham.home.createOrEditLabel" data-cy="NhuYeuPhamCreateUpdateHeading">
            <Translate contentKey="adminApp.nhuYeuPham.home.createOrEditLabel">Create or edit a NhuYeuPham</Translate>
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
                  id="nhu-yeu-pham-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('adminApp.nhuYeuPham.tenNhuYeuPham')}
                id="nhu-yeu-pham-tenNhuYeuPham"
                name="tenNhuYeuPham"
                data-cy="tenNhuYeuPham"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('adminApp.nhuYeuPham.donViTinh')}
                id="nhu-yeu-pham-donViTinh"
                name="donViTinh"
                data-cy="donViTinh"
                type="text"
              />
              <ValidatedField
                label={translate('adminApp.nhuYeuPham.loaiNhuYeuPhamId')}
                id="nhu-yeu-pham-loaiNhuYeuPhamId"
                name="loaiNhuYeuPhamId"
                data-cy="loaiNhuYeuPhamId"
                type="text"
              />
              <ValidatedField
                label={translate('adminApp.nhuYeuPham.mucCanhBao')}
                id="nhu-yeu-pham-mucCanhBao"
                name="mucCanhBao"
                data-cy="mucCanhBao"
                type="text"
              />
              <ValidatedField
                label={translate('adminApp.nhuYeuPham.createdAt')}
                id="nhu-yeu-pham-createdAt"
                name="createdAt"
                data-cy="createdAt"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('adminApp.nhuYeuPham.updatedAt')}
                id="nhu-yeu-pham-updatedAt"
                name="updatedAt"
                data-cy="updatedAt"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/nhu-yeu-pham" replace color="info">
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

export default NhuYeuPhamUpdate;
