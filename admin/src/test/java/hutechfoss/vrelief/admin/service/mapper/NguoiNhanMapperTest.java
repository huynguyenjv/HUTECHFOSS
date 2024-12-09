package hutechfoss.vrelief.admin.service.mapper;

import static hutechfoss.vrelief.admin.domain.NguoiNhanAsserts.*;
import static hutechfoss.vrelief.admin.domain.NguoiNhanTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class NguoiNhanMapperTest {

    private NguoiNhanMapper nguoiNhanMapper;

    @BeforeEach
    void setUp() {
        nguoiNhanMapper = new NguoiNhanMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getNguoiNhanSample1();
        var actual = nguoiNhanMapper.toEntity(nguoiNhanMapper.toDto(expected));
        assertNguoiNhanAllPropertiesEquals(expected, actual);
    }
}
