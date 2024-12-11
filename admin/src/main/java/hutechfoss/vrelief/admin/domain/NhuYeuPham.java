package hutechfoss.vrelief.admin.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * A NhuYeuPham.
 */
@Entity
@Table(name = "nhu_yeu_pham")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class NhuYeuPham implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "ten_nhu_yeu_pham", nullable = false)
    private String tenNhuYeuPham;

    @Column(name = "don_vi_tinh")
    private String donViTinh;

    @Column(name = "loai_nhu_yeu_pham_id")
    private Integer loaiNhuYeuPhamId;

    @Column(name = "muc_canh_bao")
    private Integer mucCanhBao;

    @Column(name = "created_at")
    private ZonedDateTime createdAt;

    @Column(name = "updated_at")
    private ZonedDateTime updatedAt;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public NhuYeuPham id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTenNhuYeuPham() {
        return this.tenNhuYeuPham;
    }

    public NhuYeuPham tenNhuYeuPham(String tenNhuYeuPham) {
        this.setTenNhuYeuPham(tenNhuYeuPham);
        return this;
    }

    public void setTenNhuYeuPham(String tenNhuYeuPham) {
        this.tenNhuYeuPham = tenNhuYeuPham;
    }

    public String getDonViTinh() {
        return this.donViTinh;
    }

    public NhuYeuPham donViTinh(String donViTinh) {
        this.setDonViTinh(donViTinh);
        return this;
    }

    public void setDonViTinh(String donViTinh) {
        this.donViTinh = donViTinh;
    }

    public Integer getLoaiNhuYeuPhamId() {
        return this.loaiNhuYeuPhamId;
    }

    public NhuYeuPham loaiNhuYeuPhamId(Integer loaiNhuYeuPhamId) {
        this.setLoaiNhuYeuPhamId(loaiNhuYeuPhamId);
        return this;
    }

    public void setLoaiNhuYeuPhamId(Integer loaiNhuYeuPhamId) {
        this.loaiNhuYeuPhamId = loaiNhuYeuPhamId;
    }

    public Integer getMucCanhBao() {
        return this.mucCanhBao;
    }

    public NhuYeuPham mucCanhBao(Integer mucCanhBao) {
        this.setMucCanhBao(mucCanhBao);
        return this;
    }

    public void setMucCanhBao(Integer mucCanhBao) {
        this.mucCanhBao = mucCanhBao;
    }

    public ZonedDateTime getCreatedAt() {
        return this.createdAt;
    }

    public NhuYeuPham createdAt(ZonedDateTime createdAt) {
        this.setCreatedAt(createdAt);
        return this;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public ZonedDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    public NhuYeuPham updatedAt(ZonedDateTime updatedAt) {
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
        if (!(o instanceof NhuYeuPham)) {
            return false;
        }
        return getId() != null && getId().equals(((NhuYeuPham) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "NhuYeuPham{" +
            "id=" + getId() +
            ", tenNhuYeuPham='" + getTenNhuYeuPham() + "'" +
            ", donViTinh='" + getDonViTinh() + "'" +
            ", loaiNhuYeuPhamId=" + getLoaiNhuYeuPhamId() +
            ", mucCanhBao=" + getMucCanhBao() +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            "}";
    }
}
