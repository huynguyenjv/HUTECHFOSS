package hutechfoss.vrelief.admin.domain;

import static hutechfoss.vrelief.admin.domain.NguoiNhanTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import hutechfoss.vrelief.admin.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class NguoiNhanTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NguoiNhan.class);
        NguoiNhan nguoiNhan1 = getNguoiNhanSample1();
        NguoiNhan nguoiNhan2 = new NguoiNhan();
        assertThat(nguoiNhan1).isNotEqualTo(nguoiNhan2);

        nguoiNhan2.setId(nguoiNhan1.getId());
        assertThat(nguoiNhan1).isEqualTo(nguoiNhan2);

        nguoiNhan2 = getNguoiNhanSample2();
        assertThat(nguoiNhan1).isNotEqualTo(nguoiNhan2);
    }
}
