package hutechfoss.vrelief.admin.service.dto;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A DTO for the {@link hutechfoss.vrelief.admin.domain.ChiTietDieuPhoi} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ChiTietDieuPhoiDTO implements Serializable {

    private Long id;

    private Integer phieuDieuPhoiId;

    private Integer nhuYeuPhamId;

    private Integer soLuong;

    private ZonedDateTime createdAt;

    private ZonedDateTime updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPhieuDieuPhoiId() {
        return phieuDieuPhoiId;
    }

    public void setPhieuDieuPhoiId(Integer phieuDieuPhoiId) {
        this.phieuDieuPhoiId = phieuDieuPhoiId;
    }

    public Integer getNhuYeuPhamId() {
        return nhuYeuPhamId;
    }

    public void setNhuYeuPhamId(Integer nhuYeuPhamId) {
        this.nhuYeuPhamId = nhuYeuPhamId;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
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
        if (!(o instanceof ChiTietDieuPhoiDTO)) {
            return false;
        }

        ChiTietDieuPhoiDTO chiTietDieuPhoiDTO = (ChiTietDieuPhoiDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, chiTietDieuPhoiDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ChiTietDieuPhoiDTO{" +
            "id=" + getId() +
            ", phieuDieuPhoiId=" + getPhieuDieuPhoiId() +
            ", nhuYeuPhamId=" + getNhuYeuPhamId() +
            ", soLuong=" + getSoLuong() +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            "}";
    }
}
