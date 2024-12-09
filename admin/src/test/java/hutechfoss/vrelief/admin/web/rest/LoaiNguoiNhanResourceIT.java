package hutechfoss.vrelief.admin.web.rest;

import static hutechfoss.vrelief.admin.domain.LoaiNguoiNhanAsserts.*;
import static hutechfoss.vrelief.admin.web.rest.TestUtil.createUpdateProxyForBean;
import static hutechfoss.vrelief.admin.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import hutechfoss.vrelief.admin.IntegrationTest;
import hutechfoss.vrelief.admin.domain.LoaiNguoiNhan;
import hutechfoss.vrelief.admin.repository.LoaiNguoiNhanRepository;
import hutechfoss.vrelief.admin.service.dto.LoaiNguoiNhanDTO;
import hutechfoss.vrelief.admin.service.mapper.LoaiNguoiNhanMapper;
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
 * Integration tests for the {@link LoaiNguoiNhanResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class LoaiNguoiNhanResourceIT {

    private static final String DEFAULT_TEN_LOAI = "AAAAAAAAAA";
    private static final String UPDATED_TEN_LOAI = "BBBBBBBBBB";

    private static final String DEFAULT_MO_TA = "AAAAAAAAAA";
    private static final String UPDATED_MO_TA = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_CREATED_AT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATED_AT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_UPDATED_AT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_UPDATED_AT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String ENTITY_API_URL = "/api/loai-nguoi-nhans";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private LoaiNguoiNhanRepository loaiNguoiNhanRepository;

    @Autowired
    private LoaiNguoiNhanMapper loaiNguoiNhanMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restLoaiNguoiNhanMockMvc;

    private LoaiNguoiNhan loaiNguoiNhan;

    private LoaiNguoiNhan insertedLoaiNguoiNhan;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LoaiNguoiNhan createEntity() {
        return new LoaiNguoiNhan()
            .tenLoai(DEFAULT_TEN_LOAI)
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
    public static LoaiNguoiNhan createUpdatedEntity() {
        return new LoaiNguoiNhan()
            .tenLoai(UPDATED_TEN_LOAI)
            .moTa(UPDATED_MO_TA)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);
    }

    @BeforeEach
    public void initTest() {
        loaiNguoiNhan = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedLoaiNguoiNhan != null) {
            loaiNguoiNhanRepository.delete(insertedLoaiNguoiNhan);
            insertedLoaiNguoiNhan = null;
        }
    }

    @Test
    @Transactional
    void createLoaiNguoiNhan() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the LoaiNguoiNhan
        LoaiNguoiNhanDTO loaiNguoiNhanDTO = loaiNguoiNhanMapper.toDto(loaiNguoiNhan);
        var returnedLoaiNguoiNhanDTO = om.readValue(
            restLoaiNguoiNhanMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(loaiNguoiNhanDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            LoaiNguoiNhanDTO.class
        );

        // Validate the LoaiNguoiNhan in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedLoaiNguoiNhan = loaiNguoiNhanMapper.toEntity(returnedLoaiNguoiNhanDTO);
        assertLoaiNguoiNhanUpdatableFieldsEquals(returnedLoaiNguoiNhan, getPersistedLoaiNguoiNhan(returnedLoaiNguoiNhan));

        insertedLoaiNguoiNhan = returnedLoaiNguoiNhan;
    }

    @Test
    @Transactional
    void createLoaiNguoiNhanWithExistingId() throws Exception {
        // Create the LoaiNguoiNhan with an existing ID
        loaiNguoiNhan.setId(1L);
        LoaiNguoiNhanDTO loaiNguoiNhanDTO = loaiNguoiNhanMapper.toDto(loaiNguoiNhan);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restLoaiNguoiNhanMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(loaiNguoiNhanDTO)))
            .andExpect(status().isBadRequest());

        // Validate the LoaiNguoiNhan in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkTenLoaiIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        loaiNguoiNhan.setTenLoai(null);

        // Create the LoaiNguoiNhan, which fails.
        LoaiNguoiNhanDTO loaiNguoiNhanDTO = loaiNguoiNhanMapper.toDto(loaiNguoiNhan);

        restLoaiNguoiNhanMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(loaiNguoiNhanDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllLoaiNguoiNhans() throws Exception {
        // Initialize the database
        insertedLoaiNguoiNhan = loaiNguoiNhanRepository.saveAndFlush(loaiNguoiNhan);

        // Get all the loaiNguoiNhanList
        restLoaiNguoiNhanMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(loaiNguoiNhan.getId().intValue())))
            .andExpect(jsonPath("$.[*].tenLoai").value(hasItem(DEFAULT_TEN_LOAI)))
            .andExpect(jsonPath("$.[*].moTa").value(hasItem(DEFAULT_MO_TA)))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(sameInstant(DEFAULT_CREATED_AT))))
            .andExpect(jsonPath("$.[*].updatedAt").value(hasItem(sameInstant(DEFAULT_UPDATED_AT))));
    }

    @Test
    @Transactional
    void getLoaiNguoiNhan() throws Exception {
        // Initialize the database
        insertedLoaiNguoiNhan = loaiNguoiNhanRepository.saveAndFlush(loaiNguoiNhan);

        // Get the loaiNguoiNhan
        restLoaiNguoiNhanMockMvc
            .perform(get(ENTITY_API_URL_ID, loaiNguoiNhan.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(loaiNguoiNhan.getId().intValue()))
            .andExpect(jsonPath("$.tenLoai").value(DEFAULT_TEN_LOAI))
            .andExpect(jsonPath("$.moTa").value(DEFAULT_MO_TA))
            .andExpect(jsonPath("$.createdAt").value(sameInstant(DEFAULT_CREATED_AT)))
            .andExpect(jsonPath("$.updatedAt").value(sameInstant(DEFAULT_UPDATED_AT)));
    }

    @Test
    @Transactional
    void getNonExistingLoaiNguoiNhan() throws Exception {
        // Get the loaiNguoiNhan
        restLoaiNguoiNhanMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingLoaiNguoiNhan() throws Exception {
        // Initialize the database
        insertedLoaiNguoiNhan = loaiNguoiNhanRepository.saveAndFlush(loaiNguoiNhan);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the loaiNguoiNhan
        LoaiNguoiNhan updatedLoaiNguoiNhan = loaiNguoiNhanRepository.findById(loaiNguoiNhan.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedLoaiNguoiNhan are not directly saved in db
        em.detach(updatedLoaiNguoiNhan);
        updatedLoaiNguoiNhan.tenLoai(UPDATED_TEN_LOAI).moTa(UPDATED_MO_TA).createdAt(UPDATED_CREATED_AT).updatedAt(UPDATED_UPDATED_AT);
        LoaiNguoiNhanDTO loaiNguoiNhanDTO = loaiNguoiNhanMapper.toDto(updatedLoaiNguoiNhan);

        restLoaiNguoiNhanMockMvc
            .perform(
                put(ENTITY_API_URL_ID, loaiNguoiNhanDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(loaiNguoiNhanDTO))
            )
            .andExpect(status().isOk());

        // Validate the LoaiNguoiNhan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedLoaiNguoiNhanToMatchAllProperties(updatedLoaiNguoiNhan);
    }

    @Test
    @Transactional
    void putNonExistingLoaiNguoiNhan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        loaiNguoiNhan.setId(longCount.incrementAndGet());

        // Create the LoaiNguoiNhan
        LoaiNguoiNhanDTO loaiNguoiNhanDTO = loaiNguoiNhanMapper.toDto(loaiNguoiNhan);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLoaiNguoiNhanMockMvc
            .perform(
                put(ENTITY_API_URL_ID, loaiNguoiNhanDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(loaiNguoiNhanDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the LoaiNguoiNhan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchLoaiNguoiNhan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        loaiNguoiNhan.setId(longCount.incrementAndGet());

        // Create the LoaiNguoiNhan
        LoaiNguoiNhanDTO loaiNguoiNhanDTO = loaiNguoiNhanMapper.toDto(loaiNguoiNhan);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLoaiNguoiNhanMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(loaiNguoiNhanDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the LoaiNguoiNhan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamLoaiNguoiNhan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        loaiNguoiNhan.setId(longCount.incrementAndGet());

        // Create the LoaiNguoiNhan
        LoaiNguoiNhanDTO loaiNguoiNhanDTO = loaiNguoiNhanMapper.toDto(loaiNguoiNhan);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLoaiNguoiNhanMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(loaiNguoiNhanDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the LoaiNguoiNhan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateLoaiNguoiNhanWithPatch() throws Exception {
        // Initialize the database
        insertedLoaiNguoiNhan = loaiNguoiNhanRepository.saveAndFlush(loaiNguoiNhan);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the loaiNguoiNhan using partial update
        LoaiNguoiNhan partialUpdatedLoaiNguoiNhan = new LoaiNguoiNhan();
        partialUpdatedLoaiNguoiNhan.setId(loaiNguoiNhan.getId());

        restLoaiNguoiNhanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedLoaiNguoiNhan.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedLoaiNguoiNhan))
            )
            .andExpect(status().isOk());

        // Validate the LoaiNguoiNhan in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertLoaiNguoiNhanUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedLoaiNguoiNhan, loaiNguoiNhan),
            getPersistedLoaiNguoiNhan(loaiNguoiNhan)
        );
    }

    @Test
    @Transactional
    void fullUpdateLoaiNguoiNhanWithPatch() throws Exception {
        // Initialize the database
        insertedLoaiNguoiNhan = loaiNguoiNhanRepository.saveAndFlush(loaiNguoiNhan);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the loaiNguoiNhan using partial update
        LoaiNguoiNhan partialUpdatedLoaiNguoiNhan = new LoaiNguoiNhan();
        partialUpdatedLoaiNguoiNhan.setId(loaiNguoiNhan.getId());

        partialUpdatedLoaiNguoiNhan
            .tenLoai(UPDATED_TEN_LOAI)
            .moTa(UPDATED_MO_TA)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);

        restLoaiNguoiNhanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedLoaiNguoiNhan.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedLoaiNguoiNhan))
            )
            .andExpect(status().isOk());

        // Validate the LoaiNguoiNhan in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertLoaiNguoiNhanUpdatableFieldsEquals(partialUpdatedLoaiNguoiNhan, getPersistedLoaiNguoiNhan(partialUpdatedLoaiNguoiNhan));
    }

    @Test
    @Transactional
    void patchNonExistingLoaiNguoiNhan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        loaiNguoiNhan.setId(longCount.incrementAndGet());

        // Create the LoaiNguoiNhan
        LoaiNguoiNhanDTO loaiNguoiNhanDTO = loaiNguoiNhanMapper.toDto(loaiNguoiNhan);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLoaiNguoiNhanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, loaiNguoiNhanDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(loaiNguoiNhanDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the LoaiNguoiNhan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchLoaiNguoiNhan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        loaiNguoiNhan.setId(longCount.incrementAndGet());

        // Create the LoaiNguoiNhan
        LoaiNguoiNhanDTO loaiNguoiNhanDTO = loaiNguoiNhanMapper.toDto(loaiNguoiNhan);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLoaiNguoiNhanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(loaiNguoiNhanDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the LoaiNguoiNhan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamLoaiNguoiNhan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        loaiNguoiNhan.setId(longCount.incrementAndGet());

        // Create the LoaiNguoiNhan
        LoaiNguoiNhanDTO loaiNguoiNhanDTO = loaiNguoiNhanMapper.toDto(loaiNguoiNhan);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLoaiNguoiNhanMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(loaiNguoiNhanDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the LoaiNguoiNhan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteLoaiNguoiNhan() throws Exception {
        // Initialize the database
        insertedLoaiNguoiNhan = loaiNguoiNhanRepository.saveAndFlush(loaiNguoiNhan);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the loaiNguoiNhan
        restLoaiNguoiNhanMockMvc
            .perform(delete(ENTITY_API_URL_ID, loaiNguoiNhan.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return loaiNguoiNhanRepository.count();
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

    protected LoaiNguoiNhan getPersistedLoaiNguoiNhan(LoaiNguoiNhan loaiNguoiNhan) {
        return loaiNguoiNhanRepository.findById(loaiNguoiNhan.getId()).orElseThrow();
    }

    protected void assertPersistedLoaiNguoiNhanToMatchAllProperties(LoaiNguoiNhan expectedLoaiNguoiNhan) {
        assertLoaiNguoiNhanAllPropertiesEquals(expectedLoaiNguoiNhan, getPersistedLoaiNguoiNhan(expectedLoaiNguoiNhan));
    }

    protected void assertPersistedLoaiNguoiNhanToMatchUpdatableProperties(LoaiNguoiNhan expectedLoaiNguoiNhan) {
        assertLoaiNguoiNhanAllUpdatablePropertiesEquals(expectedLoaiNguoiNhan, getPersistedLoaiNguoiNhan(expectedLoaiNguoiNhan));
    }
}
