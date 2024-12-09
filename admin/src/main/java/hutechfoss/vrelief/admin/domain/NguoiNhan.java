package hutechfoss.vrelief.admin.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * A NguoiNhan.
 */
@Entity
@Table(name = "nguoi_nhan")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class NguoiNhan implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "ten_nguoi_nhan", nullable = false)
    private String tenNguoiNhan;

    @Column(name = "so_dien_thoai")
    private String soDienThoai;

    @Column(name = "email")
    private String email;

    @Column(name = "dia_chi")
    private String diaChi;

    @Column(name = "vung_id")
    private Integer vungId;

    @Column(name = "so_nguoi_nhan")
    private Integer soNguoiNhan;

    @Column(name = "created_at")
    private ZonedDateTime createdAt;

    @Column(name = "updated_at")
    private ZonedDateTime updatedAt;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public NguoiNhan id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTenNguoiNhan() {
        return this.tenNguoiNhan;
    }

    public NguoiNhan tenNguoiNhan(String tenNguoiNhan) {
        this.setTenNguoiNhan(tenNguoiNhan);
        return this;
    }

    public void setTenNguoiNhan(String tenNguoiNhan) {
        this.tenNguoiNhan = tenNguoiNhan;
    }

    public String getSoDienThoai() {
        return this.soDienThoai;
    }

    public NguoiNhan soDienThoai(String soDienThoai) {
        this.setSoDienThoai(soDienThoai);
        return this;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getEmail() {
        return this.email;
    }

    public NguoiNhan email(String email) {
        this.setEmail(email);
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiaChi() {
        return this.diaChi;
    }

    public NguoiNhan diaChi(String diaChi) {
        this.setDiaChi(diaChi);
        return this;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public Integer getVungId() {
        return this.vungId;
    }

    public NguoiNhan vungId(Integer vungId) {
        this.setVungId(vungId);
        return this;
    }

    public void setVungId(Integer vungId) {
        this.vungId = vungId;
    }

    public Integer getSoNguoiNhan() {
        return this.soNguoiNhan;
    }

    public NguoiNhan soNguoiNhan(Integer soNguoiNhan) {
        this.setSoNguoiNhan(soNguoiNhan);
        return this;
    }

    public void setSoNguoiNhan(Integer soNguoiNhan) {
        this.soNguoiNhan = soNguoiNhan;
    }

    public ZonedDateTime getCreatedAt() {
        return this.createdAt;
    }

    public NguoiNhan createdAt(ZonedDateTime createdAt) {
        this.setCreatedAt(createdAt);
        return this;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public ZonedDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    public NguoiNhan updatedAt(ZonedDateTime updatedAt) {
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
        if (!(o instanceof NguoiNhan)) {
            return false;
        }
        return getId() != null && getId().equals(((NguoiNhan) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "NguoiNhan{" +
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
