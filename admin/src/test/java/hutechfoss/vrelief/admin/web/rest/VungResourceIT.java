package hutechfoss.vrelief.admin.web.rest;

import static hutechfoss.vrelief.admin.domain.VungAsserts.*;
import static hutechfoss.vrelief.admin.web.rest.TestUtil.createUpdateProxyForBean;
import static hutechfoss.vrelief.admin.web.rest.TestUtil.sameInstant;
import static hutechfoss.vrelief.admin.web.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import hutechfoss.vrelief.admin.IntegrationTest;
import hutechfoss.vrelief.admin.domain.Vung;
import hutechfoss.vrelief.admin.repository.VungRepository;
import hutechfoss.vrelief.admin.service.dto.VungDTO;
import hutechfoss.vrelief.admin.service.mapper.VungMapper;
import jakarta.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link VungResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class VungResourceIT {

    private static final String DEFAULT_TEN_VUNG = "AAAAAAAAAA";
    private static final String UPDATED_TEN_VUNG = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_LAT = new BigDecimal(1);
    private static final BigDecimal UPDATED_LAT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_LNG = new BigDecimal(1);
    private static final BigDecimal UPDATED_LNG = new BigDecimal(2);

    private static final Integer DEFAULT_LOAI_THIEN_TAI_ID = 1;
    private static final Integer UPDATED_LOAI_THIEN_TAI_ID = 2;

    private static final Integer DEFAULT_SO_DAN = 1;
    private static final Integer UPDATED_SO_DAN = 2;

    private static final ZonedDateTime DEFAULT_CREATED_AT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATED_AT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_UPDATED_AT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_UPDATED_AT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String ENTITY_API_URL = "/api/vungs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private VungRepository vungRepository;

    @Autowired
    private VungMapper vungMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restVungMockMvc;

    private Vung vung;

    private Vung insertedVung;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Vung createEntity() {
        return new Vung()
            .tenVung(DEFAULT_TEN_VUNG)
            .lat(DEFAULT_LAT)
            .lng(DEFAULT_LNG)
            .loaiThienTaiId(DEFAULT_LOAI_THIEN_TAI_ID)
            .soDan(DEFAULT_SO_DAN)
            .createdAt(DEFAULT_CREATED_AT)
            .updatedAt(DEFAULT_UPDATED_AT);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Vung createUpdatedEntity() {
        return new Vung()
            .tenVung(UPDATED_TEN_VUNG)
            .lat(UPDATED_LAT)
            .lng(UPDATED_LNG)
            .loaiThienTaiId(UPDATED_LOAI_THIEN_TAI_ID)
            .soDan(UPDATED_SO_DAN)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);
    }

    @BeforeEach
    public void initTest() {
        vung = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedVung != null) {
            vungRepository.delete(insertedVung);
            insertedVung = null;
        }
    }

    @Test
    @Transactional
    void createVung() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Vung
        VungDTO vungDTO = vungMapper.toDto(vung);
        var returnedVungDTO = om.readValue(
            restVungMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(vungDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            VungDTO.class
        );

        // Validate the Vung in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedVung = vungMapper.toEntity(returnedVungDTO);
        assertVungUpdatableFieldsEquals(returnedVung, getPersistedVung(returnedVung));

        insertedVung = returnedVung;
    }

    @Test
    @Transactional
    void createVungWithExistingId() throws Exception {
        // Create the Vung with an existing ID
        vung.setId(1L);
        VungDTO vungDTO = vungMapper.toDto(vung);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restVungMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(vungDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Vung in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkTenVungIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        vung.setTenVung(null);

        // Create the Vung, which fails.
        VungDTO vungDTO = vungMapper.toDto(vung);

        restVungMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(vungDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllVungs() throws Exception {
        // Initialize the database
        insertedVung = vungRepository.saveAndFlush(vung);

        // Get all the vungList
        restVungMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(vung.getId().intValue())))
            .andExpect(jsonPath("$.[*].tenVung").value(hasItem(DEFAULT_TEN_VUNG)))
            .andExpect(jsonPath("$.[*].lat").value(hasItem(sameNumber(DEFAULT_LAT))))
            .andExpect(jsonPath("$.[*].lng").value(hasItem(sameNumber(DEFAULT_LNG))))
            .andExpect(jsonPath("$.[*].loaiThienTaiId").value(hasItem(DEFAULT_LOAI_THIEN_TAI_ID)))
            .andExpect(jsonPath("$.[*].soDan").value(hasItem(DEFAULT_SO_DAN)))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(sameInstant(DEFAULT_CREATED_AT))))
            .andExpect(jsonPath("$.[*].updatedAt").value(hasItem(sameInstant(DEFAULT_UPDATED_AT))));
    }

    @Test
    @Transactional
    void getVung() throws Exception {
        // Initialize the database
        insertedVung = vungRepository.saveAndFlush(vung);

        // Get the vung
        restVungMockMvc
            .perform(get(ENTITY_API_URL_ID, vung.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(vung.getId().intValue()))
            .andExpect(jsonPath("$.tenVung").value(DEFAULT_TEN_VUNG))
            .andExpect(jsonPath("$.lat").value(sameNumber(DEFAULT_LAT)))
            .andExpect(jsonPath("$.lng").value(sameNumber(DEFAULT_LNG)))
            .andExpect(jsonPath("$.loaiThienTaiId").value(DEFAULT_LOAI_THIEN_TAI_ID))
            .andExpect(jsonPath("$.soDan").value(DEFAULT_SO_DAN))
            .andExpect(jsonPath("$.createdAt").value(sameInstant(DEFAULT_CREATED_AT)))
            .andExpect(jsonPath("$.updatedAt").value(sameInstant(DEFAULT_UPDATED_AT)));
    }

    @Test
    @Transactional
    void getNonExistingVung() throws Exception {
        // Get the vung
        restVungMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingVung() throws Exception {
        // Initialize the database
        insertedVung = vungRepository.saveAndFlush(vung);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the vung
        Vung updatedVung = vungRepository.findById(vung.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedVung are not directly saved in db
        em.detach(updatedVung);
        updatedVung
            .tenVung(UPDATED_TEN_VUNG)
            .lat(UPDATED_LAT)
            .lng(UPDATED_LNG)
            .loaiThienTaiId(UPDATED_LOAI_THIEN_TAI_ID)
            .soDan(UPDATED_SO_DAN)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);
        VungDTO vungDTO = vungMapper.toDto(updatedVung);

        restVungMockMvc
            .perform(put(ENTITY_API_URL_ID, vungDTO.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(vungDTO)))
            .andExpect(status().isOk());

        // Validate the Vung in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedVungToMatchAllProperties(updatedVung);
    }

    @Test
    @Transactional
    void putNonExistingVung() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        vung.setId(longCount.incrementAndGet());

        // Create the Vung
        VungDTO vungDTO = vungMapper.toDto(vung);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restVungMockMvc
            .perform(put(ENTITY_API_URL_ID, vungDTO.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(vungDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Vung in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchVung() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        vung.setId(longCount.incrementAndGet());

        // Create the Vung
        VungDTO vungDTO = vungMapper.toDto(vung);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restVungMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(vungDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Vung in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamVung() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        vung.setId(longCount.incrementAndGet());

        // Create the Vung
        VungDTO vungDTO = vungMapper.toDto(vung);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restVungMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(vungDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Vung in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateVungWithPatch() throws Exception {
        // Initialize the database
        insertedVung = vungRepository.saveAndFlush(vung);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the vung using partial update
        Vung partialUpdatedVung = new Vung();
        partialUpdatedVung.setId(vung.getId());

        partialUpdatedVung
            .tenVung(UPDATED_TEN_VUNG)
            .lng(UPDATED_LNG)
            .loaiThienTaiId(UPDATED_LOAI_THIEN_TAI_ID)
            .updatedAt(UPDATED_UPDATED_AT);

        restVungMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedVung.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedVung))
            )
            .andExpect(status().isOk());

        // Validate the Vung in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertVungUpdatableFieldsEquals(createUpdateProxyForBean(partialUpdatedVung, vung), getPersistedVung(vung));
    }

    @Test
    @Transactional
    void fullUpdateVungWithPatch() throws Exception {
        // Initialize the database
        insertedVung = vungRepository.saveAndFlush(vung);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the vung using partial update
        Vung partialUpdatedVung = new Vung();
        partialUpdatedVung.setId(vung.getId());

        partialUpdatedVung
            .tenVung(UPDATED_TEN_VUNG)
            .lat(UPDATED_LAT)
            .lng(UPDATED_LNG)
            .loaiThienTaiId(UPDATED_LOAI_THIEN_TAI_ID)
            .soDan(UPDATED_SO_DAN)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);

        restVungMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedVung.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedVung))
            )
            .andExpect(status().isOk());

        // Validate the Vung in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertVungUpdatableFieldsEquals(partialUpdatedVung, getPersistedVung(partialUpdatedVung));
    }

    @Test
    @Transactional
    void patchNonExistingVung() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        vung.setId(longCount.incrementAndGet());

        // Create the Vung
        VungDTO vungDTO = vungMapper.toDto(vung);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restVungMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, vungDTO.getId()).contentType("application/merge-patch+json").content(om.writeValueAsBytes(vungDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Vung in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchVung() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        vung.setId(longCount.incrementAndGet());

        // Create the Vung
        VungDTO vungDTO = vungMapper.toDto(vung);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restVungMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(vungDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Vung in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamVung() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        vung.setId(longCount.incrementAndGet());

        // Create the Vung
        VungDTO vungDTO = vungMapper.toDto(vung);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restVungMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(vungDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Vung in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteVung() throws Exception {
        // Initialize the database
        insertedVung = vungRepository.saveAndFlush(vung);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the vung
        restVungMockMvc
            .perform(delete(ENTITY_API_URL_ID, vung.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return vungRepository.count();
    }

    protected void assertIncrementedRepositoryCount(long countBefore) {
        assertThat(countBefore + 1).isEqualTo(getRepositoryCount());
    }

    protected void assertDecrementedRepositoryCount(long countBefore) {
        assertThat(countBefore - 1).isEqualTo(getRepositoryCount());
    }

    protected void assertSameRepositoryCount(long countBefore) {
        assertThat(countBefore).isEqualTo(getRepositoryCount());
    }

    protected Vung getPersistedVung(Vung vung) {
        return vungRepository.findById(vung.getId()).orElseThrow();
    }

    protected void assertPersistedVungToMatchAllProperties(Vung expectedVung) {
        assertVungAllPropertiesEquals(expectedVung, getPersistedVung(expectedVung));
    }

    protected void assertPersistedVungToMatchUpdatableProperties(Vung expectedVung) {
        assertVungAllUpdatablePropertiesEquals(expectedVung, getPersistedVung(expectedVung));
    }
}
