package hutechfoss.vrelief.admin.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import hutechfoss.vrelief.admin.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ChiTietDieuPhoiDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ChiTietDieuPhoiDTO.class);
        ChiTietDieuPhoiDTO chiTietDieuPhoiDTO1 = new ChiTietDieuPhoiDTO();
        chiTietDieuPhoiDTO1.setId(1L);
        ChiTietDieuPhoiDTO chiTietDieuPhoiDTO2 = new ChiTietDieuPhoiDTO();
        assertThat(chiTietDieuPhoiDTO1).isNotEqualTo(chiTietDieuPhoiDTO2);
        chiTietDieuPhoiDTO2.setId(chiTietDieuPhoiDTO1.getId());
        assertThat(chiTietDieuPhoiDTO1).isEqualTo(chiTietDieuPhoiDTO2);
        chiTietDieuPhoiDTO2.setId(2L);
        assertThat(chiTietDieuPhoiDTO1).isNotEqualTo(chiTietDieuPhoiDTO2);
        chiTietDieuPhoiDTO1.setId(null);
        assertThat(chiTietDieuPhoiDTO1).isNotEqualTo(chiTietDieuPhoiDTO2);
    }
}
