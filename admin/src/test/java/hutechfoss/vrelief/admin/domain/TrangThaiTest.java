package hutechfoss.vrelief.admin.domain;

import static hutechfoss.vrelief.admin.domain.TrangThaiTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import hutechfoss.vrelief.admin.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TrangThaiTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TrangThai.class);
        TrangThai trangThai1 = getTrangThaiSample1();
        TrangThai trangThai2 = new TrangThai();
        assertThat(trangThai1).isNotEqualTo(trangThai2);

        trangThai2.setId(trangThai1.getId());
        assertThat(trangThai1).isEqualTo(trangThai2);

        trangThai2 = getTrangThaiSample2();
        assertThat(trangThai1).isNotEqualTo(trangThai2);
    }
}
