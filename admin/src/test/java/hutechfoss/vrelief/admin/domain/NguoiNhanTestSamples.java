package hutechfoss.vrelief.admin.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class NguoiNhanTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static NguoiNhan getNguoiNhanSample1() {
        return new NguoiNhan()
            .id(1L)
            .tenNguoiNhan("tenNguoiNhan1")
            .soDienThoai("soDienThoai1")
            .email("email1")
            .diaChi("diaChi1")
            .vungId(1)
            .soNguoiNhan(1);
    }

    public static NguoiNhan getNguoiNhanSample2() {
        return new NguoiNhan()
            .id(2L)
            .tenNguoiNhan("tenNguoiNhan2")
            .soDienThoai("soDienThoai2")
            .email("email2")
            .diaChi("diaChi2")
            .vungId(2)
            .soNguoiNhan(2);
    }

    public static NguoiNhan getNguoiNhanRandomSampleGenerator() {
        return new NguoiNhan()
            .id(longCount.incrementAndGet())
            .tenNguoiNhan(UUID.randomUUID().toString())
            .soDienThoai(UUID.randomUUID().toString())
            .email(UUID.randomUUID().toString())
            .diaChi(UUID.randomUUID().toString())
            .vungId(intCount.incrementAndGet())
            .soNguoiNhan(intCount.incrementAndGet());
    }
}
