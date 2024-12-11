package hutechfoss.vrelief.admin.repository;

import hutechfoss.vrelief.admin.domain.PhieuDieuPhoi;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the PhieuDieuPhoi entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PhieuDieuPhoiRepository extends JpaRepository<PhieuDieuPhoi, Long> {}
