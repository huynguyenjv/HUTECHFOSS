package hutechfoss.vrelief.admin.service.mapper;

import static hutechfoss.vrelief.admin.domain.LoaiNguoiNhanAsserts.*;
import static hutechfoss.vrelief.admin.domain.LoaiNguoiNhanTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LoaiNguoiNhanMapperTest {

    private LoaiNguoiNhanMapper loaiNguoiNhanMapper;

    @BeforeEach
    void setUp() {
        loaiNguoiNhanMapper = new LoaiNguoiNhanMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getLoaiNguoiNhanSample1();
        var actual = loaiNguoiNhanMapper.toEntity(loaiNguoiNhanMapper.toDto(expected));
        assertLoaiNguoiNhanAllPropertiesEquals(expected, actual);
    }
}
