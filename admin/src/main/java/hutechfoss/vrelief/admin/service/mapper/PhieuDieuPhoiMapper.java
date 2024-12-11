package hutechfoss.vrelief.admin.service.mapper;

import hutechfoss.vrelief.admin.domain.PhieuDieuPhoi;
import hutechfoss.vrelief.admin.service.dto.PhieuDieuPhoiDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link PhieuDieuPhoi} and its DTO {@link PhieuDieuPhoiDTO}.
 */
@Mapper(componentModel = "spring")
public interface PhieuDieuPhoiMapper extends EntityMapper<PhieuDieuPhoiDTO, PhieuDieuPhoi> {}
