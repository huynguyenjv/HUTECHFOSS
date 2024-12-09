package hutechfoss.vrelief.admin.service.mapper;

import hutechfoss.vrelief.admin.domain.ChiTietDieuPhoi;
import hutechfoss.vrelief.admin.service.dto.ChiTietDieuPhoiDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ChiTietDieuPhoi} and its DTO {@link ChiTietDieuPhoiDTO}.
 */
@Mapper(componentModel = "spring")
public interface ChiTietDieuPhoiMapper extends EntityMapper<ChiTietDieuPhoiDTO, ChiTietDieuPhoi> {}
