package hutechfoss.vrelief.admin.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import hutechfoss.vrelief.admin.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TinhNguyenVienDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TinhNguyenVienDTO.class);
        TinhNguyenVienDTO tinhNguyenVienDTO1 = new TinhNguyenVienDTO();
        tinhNguyenVienDTO1.setId(1L);
        TinhNguyenVienDTO tinhNguyenVienDTO2 = new TinhNguyenVienDTO();
        assertThat(tinhNguyenVienDTO1).isNotEqualTo(tinhNguyenVienDTO2);
        tinhNguyenVienDTO2.setId(tinhNguyenVienDTO1.getId());
        assertThat(tinhNguyenVienDTO1).isEqualTo(tinhNguyenVienDTO2);
        tinhNguyenVienDTO2.setId(2L);
        assertThat(tinhNguyenVienDTO1).isNotEqualTo(tinhNguyenVienDTO2);
        tinhNguyenVienDTO1.setId(null);
        assertThat(tinhNguyenVienDTO1).isNotEqualTo(tinhNguyenVienDTO2);
    }
}
