package hutechfoss.vrelief.admin.domain;

import static hutechfoss.vrelief.admin.domain.LoaiNhuYeuPhamTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import hutechfoss.vrelief.admin.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class LoaiNhuYeuPhamTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(LoaiNhuYeuPham.class);
        LoaiNhuYeuPham loaiNhuYeuPham1 = getLoaiNhuYeuPhamSample1();
        LoaiNhuYeuPham loaiNhuYeuPham2 = new LoaiNhuYeuPham();
        assertThat(loaiNhuYeuPham1).isNotEqualTo(loaiNhuYeuPham2);

        loaiNhuYeuPham2.setId(loaiNhuYeuPham1.getId());
        assertThat(loaiNhuYeuPham1).isEqualTo(loaiNhuYeuPham2);

        loaiNhuYeuPham2 = getLoaiNhuYeuPhamSample2();
        assertThat(loaiNhuYeuPham1).isNotEqualTo(loaiNhuYeuPham2);
    }
}
