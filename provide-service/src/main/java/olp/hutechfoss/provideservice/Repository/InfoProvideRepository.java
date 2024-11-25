package olp.hutechfoss.provideservice.Repository;

import olp.hutechfoss.provideservice.entity.InfoProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfoProvideRepository extends JpaRepository<InfoProvider,Long> {

}
