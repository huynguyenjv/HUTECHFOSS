package hutechfoss.vrelief.admin.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A DTO for the {@link hutechfoss.vrelief.admin.domain.LoaiNhuYeuPham} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class LoaiNhuYeuPhamDTO implements Serializable {

    private Long id;

    @NotNull
    private String tenLoai;

    private ZonedDateTime createdAt;

    private ZonedDateTime updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
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
        if (!(o instanceof LoaiNhuYeuPhamDTO)) {
            return false;
        }

        LoaiNhuYeuPhamDTO loaiNhuYeuPhamDTO = (LoaiNhuYeuPhamDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, loaiNhuYeuPhamDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LoaiNhuYeuPhamDTO{" +
            "id=" + getId() +
            ", tenLoai='" + getTenLoai() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            "}";
    }
}
