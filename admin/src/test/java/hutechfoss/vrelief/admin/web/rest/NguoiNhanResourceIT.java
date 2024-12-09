package hutechfoss.vrelief.admin.web.rest;

import static hutechfoss.vrelief.admin.domain.NguoiNhanAsserts.*;
import static hutechfoss.vrelief.admin.web.rest.TestUtil.createUpdateProxyForBean;
import static hutechfoss.vrelief.admin.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import hutechfoss.vrelief.admin.IntegrationTest;
import hutechfoss.vrelief.admin.domain.NguoiNhan;
import hutechfoss.vrelief.admin.repository.NguoiNhanRepository;
import hutechfoss.vrelief.admin.service.dto.NguoiNhanDTO;
import hutechfoss.vrelief.admin.service.mapper.NguoiNhanMapper;
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
 * Integration tests for the {@link NguoiNhanResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class NguoiNhanResourceIT {

    private static final String DEFAULT_TEN_NGUOI_NHAN = "AAAAAAAAAA";
    private static final String UPDATED_TEN_NGUOI_NHAN = "BBBBBBBBBB";

    private static final String DEFAULT_SO_DIEN_THOAI = "AAAAAAAAAA";
    private static final String UPDATED_SO_DIEN_THOAI = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_DIA_CHI = "AAAAAAAAAA";
    private static final String UPDATED_DIA_CHI = "BBBBBBBBBB";

    private static final Integer DEFAULT_VUNG_ID = 1;
    private static final Integer UPDATED_VUNG_ID = 2;

    private static final Integer DEFAULT_SO_NGUOI_NHAN = 1;
    private static final Integer UPDATED_SO_NGUOI_NHAN = 2;

    private static final ZonedDateTime DEFAULT_CREATED_AT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATED_AT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_UPDATED_AT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_UPDATED_AT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String ENTITY_API_URL = "/api/nguoi-nhans";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private NguoiNhanRepository nguoiNhanRepository;

    @Autowired
    private NguoiNhanMapper nguoiNhanMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restNguoiNhanMockMvc;

    private NguoiNhan nguoiNhan;

    private NguoiNhan insertedNguoiNhan;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NguoiNhan createEntity() {
        return new NguoiNhan()
            .tenNguoiNhan(DEFAULT_TEN_NGUOI_NHAN)
            .soDienThoai(DEFAULT_SO_DIEN_THOAI)
            .email(DEFAULT_EMAIL)
            .diaChi(DEFAULT_DIA_CHI)
            .vungId(DEFAULT_VUNG_ID)
            .soNguoiNhan(DEFAULT_SO_NGUOI_NHAN)
            .createdAt(DEFAULT_CREATED_AT)
            .updatedAt(DEFAULT_UPDATED_AT);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NguoiNhan createUpdatedEntity() {
        return new NguoiNhan()
            .tenNguoiNhan(UPDATED_TEN_NGUOI_NHAN)
            .soDienThoai(UPDATED_SO_DIEN_THOAI)
            .email(UPDATED_EMAIL)
            .diaChi(UPDATED_DIA_CHI)
            .vungId(UPDATED_VUNG_ID)
            .soNguoiNhan(UPDATED_SO_NGUOI_NHAN)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);
    }

    @BeforeEach
    public void initTest() {
        nguoiNhan = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedNguoiNhan != null) {
            nguoiNhanRepository.delete(insertedNguoiNhan);
            insertedNguoiNhan = null;
        }
    }

    @Test
    @Transactional
    void createNguoiNhan() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the NguoiNhan
        NguoiNhanDTO nguoiNhanDTO = nguoiNhanMapper.toDto(nguoiNhan);
        var returnedNguoiNhanDTO = om.readValue(
            restNguoiNhanMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(nguoiNhanDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            NguoiNhanDTO.class
        );

        // Validate the NguoiNhan in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedNguoiNhan = nguoiNhanMapper.toEntity(returnedNguoiNhanDTO);
        assertNguoiNhanUpdatableFieldsEquals(returnedNguoiNhan, getPersistedNguoiNhan(returnedNguoiNhan));

        insertedNguoiNhan = returnedNguoiNhan;
    }

    @Test
    @Transactional
    void createNguoiNhanWithExistingId() throws Exception {
        // Create the NguoiNhan with an existing ID
        nguoiNhan.setId(1L);
        NguoiNhanDTO nguoiNhanDTO = nguoiNhanMapper.toDto(nguoiNhan);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restNguoiNhanMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(nguoiNhanDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NguoiNhan in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkTenNguoiNhanIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        nguoiNhan.setTenNguoiNhan(null);

        // Create the NguoiNhan, which fails.
        NguoiNhanDTO nguoiNhanDTO = nguoiNhanMapper.toDto(nguoiNhan);

        restNguoiNhanMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(nguoiNhanDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllNguoiNhans() throws Exception {
        // Initialize the database
        insertedNguoiNhan = nguoiNhanRepository.saveAndFlush(nguoiNhan);

        // Get all the nguoiNhanList
        restNguoiNhanMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(nguoiNhan.getId().intValue())))
            .andExpect(jsonPath("$.[*].tenNguoiNhan").value(hasItem(DEFAULT_TEN_NGUOI_NHAN)))
            .andExpect(jsonPath("$.[*].soDienThoai").value(hasItem(DEFAULT_SO_DIEN_THOAI)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].diaChi").value(hasItem(DEFAULT_DIA_CHI)))
            .andExpect(jsonPath("$.[*].vungId").value(hasItem(DEFAULT_VUNG_ID)))
            .andExpect(jsonPath("$.[*].soNguoiNhan").value(hasItem(DEFAULT_SO_NGUOI_NHAN)))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(sameInstant(DEFAULT_CREATED_AT))))
            .andExpect(jsonPath("$.[*].updatedAt").value(hasItem(sameInstant(DEFAULT_UPDATED_AT))));
    }

    @Test
    @Transactional
    void getNguoiNhan() throws Exception {
        // Initialize the database
        insertedNguoiNhan = nguoiNhanRepository.saveAndFlush(nguoiNhan);

        // Get the nguoiNhan
        restNguoiNhanMockMvc
            .perform(get(ENTITY_API_URL_ID, nguoiNhan.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(nguoiNhan.getId().intValue()))
            .andExpect(jsonPath("$.tenNguoiNhan").value(DEFAULT_TEN_NGUOI_NHAN))
            .andExpect(jsonPath("$.soDienThoai").value(DEFAULT_SO_DIEN_THOAI))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.diaChi").value(DEFAULT_DIA_CHI))
            .andExpect(jsonPath("$.vungId").value(DEFAULT_VUNG_ID))
            .andExpect(jsonPath("$.soNguoiNhan").value(DEFAULT_SO_NGUOI_NHAN))
            .andExpect(jsonPath("$.createdAt").value(sameInstant(DEFAULT_CREATED_AT)))
            .andExpect(jsonPath("$.updatedAt").value(sameInstant(DEFAULT_UPDATED_AT)));
    }

    @Test
    @Transactional
    void getNonExistingNguoiNhan() throws Exception {
        // Get the nguoiNhan
        restNguoiNhanMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingNguoiNhan() throws Exception {
        // Initialize the database
        insertedNguoiNhan = nguoiNhanRepository.saveAndFlush(nguoiNhan);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the nguoiNhan
        NguoiNhan updatedNguoiNhan = nguoiNhanRepository.findById(nguoiNhan.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedNguoiNhan are not directly saved in db
        em.detach(updatedNguoiNhan);
        updatedNguoiNhan
            .tenNguoiNhan(UPDATED_TEN_NGUOI_NHAN)
            .soDienThoai(UPDATED_SO_DIEN_THOAI)
            .email(UPDATED_EMAIL)
            .diaChi(UPDATED_DIA_CHI)
            .vungId(UPDATED_VUNG_ID)
            .soNguoiNhan(UPDATED_SO_NGUOI_NHAN)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);
        NguoiNhanDTO nguoiNhanDTO = nguoiNhanMapper.toDto(updatedNguoiNhan);

        restNguoiNhanMockMvc
            .perform(
                put(ENTITY_API_URL_ID, nguoiNhanDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(nguoiNhanDTO))
            )
            .andExpect(status().isOk());

        // Validate the NguoiNhan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedNguoiNhanToMatchAllProperties(updatedNguoiNhan);
    }

    @Test
    @Transactional
    void putNonExistingNguoiNhan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        nguoiNhan.setId(longCount.incrementAndGet());

        // Create the NguoiNhan
        NguoiNhanDTO nguoiNhanDTO = nguoiNhanMapper.toDto(nguoiNhan);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNguoiNhanMockMvc
            .perform(
                put(ENTITY_API_URL_ID, nguoiNhanDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(nguoiNhanDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NguoiNhan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchNguoiNhan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        nguoiNhan.setId(longCount.incrementAndGet());

        // Create the NguoiNhan
        NguoiNhanDTO nguoiNhanDTO = nguoiNhanMapper.toDto(nguoiNhan);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNguoiNhanMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(nguoiNhanDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NguoiNhan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamNguoiNhan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        nguoiNhan.setId(longCount.incrementAndGet());

        // Create the NguoiNhan
        NguoiNhanDTO nguoiNhanDTO = nguoiNhanMapper.toDto(nguoiNhan);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNguoiNhanMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(nguoiNhanDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the NguoiNhan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateNguoiNhanWithPatch() throws Exception {
        // Initialize the database
        insertedNguoiNhan = nguoiNhanRepository.saveAndFlush(nguoiNhan);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the nguoiNhan using partial update
        NguoiNhan partialUpdatedNguoiNhan = new NguoiNhan();
        partialUpdatedNguoiNhan.setId(nguoiNhan.getId());

        partialUpdatedNguoiNhan.vungId(UPDATED_VUNG_ID).soNguoiNhan(UPDATED_SO_NGUOI_NHAN);

        restNguoiNhanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedNguoiNhan.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedNguoiNhan))
            )
            .andExpect(status().isOk());

        // Validate the NguoiNhan in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertNguoiNhanUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedNguoiNhan, nguoiNhan),
            getPersistedNguoiNhan(nguoiNhan)
        );
    }

    @Test
    @Transactional
    void fullUpdateNguoiNhanWithPatch() throws Exception {
        // Initialize the database
        insertedNguoiNhan = nguoiNhanRepository.saveAndFlush(nguoiNhan);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the nguoiNhan using partial update
        NguoiNhan partialUpdatedNguoiNhan = new NguoiNhan();
        partialUpdatedNguoiNhan.setId(nguoiNhan.getId());

        partialUpdatedNguoiNhan
            .tenNguoiNhan(UPDATED_TEN_NGUOI_NHAN)
            .soDienThoai(UPDATED_SO_DIEN_THOAI)
            .email(UPDATED_EMAIL)
            .diaChi(UPDATED_DIA_CHI)
            .vungId(UPDATED_VUNG_ID)
            .soNguoiNhan(UPDATED_SO_NGUOI_NHAN)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);

        restNguoiNhanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedNguoiNhan.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedNguoiNhan))
            )
            .andExpect(status().isOk());

        // Validate the NguoiNhan in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertNguoiNhanUpdatableFieldsEquals(partialUpdatedNguoiNhan, getPersistedNguoiNhan(partialUpdatedNguoiNhan));
    }

    @Test
    @Transactional
    void patchNonExistingNguoiNhan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        nguoiNhan.setId(longCount.incrementAndGet());

        // Create the NguoiNhan
        NguoiNhanDTO nguoiNhanDTO = nguoiNhanMapper.toDto(nguoiNhan);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNguoiNhanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, nguoiNhanDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(nguoiNhanDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NguoiNhan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchNguoiNhan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        nguoiNhan.setId(longCount.incrementAndGet());

        // Create the NguoiNhan
        NguoiNhanDTO nguoiNhanDTO = nguoiNhanMapper.toDto(nguoiNhan);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNguoiNhanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(nguoiNhanDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NguoiNhan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamNguoiNhan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        nguoiNhan.setId(longCount.incrementAndGet());

        // Create the NguoiNhan
        NguoiNhanDTO nguoiNhanDTO = nguoiNhanMapper.toDto(nguoiNhan);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNguoiNhanMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(nguoiNhanDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the NguoiNhan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteNguoiNhan() throws Exception {
        // Initialize the database
        insertedNguoiNhan = nguoiNhanRepository.saveAndFlush(nguoiNhan);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the nguoiNhan
        restNguoiNhanMockMvc
            .perform(delete(ENTITY_API_URL_ID, nguoiNhan.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return nguoiNhanRepository.count();
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

    protected NguoiNhan getPersistedNguoiNhan(NguoiNhan nguoiNhan) {
        return nguoiNhanRepository.findById(nguoiNhan.getId()).orElseThrow();
    }

    protected void assertPersistedNguoiNhanToMatchAllProperties(NguoiNhan expectedNguoiNhan) {
        assertNguoiNhanAllPropertiesEquals(expectedNguoiNhan, getPersistedNguoiNhan(expectedNguoiNhan));
    }

    protected void assertPersistedNguoiNhanToMatchUpdatableProperties(NguoiNhan expectedNguoiNhan) {
        assertNguoiNhanAllUpdatablePropertiesEquals(expectedNguoiNhan, getPersistedNguoiNhan(expectedNguoiNhan));
    }
}
