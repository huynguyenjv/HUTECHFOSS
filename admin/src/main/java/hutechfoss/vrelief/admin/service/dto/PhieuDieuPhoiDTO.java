package hutechfoss.vrelief.admin.service.dto;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A DTO for the {@link hutechfoss.vrelief.admin.domain.PhieuDieuPhoi} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class PhieuDieuPhoiDTO implements Serializable {

    private Long id;

    private Integer nhaCungCapId;

    private Integer nguoiNhanId;

    private Integer tinhNguyenVienId;

    private Integer trangThaiId;

    private ZonedDateTime thoiGianDen;

    private ZonedDateTime thoiGianCho;

    private ZonedDateTime createdAt;

    private ZonedDateTime updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNhaCungCapId() {
        return nhaCungCapId;
    }

    public void setNhaCungCapId(Integer nhaCungCapId) {
        this.nhaCungCapId = nhaCungCapId;
    }

    public Integer getNguoiNhanId() {
        return nguoiNhanId;
    }

    public void setNguoiNhanId(Integer nguoiNhanId) {
        this.nguoiNhanId = nguoiNhanId;
    }

    public Integer getTinhNguyenVienId() {
        return tinhNguyenVienId;
    }

    public void setTinhNguyenVienId(Integer tinhNguyenVienId) {
        this.tinhNguyenVienId = tinhNguyenVienId;
    }

    public Integer getTrangThaiId() {
        return trangThaiId;
    }

    public void setTrangThaiId(Integer trangThaiId) {
        this.trangThaiId = trangThaiId;
    }

    public ZonedDateTime getThoiGianDen() {
        return thoiGianDen;
    }

    public void setThoiGianDen(ZonedDateTime thoiGianDen) {
        this.thoiGianDen = thoiGianDen;
    }

    public ZonedDateTime getThoiGianCho() {
        return thoiGianCho;
    }

    public void setThoiGianCho(ZonedDateTime thoiGianCho) {
        this.thoiGianCho = thoiGianCho;
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
        if (!(o instanceof PhieuDieuPhoiDTO)) {
            return false;
        }

        PhieuDieuPhoiDTO phieuDieuPhoiDTO = (PhieuDieuPhoiDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, phieuDieuPhoiDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PhieuDieuPhoiDTO{" +
            "id=" + getId() +
            ", nhaCungCapId=" + getNhaCungCapId() +
            ", nguoiNhanId=" + getNguoiNhanId() +
            ", tinhNguyenVienId=" + getTinhNguyenVienId() +
            ", trangThaiId=" + getTrangThaiId() +
            ", thoiGianDen='" + getThoiGianDen() + "'" +
            ", thoiGianCho='" + getThoiGianCho() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            "}";
    }
}
