package hutechfoss.vrelief.admin.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class NhuYeuPhamTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static NhuYeuPham getNhuYeuPhamSample1() {
        return new NhuYeuPham().id(1L).tenNhuYeuPham("tenNhuYeuPham1").donViTinh("donViTinh1").loaiNhuYeuPhamId(1).mucCanhBao(1);
    }

    public static NhuYeuPham getNhuYeuPhamSample2() {
        return new NhuYeuPham().id(2L).tenNhuYeuPham("tenNhuYeuPham2").donViTinh("donViTinh2").loaiNhuYeuPhamId(2).mucCanhBao(2);
    }

    public static NhuYeuPham getNhuYeuPhamRandomSampleGenerator() {
        return new NhuYeuPham()
            .id(longCount.incrementAndGet())
            .tenNhuYeuPham(UUID.randomUUID().toString())
            .donViTinh(UUID.randomUUID().toString())
            .loaiNhuYeuPhamId(intCount.incrementAndGet())
            .mucCanhBao(intCount.incrementAndGet());
    }
}
