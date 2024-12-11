package hutechfoss.vrelief.admin.service.mapper;

import static hutechfoss.vrelief.admin.domain.LoaiThienTaiAsserts.*;
import static hutechfoss.vrelief.admin.domain.LoaiThienTaiTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LoaiThienTaiMapperTest {

    private LoaiThienTaiMapper loaiThienTaiMapper;

    @BeforeEach
    void setUp() {
        loaiThienTaiMapper = new LoaiThienTaiMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getLoaiThienTaiSample1();
        var actual = loaiThienTaiMapper.toEntity(loaiThienTaiMapper.toDto(expected));
        assertLoaiThienTaiAllPropertiesEquals(expected, actual);
    }
}
