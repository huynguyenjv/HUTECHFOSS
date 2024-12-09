package hutechfoss.vrelief.admin.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class LoaiThienTaiTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static LoaiThienTai getLoaiThienTaiSample1() {
        return new LoaiThienTai().id(1L).tenLoaiThienTai("tenLoaiThienTai1").moTa("moTa1");
    }

    public static LoaiThienTai getLoaiThienTaiSample2() {
        return new LoaiThienTai().id(2L).tenLoaiThienTai("tenLoaiThienTai2").moTa("moTa2");
    }

    public static LoaiThienTai getLoaiThienTaiRandomSampleGenerator() {
        return new LoaiThienTai()
            .id(longCount.incrementAndGet())
            .tenLoaiThienTai(UUID.randomUUID().toString())
            .moTa(UUID.randomUUID().toString());
    }
}
