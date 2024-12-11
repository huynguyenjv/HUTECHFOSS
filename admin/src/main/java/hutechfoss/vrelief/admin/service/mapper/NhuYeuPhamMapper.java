package hutechfoss.vrelief.admin.service.mapper;

import hutechfoss.vrelief.admin.domain.NhuYeuPham;
import hutechfoss.vrelief.admin.service.dto.NhuYeuPhamDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link NhuYeuPham} and its DTO {@link NhuYeuPhamDTO}.
 */
@Mapper(componentModel = "spring")
public interface NhuYeuPhamMapper extends EntityMapper<NhuYeuPhamDTO, NhuYeuPham> {}
