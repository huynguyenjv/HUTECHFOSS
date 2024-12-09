import React, { useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate, ValidatedField, ValidatedForm, translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { createEntity, getEntity, reset, updateEntity } from './phieu-dieu-phoi.reducer';

export const PhieuDieuPhoiUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const phieuDieuPhoiEntity = useAppSelector(state => state.phieuDieuPhoi.entity);
  const loading = useAppSelector(state => state.phieuDieuPhoi.loading);
  const updating = useAppSelector(state => state.phieuDieuPhoi.updating);
  const updateSuccess = useAppSelector(state => state.phieuDieuPhoi.updateSuccess);

  const handleClose = () => {
    navigate(`/phieu-dieu-phoi${location.search}`);
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
    if (values.nhaCungCapId !== undefined && typeof values.nhaCungCapId !== 'number') {
      values.nhaCungCapId = Number(values.nhaCungCapId);
    }
    if (values.nguoiNhanId !== undefined && typeof values.nguoiNhanId !== 'number') {
      values.nguoiNhanId = Number(values.nguoiNhanId);
    }
    if (values.tinhNguyenVienId !== undefined && typeof values.tinhNguyenVienId !== 'number') {
      values.tinhNguyenVienId = Number(values.tinhNguyenVienId);
    }
    if (values.trangThaiId !== undefined && typeof values.trangThaiId !== 'number') {
      values.trangThaiId = Number(values.trangThaiId);
    }
    values.thoiGianDen = convertDateTimeToServer(values.thoiGianDen);
    values.thoiGianCho = convertDateTimeToServer(values.thoiGianCho);
    values.createdAt = convertDateTimeToServer(values.createdAt);
    values.updatedAt = convertDateTimeToServer(values.updatedAt);

    const entity = {
      ...phieuDieuPhoiEntity,
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
          thoiGianDen: displayDefaultDateTime(),
          thoiGianCho: displayDefaultDateTime(),
          createdAt: displayDefaultDateTime(),
          updatedAt: displayDefaultDateTime(),
        }
      : {
          ...phieuDieuPhoiEntity,
          thoiGianDen: convertDateTimeFromServer(phieuDieuPhoiEntity.thoiGianDen),
          thoiGianCho: convertDateTimeFromServer(phieuDieuPhoiEntity.thoiGianCho),
          createdAt: convertDateTimeFromServer(phieuDieuPhoiEntity.createdAt),
          updatedAt: convertDateTimeFromServer(phieuDieuPhoiEntity.updatedAt),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="adminApp.phieuDieuPhoi.home.createOrEditLabel" data-cy="PhieuDieuPhoiCreateUpdateHeading">
            <Translate contentKey="adminApp.phieuDieuPhoi.home.createOrEditLabel">Create or edit a PhieuDieuPhoi</Translate>
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
                  id="phieu-dieu-phoi-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('adminApp.phieuDieuPhoi.nhaCungCapId')}
                id="phieu-dieu-phoi-nhaCungCapId"
                name="nhaCungCapId"
                data-cy="nhaCungCapId"
                type="text"
              />
              <ValidatedField
                label={translate('adminApp.phieuDieuPhoi.nguoiNhanId')}
                id="phieu-dieu-phoi-nguoiNhanId"
                name="nguoiNhanId"
                data-cy="nguoiNhanId"
                type="text"
              />
              <ValidatedField
                label={translate('adminApp.phieuDieuPhoi.tinhNguyenVienId')}
                id="phieu-dieu-phoi-tinhNguyenVienId"
                name="tinhNguyenVienId"
                data-cy="tinhNguyenVienId"
                type="text"
              />
              <ValidatedField
                label={translate('adminApp.phieuDieuPhoi.trangThaiId')}
                id="phieu-dieu-phoi-trangThaiId"
                name="trangThaiId"
                data-cy="trangThaiId"
                type="text"
              />
              <ValidatedField
                label={translate('adminApp.phieuDieuPhoi.thoiGianDen')}
                id="phieu-dieu-phoi-thoiGianDen"
                name="thoiGianDen"
                data-cy="thoiGianDen"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('adminApp.phieuDieuPhoi.thoiGianCho')}
                id="phieu-dieu-phoi-thoiGianCho"
                name="thoiGianCho"
                data-cy="thoiGianCho"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('adminApp.phieuDieuPhoi.createdAt')}
                id="phieu-dieu-phoi-createdAt"
                name="createdAt"
                data-cy="createdAt"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('adminApp.phieuDieuPhoi.updatedAt')}
                id="phieu-dieu-phoi-updatedAt"
                name="updatedAt"
                data-cy="updatedAt"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/phieu-dieu-phoi" replace color="info">
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

export default PhieuDieuPhoiUpdate;
