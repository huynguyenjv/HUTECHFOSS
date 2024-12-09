package hutechfoss.vrelief.admin.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class TinhNguyenVienTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static TinhNguyenVien getTinhNguyenVienSample1() {
        return new TinhNguyenVien()
            .id(1L)
            .tenTinhNguyenVien("tenTinhNguyenVien1")
            .soDienThoai("soDienThoai1")
            .diaChi("diaChi1")
            .trangThaiId(1);
    }

    public static TinhNguyenVien getTinhNguyenVienSample2() {
        return new TinhNguyenVien()
            .id(2L)
            .tenTinhNguyenVien("tenTinhNguyenVien2")
            .soDienThoai("soDienThoai2")
            .diaChi("diaChi2")
            .trangThaiId(2);
    }

    public static TinhNguyenVien getTinhNguyenVienRandomSampleGenerator() {
        return new TinhNguyenVien()
            .id(longCount.incrementAndGet())
            .tenTinhNguyenVien(UUID.randomUUID().toString())
            .soDienThoai(UUID.randomUUID().toString())
            .diaChi(UUID.randomUUID().toString())
            .trangThaiId(intCount.incrementAndGet());
    }
}
