import React from 'react';

const EntitiesMenu = () => {
  return (
    <>
      {/* prettier-ignore */}
      <MenuItem icon="asterisk" to="/chi-tiet-dieu-phoi">
        <Translate contentKey="global.menu.entities.chiTietDieuPhoi" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/loai-nguoi-nhan">
        <Translate contentKey="global.menu.entities.loaiNguoiNhan" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/loai-nhu-yeu-pham">
        <Translate contentKey="global.menu.entities.loaiNhuYeuPham" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/loai-thien-tai">
        <Translate contentKey="global.menu.entities.loaiThienTai" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/nguoi-nhan">
        <Translate contentKey="global.menu.entities.nguoiNhan" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/nha-cung-cap">
        <Translate contentKey="global.menu.entities.nhaCungCap" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/nhu-yeu-pham">
        <Translate contentKey="global.menu.entities.nhuYeuPham" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/phieu-dieu-phoi">
        <Translate contentKey="global.menu.entities.phieuDieuPhoi" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/tinh-nguyen-vien">
        <Translate contentKey="global.menu.entities.tinhNguyenVien" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/trang-thai">
        <Translate contentKey="global.menu.entities.trangThai" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/vung">
        <Translate contentKey="global.menu.entities.vung" />
      </MenuItem>
      {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
    </>
  );
};

export default EntitiesMenu;
