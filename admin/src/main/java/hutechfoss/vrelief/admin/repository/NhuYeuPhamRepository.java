package hutechfoss.vrelief.admin.repository;

import hutechfoss.vrelief.admin.domain.NhuYeuPham;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the NhuYeuPham entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NhuYeuPhamRepository extends JpaRepository<NhuYeuPham, Long> {}
