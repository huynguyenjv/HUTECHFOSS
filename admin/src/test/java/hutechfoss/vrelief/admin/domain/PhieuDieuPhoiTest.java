package hutechfoss.vrelief.admin.domain;

import static hutechfoss.vrelief.admin.domain.PhieuDieuPhoiTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import hutechfoss.vrelief.admin.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PhieuDieuPhoiTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PhieuDieuPhoi.class);
        PhieuDieuPhoi phieuDieuPhoi1 = getPhieuDieuPhoiSample1();
        PhieuDieuPhoi phieuDieuPhoi2 = new PhieuDieuPhoi();
        assertThat(phieuDieuPhoi1).isNotEqualTo(phieuDieuPhoi2);

        phieuDieuPhoi2.setId(phieuDieuPhoi1.getId());
        assertThat(phieuDieuPhoi1).isEqualTo(phieuDieuPhoi2);

        phieuDieuPhoi2 = getPhieuDieuPhoiSample2();
        assertThat(phieuDieuPhoi1).isNotEqualTo(phieuDieuPhoi2);
    }
}
