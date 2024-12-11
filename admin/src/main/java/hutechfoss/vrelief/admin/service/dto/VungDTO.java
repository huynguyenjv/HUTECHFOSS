package hutechfoss.vrelief.admin.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A DTO for the {@link hutechfoss.vrelief.admin.domain.Vung} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class VungDTO implements Serializable {

    private Long id;

    @NotNull
    private String tenVung;

    private BigDecimal lat;

    private BigDecimal lng;

    private Integer loaiThienTaiId;

    private Integer soDan;

    private ZonedDateTime createdAt;

    private ZonedDateTime updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTenVung() {
        return tenVung;
    }

    public void setTenVung(String tenVung) {
        this.tenVung = tenVung;
    }

    public BigDecimal getLat() {
        return lat;
    }

    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }

    public BigDecimal getLng() {
        return lng;
    }

    public void setLng(BigDecimal lng) {
        this.lng = lng;
    }

    public Integer getLoaiThienTaiId() {
        return loaiThienTaiId;
    }

    public void setLoaiThienTaiId(Integer loaiThienTaiId) {
        this.loaiThienTaiId = loaiThienTaiId;
    }

    public Integer getSoDan() {
        return soDan;
    }

    public void setSoDan(Integer soDan) {
        this.soDan = soDan;
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
        if (!(o instanceof VungDTO)) {
            return false;
        }

        VungDTO vungDTO = (VungDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, vungDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "VungDTO{" +
            "id=" + getId() +
            ", tenVung='" + getTenVung() + "'" +
            ", lat=" + getLat() +
            ", lng=" + getLng() +
            ", loaiThienTaiId=" + getLoaiThienTaiId() +
            ", soDan=" + getSoDan() +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            "}";
    }
}
