package hutechfoss.vrelief.admin.domain;

import static hutechfoss.vrelief.admin.domain.AssertUtils.zonedDataTimeSameInstant;
import static org.assertj.core.api.Assertions.assertThat;

public class TinhNguyenVienAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertTinhNguyenVienAllPropertiesEquals(TinhNguyenVien expected, TinhNguyenVien actual) {
        assertTinhNguyenVienAutoGeneratedPropertiesEquals(expected, actual);
        assertTinhNguyenVienAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertTinhNguyenVienAllUpdatablePropertiesEquals(TinhNguyenVien expected, TinhNguyenVien actual) {
        assertTinhNguyenVienUpdatableFieldsEquals(expected, actual);
        assertTinhNguyenVienUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertTinhNguyenVienAutoGeneratedPropertiesEquals(TinhNguyenVien expected, TinhNguyenVien actual) {
        assertThat(expected)
            .as("Verify TinhNguyenVien auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertTinhNguyenVienUpdatableFieldsEquals(TinhNguyenVien expected, TinhNguyenVien actual) {
        assertThat(expected)
            .as("Verify TinhNguyenVien relevant properties")
            .satisfies(e -> assertThat(e.getTenTinhNguyenVien()).as("check tenTinhNguyenVien").isEqualTo(actual.getTenTinhNguyenVien()))
            .satisfies(e -> assertThat(e.getSoDienThoai()).as("check soDienThoai").isEqualTo(actual.getSoDienThoai()))
            .satisfies(e -> assertThat(e.getDiaChi()).as("check diaChi").isEqualTo(actual.getDiaChi()))
            .satisfies(e -> assertThat(e.getTrangThaiId()).as("check trangThaiId").isEqualTo(actual.getTrangThaiId()))
            .satisfies(e ->
                assertThat(e.getCreatedAt())
                    .as("check createdAt")
                    .usingComparator(zonedDataTimeSameInstant)
                    .isEqualTo(actual.getCreatedAt())
            )
            .satisfies(e ->
                assertThat(e.getUpdatedAt())
                    .as("check updatedAt")
                    .usingComparator(zonedDataTimeSameInstant)
                    .isEqualTo(actual.getUpdatedAt())
            );
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertTinhNguyenVienUpdatableRelationshipsEquals(TinhNguyenVien expected, TinhNguyenVien actual) {
        // empty method
    }
}
