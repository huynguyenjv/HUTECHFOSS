package hutechfoss.vrelief.admin.web.rest;

import static hutechfoss.vrelief.admin.domain.LoaiThienTaiAsserts.*;
import static hutechfoss.vrelief.admin.web.rest.TestUtil.createUpdateProxyForBean;
import static hutechfoss.vrelief.admin.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import hutechfoss.vrelief.admin.IntegrationTest;
import hutechfoss.vrelief.admin.domain.LoaiThienTai;
import hutechfoss.vrelief.admin.repository.LoaiThienTaiRepository;
import hutechfoss.vrelief.admin.service.dto.LoaiThienTaiDTO;
import hutechfoss.vrelief.admin.service.mapper.LoaiThienTaiMapper;
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
 * Integration tests for the {@link LoaiThienTaiResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class LoaiThienTaiResourceIT {

    private static final String DEFAULT_TEN_LOAI_THIEN_TAI = "AAAAAAAAAA";
    private static final String UPDATED_TEN_LOAI_THIEN_TAI = "BBBBBBBBBB";

    private static final String DEFAULT_MO_TA = "AAAAAAAAAA";
    private static final String UPDATED_MO_TA = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_CREATED_AT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATED_AT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_UPDATED_AT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_UPDATED_AT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String ENTITY_API_URL = "/api/loai-thien-tais";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private LoaiThienTaiRepository loaiThienTaiRepository;

    @Autowired
    private LoaiThienTaiMapper loaiThienTaiMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restLoaiThienTaiMockMvc;

    private LoaiThienTai loaiThienTai;

    private LoaiThienTai insertedLoaiThienTai;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LoaiThienTai createEntity() {
        return new LoaiThienTai()
            .tenLoaiThienTai(DEFAULT_TEN_LOAI_THIEN_TAI)
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
    public static LoaiThienTai createUpdatedEntity() {
        return new LoaiThienTai()
            .tenLoaiThienTai(UPDATED_TEN_LOAI_THIEN_TAI)
            .moTa(UPDATED_MO_TA)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);
    }

    @BeforeEach
    public void initTest() {
        loaiThienTai = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedLoaiThienTai != null) {
            loaiThienTaiRepository.delete(insertedLoaiThienTai);
            insertedLoaiThienTai = null;
        }
    }

    @Test
    @Transactional
    void createLoaiThienTai() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the LoaiThienTai
        LoaiThienTaiDTO loaiThienTaiDTO = loaiThienTaiMapper.toDto(loaiThienTai);
        var returnedLoaiThienTaiDTO = om.readValue(
            restLoaiThienTaiMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(loaiThienTaiDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            LoaiThienTaiDTO.class
        );

        // Validate the LoaiThienTai in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedLoaiThienTai = loaiThienTaiMapper.toEntity(returnedLoaiThienTaiDTO);
        assertLoaiThienTaiUpdatableFieldsEquals(returnedLoaiThienTai, getPersistedLoaiThienTai(returnedLoaiThienTai));

        insertedLoaiThienTai = returnedLoaiThienTai;
    }

    @Test
    @Transactional
    void createLoaiThienTaiWithExistingId() throws Exception {
        // Create the LoaiThienTai with an existing ID
        loaiThienTai.setId(1L);
        LoaiThienTaiDTO loaiThienTaiDTO = loaiThienTaiMapper.toDto(loaiThienTai);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restLoaiThienTaiMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(loaiThienTaiDTO)))
            .andExpect(status().isBadRequest());

        // Validate the LoaiThienTai in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkTenLoaiThienTaiIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        loaiThienTai.setTenLoaiThienTai(null);

        // Create the LoaiThienTai, which fails.
        LoaiThienTaiDTO loaiThienTaiDTO = loaiThienTaiMapper.toDto(loaiThienTai);

        restLoaiThienTaiMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(loaiThienTaiDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllLoaiThienTais() throws Exception {
        // Initialize the database
        insertedLoaiThienTai = loaiThienTaiRepository.saveAndFlush(loaiThienTai);

        // Get all the loaiThienTaiList
        restLoaiThienTaiMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(loaiThienTai.getId().intValue())))
            .andExpect(jsonPath("$.[*].tenLoaiThienTai").value(hasItem(DEFAULT_TEN_LOAI_THIEN_TAI)))
            .andExpect(jsonPath("$.[*].moTa").value(hasItem(DEFAULT_MO_TA)))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(sameInstant(DEFAULT_CREATED_AT))))
            .andExpect(jsonPath("$.[*].updatedAt").value(hasItem(sameInstant(DEFAULT_UPDATED_AT))));
    }

    @Test
    @Transactional
    void getLoaiThienTai() throws Exception {
        // Initialize the database
        insertedLoaiThienTai = loaiThienTaiRepository.saveAndFlush(loaiThienTai);

        // Get the loaiThienTai
        restLoaiThienTaiMockMvc
            .perform(get(ENTITY_API_URL_ID, loaiThienTai.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(loaiThienTai.getId().intValue()))
            .andExpect(jsonPath("$.tenLoaiThienTai").value(DEFAULT_TEN_LOAI_THIEN_TAI))
            .andExpect(jsonPath("$.moTa").value(DEFAULT_MO_TA))
            .andExpect(jsonPath("$.createdAt").value(sameInstant(DEFAULT_CREATED_AT)))
            .andExpect(jsonPath("$.updatedAt").value(sameInstant(DEFAULT_UPDATED_AT)));
    }

    @Test
    @Transactional
    void getNonExistingLoaiThienTai() throws Exception {
        // Get the loaiThienTai
        restLoaiThienTaiMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingLoaiThienTai() throws Exception {
        // Initialize the database
        insertedLoaiThienTai = loaiThienTaiRepository.saveAndFlush(loaiThienTai);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the loaiThienTai
        LoaiThienTai updatedLoaiThienTai = loaiThienTaiRepository.findById(loaiThienTai.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedLoaiThienTai are not directly saved in db
        em.detach(updatedLoaiThienTai);
        updatedLoaiThienTai
            .tenLoaiThienTai(UPDATED_TEN_LOAI_THIEN_TAI)
            .moTa(UPDATED_MO_TA)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);
        LoaiThienTaiDTO loaiThienTaiDTO = loaiThienTaiMapper.toDto(updatedLoaiThienTai);

        restLoaiThienTaiMockMvc
            .perform(
                put(ENTITY_API_URL_ID, loaiThienTaiDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(loaiThienTaiDTO))
            )
            .andExpect(status().isOk());

        // Validate the LoaiThienTai in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedLoaiThienTaiToMatchAllProperties(updatedLoaiThienTai);
    }

    @Test
    @Transactional
    void putNonExistingLoaiThienTai() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        loaiThienTai.setId(longCount.incrementAndGet());

        // Create the LoaiThienTai
        LoaiThienTaiDTO loaiThienTaiDTO = loaiThienTaiMapper.toDto(loaiThienTai);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLoaiThienTaiMockMvc
            .perform(
                put(ENTITY_API_URL_ID, loaiThienTaiDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(loaiThienTaiDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the LoaiThienTai in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchLoaiThienTai() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        loaiThienTai.setId(longCount.incrementAndGet());

        // Create the LoaiThienTai
        LoaiThienTaiDTO loaiThienTaiDTO = loaiThienTaiMapper.toDto(loaiThienTai);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLoaiThienTaiMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(loaiThienTaiDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the LoaiThienTai in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamLoaiThienTai() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        loaiThienTai.setId(longCount.incrementAndGet());

        // Create the LoaiThienTai
        LoaiThienTaiDTO loaiThienTaiDTO = loaiThienTaiMapper.toDto(loaiThienTai);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLoaiThienTaiMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(loaiThienTaiDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the LoaiThienTai in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateLoaiThienTaiWithPatch() throws Exception {
        // Initialize the database
        insertedLoaiThienTai = loaiThienTaiRepository.saveAndFlush(loaiThienTai);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the loaiThienTai using partial update
        LoaiThienTai partialUpdatedLoaiThienTai = new LoaiThienTai();
        partialUpdatedLoaiThienTai.setId(loaiThienTai.getId());

        partialUpdatedLoaiThienTai.moTa(UPDATED_MO_TA).updatedAt(UPDATED_UPDATED_AT);

        restLoaiThienTaiMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedLoaiThienTai.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedLoaiThienTai))
            )
            .andExpect(status().isOk());

        // Validate the LoaiThienTai in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertLoaiThienTaiUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedLoaiThienTai, loaiThienTai),
            getPersistedLoaiThienTai(loaiThienTai)
        );
    }

    @Test
    @Transactional
    void fullUpdateLoaiThienTaiWithPatch() throws Exception {
        // Initialize the database
        insertedLoaiThienTai = loaiThienTaiRepository.saveAndFlush(loaiThienTai);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the loaiThienTai using partial update
        LoaiThienTai partialUpdatedLoaiThienTai = new LoaiThienTai();
        partialUpdatedLoaiThienTai.setId(loaiThienTai.getId());

        partialUpdatedLoaiThienTai
            .tenLoaiThienTai(UPDATED_TEN_LOAI_THIEN_TAI)
            .moTa(UPDATED_MO_TA)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);

        restLoaiThienTaiMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedLoaiThienTai.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedLoaiThienTai))
            )
            .andExpect(status().isOk());

        // Validate the LoaiThienTai in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertLoaiThienTaiUpdatableFieldsEquals(partialUpdatedLoaiThienTai, getPersistedLoaiThienTai(partialUpdatedLoaiThienTai));
    }

    @Test
    @Transactional
    void patchNonExistingLoaiThienTai() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        loaiThienTai.setId(longCount.incrementAndGet());

        // Create the LoaiThienTai
        LoaiThienTaiDTO loaiThienTaiDTO = loaiThienTaiMapper.toDto(loaiThienTai);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLoaiThienTaiMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, loaiThienTaiDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(loaiThienTaiDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the LoaiThienTai in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchLoaiThienTai() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        loaiThienTai.setId(longCount.incrementAndGet());

        // Create the LoaiThienTai
        LoaiThienTaiDTO loaiThienTaiDTO = loaiThienTaiMapper.toDto(loaiThienTai);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLoaiThienTaiMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(loaiThienTaiDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the LoaiThienTai in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamLoaiThienTai() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        loaiThienTai.setId(longCount.incrementAndGet());

        // Create the LoaiThienTai
        LoaiThienTaiDTO loaiThienTaiDTO = loaiThienTaiMapper.toDto(loaiThienTai);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLoaiThienTaiMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(loaiThienTaiDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the LoaiThienTai in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteLoaiThienTai() throws Exception {
        // Initialize the database
        insertedLoaiThienTai = loaiThienTaiRepository.saveAndFlush(loaiThienTai);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the loaiThienTai
        restLoaiThienTaiMockMvc
            .perform(delete(ENTITY_API_URL_ID, loaiThienTai.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return loaiThienTaiRepository.count();
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

    protected LoaiThienTai getPersistedLoaiThienTai(LoaiThienTai loaiThienTai) {
        return loaiThienTaiRepository.findById(loaiThienTai.getId()).orElseThrow();
    }

    protected void assertPersistedLoaiThienTaiToMatchAllProperties(LoaiThienTai expectedLoaiThienTai) {
        assertLoaiThienTaiAllPropertiesEquals(expectedLoaiThienTai, getPersistedLoaiThienTai(expectedLoaiThienTai));
    }

    protected void assertPersistedLoaiThienTaiToMatchUpdatableProperties(LoaiThienTai expectedLoaiThienTai) {
        assertLoaiThienTaiAllUpdatablePropertiesEquals(expectedLoaiThienTai, getPersistedLoaiThienTai(expectedLoaiThienTai));
    }
}
