package hutechfoss.vrelief.admin.service.mapper;

import hutechfoss.vrelief.admin.domain.NguoiNhan;
import hutechfoss.vrelief.admin.service.dto.NguoiNhanDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link NguoiNhan} and its DTO {@link NguoiNhanDTO}.
 */
@Mapper(componentModel = "spring")
public interface NguoiNhanMapper extends EntityMapper<NguoiNhanDTO, NguoiNhan> {}
