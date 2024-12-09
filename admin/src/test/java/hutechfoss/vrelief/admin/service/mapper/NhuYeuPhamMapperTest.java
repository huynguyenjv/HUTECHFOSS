package hutechfoss.vrelief.admin.service.mapper;

import static hutechfoss.vrelief.admin.domain.NhuYeuPhamAsserts.*;
import static hutechfoss.vrelief.admin.domain.NhuYeuPhamTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class NhuYeuPhamMapperTest {

    private NhuYeuPhamMapper nhuYeuPhamMapper;

    @BeforeEach
    void setUp() {
        nhuYeuPhamMapper = new NhuYeuPhamMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getNhuYeuPhamSample1();
        var actual = nhuYeuPhamMapper.toEntity(nhuYeuPhamMapper.toDto(expected));
        assertNhuYeuPhamAllPropertiesEquals(expected, actual);
    }
}
