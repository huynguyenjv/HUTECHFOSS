package hutechfoss.vrelief.admin.service.mapper;

import static hutechfoss.vrelief.admin.domain.PhieuDieuPhoiAsserts.*;
import static hutechfoss.vrelief.admin.domain.PhieuDieuPhoiTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PhieuDieuPhoiMapperTest {

    private PhieuDieuPhoiMapper phieuDieuPhoiMapper;

    @BeforeEach
    void setUp() {
        phieuDieuPhoiMapper = new PhieuDieuPhoiMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getPhieuDieuPhoiSample1();
        var actual = phieuDieuPhoiMapper.toEntity(phieuDieuPhoiMapper.toDto(expected));
        assertPhieuDieuPhoiAllPropertiesEquals(expected, actual);
    }
}
