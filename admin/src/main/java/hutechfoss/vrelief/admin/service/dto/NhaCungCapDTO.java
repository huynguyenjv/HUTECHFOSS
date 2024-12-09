package hutechfoss.vrelief.admin.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A DTO for the {@link hutechfoss.vrelief.admin.domain.NhaCungCap} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class NhaCungCapDTO implements Serializable {

    private Long id;

    @NotNull
    private String tenNhaCungCap;

    private String soDienThoai;

    private String email;

    private String diaChi;

    private ZonedDateTime createdAt;

    private ZonedDateTime updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTenNhaCungCap() {
        return tenNhaCungCap;
    }

    public void setTenNhaCungCap(String tenNhaCungCap) {
        this.tenNhaCungCap = tenNhaCungCap;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
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
        if (!(o instanceof NhaCungCapDTO)) {
            return false;
        }

        NhaCungCapDTO nhaCungCapDTO = (NhaCungCapDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, nhaCungCapDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "NhaCungCapDTO{" +
            "id=" + getId() +
            ", tenNhaCungCap='" + getTenNhaCungCap() + "'" +
            ", soDienThoai='" + getSoDienThoai() + "'" +
            ", email='" + getEmail() + "'" +
            ", diaChi='" + getDiaChi() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            "}";
    }
}
