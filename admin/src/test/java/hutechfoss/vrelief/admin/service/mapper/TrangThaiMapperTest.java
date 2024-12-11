package hutechfoss.vrelief.admin.service.mapper;

import static hutechfoss.vrelief.admin.domain.TrangThaiAsserts.*;
import static hutechfoss.vrelief.admin.domain.TrangThaiTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TrangThaiMapperTest {

    private TrangThaiMapper trangThaiMapper;

    @BeforeEach
    void setUp() {
        trangThaiMapper = new TrangThaiMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getTrangThaiSample1();
        var actual = trangThaiMapper.toEntity(trangThaiMapper.toDto(expected));
        assertTrangThaiAllPropertiesEquals(expected, actual);
    }
}
