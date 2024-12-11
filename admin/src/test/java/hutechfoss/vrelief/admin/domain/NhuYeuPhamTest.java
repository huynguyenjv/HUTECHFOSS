package hutechfoss.vrelief.admin.domain;

import static hutechfoss.vrelief.admin.domain.NhuYeuPhamTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import hutechfoss.vrelief.admin.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class NhuYeuPhamTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NhuYeuPham.class);
        NhuYeuPham nhuYeuPham1 = getNhuYeuPhamSample1();
        NhuYeuPham nhuYeuPham2 = new NhuYeuPham();
        assertThat(nhuYeuPham1).isNotEqualTo(nhuYeuPham2);

        nhuYeuPham2.setId(nhuYeuPham1.getId());
        assertThat(nhuYeuPham1).isEqualTo(nhuYeuPham2);

        nhuYeuPham2 = getNhuYeuPhamSample2();
        assertThat(nhuYeuPham1).isNotEqualTo(nhuYeuPham2);
    }
}
