package hutechfoss.vrelief.admin.domain;

import static hutechfoss.vrelief.admin.domain.ChiTietDieuPhoiTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import hutechfoss.vrelief.admin.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ChiTietDieuPhoiTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ChiTietDieuPhoi.class);
        ChiTietDieuPhoi chiTietDieuPhoi1 = getChiTietDieuPhoiSample1();
        ChiTietDieuPhoi chiTietDieuPhoi2 = new ChiTietDieuPhoi();
        assertThat(chiTietDieuPhoi1).isNotEqualTo(chiTietDieuPhoi2);

        chiTietDieuPhoi2.setId(chiTietDieuPhoi1.getId());
        assertThat(chiTietDieuPhoi1).isEqualTo(chiTietDieuPhoi2);

        chiTietDieuPhoi2 = getChiTietDieuPhoiSample2();
        assertThat(chiTietDieuPhoi1).isNotEqualTo(chiTietDieuPhoi2);
    }
}
