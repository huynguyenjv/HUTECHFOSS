package hutechfoss.vrelief.admin.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import hutechfoss.vrelief.admin.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class NhuYeuPhamDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(NhuYeuPhamDTO.class);
        NhuYeuPhamDTO nhuYeuPhamDTO1 = new NhuYeuPhamDTO();
        nhuYeuPhamDTO1.setId(1L);
        NhuYeuPhamDTO nhuYeuPhamDTO2 = new NhuYeuPhamDTO();
        assertThat(nhuYeuPhamDTO1).isNotEqualTo(nhuYeuPhamDTO2);
        nhuYeuPhamDTO2.setId(nhuYeuPhamDTO1.getId());
        assertThat(nhuYeuPhamDTO1).isEqualTo(nhuYeuPhamDTO2);
        nhuYeuPhamDTO2.setId(2L);
        assertThat(nhuYeuPhamDTO1).isNotEqualTo(nhuYeuPhamDTO2);
        nhuYeuPhamDTO1.setId(null);
        assertThat(nhuYeuPhamDTO1).isNotEqualTo(nhuYeuPhamDTO2);
    }
}
