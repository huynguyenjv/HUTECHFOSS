package hutechfoss.vrelief.admin.service.mapper;

import static hutechfoss.vrelief.admin.domain.LoaiNhuYeuPhamAsserts.*;
import static hutechfoss.vrelief.admin.domain.LoaiNhuYeuPhamTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LoaiNhuYeuPhamMapperTest {

    private LoaiNhuYeuPhamMapper loaiNhuYeuPhamMapper;

    @BeforeEach
    void setUp() {
        loaiNhuYeuPhamMapper = new LoaiNhuYeuPhamMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getLoaiNhuYeuPhamSample1();
        var actual = loaiNhuYeuPhamMapper.toEntity(loaiNhuYeuPhamMapper.toDto(expected));
        assertLoaiNhuYeuPhamAllPropertiesEquals(expected, actual);
    }
}
