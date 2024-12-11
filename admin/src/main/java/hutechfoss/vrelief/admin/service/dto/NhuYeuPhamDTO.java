package hutechfoss.vrelief.admin.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A DTO for the {@link hutechfoss.vrelief.admin.domain.NhuYeuPham} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class NhuYeuPhamDTO implements Serializable {

    private Long id;

    @NotNull
    private String tenNhuYeuPham;

    private String donViTinh;

    private Integer loaiNhuYeuPhamId;

    private Integer mucCanhBao;

    private ZonedDateTime createdAt;

    private ZonedDateTime updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTenNhuYeuPham() {
        return tenNhuYeuPham;
    }

    public void setTenNhuYeuPham(String tenNhuYeuPham) {
        this.tenNhuYeuPham = tenNhuYeuPham;
    }

    public String getDonViTinh() {
        return donViTinh;
    }

    public void setDonViTinh(String donViTinh) {
        this.donViTinh = donViTinh;
    }

    public Integer getLoaiNhuYeuPhamId() {
        return loaiNhuYeuPhamId;
    }

    public void setLoaiNhuYeuPhamId(Integer loaiNhuYeuPhamId) {
        this.loaiNhuYeuPhamId = loaiNhuYeuPhamId;
    }

    public Integer getMucCanhBao() {
        return mucCanhBao;
    }

    public void setMucCanhBao(Integer mucCanhBao) {
        this.mucCanhBao = mucCanhBao;
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
        if (!(o instanceof NhuYeuPhamDTO)) {
            return false;
        }

        NhuYeuPhamDTO nhuYeuPhamDTO = (NhuYeuPhamDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, nhuYeuPhamDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "NhuYeuPhamDTO{" +
            "id=" + getId() +
            ", tenNhuYeuPham='" + getTenNhuYeuPham() + "'" +
            ", donViTinh='" + getDonViTinh() + "'" +
            ", loaiNhuYeuPhamId=" + getLoaiNhuYeuPhamId() +
            ", mucCanhBao=" + getMucCanhBao() +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            "}";
    }
}
