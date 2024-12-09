package hutechfoss.vrelief.admin.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class LoaiNguoiNhanTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static LoaiNguoiNhan getLoaiNguoiNhanSample1() {
        return new LoaiNguoiNhan().id(1L).tenLoai("tenLoai1").moTa("moTa1");
    }

    public static LoaiNguoiNhan getLoaiNguoiNhanSample2() {
        return new LoaiNguoiNhan().id(2L).tenLoai("tenLoai2").moTa("moTa2");
    }

    public static LoaiNguoiNhan getLoaiNguoiNhanRandomSampleGenerator() {
        return new LoaiNguoiNhan().id(longCount.incrementAndGet()).tenLoai(UUID.randomUUID().toString()).moTa(UUID.randomUUID().toString());
    }
}
