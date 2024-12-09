package hutechfoss.vrelief.admin.web.rest;

import static hutechfoss.vrelief.admin.domain.TrangThaiAsserts.*;
import static hutechfoss.vrelief.admin.web.rest.TestUtil.createUpdateProxyForBean;
import static hutechfoss.vrelief.admin.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import hutechfoss.vrelief.admin.IntegrationTest;
import hutechfoss.vrelief.admin.domain.TrangThai;
import hutechfoss.vrelief.admin.repository.TrangThaiRepository;
import hutechfoss.vrelief.admin.service.dto.TrangThaiDTO;
import hutechfoss.vrelief.admin.service.mapper.TrangThaiMapper;
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
 * Integration tests for the {@link TrangThaiResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TrangThaiResourceIT {

    private static final String DEFAULT_TEN_TRANG_THAI = "AAAAAAAAAA";
    private static final String UPDATED_TEN_TRANG_THAI = "BBBBBBBBBB";

    private static final String DEFAULT_MO_TA = "AAAAAAAAAA";
    private static final String UPDATED_MO_TA = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_CREATED_AT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATED_AT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_UPDATED_AT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_UPDATED_AT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String ENTITY_API_URL = "/api/trang-thais";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private TrangThaiRepository trangThaiRepository;

    @Autowired
    private TrangThaiMapper trangThaiMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTrangThaiMockMvc;

    private TrangThai trangThai;

    private TrangThai insertedTrangThai;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TrangThai createEntity() {
        return new TrangThai()
            .tenTrangThai(DEFAULT_TEN_TRANG_THAI)
            .moTa(DEFAULT_MO_TA)
            .createdAt(DEFAULT_CREATED_AT)
            .updatedAt(DEFAULT_UPDATED_AT);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TrangThai createUpdatedEntity() {
        return new TrangThai()
            .tenTrangThai(UPDATED_TEN_TRANG_THAI)
            .moTa(UPDATED_MO_TA)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);
    }

    @BeforeEach
    public void initTest() {
        trangThai = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedTrangThai != null) {
            trangThaiRepository.delete(insertedTrangThai);
            insertedTrangThai = null;
        }
    }

    @Test
    @Transactional
    void createTrangThai() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the TrangThai
        TrangThaiDTO trangThaiDTO = trangThaiMapper.toDto(trangThai);
        var returnedTrangThaiDTO = om.readValue(
            restTrangThaiMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(trangThaiDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            TrangThaiDTO.class
        );

        // Validate the TrangThai in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedTrangThai = trangThaiMapper.toEntity(returnedTrangThaiDTO);
        assertTrangThaiUpdatableFieldsEquals(returnedTrangThai, getPersistedTrangThai(returnedTrangThai));

        insertedTrangThai = returnedTrangThai;
    }

    @Test
    @Transactional
    void createTrangThaiWithExistingId() throws Exception {
        // Create the TrangThai with an existing ID
        trangThai.setId(1L);
        TrangThaiDTO trangThaiDTO = trangThaiMapper.toDto(trangThai);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTrangThaiMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(trangThaiDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TrangThai in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkTenTrangThaiIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        trangThai.setTenTrangThai(null);

        // Create the TrangThai, which fails.
        TrangThaiDTO trangThaiDTO = trangThaiMapper.toDto(trangThai);

        restTrangThaiMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(trangThaiDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllTrangThais() throws Exception {
        // Initialize the database
        insertedTrangThai = trangThaiRepository.saveAndFlush(trangThai);

        // Get all the trangThaiList
        restTrangThaiMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(trangThai.getId().intValue())))
            .andExpect(jsonPath("$.[*].tenTrangThai").value(hasItem(DEFAULT_TEN_TRANG_THAI)))
            .andExpect(jsonPath("$.[*].moTa").value(hasItem(DEFAULT_MO_TA)))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(sameInstant(DEFAULT_CREATED_AT))))
            .andExpect(jsonPath("$.[*].updatedAt").value(hasItem(sameInstant(DEFAULT_UPDATED_AT))));
    }

    @Test
    @Transactional
    void getTrangThai() throws Exception {
        // Initialize the database
        insertedTrangThai = trangThaiRepository.saveAndFlush(trangThai);

        // Get the trangThai
        restTrangThaiMockMvc
            .perform(get(ENTITY_API_URL_ID, trangThai.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(trangThai.getId().intValue()))
            .andExpect(jsonPath("$.tenTrangThai").value(DEFAULT_TEN_TRANG_THAI))
            .andExpect(jsonPath("$.moTa").value(DEFAULT_MO_TA))
            .andExpect(jsonPath("$.createdAt").value(sameInstant(DEFAULT_CREATED_AT)))
            .andExpect(jsonPath("$.updatedAt").value(sameInstant(DEFAULT_UPDATED_AT)));
    }

    @Test
    @Transactional
    void getNonExistingTrangThai() throws Exception {
        // Get the trangThai
        restTrangThaiMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingTrangThai() throws Exception {
        // Initialize the database
        insertedTrangThai = trangThaiRepository.saveAndFlush(trangThai);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the trangThai
        TrangThai updatedTrangThai = trangThaiRepository.findById(trangThai.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedTrangThai are not directly saved in db
        em.detach(updatedTrangThai);
        updatedTrangThai
            .tenTrangThai(UPDATED_TEN_TRANG_THAI)
            .moTa(UPDATED_MO_TA)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);
        TrangThaiDTO trangThaiDTO = trangThaiMapper.toDto(updatedTrangThai);

        restTrangThaiMockMvc
            .perform(
                put(ENTITY_API_URL_ID, trangThaiDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(trangThaiDTO))
            )
            .andExpect(status().isOk());

        // Validate the TrangThai in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedTrangThaiToMatchAllProperties(updatedTrangThai);
    }

    @Test
    @Transactional
    void putNonExistingTrangThai() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        trangThai.setId(longCount.incrementAndGet());

        // Create the TrangThai
        TrangThaiDTO trangThaiDTO = trangThaiMapper.toDto(trangThai);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTrangThaiMockMvc
            .perform(
                put(ENTITY_API_URL_ID, trangThaiDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(trangThaiDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TrangThai in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTrangThai() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        trangThai.setId(longCount.incrementAndGet());

        // Create the TrangThai
        TrangThaiDTO trangThaiDTO = trangThaiMapper.toDto(trangThai);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTrangThaiMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(trangThaiDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TrangThai in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTrangThai() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        trangThai.setId(longCount.incrementAndGet());

        // Create the TrangThai
        TrangThaiDTO trangThaiDTO = trangThaiMapper.toDto(trangThai);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTrangThaiMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(trangThaiDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the TrangThai in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTrangThaiWithPatch() throws Exception {
        // Initialize the database
        insertedTrangThai = trangThaiRepository.saveAndFlush(trangThai);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the trangThai using partial update
        TrangThai partialUpdatedTrangThai = new TrangThai();
        partialUpdatedTrangThai.setId(trangThai.getId());

        partialUpdatedTrangThai.moTa(UPDATED_MO_TA);

        restTrangThaiMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTrangThai.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedTrangThai))
            )
            .andExpect(status().isOk());

        // Validate the TrangThai in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertTrangThaiUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedTrangThai, trangThai),
            getPersistedTrangThai(trangThai)
        );
    }

    @Test
    @Transactional
    void fullUpdateTrangThaiWithPatch() throws Exception {
        // Initialize the database
        insertedTrangThai = trangThaiRepository.saveAndFlush(trangThai);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the trangThai using partial update
        TrangThai partialUpdatedTrangThai = new TrangThai();
        partialUpdatedTrangThai.setId(trangThai.getId());

        partialUpdatedTrangThai
            .tenTrangThai(UPDATED_TEN_TRANG_THAI)
            .moTa(UPDATED_MO_TA)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);

        restTrangThaiMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTrangThai.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedTrangThai))
            )
            .andExpect(status().isOk());

        // Validate the TrangThai in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertTrangThaiUpdatableFieldsEquals(partialUpdatedTrangThai, getPersistedTrangThai(partialUpdatedTrangThai));
    }

    @Test
    @Transactional
    void patchNonExistingTrangThai() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        trangThai.setId(longCount.incrementAndGet());

        // Create the TrangThai
        TrangThaiDTO trangThaiDTO = trangThaiMapper.toDto(trangThai);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTrangThaiMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, trangThaiDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(trangThaiDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TrangThai in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTrangThai() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        trangThai.setId(longCount.incrementAndGet());

        // Create the TrangThai
        TrangThaiDTO trangThaiDTO = trangThaiMapper.toDto(trangThai);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTrangThaiMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(trangThaiDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TrangThai in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTrangThai() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        trangThai.setId(longCount.incrementAndGet());

        // Create the TrangThai
        TrangThaiDTO trangThaiDTO = trangThaiMapper.toDto(trangThai);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTrangThaiMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(trangThaiDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the TrangThai in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTrangThai() throws Exception {
        // Initialize the database
        insertedTrangThai = trangThaiRepository.saveAndFlush(trangThai);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the trangThai
        restTrangThaiMockMvc
            .perform(delete(ENTITY_API_URL_ID, trangThai.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return trangThaiRepository.count();
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

    protected TrangThai getPersistedTrangThai(TrangThai trangThai) {
        return trangThaiRepository.findById(trangThai.getId()).orElseThrow();
    }

    protected void assertPersistedTrangThaiToMatchAllProperties(TrangThai expectedTrangThai) {
        assertTrangThaiAllPropertiesEquals(expectedTrangThai, getPersistedTrangThai(expectedTrangThai));
    }

    protected void assertPersistedTrangThaiToMatchUpdatableProperties(TrangThai expectedTrangThai) {
        assertTrangThaiAllUpdatablePropertiesEquals(expectedTrangThai, getPersistedTrangThai(expectedTrangThai));
    }
}
