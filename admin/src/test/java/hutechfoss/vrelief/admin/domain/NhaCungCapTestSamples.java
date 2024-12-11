package hutechfoss.vrelief.admin.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class NhaCungCapTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static NhaCungCap getNhaCungCapSample1() {
        return new NhaCungCap().id(1L).tenNhaCungCap("tenNhaCungCap1").soDienThoai("soDienThoai1").email("email1").diaChi("diaChi1");
    }

    public static NhaCungCap getNhaCungCapSample2() {
        return new NhaCungCap().id(2L).tenNhaCungCap("tenNhaCungCap2").soDienThoai("soDienThoai2").email("email2").diaChi("diaChi2");
    }

    public static NhaCungCap getNhaCungCapRandomSampleGenerator() {
        return new NhaCungCap()
            .id(longCount.incrementAndGet())
            .tenNhaCungCap(UUID.randomUUID().toString())
            .soDienThoai(UUID.randomUUID().toString())
            .email(UUID.randomUUID().toString())
            .diaChi(UUID.randomUUID().toString());
    }
}
