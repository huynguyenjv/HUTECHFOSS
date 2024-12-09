package hutechfoss.vrelief.admin.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import hutechfoss.vrelief.admin.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class VungDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(VungDTO.class);
        VungDTO vungDTO1 = new VungDTO();
        vungDTO1.setId(1L);
        VungDTO vungDTO2 = new VungDTO();
        assertThat(vungDTO1).isNotEqualTo(vungDTO2);
        vungDTO2.setId(vungDTO1.getId());
        assertThat(vungDTO1).isEqualTo(vungDTO2);
        vungDTO2.setId(2L);
        assertThat(vungDTO1).isNotEqualTo(vungDTO2);
        vungDTO1.setId(null);
        assertThat(vungDTO1).isNotEqualTo(vungDTO2);
    }
}
