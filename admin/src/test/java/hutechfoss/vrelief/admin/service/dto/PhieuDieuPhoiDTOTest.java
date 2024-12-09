package hutechfoss.vrelief.admin.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import hutechfoss.vrelief.admin.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PhieuDieuPhoiDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PhieuDieuPhoiDTO.class);
        PhieuDieuPhoiDTO phieuDieuPhoiDTO1 = new PhieuDieuPhoiDTO();
        phieuDieuPhoiDTO1.setId(1L);
        PhieuDieuPhoiDTO phieuDieuPhoiDTO2 = new PhieuDieuPhoiDTO();
        assertThat(phieuDieuPhoiDTO1).isNotEqualTo(phieuDieuPhoiDTO2);
        phieuDieuPhoiDTO2.setId(phieuDieuPhoiDTO1.getId());
        assertThat(phieuDieuPhoiDTO1).isEqualTo(phieuDieuPhoiDTO2);
        phieuDieuPhoiDTO2.setId(2L);
        assertThat(phieuDieuPhoiDTO1).isNotEqualTo(phieuDieuPhoiDTO2);
        phieuDieuPhoiDTO1.setId(null);
        assertThat(phieuDieuPhoiDTO1).isNotEqualTo(phieuDieuPhoiDTO2);
    }
}
