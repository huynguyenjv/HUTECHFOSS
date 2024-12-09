import React, { useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate, ValidatedField, ValidatedForm, translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { createEntity, getEntity, reset, updateEntity } from './loai-nguoi-nhan.reducer';

export const LoaiNguoiNhanUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const loaiNguoiNhanEntity = useAppSelector(state => state.loaiNguoiNhan.entity);
  const loading = useAppSelector(state => state.loaiNguoiNhan.loading);
  const updating = useAppSelector(state => state.loaiNguoiNhan.updating);
  const updateSuccess = useAppSelector(state => state.loaiNguoiNhan.updateSuccess);

  const handleClose = () => {
    navigate(`/loai-nguoi-nhan${location.search}`);
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
      ...loaiNguoiNhanEntity,
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
          ...loaiNguoiNhanEntity,
          createdAt: convertDateTimeFromServer(loaiNguoiNhanEntity.createdAt),
          updatedAt: convertDateTimeFromServer(loaiNguoiNhanEntity.updatedAt),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="adminApp.loaiNguoiNhan.home.createOrEditLabel" data-cy="LoaiNguoiNhanCreateUpdateHeading">
            <Translate contentKey="adminApp.loaiNguoiNhan.home.createOrEditLabel">Create or edit a LoaiNguoiNhan</Translate>
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
                  id="loai-nguoi-nhan-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('adminApp.loaiNguoiNhan.tenLoai')}
                id="loai-nguoi-nhan-tenLoai"
                name="tenLoai"
                data-cy="tenLoai"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('adminApp.loaiNguoiNhan.moTa')}
                id="loai-nguoi-nhan-moTa"
                name="moTa"
                data-cy="moTa"
                type="text"
              />
              <ValidatedField
                label={translate('adminApp.loaiNguoiNhan.createdAt')}
                id="loai-nguoi-nhan-createdAt"
                name="createdAt"
                data-cy="createdAt"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('adminApp.loaiNguoiNhan.updatedAt')}
                id="loai-nguoi-nhan-updatedAt"
                name="updatedAt"
                data-cy="updatedAt"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/loai-nguoi-nhan" replace color="info">
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

export default LoaiNguoiNhanUpdate;
