package hutechfoss.vrelief.admin.repository;

import hutechfoss.vrelief.admin.domain.Vung;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Vung entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VungRepository extends JpaRepository<Vung, Long> {}
