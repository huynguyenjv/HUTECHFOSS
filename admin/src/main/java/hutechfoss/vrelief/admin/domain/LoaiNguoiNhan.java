package hutechfoss.vrelief.admin.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * A LoaiNguoiNhan.
 */
@Entity
@Table(name = "loai_nguoi_nhan")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class LoaiNguoiNhan implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "ten_loai", nullable = false, unique = true)
    private String tenLoai;

    @Column(name = "mo_ta")
    private String moTa;

    @Column(name = "created_at")
    private ZonedDateTime createdAt;

    @Column(name = "updated_at")
    private ZonedDateTime updatedAt;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public LoaiNguoiNhan id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTenLoai() {
        return this.tenLoai;
    }

    public LoaiNguoiNhan tenLoai(String tenLoai) {
        this.setTenLoai(tenLoai);
        return this;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    public String getMoTa() {
        return this.moTa;
    }

    public LoaiNguoiNhan moTa(String moTa) {
        this.setMoTa(moTa);
        return this;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public ZonedDateTime getCreatedAt() {
        return this.createdAt;
    }

    public LoaiNguoiNhan createdAt(ZonedDateTime createdAt) {
        this.setCreatedAt(createdAt);
        return this;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public ZonedDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    public LoaiNguoiNhan updatedAt(ZonedDateTime updatedAt) {
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
        if (!(o instanceof LoaiNguoiNhan)) {
            return false;
        }
        return getId() != null && getId().equals(((LoaiNguoiNhan) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LoaiNguoiNhan{" +
            "id=" + getId() +
            ", tenLoai='" + getTenLoai() + "'" +
            ", moTa='" + getMoTa() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            "}";
    }
}
