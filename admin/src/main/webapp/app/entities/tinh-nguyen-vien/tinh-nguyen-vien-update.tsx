import React, { useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate, ValidatedField, ValidatedForm, translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { createEntity, getEntity, reset, updateEntity } from './tinh-nguyen-vien.reducer';

export const TinhNguyenVienUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const tinhNguyenVienEntity = useAppSelector(state => state.tinhNguyenVien.entity);
  const loading = useAppSelector(state => state.tinhNguyenVien.loading);
  const updating = useAppSelector(state => state.tinhNguyenVien.updating);
  const updateSuccess = useAppSelector(state => state.tinhNguyenVien.updateSuccess);

  const handleClose = () => {
    navigate(`/tinh-nguyen-vien${location.search}`);
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
    if (values.trangThaiId !== undefined && typeof values.trangThaiId !== 'number') {
      values.trangThaiId = Number(values.trangThaiId);
    }
    values.createdAt = convertDateTimeToServer(values.createdAt);
    values.updatedAt = convertDateTimeToServer(values.updatedAt);

    const entity = {
      ...tinhNguyenVienEntity,
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
          ...tinhNguyenVienEntity,
          createdAt: convertDateTimeFromServer(tinhNguyenVienEntity.createdAt),
          updatedAt: convertDateTimeFromServer(tinhNguyenVienEntity.updatedAt),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="adminApp.tinhNguyenVien.home.createOrEditLabel" data-cy="TinhNguyenVienCreateUpdateHeading">
            <Translate contentKey="adminApp.tinhNguyenVien.home.createOrEditLabel">Create or edit a TinhNguyenVien</Translate>
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
                  id="tinh-nguyen-vien-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('adminApp.tinhNguyenVien.tenTinhNguyenVien')}
                id="tinh-nguyen-vien-tenTinhNguyenVien"
                name="tenTinhNguyenVien"
                data-cy="tenTinhNguyenVien"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('adminApp.tinhNguyenVien.soDienThoai')}
                id="tinh-nguyen-vien-soDienThoai"
                name="soDienThoai"
                data-cy="soDienThoai"
                type="text"
              />
              <ValidatedField
                label={translate('adminApp.tinhNguyenVien.diaChi')}
                id="tinh-nguyen-vien-diaChi"
                name="diaChi"
                data-cy="diaChi"
                type="text"
              />
              <ValidatedField
                label={translate('adminApp.tinhNguyenVien.trangThaiId')}
                id="tinh-nguyen-vien-trangThaiId"
                name="trangThaiId"
                data-cy="trangThaiId"
                type="text"
              />
              <ValidatedField
                label={translate('adminApp.tinhNguyenVien.createdAt')}
                id="tinh-nguyen-vien-createdAt"
                name="createdAt"
                data-cy="createdAt"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('adminApp.tinhNguyenVien.updatedAt')}
                id="tinh-nguyen-vien-updatedAt"
                name="updatedAt"
                data-cy="updatedAt"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/tinh-nguyen-vien" replace color="info">
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

export default TinhNguyenVienUpdate;
