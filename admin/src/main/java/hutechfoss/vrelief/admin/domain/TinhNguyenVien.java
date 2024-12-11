package hutechfoss.vrelief.admin.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * A TinhNguyenVien.
 */
@Entity
@Table(name = "tinh_nguyen_vien")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TinhNguyenVien implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "ten_tinh_nguyen_vien", nullable = false, unique = true)
    private String tenTinhNguyenVien;

    @Column(name = "so_dien_thoai")
    private String soDienThoai;

    @Column(name = "dia_chi")
    private String diaChi;

    @Column(name = "trang_thai_id")
    private Integer trangThaiId;

    @Column(name = "created_at")
    private ZonedDateTime createdAt;

    @Column(name = "updated_at")
    private ZonedDateTime updatedAt;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public TinhNguyenVien id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTenTinhNguyenVien() {
        return this.tenTinhNguyenVien;
    }

    public TinhNguyenVien tenTinhNguyenVien(String tenTinhNguyenVien) {
        this.setTenTinhNguyenVien(tenTinhNguyenVien);
        return this;
    }

    public void setTenTinhNguyenVien(String tenTinhNguyenVien) {
        this.tenTinhNguyenVien = tenTinhNguyenVien;
    }

    public String getSoDienThoai() {
        return this.soDienThoai;
    }

    public TinhNguyenVien soDienThoai(String soDienThoai) {
        this.setSoDienThoai(soDienThoai);
        return this;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getDiaChi() {
        return this.diaChi;
    }

    public TinhNguyenVien diaChi(String diaChi) {
        this.setDiaChi(diaChi);
        return this;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public Integer getTrangThaiId() {
        return this.trangThaiId;
    }

    public TinhNguyenVien trangThaiId(Integer trangThaiId) {
        this.setTrangThaiId(trangThaiId);
        return this;
    }

    public void setTrangThaiId(Integer trangThaiId) {
        this.trangThaiId = trangThaiId;
    }

    public ZonedDateTime getCreatedAt() {
        return this.createdAt;
    }

    public TinhNguyenVien createdAt(ZonedDateTime createdAt) {
        this.setCreatedAt(createdAt);
        return this;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public ZonedDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    public TinhNguyenVien updatedAt(ZonedDateTime updatedAt) {
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
        if (!(o instanceof TinhNguyenVien)) {
            return false;
        }
        return getId() != null && getId().equals(((TinhNguyenVien) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TinhNguyenVien{" +
            "id=" + getId() +
            ", tenTinhNguyenVien='" + getTenTinhNguyenVien() + "'" +
            ", soDienThoai='" + getSoDienThoai() + "'" +
            ", diaChi='" + getDiaChi() + "'" +
            ", trangThaiId=" + getTrangThaiId() +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            "}";
    }
}
