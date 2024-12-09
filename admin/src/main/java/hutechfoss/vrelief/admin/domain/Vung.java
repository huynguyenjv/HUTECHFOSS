package hutechfoss.vrelief.admin.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

/**
 * A Vung.
 */
@Entity
@Table(name = "vung")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Vung implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "ten_vung", nullable = false, unique = true)
    private String tenVung;

    @Column(name = "lat", precision = 21, scale = 2)
    private BigDecimal lat;

    @Column(name = "lng", precision = 21, scale = 2)
    private BigDecimal lng;

    @Column(name = "loai_thien_tai_id")
    private Integer loaiThienTaiId;

    @Column(name = "so_dan")
    private Integer soDan;

    @Column(name = "created_at")
    private ZonedDateTime createdAt;

    @Column(name = "updated_at")
    private ZonedDateTime updatedAt;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Vung id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTenVung() {
        return this.tenVung;
    }

    public Vung tenVung(String tenVung) {
        this.setTenVung(tenVung);
        return this;
    }

    public void setTenVung(String tenVung) {
        this.tenVung = tenVung;
    }

    public BigDecimal getLat() {
        return this.lat;
    }

    public Vung lat(BigDecimal lat) {
        this.setLat(lat);
        return this;
    }

    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }

    public BigDecimal getLng() {
        return this.lng;
    }

    public Vung lng(BigDecimal lng) {
        this.setLng(lng);
        return this;
    }

    public void setLng(BigDecimal lng) {
        this.lng = lng;
    }

    public Integer getLoaiThienTaiId() {
        return this.loaiThienTaiId;
    }

    public Vung loaiThienTaiId(Integer loaiThienTaiId) {
        this.setLoaiThienTaiId(loaiThienTaiId);
        return this;
    }

    public void setLoaiThienTaiId(Integer loaiThienTaiId) {
        this.loaiThienTaiId = loaiThienTaiId;
    }

    public Integer getSoDan() {
        return this.soDan;
    }

    public Vung soDan(Integer soDan) {
        this.setSoDan(soDan);
        return this;
    }

    public void setSoDan(Integer soDan) {
        this.soDan = soDan;
    }

    public ZonedDateTime getCreatedAt() {
        return this.createdAt;
    }

    public Vung createdAt(ZonedDateTime createdAt) {
        this.setCreatedAt(createdAt);
        return this;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public ZonedDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    public Vung updatedAt(ZonedDateTime updatedAt) {
        this.setUpdatedAt(updatedAt);
        return this;
    }

    public void setUpdatedAt(ZonedDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Vung)) {
            return false;
        }
        return getId() != null && getId().equals(((Vung) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Vung{" +
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
