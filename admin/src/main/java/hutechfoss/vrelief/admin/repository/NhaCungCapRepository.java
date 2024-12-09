package hutechfoss.vrelief.admin.repository;

import hutechfoss.vrelief.admin.domain.NhaCungCap;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the NhaCungCap entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NhaCungCapRepository extends JpaRepository<NhaCungCap, Long> {}
