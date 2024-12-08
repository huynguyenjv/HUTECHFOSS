package hutechfoss.vrelief.admin.domain;

import static hutechfoss.vrelief.admin.domain.AssertUtils.zonedDataTimeSameInstant;
import static org.assertj.core.api.Assertions.assertThat;

public class TrangThaiAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertTrangThaiAllPropertiesEquals(TrangThai expected, TrangThai actual) {
        assertTrangThaiAutoGeneratedPropertiesEquals(expected, actual);
        assertTrangThaiAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertTrangThaiAllUpdatablePropertiesEquals(TrangThai expected, TrangThai actual) {
        assertTrangThaiUpdatableFieldsEquals(expected, actual);
        assertTrangThaiUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertTrangThaiAutoGeneratedPropertiesEquals(TrangThai expected, TrangThai actual) {
        assertThat(expected)
            .as("Verify TrangThai auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertTrangThaiUpdatableFieldsEquals(TrangThai expected, TrangThai actual) {
        assertThat(expected)
            .as("Verify TrangThai relevant properties")
            .satisfies(e -> assertThat(e.getTenTrangThai()).as("check tenTrangThai").isEqualTo(actual.getTenTrangThai()))
            .satisfies(e -> assertThat(e.getMoTa()).as("check moTa").isEqualTo(actual.getMoTa()))
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
    public static void assertTrangThaiUpdatableRelationshipsEquals(TrangThai expected, TrangThai actual) {
        // empty method
    }
}
