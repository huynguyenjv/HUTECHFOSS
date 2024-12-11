package hutechfoss.vrelief.admin.domain;

import static hutechfoss.vrelief.admin.domain.LoaiThienTaiTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import hutechfoss.vrelief.admin.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class LoaiThienTaiTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(LoaiThienTai.class);
        LoaiThienTai loaiThienTai1 = getLoaiThienTaiSample1();
        LoaiThienTai loaiThienTai2 = new LoaiThienTai();
        assertThat(loaiThienTai1).isNotEqualTo(loaiThienTai2);

        loaiThienTai2.setId(loaiThienTai1.getId());
        assertThat(loaiThienTai1).isEqualTo(loaiThienTai2);

        loaiThienTai2 = getLoaiThienTaiSample2();
        assertThat(loaiThienTai1).isNotEqualTo(loaiThienTai2);
    }
}
