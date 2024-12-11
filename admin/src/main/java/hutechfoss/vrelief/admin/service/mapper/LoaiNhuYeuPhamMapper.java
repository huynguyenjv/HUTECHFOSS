package hutechfoss.vrelief.admin.service.mapper;

import hutechfoss.vrelief.admin.domain.LoaiNhuYeuPham;
import hutechfoss.vrelief.admin.service.dto.LoaiNhuYeuPhamDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link LoaiNhuYeuPham} and its DTO {@link LoaiNhuYeuPhamDTO}.
 */
@Mapper(componentModel = "spring")
public interface LoaiNhuYeuPhamMapper extends EntityMapper<LoaiNhuYeuPhamDTO, LoaiNhuYeuPham> {}
