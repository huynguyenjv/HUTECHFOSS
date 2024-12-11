package hutechfoss.vrelief.admin.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import hutechfoss.vrelief.admin.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class LoaiThienTaiDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(LoaiThienTaiDTO.class);
        LoaiThienTaiDTO loaiThienTaiDTO1 = new LoaiThienTaiDTO();
        loaiThienTaiDTO1.setId(1L);
        LoaiThienTaiDTO loaiThienTaiDTO2 = new LoaiThienTaiDTO();
        assertThat(loaiThienTaiDTO1).isNotEqualTo(loaiThienTaiDTO2);
        loaiThienTaiDTO2.setId(loaiThienTaiDTO1.getId());
        assertThat(loaiThienTaiDTO1).isEqualTo(loaiThienTaiDTO2);
        loaiThienTaiDTO2.setId(2L);
        assertThat(loaiThienTaiDTO1).isNotEqualTo(loaiThienTaiDTO2);
        loaiThienTaiDTO1.setId(null);
        assertThat(loaiThienTaiDTO1).isNotEqualTo(loaiThienTaiDTO2);
    }
}
