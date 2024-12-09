package hutechfoss.vrelief.admin.repository;

import hutechfoss.vrelief.admin.domain.LoaiNguoiNhan;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the LoaiNguoiNhan entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LoaiNguoiNhanRepository extends JpaRepository<LoaiNguoiNhan, Long> {}
