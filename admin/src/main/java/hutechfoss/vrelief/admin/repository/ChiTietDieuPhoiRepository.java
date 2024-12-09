package hutechfoss.vrelief.admin.repository;

import hutechfoss.vrelief.admin.domain.ChiTietDieuPhoi;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ChiTietDieuPhoi entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ChiTietDieuPhoiRepository extends JpaRepository<ChiTietDieuPhoi, Long> {}
