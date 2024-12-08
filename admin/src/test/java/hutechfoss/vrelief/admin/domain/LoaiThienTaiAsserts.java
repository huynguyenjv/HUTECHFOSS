package hutechfoss.vrelief.admin.domain;

import static hutechfoss.vrelief.admin.domain.AssertUtils.zonedDataTimeSameInstant;
import static org.assertj.core.api.Assertions.assertThat;

public class LoaiThienTaiAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertLoaiThienTaiAllPropertiesEquals(LoaiThienTai expected, LoaiThienTai actual) {
        assertLoaiThienTaiAutoGeneratedPropertiesEquals(expected, actual);
        assertLoaiThienTaiAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertLoaiThienTaiAllUpdatablePropertiesEquals(LoaiThienTai expected, LoaiThienTai actual) {
        assertLoaiThienTaiUpdatableFieldsEquals(expected, actual);
        assertLoaiThienTaiUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertLoaiThienTaiAutoGeneratedPropertiesEquals(LoaiThienTai expected, LoaiThienTai actual) {
        assertThat(expected)
            .as("Verify LoaiThienTai auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertLoaiThienTaiUpdatableFieldsEquals(LoaiThienTai expected, LoaiThienTai actual) {
        assertThat(expected)
            .as("Verify LoaiThienTai relevant properties")
            .satisfies(e -> assertThat(e.getTenLoaiThienTai()).as("check tenLoaiThienTai").isEqualTo(actual.getTenLoaiThienTai()))
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
    public static void assertLoaiThienTaiUpdatableRelationshipsEquals(LoaiThienTai expected, LoaiThienTai actual) {
        // empty method
    }
}
