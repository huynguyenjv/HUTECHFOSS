package hutechfoss.vrelief.admin.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * A PhieuDieuPhoi.
 */
@Entity
@Table(name = "phieu_dieu_phoi")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class PhieuDieuPhoi implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nha_cung_cap_id")
    private Integer nhaCungCapId;

    @Column(name = "nguoi_nhan_id")
    private Integer nguoiNhanId;

    @Column(name = "tinh_nguyen_vien_id")
    private Integer tinhNguyenVienId;

    @Column(name = "trang_thai_id")
    private Integer trangThaiId;

    @Column(name = "thoi_gian_den")
    private ZonedDateTime thoiGianDen;

    @Column(name = "thoi_gian_cho")
    private ZonedDateTime thoiGianCho;

    @Column(name = "created_at")
    private ZonedDateTime createdAt;

    @Column(name = "updated_at")
    private ZonedDateTime updatedAt;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public PhieuDieuPhoi id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNhaCungCapId() {
        return this.nhaCungCapId;
    }

    public PhieuDieuPhoi nhaCungCapId(Integer nhaCungCapId) {
        this.setNhaCungCapId(nhaCungCapId);
        return this;
    }

    public void setNhaCungCapId(Integer nhaCungCapId) {
        this.nhaCungCapId = nhaCungCapId;
    }

    public Integer getNguoiNhanId() {
        return this.nguoiNhanId;
    }

    public PhieuDieuPhoi nguoiNhanId(Integer nguoiNhanId) {
        this.setNguoiNhanId(nguoiNhanId);
        return this;
    }

    public void setNguoiNhanId(Integer nguoiNhanId) {
        this.nguoiNhanId = nguoiNhanId;
    }

    public Integer getTinhNguyenVienId() {
        return this.tinhNguyenVienId;
    }

    public PhieuDieuPhoi tinhNguyenVienId(Integer tinhNguyenVienId) {
        this.setTinhNguyenVienId(tinhNguyenVienId);
        return this;
    }

    public void setTinhNguyenVienId(Integer tinhNguyenVienId) {
        this.tinhNguyenVienId = tinhNguyenVienId;
    }

    public Integer getTrangThaiId() {
        return this.trangThaiId;
    }

    public PhieuDieuPhoi trangThaiId(Integer trangThaiId) {
        this.setTrangThaiId(trangThaiId);
        return this;
    }

    public void setTrangThaiId(Integer trangThaiId) {
        this.trangThaiId = trangThaiId;
    }

    public ZonedDateTime getThoiGianDen() {
        return this.thoiGianDen;
    }

    public PhieuDieuPhoi thoiGianDen(ZonedDateTime thoiGianDen) {
        this.setThoiGianDen(thoiGianDen);
        return this;
    }

    public void setThoiGianDen(ZonedDateTime thoiGianDen) {
        this.thoiGianDen = thoiGianDen;
    }

    public ZonedDateTime getThoiGianCho() {
        return this.thoiGianCho;
    }

    public PhieuDieuPhoi thoiGianCho(ZonedDateTime thoiGianCho) {
        this.setThoiGianCho(thoiGianCho);
        return this;
    }

    public void setThoiGianCho(ZonedDateTime thoiGianCho) {
        this.thoiGianCho = thoiGianCho;
    }

    public ZonedDateTime getCreatedAt() {
        return this.createdAt;
    }

    public PhieuDieuPhoi createdAt(ZonedDateTime createdAt) {
        this.setCreatedAt(createdAt);
        return this;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public ZonedDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    public PhieuDieuPhoi updatedAt(ZonedDateTime updatedAt) {
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
        if (!(o instanceof PhieuDieuPhoi)) {
            return false;
        }
        return getId() != null && getId().equals(((PhieuDieuPhoi) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PhieuDieuPhoi{" +
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
