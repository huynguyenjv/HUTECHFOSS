package hutechfoss.vrelief.admin.service.mapper;

import hutechfoss.vrelief.admin.domain.NhaCungCap;
import hutechfoss.vrelief.admin.service.dto.NhaCungCapDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link NhaCungCap} and its DTO {@link NhaCungCapDTO}.
 */
@Mapper(componentModel = "spring")
public interface NhaCungCapMapper extends EntityMapper<NhaCungCapDTO, NhaCungCap> {}
