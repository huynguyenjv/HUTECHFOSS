package hutechfoss.vrelief.admin.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A DTO for the {@link hutechfoss.vrelief.admin.domain.NguoiNhan} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class NguoiNhanDTO implements Serializable {

    private Long id;

    @NotNull
    private String tenNguoiNhan;

    private String soDienThoai;

    private String email;

    private String diaChi;

    private Integer vungId;

    private Integer soNguoiNhan;

    private ZonedDateTime createdAt;

    private ZonedDateTime updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTenNguoiNhan() {
        return tenNguoiNhan;
    }

    public void setTenNguoiNhan(String tenNguoiNhan) {
        this.tenNguoiNhan = tenNguoiNhan;
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

    public Integer getVungId() {
        return vungId;
    }

    public void setVungId(Integer vungId) {
        this.vungId = vungId;
    }

    public Integer getSoNguoiNhan() {
        return soNguoiNhan;
    }

    public void setSoNguoiNhan(Integer soNguoiNhan) {
        this.soNguoiNhan = soNguoiNhan;
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
        if (!(o instanceof NguoiNhanDTO)) {
            return false;
        }

        NguoiNhanDTO nguoiNhanDTO = (NguoiNhanDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, nguoiNhanDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "NguoiNhanDTO{" +
            "id=" + getId() +
            ", tenNguoiNhan='" + getTenNguoiNhan() + "'" +
            ", soDienThoai='" + getSoDienThoai() + "'" +
            ", email='" + getEmail() + "'" +
            ", diaChi='" + getDiaChi() + "'" +
            ", vungId=" + getVungId() +
            ", soNguoiNhan=" + getSoNguoiNhan() +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            "}";
    }
}
