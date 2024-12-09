package hutechfoss.vrelief.admin.domain;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class PhieuDieuPhoiTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static PhieuDieuPhoi getPhieuDieuPhoiSample1() {
        return new PhieuDieuPhoi().id(1L).nhaCungCapId(1).nguoiNhanId(1).tinhNguyenVienId(1).trangThaiId(1);
    }

    public static PhieuDieuPhoi getPhieuDieuPhoiSample2() {
        return new PhieuDieuPhoi().id(2L).nhaCungCapId(2).nguoiNhanId(2).tinhNguyenVienId(2).trangThaiId(2);
    }

    public static PhieuDieuPhoi getPhieuDieuPhoiRandomSampleGenerator() {
        return new PhieuDieuPhoi()
            .id(longCount.incrementAndGet())
            .nhaCungCapId(intCount.incrementAndGet())
            .nguoiNhanId(intCount.incrementAndGet())
            .tinhNguyenVienId(intCount.incrementAndGet())
            .trangThaiId(intCount.incrementAndGet());
    }
}
