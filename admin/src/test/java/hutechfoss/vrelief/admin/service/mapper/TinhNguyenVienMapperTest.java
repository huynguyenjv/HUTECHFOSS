package hutechfoss.vrelief.admin.service.mapper;

import static hutechfoss.vrelief.admin.domain.TinhNguyenVienAsserts.*;
import static hutechfoss.vrelief.admin.domain.TinhNguyenVienTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TinhNguyenVienMapperTest {

    private TinhNguyenVienMapper tinhNguyenVienMapper;

    @BeforeEach
    void setUp() {
        tinhNguyenVienMapper = new TinhNguyenVienMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getTinhNguyenVienSample1();
        var actual = tinhNguyenVienMapper.toEntity(tinhNguyenVienMapper.toDto(expected));
        assertTinhNguyenVienAllPropertiesEquals(expected, actual);
    }
}
