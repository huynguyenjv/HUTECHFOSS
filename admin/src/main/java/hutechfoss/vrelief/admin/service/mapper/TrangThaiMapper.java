package hutechfoss.vrelief.admin.service.mapper;

import hutechfoss.vrelief.admin.domain.TrangThai;
import hutechfoss.vrelief.admin.service.dto.TrangThaiDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TrangThai} and its DTO {@link TrangThaiDTO}.
 */
@Mapper(componentModel = "spring")
public interface TrangThaiMapper extends EntityMapper<TrangThaiDTO, TrangThai> {}
