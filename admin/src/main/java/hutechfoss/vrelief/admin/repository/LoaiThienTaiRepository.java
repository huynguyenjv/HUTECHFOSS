package hutechfoss.vrelief.admin.repository;

import hutechfoss.vrelief.admin.domain.LoaiThienTai;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the LoaiThienTai entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LoaiThienTaiRepository extends JpaRepository<LoaiThienTai, Long> {}
