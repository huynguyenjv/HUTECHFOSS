package hutechfoss.vrelief.admin.service.mapper;

import hutechfoss.vrelief.admin.domain.TinhNguyenVien;
import hutechfoss.vrelief.admin.service.dto.TinhNguyenVienDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TinhNguyenVien} and its DTO {@link TinhNguyenVienDTO}.
 */
@Mapper(componentModel = "spring")
public interface TinhNguyenVienMapper extends EntityMapper<TinhNguyenVienDTO, TinhNguyenVien> {}
