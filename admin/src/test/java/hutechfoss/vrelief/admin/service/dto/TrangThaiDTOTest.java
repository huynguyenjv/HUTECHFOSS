package hutechfoss.vrelief.admin.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import hutechfoss.vrelief.admin.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TrangThaiDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TrangThaiDTO.class);
        TrangThaiDTO trangThaiDTO1 = new TrangThaiDTO();
        trangThaiDTO1.setId(1L);
        TrangThaiDTO trangThaiDTO2 = new TrangThaiDTO();
        assertThat(trangThaiDTO1).isNotEqualTo(trangThaiDTO2);
        trangThaiDTO2.setId(trangThaiDTO1.getId());
        assertThat(trangThaiDTO1).isEqualTo(trangThaiDTO2);
        trangThaiDTO2.setId(2L);
        assertThat(trangThaiDTO1).isNotEqualTo(trangThaiDTO2);
        trangThaiDTO1.setId(null);
        assertThat(trangThaiDTO1).isNotEqualTo(trangThaiDTO2);
    }
}
