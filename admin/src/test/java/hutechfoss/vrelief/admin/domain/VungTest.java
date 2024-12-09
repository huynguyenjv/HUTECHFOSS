package hutechfoss.vrelief.admin.domain;

import static hutechfoss.vrelief.admin.domain.VungTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import hutechfoss.vrelief.admin.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class VungTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Vung.class);
        Vung vung1 = getVungSample1();
        Vung vung2 = new Vung();
        assertThat(vung1).isNotEqualTo(vung2);

        vung2.setId(vung1.getId());
        assertThat(vung1).isEqualTo(vung2);

        vung2 = getVungSample2();
        assertThat(vung1).isNotEqualTo(vung2);
    }
}
