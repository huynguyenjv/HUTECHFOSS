package hutechfoss.vrelief.admin.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class VungTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Vung getVungSample1() {
        return new Vung().id(1L).tenVung("tenVung1").loaiThienTaiId(1).soDan(1);
    }

    public static Vung getVungSample2() {
        return new Vung().id(2L).tenVung("tenVung2").loaiThienTaiId(2).soDan(2);
    }

    public static Vung getVungRandomSampleGenerator() {
        return new Vung()
            .id(longCount.incrementAndGet())
            .tenVung(UUID.randomUUID().toString())
            .loaiThienTaiId(intCount.incrementAndGet())
            .soDan(intCount.incrementAndGet());
    }
}
