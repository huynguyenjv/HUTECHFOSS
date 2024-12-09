package hutechfoss.vrelief.admin.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import hutechfoss.vrelief.admin.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class LoaiNguoiNhanDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(LoaiNguoiNhanDTO.class);
        LoaiNguoiNhanDTO loaiNguoiNhanDTO1 = new LoaiNguoiNhanDTO();
        loaiNguoiNhanDTO1.setId(1L);
        LoaiNguoiNhanDTO loaiNguoiNhanDTO2 = new LoaiNguoiNhanDTO();
        assertThat(loaiNguoiNhanDTO1).isNotEqualTo(loaiNguoiNhanDTO2);
        loaiNguoiNhanDTO2.setId(loaiNguoiNhanDTO1.getId());
        assertThat(loaiNguoiNhanDTO1).isEqualTo(loaiNguoiNhanDTO2);
        loaiNguoiNhanDTO2.setId(2L);
        assertThat(loaiNguoiNhanDTO1).isNotEqualTo(loaiNguoiNhanDTO2);
        loaiNguoiNhanDTO1.setId(null);
        assertThat(loaiNguoiNhanDTO1).isNotEqualTo(loaiNguoiNhanDTO2);
    }
}
