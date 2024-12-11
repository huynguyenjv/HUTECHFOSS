package hutechfoss.vrelief.admin.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class TrangThaiTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static TrangThai getTrangThaiSample1() {
        return new TrangThai().id(1L).tenTrangThai("tenTrangThai1").moTa("moTa1");
    }

    public static TrangThai getTrangThaiSample2() {
        return new TrangThai().id(2L).tenTrangThai("tenTrangThai2").moTa("moTa2");
    }

    public static TrangThai getTrangThaiRandomSampleGenerator() {
        return new TrangThai()
            .id(longCount.incrementAndGet())
            .tenTrangThai(UUID.randomUUID().toString())
            .moTa(UUID.randomUUID().toString());
    }
}
