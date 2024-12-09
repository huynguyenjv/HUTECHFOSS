package hutechfoss.vrelief.admin.web.rest;

import static hutechfoss.vrelief.admin.domain.ChiTietDieuPhoiAsserts.*;
import static hutechfoss.vrelief.admin.web.rest.TestUtil.createUpdateProxyForBean;
import static hutechfoss.vrelief.admin.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import hutechfoss.vrelief.admin.IntegrationTest;
import hutechfoss.vrelief.admin.domain.ChiTietDieuPhoi;
import hutechfoss.vrelief.admin.repository.ChiTietDieuPhoiRepository;
import hutechfoss.vrelief.admin.service.dto.ChiTietDieuPhoiDTO;
import hutechfoss.vrelief.admin.service.mapper.ChiTietDieuPhoiMapper;
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
 * Integration tests for the {@link ChiTietDieuPhoiResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ChiTietDieuPhoiResourceIT {

    private static final Integer DEFAULT_PHIEU_DIEU_PHOI_ID = 1;
    private static final Integer UPDATED_PHIEU_DIEU_PHOI_ID = 2;

    private static final Integer DEFAULT_NHU_YEU_PHAM_ID = 1;
    private static final Integer UPDATED_NHU_YEU_PHAM_ID = 2;

    private static final Integer DEFAULT_SO_LUONG = 1;
    private static final Integer UPDATED_SO_LUONG = 2;

    private static final ZonedDateTime DEFAULT_CREATED_AT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATED_AT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_UPDATED_AT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_UPDATED_AT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String ENTITY_API_URL = "/api/chi-tiet-dieu-phois";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private ChiTietDieuPhoiRepository chiTietDieuPhoiRepository;

    @Autowired
    private ChiTietDieuPhoiMapper chiTietDieuPhoiMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restChiTietDieuPhoiMockMvc;

    private ChiTietDieuPhoi chiTietDieuPhoi;

    private ChiTietDieuPhoi insertedChiTietDieuPhoi;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ChiTietDieuPhoi createEntity() {
        return new ChiTietDieuPhoi()
            .phieuDieuPhoiId(DEFAULT_PHIEU_DIEU_PHOI_ID)
            .nhuYeuPhamId(DEFAULT_NHU_YEU_PHAM_ID)
            .soLuong(DEFAULT_SO_LUONG)
            .createdAt(DEFAULT_CREATED_AT)
            .updatedAt(DEFAULT_UPDATED_AT);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ChiTietDieuPhoi createUpdatedEntity() {
        return new ChiTietDieuPhoi()
            .phieuDieuPhoiId(UPDATED_PHIEU_DIEU_PHOI_ID)
            .nhuYeuPhamId(UPDATED_NHU_YEU_PHAM_ID)
            .soLuong(UPDATED_SO_LUONG)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);
    }

    @BeforeEach
    public void initTest() {
        chiTietDieuPhoi = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedChiTietDieuPhoi != null) {
            chiTietDieuPhoiRepository.delete(insertedChiTietDieuPhoi);
            insertedChiTietDieuPhoi = null;
        }
    }

    @Test
    @Transactional
    void createChiTietDieuPhoi() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the ChiTietDieuPhoi
        ChiTietDieuPhoiDTO chiTietDieuPhoiDTO = chiTietDieuPhoiMapper.toDto(chiTietDieuPhoi);
        var returnedChiTietDieuPhoiDTO = om.readValue(
            restChiTietDieuPhoiMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(chiTietDieuPhoiDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            ChiTietDieuPhoiDTO.class
        );

        // Validate the ChiTietDieuPhoi in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedChiTietDieuPhoi = chiTietDieuPhoiMapper.toEntity(returnedChiTietDieuPhoiDTO);
        assertChiTietDieuPhoiUpdatableFieldsEquals(returnedChiTietDieuPhoi, getPersistedChiTietDieuPhoi(returnedChiTietDieuPhoi));

        insertedChiTietDieuPhoi = returnedChiTietDieuPhoi;
    }

    @Test
    @Transactional
    void createChiTietDieuPhoiWithExistingId() throws Exception {
        // Create the ChiTietDieuPhoi with an existing ID
        chiTietDieuPhoi.setId(1L);
        ChiTietDieuPhoiDTO chiTietDieuPhoiDTO = chiTietDieuPhoiMapper.toDto(chiTietDieuPhoi);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restChiTietDieuPhoiMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(chiTietDieuPhoiDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ChiTietDieuPhoi in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllChiTietDieuPhois() throws Exception {
        // Initialize the database
        insertedChiTietDieuPhoi = chiTietDieuPhoiRepository.saveAndFlush(chiTietDieuPhoi);

        // Get all the chiTietDieuPhoiList
        restChiTietDieuPhoiMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(chiTietDieuPhoi.getId().intValue())))
            .andExpect(jsonPath("$.[*].phieuDieuPhoiId").value(hasItem(DEFAULT_PHIEU_DIEU_PHOI_ID)))
            .andExpect(jsonPath("$.[*].nhuYeuPhamId").value(hasItem(DEFAULT_NHU_YEU_PHAM_ID)))
            .andExpect(jsonPath("$.[*].soLuong").value(hasItem(DEFAULT_SO_LUONG)))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(sameInstant(DEFAULT_CREATED_AT))))
            .andExpect(jsonPath("$.[*].updatedAt").value(hasItem(sameInstant(DEFAULT_UPDATED_AT))));
    }

    @Test
    @Transactional
    void getChiTietDieuPhoi() throws Exception {
        // Initialize the database
        insertedChiTietDieuPhoi = chiTietDieuPhoiRepository.saveAndFlush(chiTietDieuPhoi);

        // Get the chiTietDieuPhoi
        restChiTietDieuPhoiMockMvc
            .perform(get(ENTITY_API_URL_ID, chiTietDieuPhoi.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(chiTietDieuPhoi.getId().intValue()))
            .andExpect(jsonPath("$.phieuDieuPhoiId").value(DEFAULT_PHIEU_DIEU_PHOI_ID))
            .andExpect(jsonPath("$.nhuYeuPhamId").value(DEFAULT_NHU_YEU_PHAM_ID))
            .andExpect(jsonPath("$.soLuong").value(DEFAULT_SO_LUONG))
            .andExpect(jsonPath("$.createdAt").value(sameInstant(DEFAULT_CREATED_AT)))
            .andExpect(jsonPath("$.updatedAt").value(sameInstant(DEFAULT_UPDATED_AT)));
    }

    @Test
    @Transactional
    void getNonExistingChiTietDieuPhoi() throws Exception {
        // Get the chiTietDieuPhoi
        restChiTietDieuPhoiMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingChiTietDieuPhoi() throws Exception {
        // Initialize the database
        insertedChiTietDieuPhoi = chiTietDieuPhoiRepository.saveAndFlush(chiTietDieuPhoi);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the chiTietDieuPhoi
        ChiTietDieuPhoi updatedChiTietDieuPhoi = chiTietDieuPhoiRepository.findById(chiTietDieuPhoi.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedChiTietDieuPhoi are not directly saved in db
        em.detach(updatedChiTietDieuPhoi);
        updatedChiTietDieuPhoi
            .phieuDieuPhoiId(UPDATED_PHIEU_DIEU_PHOI_ID)
            .nhuYeuPhamId(UPDATED_NHU_YEU_PHAM_ID)
            .soLuong(UPDATED_SO_LUONG)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);
        ChiTietDieuPhoiDTO chiTietDieuPhoiDTO = chiTietDieuPhoiMapper.toDto(updatedChiTietDieuPhoi);

        restChiTietDieuPhoiMockMvc
            .perform(
                put(ENTITY_API_URL_ID, chiTietDieuPhoiDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(chiTietDieuPhoiDTO))
            )
            .andExpect(status().isOk());

        // Validate the ChiTietDieuPhoi in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedChiTietDieuPhoiToMatchAllProperties(updatedChiTietDieuPhoi);
    }

    @Test
    @Transactional
    void putNonExistingChiTietDieuPhoi() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        chiTietDieuPhoi.setId(longCount.incrementAndGet());

        // Create the ChiTietDieuPhoi
        ChiTietDieuPhoiDTO chiTietDieuPhoiDTO = chiTietDieuPhoiMapper.toDto(chiTietDieuPhoi);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restChiTietDieuPhoiMockMvc
            .perform(
                put(ENTITY_API_URL_ID, chiTietDieuPhoiDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(chiTietDieuPhoiDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ChiTietDieuPhoi in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchChiTietDieuPhoi() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        chiTietDieuPhoi.setId(longCount.incrementAndGet());

        // Create the ChiTietDieuPhoi
        ChiTietDieuPhoiDTO chiTietDieuPhoiDTO = chiTietDieuPhoiMapper.toDto(chiTietDieuPhoi);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restChiTietDieuPhoiMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(chiTietDieuPhoiDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ChiTietDieuPhoi in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamChiTietDieuPhoi() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        chiTietDieuPhoi.setId(longCount.incrementAndGet());

        // Create the ChiTietDieuPhoi
        ChiTietDieuPhoiDTO chiTietDieuPhoiDTO = chiTietDieuPhoiMapper.toDto(chiTietDieuPhoi);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restChiTietDieuPhoiMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(chiTietDieuPhoiDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the ChiTietDieuPhoi in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateChiTietDieuPhoiWithPatch() throws Exception {
        // Initialize the database
        insertedChiTietDieuPhoi = chiTietDieuPhoiRepository.saveAndFlush(chiTietDieuPhoi);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the chiTietDieuPhoi using partial update
        ChiTietDieuPhoi partialUpdatedChiTietDieuPhoi = new ChiTietDieuPhoi();
        partialUpdatedChiTietDieuPhoi.setId(chiTietDieuPhoi.getId());

        partialUpdatedChiTietDieuPhoi.createdAt(UPDATED_CREATED_AT);

        restChiTietDieuPhoiMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedChiTietDieuPhoi.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedChiTietDieuPhoi))
            )
            .andExpect(status().isOk());

        // Validate the ChiTietDieuPhoi in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertChiTietDieuPhoiUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedChiTietDieuPhoi, chiTietDieuPhoi),
            getPersistedChiTietDieuPhoi(chiTietDieuPhoi)
        );
    }

    @Test
    @Transactional
    void fullUpdateChiTietDieuPhoiWithPatch() throws Exception {
        // Initialize the database
        insertedChiTietDieuPhoi = chiTietDieuPhoiRepository.saveAndFlush(chiTietDieuPhoi);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the chiTietDieuPhoi using partial update
        ChiTietDieuPhoi partialUpdatedChiTietDieuPhoi = new ChiTietDieuPhoi();
        partialUpdatedChiTietDieuPhoi.setId(chiTietDieuPhoi.getId());

        partialUpdatedChiTietDieuPhoi
            .phieuDieuPhoiId(UPDATED_PHIEU_DIEU_PHOI_ID)
            .nhuYeuPhamId(UPDATED_NHU_YEU_PHAM_ID)
            .soLuong(UPDATED_SO_LUONG)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);

        restChiTietDieuPhoiMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedChiTietDieuPhoi.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedChiTietDieuPhoi))
            )
            .andExpect(status().isOk());

        // Validate the ChiTietDieuPhoi in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertChiTietDieuPhoiUpdatableFieldsEquals(
            partialUpdatedChiTietDieuPhoi,
            getPersistedChiTietDieuPhoi(partialUpdatedChiTietDieuPhoi)
        );
    }

    @Test
    @Transactional
    void patchNonExistingChiTietDieuPhoi() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        chiTietDieuPhoi.setId(longCount.incrementAndGet());

        // Create the ChiTietDieuPhoi
        ChiTietDieuPhoiDTO chiTietDieuPhoiDTO = chiTietDieuPhoiMapper.toDto(chiTietDieuPhoi);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restChiTietDieuPhoiMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, chiTietDieuPhoiDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(chiTietDieuPhoiDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ChiTietDieuPhoi in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchChiTietDieuPhoi() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        chiTietDieuPhoi.setId(longCount.incrementAndGet());

        // Create the ChiTietDieuPhoi
        ChiTietDieuPhoiDTO chiTietDieuPhoiDTO = chiTietDieuPhoiMapper.toDto(chiTietDieuPhoi);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restChiTietDieuPhoiMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(chiTietDieuPhoiDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ChiTietDieuPhoi in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamChiTietDieuPhoi() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        chiTietDieuPhoi.setId(longCount.incrementAndGet());

        // Create the ChiTietDieuPhoi
        ChiTietDieuPhoiDTO chiTietDieuPhoiDTO = chiTietDieuPhoiMapper.toDto(chiTietDieuPhoi);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restChiTietDieuPhoiMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(chiTietDieuPhoiDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the ChiTietDieuPhoi in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteChiTietDieuPhoi() throws Exception {
        // Initialize the database
        insertedChiTietDieuPhoi = chiTietDieuPhoiRepository.saveAndFlush(chiTietDieuPhoi);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the chiTietDieuPhoi
        restChiTietDieuPhoiMockMvc
            .perform(delete(ENTITY_API_URL_ID, chiTietDieuPhoi.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return chiTietDieuPhoiRepository.count();
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

    protected ChiTietDieuPhoi getPersistedChiTietDieuPhoi(ChiTietDieuPhoi chiTietDieuPhoi) {
        return chiTietDieuPhoiRepository.findById(chiTietDieuPhoi.getId()).orElseThrow();
    }

    protected void assertPersistedChiTietDieuPhoiToMatchAllProperties(ChiTietDieuPhoi expectedChiTietDieuPhoi) {
        assertChiTietDieuPhoiAllPropertiesEquals(expectedChiTietDieuPhoi, getPersistedChiTietDieuPhoi(expectedChiTietDieuPhoi));
    }

    protected void assertPersistedChiTietDieuPhoiToMatchUpdatableProperties(ChiTietDieuPhoi expectedChiTietDieuPhoi) {
        assertChiTietDieuPhoiAllUpdatablePropertiesEquals(expectedChiTietDieuPhoi, getPersistedChiTietDieuPhoi(expectedChiTietDieuPhoi));
    }
}
