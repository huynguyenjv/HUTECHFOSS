package hutechfoss.vrelief.admin.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import hutechfoss.vrelief.admin.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class NguoiNhanDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(NguoiNhanDTO.class);
        NguoiNhanDTO nguoiNhanDTO1 = new NguoiNhanDTO();
        nguoiNhanDTO1.setId(1L);
        NguoiNhanDTO nguoiNhanDTO2 = new NguoiNhanDTO();
        assertThat(nguoiNhanDTO1).isNotEqualTo(nguoiNhanDTO2);
        nguoiNhanDTO2.setId(nguoiNhanDTO1.getId());
        assertThat(nguoiNhanDTO1).isEqualTo(nguoiNhanDTO2);
        nguoiNhanDTO2.setId(2L);
        assertThat(nguoiNhanDTO1).isNotEqualTo(nguoiNhanDTO2);
        nguoiNhanDTO1.setId(null);
        assertThat(nguoiNhanDTO1).isNotEqualTo(nguoiNhanDTO2);
    }
}
