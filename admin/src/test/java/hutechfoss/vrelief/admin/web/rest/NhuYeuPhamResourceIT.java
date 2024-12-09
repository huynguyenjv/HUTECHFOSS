package hutechfoss.vrelief.admin.web.rest;

import static hutechfoss.vrelief.admin.domain.NhuYeuPhamAsserts.*;
import static hutechfoss.vrelief.admin.web.rest.TestUtil.createUpdateProxyForBean;
import static hutechfoss.vrelief.admin.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import hutechfoss.vrelief.admin.IntegrationTest;
import hutechfoss.vrelief.admin.domain.NhuYeuPham;
import hutechfoss.vrelief.admin.repository.NhuYeuPhamRepository;
import hutechfoss.vrelief.admin.service.dto.NhuYeuPhamDTO;
import hutechfoss.vrelief.admin.service.mapper.NhuYeuPhamMapper;
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
 * Integration tests for the {@link NhuYeuPhamResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class NhuYeuPhamResourceIT {

    private static final String DEFAULT_TEN_NHU_YEU_PHAM = "AAAAAAAAAA";
    private static final String UPDATED_TEN_NHU_YEU_PHAM = "BBBBBBBBBB";

    private static final String DEFAULT_DON_VI_TINH = "AAAAAAAAAA";
    private static final String UPDATED_DON_VI_TINH = "BBBBBBBBBB";

    private static final Integer DEFAULT_LOAI_NHU_YEU_PHAM_ID = 1;
    private static final Integer UPDATED_LOAI_NHU_YEU_PHAM_ID = 2;

    private static final Integer DEFAULT_MUC_CANH_BAO = 1;
    private static final Integer UPDATED_MUC_CANH_BAO = 2;

    private static final ZonedDateTime DEFAULT_CREATED_AT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATED_AT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_UPDATED_AT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_UPDATED_AT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String ENTITY_API_URL = "/api/nhu-yeu-phams";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private NhuYeuPhamRepository nhuYeuPhamRepository;

    @Autowired
    private NhuYeuPhamMapper nhuYeuPhamMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restNhuYeuPhamMockMvc;

    private NhuYeuPham nhuYeuPham;

    private NhuYeuPham insertedNhuYeuPham;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NhuYeuPham createEntity() {
        return new NhuYeuPham()
            .tenNhuYeuPham(DEFAULT_TEN_NHU_YEU_PHAM)
            .donViTinh(DEFAULT_DON_VI_TINH)
            .loaiNhuYeuPhamId(DEFAULT_LOAI_NHU_YEU_PHAM_ID)
            .mucCanhBao(DEFAULT_MUC_CANH_BAO)
            .createdAt(DEFAULT_CREATED_AT)
            .updatedAt(DEFAULT_UPDATED_AT);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NhuYeuPham createUpdatedEntity() {
        return new NhuYeuPham()
            .tenNhuYeuPham(UPDATED_TEN_NHU_YEU_PHAM)
            .donViTinh(UPDATED_DON_VI_TINH)
            .loaiNhuYeuPhamId(UPDATED_LOAI_NHU_YEU_PHAM_ID)
            .mucCanhBao(UPDATED_MUC_CANH_BAO)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);
    }

    @BeforeEach
    public void initTest() {
        nhuYeuPham = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedNhuYeuPham != null) {
            nhuYeuPhamRepository.delete(insertedNhuYeuPham);
            insertedNhuYeuPham = null;
        }
    }

    @Test
    @Transactional
    void createNhuYeuPham() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the NhuYeuPham
        NhuYeuPhamDTO nhuYeuPhamDTO = nhuYeuPhamMapper.toDto(nhuYeuPham);
        var returnedNhuYeuPhamDTO = om.readValue(
            restNhuYeuPhamMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(nhuYeuPhamDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            NhuYeuPhamDTO.class
        );

        // Validate the NhuYeuPham in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedNhuYeuPham = nhuYeuPhamMapper.toEntity(returnedNhuYeuPhamDTO);
        assertNhuYeuPhamUpdatableFieldsEquals(returnedNhuYeuPham, getPersistedNhuYeuPham(returnedNhuYeuPham));

        insertedNhuYeuPham = returnedNhuYeuPham;
    }

    @Test
    @Transactional
    void createNhuYeuPhamWithExistingId() throws Exception {
        // Create the NhuYeuPham with an existing ID
        nhuYeuPham.setId(1L);
        NhuYeuPhamDTO nhuYeuPhamDTO = nhuYeuPhamMapper.toDto(nhuYeuPham);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restNhuYeuPhamMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(nhuYeuPhamDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NhuYeuPham in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkTenNhuYeuPhamIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        nhuYeuPham.setTenNhuYeuPham(null);

        // Create the NhuYeuPham, which fails.
        NhuYeuPhamDTO nhuYeuPhamDTO = nhuYeuPhamMapper.toDto(nhuYeuPham);

        restNhuYeuPhamMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(nhuYeuPhamDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllNhuYeuPhams() throws Exception {
        // Initialize the database
        insertedNhuYeuPham = nhuYeuPhamRepository.saveAndFlush(nhuYeuPham);

        // Get all the nhuYeuPhamList
        restNhuYeuPhamMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(nhuYeuPham.getId().intValue())))
            .andExpect(jsonPath("$.[*].tenNhuYeuPham").value(hasItem(DEFAULT_TEN_NHU_YEU_PHAM)))
            .andExpect(jsonPath("$.[*].donViTinh").value(hasItem(DEFAULT_DON_VI_TINH)))
            .andExpect(jsonPath("$.[*].loaiNhuYeuPhamId").value(hasItem(DEFAULT_LOAI_NHU_YEU_PHAM_ID)))
            .andExpect(jsonPath("$.[*].mucCanhBao").value(hasItem(DEFAULT_MUC_CANH_BAO)))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(sameInstant(DEFAULT_CREATED_AT))))
            .andExpect(jsonPath("$.[*].updatedAt").value(hasItem(sameInstant(DEFAULT_UPDATED_AT))));
    }

    @Test
    @Transactional
    void getNhuYeuPham() throws Exception {
        // Initialize the database
        insertedNhuYeuPham = nhuYeuPhamRepository.saveAndFlush(nhuYeuPham);

        // Get the nhuYeuPham
        restNhuYeuPhamMockMvc
            .perform(get(ENTITY_API_URL_ID, nhuYeuPham.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(nhuYeuPham.getId().intValue()))
            .andExpect(jsonPath("$.tenNhuYeuPham").value(DEFAULT_TEN_NHU_YEU_PHAM))
            .andExpect(jsonPath("$.donViTinh").value(DEFAULT_DON_VI_TINH))
            .andExpect(jsonPath("$.loaiNhuYeuPhamId").value(DEFAULT_LOAI_NHU_YEU_PHAM_ID))
            .andExpect(jsonPath("$.mucCanhBao").value(DEFAULT_MUC_CANH_BAO))
            .andExpect(jsonPath("$.createdAt").value(sameInstant(DEFAULT_CREATED_AT)))
            .andExpect(jsonPath("$.updatedAt").value(sameInstant(DEFAULT_UPDATED_AT)));
    }

    @Test
    @Transactional
    void getNonExistingNhuYeuPham() throws Exception {
        // Get the nhuYeuPham
        restNhuYeuPhamMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingNhuYeuPham() throws Exception {
        // Initialize the database
        insertedNhuYeuPham = nhuYeuPhamRepository.saveAndFlush(nhuYeuPham);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the nhuYeuPham
        NhuYeuPham updatedNhuYeuPham = nhuYeuPhamRepository.findById(nhuYeuPham.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedNhuYeuPham are not directly saved in db
        em.detach(updatedNhuYeuPham);
        updatedNhuYeuPham
            .tenNhuYeuPham(UPDATED_TEN_NHU_YEU_PHAM)
            .donViTinh(UPDATED_DON_VI_TINH)
            .loaiNhuYeuPhamId(UPDATED_LOAI_NHU_YEU_PHAM_ID)
            .mucCanhBao(UPDATED_MUC_CANH_BAO)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);
        NhuYeuPhamDTO nhuYeuPhamDTO = nhuYeuPhamMapper.toDto(updatedNhuYeuPham);

        restNhuYeuPhamMockMvc
            .perform(
                put(ENTITY_API_URL_ID, nhuYeuPhamDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(nhuYeuPhamDTO))
            )
            .andExpect(status().isOk());

        // Validate the NhuYeuPham in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedNhuYeuPhamToMatchAllProperties(updatedNhuYeuPham);
    }

    @Test
    @Transactional
    void putNonExistingNhuYeuPham() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        nhuYeuPham.setId(longCount.incrementAndGet());

        // Create the NhuYeuPham
        NhuYeuPhamDTO nhuYeuPhamDTO = nhuYeuPhamMapper.toDto(nhuYeuPham);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNhuYeuPhamMockMvc
            .perform(
                put(ENTITY_API_URL_ID, nhuYeuPhamDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(nhuYeuPhamDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NhuYeuPham in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchNhuYeuPham() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        nhuYeuPham.setId(longCount.incrementAndGet());

        // Create the NhuYeuPham
        NhuYeuPhamDTO nhuYeuPhamDTO = nhuYeuPhamMapper.toDto(nhuYeuPham);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNhuYeuPhamMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(nhuYeuPhamDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NhuYeuPham in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamNhuYeuPham() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        nhuYeuPham.setId(longCount.incrementAndGet());

        // Create the NhuYeuPham
        NhuYeuPhamDTO nhuYeuPhamDTO = nhuYeuPhamMapper.toDto(nhuYeuPham);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNhuYeuPhamMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(nhuYeuPhamDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the NhuYeuPham in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateNhuYeuPhamWithPatch() throws Exception {
        // Initialize the database
        insertedNhuYeuPham = nhuYeuPhamRepository.saveAndFlush(nhuYeuPham);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the nhuYeuPham using partial update
        NhuYeuPham partialUpdatedNhuYeuPham = new NhuYeuPham();
        partialUpdatedNhuYeuPham.setId(nhuYeuPham.getId());

        partialUpdatedNhuYeuPham
            .tenNhuYeuPham(UPDATED_TEN_NHU_YEU_PHAM)
            .loaiNhuYeuPhamId(UPDATED_LOAI_NHU_YEU_PHAM_ID)
            .updatedAt(UPDATED_UPDATED_AT);

        restNhuYeuPhamMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedNhuYeuPham.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedNhuYeuPham))
            )
            .andExpect(status().isOk());

        // Validate the NhuYeuPham in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertNhuYeuPhamUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedNhuYeuPham, nhuYeuPham),
            getPersistedNhuYeuPham(nhuYeuPham)
        );
    }

    @Test
    @Transactional
    void fullUpdateNhuYeuPhamWithPatch() throws Exception {
        // Initialize the database
        insertedNhuYeuPham = nhuYeuPhamRepository.saveAndFlush(nhuYeuPham);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the nhuYeuPham using partial update
        NhuYeuPham partialUpdatedNhuYeuPham = new NhuYeuPham();
        partialUpdatedNhuYeuPham.setId(nhuYeuPham.getId());

        partialUpdatedNhuYeuPham
            .tenNhuYeuPham(UPDATED_TEN_NHU_YEU_PHAM)
            .donViTinh(UPDATED_DON_VI_TINH)
            .loaiNhuYeuPhamId(UPDATED_LOAI_NHU_YEU_PHAM_ID)
            .mucCanhBao(UPDATED_MUC_CANH_BAO)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);

        restNhuYeuPhamMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedNhuYeuPham.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedNhuYeuPham))
            )
            .andExpect(status().isOk());

        // Validate the NhuYeuPham in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertNhuYeuPhamUpdatableFieldsEquals(partialUpdatedNhuYeuPham, getPersistedNhuYeuPham(partialUpdatedNhuYeuPham));
    }

    @Test
    @Transactional
    void patchNonExistingNhuYeuPham() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        nhuYeuPham.setId(longCount.incrementAndGet());

        // Create the NhuYeuPham
        NhuYeuPhamDTO nhuYeuPhamDTO = nhuYeuPhamMapper.toDto(nhuYeuPham);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNhuYeuPhamMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, nhuYeuPhamDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(nhuYeuPhamDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NhuYeuPham in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchNhuYeuPham() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        nhuYeuPham.setId(longCount.incrementAndGet());

        // Create the NhuYeuPham
        NhuYeuPhamDTO nhuYeuPhamDTO = nhuYeuPhamMapper.toDto(nhuYeuPham);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNhuYeuPhamMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(nhuYeuPhamDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NhuYeuPham in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamNhuYeuPham() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        nhuYeuPham.setId(longCount.incrementAndGet());

        // Create the NhuYeuPham
        NhuYeuPhamDTO nhuYeuPhamDTO = nhuYeuPhamMapper.toDto(nhuYeuPham);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNhuYeuPhamMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(nhuYeuPhamDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the NhuYeuPham in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteNhuYeuPham() throws Exception {
        // Initialize the database
        insertedNhuYeuPham = nhuYeuPhamRepository.saveAndFlush(nhuYeuPham);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the nhuYeuPham
        restNhuYeuPhamMockMvc
            .perform(delete(ENTITY_API_URL_ID, nhuYeuPham.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return nhuYeuPhamRepository.count();
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

    protected NhuYeuPham getPersistedNhuYeuPham(NhuYeuPham nhuYeuPham) {
        return nhuYeuPhamRepository.findById(nhuYeuPham.getId()).orElseThrow();
    }

    protected void assertPersistedNhuYeuPhamToMatchAllProperties(NhuYeuPham expectedNhuYeuPham) {
        assertNhuYeuPhamAllPropertiesEquals(expectedNhuYeuPham, getPersistedNhuYeuPham(expectedNhuYeuPham));
    }

    protected void assertPersistedNhuYeuPhamToMatchUpdatableProperties(NhuYeuPham expectedNhuYeuPham) {
        assertNhuYeuPhamAllUpdatablePropertiesEquals(expectedNhuYeuPham, getPersistedNhuYeuPham(expectedNhuYeuPham));
    }
}
