package hutechfoss.vrelief.admin.service.mapper;

import static hutechfoss.vrelief.admin.domain.NhaCungCapAsserts.*;
import static hutechfoss.vrelief.admin.domain.NhaCungCapTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class NhaCungCapMapperTest {

    private NhaCungCapMapper nhaCungCapMapper;

    @BeforeEach
    void setUp() {
        nhaCungCapMapper = new NhaCungCapMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getNhaCungCapSample1();
        var actual = nhaCungCapMapper.toEntity(nhaCungCapMapper.toDto(expected));
        assertNhaCungCapAllPropertiesEquals(expected, actual);
    }
}
