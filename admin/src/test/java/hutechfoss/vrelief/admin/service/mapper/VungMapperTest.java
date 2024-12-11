package hutechfoss.vrelief.admin.service.mapper;

import static hutechfoss.vrelief.admin.domain.VungAsserts.*;
import static hutechfoss.vrelief.admin.domain.VungTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class VungMapperTest {

    private VungMapper vungMapper;

    @BeforeEach
    void setUp() {
        vungMapper = new VungMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getVungSample1();
        var actual = vungMapper.toEntity(vungMapper.toDto(expected));
        assertVungAllPropertiesEquals(expected, actual);
    }
}
