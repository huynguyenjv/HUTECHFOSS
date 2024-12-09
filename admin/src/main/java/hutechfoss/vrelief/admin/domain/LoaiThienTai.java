package hutechfoss.vrelief.admin.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * A LoaiThienTai.
 */
@Entity
@Table(name = "loai_thien_tai")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class LoaiThienTai implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "ten_loai_thien_tai", nullable = false, unique = true)
    private String tenLoaiThienTai;

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

    public LoaiThienTai id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTenLoaiThienTai() {
        return this.tenLoaiThienTai;
    }

    public LoaiThienTai tenLoaiThienTai(String tenLoaiThienTai) {
        this.setTenLoaiThienTai(tenLoaiThienTai);
        return this;
    }

    public void setTenLoaiThienTai(String tenLoaiThienTai) {
        this.tenLoaiThienTai = tenLoaiThienTai;
    }

    public String getMoTa() {
        return this.moTa;
    }

    public LoaiThienTai moTa(String moTa) {
        this.setMoTa(moTa);
        return this;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public ZonedDateTime getCreatedAt() {
        return this.createdAt;
    }

    public LoaiThienTai createdAt(ZonedDateTime createdAt) {
        this.setCreatedAt(createdAt);
        return this;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public ZonedDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    public LoaiThienTai updatedAt(ZonedDateTime updatedAt) {
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
        if (!(o instanceof LoaiThienTai)) {
            return false;
        }
        return getId() != null && getId().equals(((LoaiThienTai) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LoaiThienTai{" +
            "id=" + getId() +
            ", tenLoaiThienTai='" + getTenLoaiThienTai() + "'" +
            ", moTa='" + getMoTa() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            "}";
    }
}
