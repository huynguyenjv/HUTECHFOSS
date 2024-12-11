package hutechfoss.vrelief.admin.repository;

import hutechfoss.vrelief.admin.domain.LoaiNhuYeuPham;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the LoaiNhuYeuPham entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LoaiNhuYeuPhamRepository extends JpaRepository<LoaiNhuYeuPham, Long> {}
