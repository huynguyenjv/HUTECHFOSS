package hutechfoss.vrelief.admin.domain;

import static hutechfoss.vrelief.admin.domain.LoaiNguoiNhanTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import hutechfoss.vrelief.admin.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class LoaiNguoiNhanTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(LoaiNguoiNhan.class);
        LoaiNguoiNhan loaiNguoiNhan1 = getLoaiNguoiNhanSample1();
        LoaiNguoiNhan loaiNguoiNhan2 = new LoaiNguoiNhan();
        assertThat(loaiNguoiNhan1).isNotEqualTo(loaiNguoiNhan2);

        loaiNguoiNhan2.setId(loaiNguoiNhan1.getId());
        assertThat(loaiNguoiNhan1).isEqualTo(loaiNguoiNhan2);

        loaiNguoiNhan2 = getLoaiNguoiNhanSample2();
        assertThat(loaiNguoiNhan1).isNotEqualTo(loaiNguoiNhan2);
    }
}
