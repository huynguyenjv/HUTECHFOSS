package hutechfoss.vrelief.admin.service.mapper;

import hutechfoss.vrelief.admin.domain.Vung;
import hutechfoss.vrelief.admin.service.dto.VungDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Vung} and its DTO {@link VungDTO}.
 */
@Mapper(componentModel = "spring")
public interface VungMapper extends EntityMapper<VungDTO, Vung> {}
