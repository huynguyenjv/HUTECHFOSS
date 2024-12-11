package hutechfoss.vrelief.admin.service.mapper;

import static hutechfoss.vrelief.admin.domain.ChiTietDieuPhoiAsserts.*;
import static hutechfoss.vrelief.admin.domain.ChiTietDieuPhoiTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ChiTietDieuPhoiMapperTest {

    private ChiTietDieuPhoiMapper chiTietDieuPhoiMapper;

    @BeforeEach
    void setUp() {
        chiTietDieuPhoiMapper = new ChiTietDieuPhoiMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getChiTietDieuPhoiSample1();
        var actual = chiTietDieuPhoiMapper.toEntity(chiTietDieuPhoiMapper.toDto(expected));
        assertChiTietDieuPhoiAllPropertiesEquals(expected, actual);
    }
}
