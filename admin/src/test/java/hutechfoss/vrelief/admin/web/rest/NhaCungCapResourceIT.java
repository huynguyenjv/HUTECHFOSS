package hutechfoss.vrelief.admin.web.rest;

import static hutechfoss.vrelief.admin.domain.NhaCungCapAsserts.*;
import static hutechfoss.vrelief.admin.web.rest.TestUtil.createUpdateProxyForBean;
import static hutechfoss.vrelief.admin.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import hutechfoss.vrelief.admin.IntegrationTest;
import hutechfoss.vrelief.admin.domain.NhaCungCap;
import hutechfoss.vrelief.admin.repository.NhaCungCapRepository;
import hutechfoss.vrelief.admin.service.dto.NhaCungCapDTO;
import hutechfoss.vrelief.admin.service.mapper.NhaCungCapMapper;
import jakarta.persistence.EntityManager;
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
 * Integration tests for the {@link NhaCungCapResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class NhaCungCapResourceIT {

    private static final String DEFAULT_TEN_NHA_CUNG_CAP = "AAAAAAAAAA";
    private static final String UPDATED_TEN_NHA_CUNG_CAP = "BBBBBBBBBB";

    private static final String DEFAULT_SO_DIEN_THOAI = "AAAAAAAAAA";
    private static final String UPDATED_SO_DIEN_THOAI = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_DIA_CHI = "AAAAAAAAAA";
    private static final String UPDATED_DIA_CHI = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_CREATED_AT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATED_AT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_UPDATED_AT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_UPDATED_AT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String ENTITY_API_URL = "/api/nha-cung-caps";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private NhaCungCapRepository nhaCungCapRepository;

    @Autowired
    private NhaCungCapMapper nhaCungCapMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restNhaCungCapMockMvc;

    private NhaCungCap nhaCungCap;

    private NhaCungCap insertedNhaCungCap;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NhaCungCap createEntity() {
        return new NhaCungCap()
            .tenNhaCungCap(DEFAULT_TEN_NHA_CUNG_CAP)
            .soDienThoai(DEFAULT_SO_DIEN_THOAI)
            .email(DEFAULT_EMAIL)
            .diaChi(DEFAULT_DIA_CHI)
            .createdAt(DEFAULT_CREATED_AT)
            .updatedAt(DEFAULT_UPDATED_AT);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NhaCungCap createUpdatedEntity() {
        return new NhaCungCap()
            .tenNhaCungCap(UPDATED_TEN_NHA_CUNG_CAP)
            .soDienThoai(UPDATED_SO_DIEN_THOAI)
            .email(UPDATED_EMAIL)
            .diaChi(UPDATED_DIA_CHI)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);
    }

    @BeforeEach
    public void initTest() {
        nhaCungCap = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedNhaCungCap != null) {
            nhaCungCapRepository.delete(insertedNhaCungCap);
            insertedNhaCungCap = null;
        }
    }

    @Test
    @Transactional
    void createNhaCungCap() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the NhaCungCap
        NhaCungCapDTO nhaCungCapDTO = nhaCungCapMapper.toDto(nhaCungCap);
        var returnedNhaCungCapDTO = om.readValue(
            restNhaCungCapMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(nhaCungCapDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            NhaCungCapDTO.class
        );

        // Validate the NhaCungCap in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedNhaCungCap = nhaCungCapMapper.toEntity(returnedNhaCungCapDTO);
        assertNhaCungCapUpdatableFieldsEquals(returnedNhaCungCap, getPersistedNhaCungCap(returnedNhaCungCap));

        insertedNhaCungCap = returnedNhaCungCap;
    }

    @Test
    @Transactional
    void createNhaCungCapWithExistingId() throws Exception {
        // Create the NhaCungCap with an existing ID
        nhaCungCap.setId(1L);
        NhaCungCapDTO nhaCungCapDTO = nhaCungCapMapper.toDto(nhaCungCap);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restNhaCungCapMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(nhaCungCapDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NhaCungCap in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkTenNhaCungCapIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        nhaCungCap.setTenNhaCungCap(null);

        // Create the NhaCungCap, which fails.
        NhaCungCapDTO nhaCungCapDTO = nhaCungCapMapper.toDto(nhaCungCap);

        restNhaCungCapMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(nhaCungCapDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllNhaCungCaps() throws Exception {
        // Initialize the database
        insertedNhaCungCap = nhaCungCapRepository.saveAndFlush(nhaCungCap);

        // Get all the nhaCungCapList
        restNhaCungCapMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(nhaCungCap.getId().intValue())))
            .andExpect(jsonPath("$.[*].tenNhaCungCap").value(hasItem(DEFAULT_TEN_NHA_CUNG_CAP)))
            .andExpect(jsonPath("$.[*].soDienThoai").value(hasItem(DEFAULT_SO_DIEN_THOAI)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].diaChi").value(hasItem(DEFAULT_DIA_CHI)))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(sameInstant(DEFAULT_CREATED_AT))))
            .andExpect(jsonPath("$.[*].updatedAt").value(hasItem(sameInstant(DEFAULT_UPDATED_AT))));
    }

    @Test
    @Transactional
    void getNhaCungCap() throws Exception {
        // Initialize the database
        insertedNhaCungCap = nhaCungCapRepository.saveAndFlush(nhaCungCap);

        // Get the nhaCungCap
        restNhaCungCapMockMvc
            .perform(get(ENTITY_API_URL_ID, nhaCungCap.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(nhaCungCap.getId().intValue()))
            .andExpect(jsonPath("$.tenNhaCungCap").value(DEFAULT_TEN_NHA_CUNG_CAP))
            .andExpect(jsonPath("$.soDienThoai").value(DEFAULT_SO_DIEN_THOAI))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.diaChi").value(DEFAULT_DIA_CHI))
            .andExpect(jsonPath("$.createdAt").value(sameInstant(DEFAULT_CREATED_AT)))
            .andExpect(jsonPath("$.updatedAt").value(sameInstant(DEFAULT_UPDATED_AT)));
    }

    @Test
    @Transactional
    void getNonExistingNhaCungCap() throws Exception {
        // Get the nhaCungCap
        restNhaCungCapMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingNhaCungCap() throws Exception {
        // Initialize the database
        insertedNhaCungCap = nhaCungCapRepository.saveAndFlush(nhaCungCap);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the nhaCungCap
        NhaCungCap updatedNhaCungCap = nhaCungCapRepository.findById(nhaCungCap.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedNhaCungCap are not directly saved in db
        em.detach(updatedNhaCungCap);
        updatedNhaCungCap
            .tenNhaCungCap(UPDATED_TEN_NHA_CUNG_CAP)
            .soDienThoai(UPDATED_SO_DIEN_THOAI)
            .email(UPDATED_EMAIL)
            .diaChi(UPDATED_DIA_CHI)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);
        NhaCungCapDTO nhaCungCapDTO = nhaCungCapMapper.toDto(updatedNhaCungCap);

        restNhaCungCapMockMvc
            .perform(
                put(ENTITY_API_URL_ID, nhaCungCapDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(nhaCungCapDTO))
            )
            .andExpect(status().isOk());

        // Validate the NhaCungCap in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedNhaCungCapToMatchAllProperties(updatedNhaCungCap);
    }

    @Test
    @Transactional
    void putNonExistingNhaCungCap() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        nhaCungCap.setId(longCount.incrementAndGet());

        // Create the NhaCungCap
        NhaCungCapDTO nhaCungCapDTO = nhaCungCapMapper.toDto(nhaCungCap);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNhaCungCapMockMvc
            .perform(
                put(ENTITY_API_URL_ID, nhaCungCapDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(nhaCungCapDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NhaCungCap in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchNhaCungCap() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        nhaCungCap.setId(longCount.incrementAndGet());

        // Create the NhaCungCap
        NhaCungCapDTO nhaCungCapDTO = nhaCungCapMapper.toDto(nhaCungCap);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNhaCungCapMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(nhaCungCapDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NhaCungCap in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamNhaCungCap() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        nhaCungCap.setId(longCount.incrementAndGet());

        // Create the NhaCungCap
        NhaCungCapDTO nhaCungCapDTO = nhaCungCapMapper.toDto(nhaCungCap);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNhaCungCapMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(nhaCungCapDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the NhaCungCap in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateNhaCungCapWithPatch() throws Exception {
        // Initialize the database
        insertedNhaCungCap = nhaCungCapRepository.saveAndFlush(nhaCungCap);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the nhaCungCap using partial update
        NhaCungCap partialUpdatedNhaCungCap = new NhaCungCap();
        partialUpdatedNhaCungCap.setId(nhaCungCap.getId());

        partialUpdatedNhaCungCap.tenNhaCungCap(UPDATED_TEN_NHA_CUNG_CAP);

        restNhaCungCapMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedNhaCungCap.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedNhaCungCap))
            )
            .andExpect(status().isOk());

        // Validate the NhaCungCap in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertNhaCungCapUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedNhaCungCap, nhaCungCap),
            getPersistedNhaCungCap(nhaCungCap)
        );
    }

    @Test
    @Transactional
    void fullUpdateNhaCungCapWithPatch() throws Exception {
        // Initialize the database
        insertedNhaCungCap = nhaCungCapRepository.saveAndFlush(nhaCungCap);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the nhaCungCap using partial update
        NhaCungCap partialUpdatedNhaCungCap = new NhaCungCap();
        partialUpdatedNhaCungCap.setId(nhaCungCap.getId());

        partialUpdatedNhaCungCap
            .tenNhaCungCap(UPDATED_TEN_NHA_CUNG_CAP)
            .soDienThoai(UPDATED_SO_DIEN_THOAI)
            .email(UPDATED_EMAIL)
            .diaChi(UPDATED_DIA_CHI)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);

        restNhaCungCapMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedNhaCungCap.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedNhaCungCap))
            )
            .andExpect(status().isOk());

        // Validate the NhaCungCap in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertNhaCungCapUpdatableFieldsEquals(partialUpdatedNhaCungCap, getPersistedNhaCungCap(partialUpdatedNhaCungCap));
    }

    @Test
    @Transactional
    void patchNonExistingNhaCungCap() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        nhaCungCap.setId(longCount.incrementAndGet());

        // Create the NhaCungCap
        NhaCungCapDTO nhaCungCapDTO = nhaCungCapMapper.toDto(nhaCungCap);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNhaCungCapMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, nhaCungCapDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(nhaCungCapDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NhaCungCap in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchNhaCungCap() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        nhaCungCap.setId(longCount.incrementAndGet());

        // Create the NhaCungCap
        NhaCungCapDTO nhaCungCapDTO = nhaCungCapMapper.toDto(nhaCungCap);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNhaCungCapMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(nhaCungCapDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NhaCungCap in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamNhaCungCap() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        nhaCungCap.setId(longCount.incrementAndGet());

        // Create the NhaCungCap
        NhaCungCapDTO nhaCungCapDTO = nhaCungCapMapper.toDto(nhaCungCap);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNhaCungCapMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(nhaCungCapDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the NhaCungCap in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteNhaCungCap() throws Exception {
        // Initialize the database
        insertedNhaCungCap = nhaCungCapRepository.saveAndFlush(nhaCungCap);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the nhaCungCap
        restNhaCungCapMockMvc
            .perform(delete(ENTITY_API_URL_ID, nhaCungCap.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return nhaCungCapRepository.count();
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

    protected NhaCungCap getPersistedNhaCungCap(NhaCungCap nhaCungCap) {
        return nhaCungCapRepository.findById(nhaCungCap.getId()).orElseThrow();
    }

    protected void assertPersistedNhaCungCapToMatchAllProperties(NhaCungCap expectedNhaCungCap) {
        assertNhaCungCapAllPropertiesEquals(expectedNhaCungCap, getPersistedNhaCungCap(expectedNhaCungCap));
    }

    protected void assertPersistedNhaCungCapToMatchUpdatableProperties(NhaCungCap expectedNhaCungCap) {
        assertNhaCungCapAllUpdatablePropertiesEquals(expectedNhaCungCap, getPersistedNhaCungCap(expectedNhaCungCap));
    }
}
