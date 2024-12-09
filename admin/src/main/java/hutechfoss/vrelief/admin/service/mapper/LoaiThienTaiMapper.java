package hutechfoss.vrelief.admin.service.mapper;

import hutechfoss.vrelief.admin.domain.LoaiThienTai;
import hutechfoss.vrelief.admin.service.dto.LoaiThienTaiDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link LoaiThienTai} and its DTO {@link LoaiThienTaiDTO}.
 */
@Mapper(componentModel = "spring")
public interface LoaiThienTaiMapper extends EntityMapper<LoaiThienTaiDTO, LoaiThienTai> {}
