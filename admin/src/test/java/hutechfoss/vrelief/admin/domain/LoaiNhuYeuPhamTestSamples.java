package hutechfoss.vrelief.admin.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class LoaiNhuYeuPhamTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static LoaiNhuYeuPham getLoaiNhuYeuPhamSample1() {
        return new LoaiNhuYeuPham().id(1L).tenLoai("tenLoai1");
    }

    public static LoaiNhuYeuPham getLoaiNhuYeuPhamSample2() {
        return new LoaiNhuYeuPham().id(2L).tenLoai("tenLoai2");
    }

    public static LoaiNhuYeuPham getLoaiNhuYeuPhamRandomSampleGenerator() {
        return new LoaiNhuYeuPham().id(longCount.incrementAndGet()).tenLoai(UUID.randomUUID().toString());
    }
}
