package hutechfoss.vrelief.admin.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import hutechfoss.vrelief.admin.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class NhaCungCapDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(NhaCungCapDTO.class);
        NhaCungCapDTO nhaCungCapDTO1 = new NhaCungCapDTO();
        nhaCungCapDTO1.setId(1L);
        NhaCungCapDTO nhaCungCapDTO2 = new NhaCungCapDTO();
        assertThat(nhaCungCapDTO1).isNotEqualTo(nhaCungCapDTO2);
        nhaCungCapDTO2.setId(nhaCungCapDTO1.getId());
        assertThat(nhaCungCapDTO1).isEqualTo(nhaCungCapDTO2);
        nhaCungCapDTO2.setId(2L);
        assertThat(nhaCungCapDTO1).isNotEqualTo(nhaCungCapDTO2);
        nhaCungCapDTO1.setId(null);
        assertThat(nhaCungCapDTO1).isNotEqualTo(nhaCungCapDTO2);
    }
}
