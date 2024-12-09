package hutechfoss.vrelief.admin.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * A ChiTietDieuPhoi.
 */
@Entity
@Table(name = "chi_tiet_dieu_phoi")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ChiTietDieuPhoi implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "phieu_dieu_phoi_id")
    private Integer phieuDieuPhoiId;

    @Column(name = "nhu_yeu_pham_id")
    private Integer nhuYeuPhamId;

    @Column(name = "so_luong")
    private Integer soLuong;

    @Column(name = "created_at")
    private ZonedDateTime createdAt;

    @Column(name = "updated_at")
    private ZonedDateTime updatedAt;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ChiTietDieuPhoi id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPhieuDieuPhoiId() {
        return this.phieuDieuPhoiId;
    }

    public ChiTietDieuPhoi phieuDieuPhoiId(Integer phieuDieuPhoiId) {
        this.setPhieuDieuPhoiId(phieuDieuPhoiId);
        return this;
    }

    public void setPhieuDieuPhoiId(Integer phieuDieuPhoiId) {
        this.phieuDieuPhoiId = phieuDieuPhoiId;
    }

    public Integer getNhuYeuPhamId() {
        return this.nhuYeuPhamId;
    }

    public ChiTietDieuPhoi nhuYeuPhamId(Integer nhuYeuPhamId) {
        this.setNhuYeuPhamId(nhuYeuPhamId);
        return this;
    }

    public void setNhuYeuPhamId(Integer nhuYeuPhamId) {
        this.nhuYeuPhamId = nhuYeuPhamId;
    }

    public Integer getSoLuong() {
        return this.soLuong;
    }

    public ChiTietDieuPhoi soLuong(Integer soLuong) {
        this.setSoLuong(soLuong);
        return this;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public ZonedDateTime getCreatedAt() {
        return this.createdAt;
    }

    public ChiTietDieuPhoi createdAt(ZonedDateTime createdAt) {
        this.setCreatedAt(createdAt);
        return this;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public ZonedDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    public ChiTietDieuPhoi updatedAt(ZonedDateTime updatedAt) {
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
        if (!(o instanceof ChiTietDieuPhoi)) {
            return false;
        }
        return getId() != null && getId().equals(((ChiTietDieuPhoi) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ChiTietDieuPhoi{" +
            "id=" + getId() +
            ", phieuDieuPhoiId=" + getPhieuDieuPhoiId() +
            ", nhuYeuPhamId=" + getNhuYeuPhamId() +
            ", soLuong=" + getSoLuong() +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            "}";
    }
}
