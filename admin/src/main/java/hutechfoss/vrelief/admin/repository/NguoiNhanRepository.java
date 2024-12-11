package hutechfoss.vrelief.admin.repository;

import hutechfoss.vrelief.admin.domain.NguoiNhan;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the NguoiNhan entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NguoiNhanRepository extends JpaRepository<NguoiNhan, Long> {}
