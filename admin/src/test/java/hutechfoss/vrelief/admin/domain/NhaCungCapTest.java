package hutechfoss.vrelief.admin.domain;

import static hutechfoss.vrelief.admin.domain.NhaCungCapTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import hutechfoss.vrelief.admin.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class NhaCungCapTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NhaCungCap.class);
        NhaCungCap nhaCungCap1 = getNhaCungCapSample1();
        NhaCungCap nhaCungCap2 = new NhaCungCap();
        assertThat(nhaCungCap1).isNotEqualTo(nhaCungCap2);

        nhaCungCap2.setId(nhaCungCap1.getId());
        assertThat(nhaCungCap1).isEqualTo(nhaCungCap2);

        nhaCungCap2 = getNhaCungCapSample2();
        assertThat(nhaCungCap1).isNotEqualTo(nhaCungCap2);
    }
}
