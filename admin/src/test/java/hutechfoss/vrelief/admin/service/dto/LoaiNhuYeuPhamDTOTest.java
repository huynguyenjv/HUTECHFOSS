package hutechfoss.vrelief.admin.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import hutechfoss.vrelief.admin.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class LoaiNhuYeuPhamDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(LoaiNhuYeuPhamDTO.class);
        LoaiNhuYeuPhamDTO loaiNhuYeuPhamDTO1 = new LoaiNhuYeuPhamDTO();
        loaiNhuYeuPhamDTO1.setId(1L);
        LoaiNhuYeuPhamDTO loaiNhuYeuPhamDTO2 = new LoaiNhuYeuPhamDTO();
        assertThat(loaiNhuYeuPhamDTO1).isNotEqualTo(loaiNhuYeuPhamDTO2);
        loaiNhuYeuPhamDTO2.setId(loaiNhuYeuPhamDTO1.getId());
        assertThat(loaiNhuYeuPhamDTO1).isEqualTo(loaiNhuYeuPhamDTO2);
        loaiNhuYeuPhamDTO2.setId(2L);
        assertThat(loaiNhuYeuPhamDTO1).isNotEqualTo(loaiNhuYeuPhamDTO2);
        loaiNhuYeuPhamDTO1.setId(null);
        assertThat(loaiNhuYeuPhamDTO1).isNotEqualTo(loaiNhuYeuPhamDTO2);
    }
}
