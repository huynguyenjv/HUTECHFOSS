package hutechfoss.vrelief.admin.repository;

import hutechfoss.vrelief.admin.domain.TrangThai;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the TrangThai entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TrangThaiRepository extends JpaRepository<TrangThai, Long> {}
