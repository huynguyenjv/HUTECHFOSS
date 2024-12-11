package hutechfoss.vrelief.admin.repository;

import hutechfoss.vrelief.admin.domain.TinhNguyenVien;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the TinhNguyenVien entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TinhNguyenVienRepository extends JpaRepository<TinhNguyenVien, Long> {}
