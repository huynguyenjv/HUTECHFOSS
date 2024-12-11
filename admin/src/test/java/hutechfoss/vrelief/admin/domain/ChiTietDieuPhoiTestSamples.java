package hutechfoss.vrelief.admin.domain;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class ChiTietDieuPhoiTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static ChiTietDieuPhoi getChiTietDieuPhoiSample1() {
        return new ChiTietDieuPhoi().id(1L).phieuDieuPhoiId(1).nhuYeuPhamId(1).soLuong(1);
    }

    public static ChiTietDieuPhoi getChiTietDieuPhoiSample2() {
        return new ChiTietDieuPhoi().id(2L).phieuDieuPhoiId(2).nhuYeuPhamId(2).soLuong(2);
    }

    public static ChiTietDieuPhoi getChiTietDieuPhoiRandomSampleGenerator() {
        return new ChiTietDieuPhoi()
            .id(longCount.incrementAndGet())
            .phieuDieuPhoiId(intCount.incrementAndGet())
            .nhuYeuPhamId(intCount.incrementAndGet())
            .soLuong(intCount.incrementAndGet());
    }
}
