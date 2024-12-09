package hutechfoss.vrelief.admin.service.mapper;

import hutechfoss.vrelief.admin.domain.LoaiNguoiNhan;
import hutechfoss.vrelief.admin.service.dto.LoaiNguoiNhanDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link LoaiNguoiNhan} and its DTO {@link LoaiNguoiNhanDTO}.
 */
@Mapper(componentModel = "spring")
public interface LoaiNguoiNhanMapper extends EntityMapper<LoaiNguoiNhanDTO, LoaiNguoiNhan> {}
