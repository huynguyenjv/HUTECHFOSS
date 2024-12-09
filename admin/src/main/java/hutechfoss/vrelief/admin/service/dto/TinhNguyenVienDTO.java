package hutechfoss.vrelief.admin.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A DTO for the {@link hutechfoss.vrelief.admin.domain.TinhNguyenVien} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TinhNguyenVienDTO implements Serializable {

    private Long id;

    @NotNull
    private String tenTinhNguyenVien;

    private String soDienThoai;

    private String diaChi;

    private Integer trangThaiId;

    private ZonedDateTime createdAt;

    private ZonedDateTime updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTenTinhNguyenVien() {
        return tenTinhNguyenVien;
    }

    public void setTenTinhNguyenVien(String tenTinhNguyenVien) {
        this.tenTinhNguyenVien = tenTinhNguyenVien;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public Integer getTrangThaiId() {
        return trangThaiId;
    }

    public void setTrangThaiId(Integer trangThaiId) {
        this.trangThaiId = trangThaiId;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public ZonedDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(ZonedDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TinhNguyenVienDTO)) {
            return false;
        }

        TinhNguyenVienDTO tinhNguyenVienDTO = (TinhNguyenVienDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, tinhNguyenVienDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TinhNguyenVienDTO{" +
            "id=" + getId() +
            ", tenTinhNguyenVien='" + getTenTinhNguyenVien() + "'" +
            ", soDienThoai='" + getSoDienThoai() + "'" +
            ", diaChi='" + getDiaChi() + "'" +
            ", trangThaiId=" + getTrangThaiId() +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            "}";
    }
}
