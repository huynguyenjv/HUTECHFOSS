package hutechfoss.vrelief.admin.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A DTO for the {@link hutechfoss.vrelief.admin.domain.LoaiThienTai} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class LoaiThienTaiDTO implements Serializable {

    private Long id;

    @NotNull
    private String tenLoaiThienTai;

    private String moTa;

    private ZonedDateTime createdAt;

    private ZonedDateTime updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTenLoaiThienTai() {
        return tenLoaiThienTai;
    }

    public void setTenLoaiThienTai(String tenLoaiThienTai) {
        this.tenLoaiThienTai = tenLoaiThienTai;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
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
        if (!(o instanceof LoaiThienTaiDTO)) {
            return false;
        }

        LoaiThienTaiDTO loaiThienTaiDTO = (LoaiThienTaiDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, loaiThienTaiDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LoaiThienTaiDTO{" +
            "id=" + getId() +
            ", tenLoaiThienTai='" + getTenLoaiThienTai() + "'" +
            ", moTa='" + getMoTa() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            "}";
    }
}
