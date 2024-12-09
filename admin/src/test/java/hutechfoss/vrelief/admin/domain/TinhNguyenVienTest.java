package hutechfoss.vrelief.admin.domain;

import static hutechfoss.vrelief.admin.domain.TinhNguyenVienTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import hutechfoss.vrelief.admin.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TinhNguyenVienTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TinhNguyenVien.class);
        TinhNguyenVien tinhNguyenVien1 = getTinhNguyenVienSample1();
        TinhNguyenVien tinhNguyenVien2 = new TinhNguyenVien();
        assertThat(tinhNguyenVien1).isNotEqualTo(tinhNguyenVien2);

        tinhNguyenVien2.setId(tinhNguyenVien1.getId());
        assertThat(tinhNguyenVien1).isEqualTo(tinhNguyenVien2);

        tinhNguyenVien2 = getTinhNguyenVienSample2();
        assertThat(tinhNguyenVien1).isNotEqualTo(tinhNguyenVien2);
    }
}
