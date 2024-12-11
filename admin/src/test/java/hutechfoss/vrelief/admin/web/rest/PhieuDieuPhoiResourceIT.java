package hutechfoss.vrelief.admin.web.rest;

import static hutechfoss.vrelief.admin.domain.PhieuDieuPhoiAsserts.*;
import static hutechfoss.vrelief.admin.web.rest.TestUtil.createUpdateProxyForBean;
import static hutechfoss.vrelief.admin.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import hutechfoss.vrelief.admin.IntegrationTest;
import hutechfoss.vrelief.admin.domain.PhieuDieuPhoi;
import hutechfoss.vrelief.admin.repository.PhieuDieuPhoiRepository;
import hutechfoss.vrelief.admin.service.dto.PhieuDieuPhoiDTO;
import hutechfoss.vrelief.admin.service.mapper.PhieuDieuPhoiMapper;
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
 * Integration tests for the {@link PhieuDieuPhoiResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class PhieuDieuPhoiResourceIT {

    private static final Integer DEFAULT_NHA_CUNG_CAP_ID = 1;
    private static final Integer UPDATED_NHA_CUNG_CAP_ID = 2;

    private static final Integer DEFAULT_NGUOI_NHAN_ID = 1;
    private static final Integer UPDATED_NGUOI_NHAN_ID = 2;

    private static final Integer DEFAULT_TINH_NGUYEN_VIEN_ID = 1;
    private static final Integer UPDATED_TINH_NGUYEN_VIEN_ID = 2;

    private static final Integer DEFAULT_TRANG_THAI_ID = 1;
    private static final Integer UPDATED_TRANG_THAI_ID = 2;

    private static final ZonedDateTime DEFAULT_THOI_GIAN_DEN = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_THOI_GIAN_DEN = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_THOI_GIAN_CHO = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_THOI_GIAN_CHO = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_CREATED_AT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATED_AT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_UPDATED_AT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_UPDATED_AT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String ENTITY_API_URL = "/api/phieu-dieu-phois";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private PhieuDieuPhoiRepository phieuDieuPhoiRepository;

    @Autowired
    private PhieuDieuPhoiMapper phieuDieuPhoiMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPhieuDieuPhoiMockMvc;

    private PhieuDieuPhoi phieuDieuPhoi;

    private PhieuDieuPhoi insertedPhieuDieuPhoi;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PhieuDieuPhoi createEntity() {
        return new PhieuDieuPhoi()
            .nhaCungCapId(DEFAULT_NHA_CUNG_CAP_ID)
            .nguoiNhanId(DEFAULT_NGUOI_NHAN_ID)
            .tinhNguyenVienId(DEFAULT_TINH_NGUYEN_VIEN_ID)
            .trangThaiId(DEFAULT_TRANG_THAI_ID)
            .thoiGianDen(DEFAULT_THOI_GIAN_DEN)
            .thoiGianCho(DEFAULT_THOI_GIAN_CHO)
            .createdAt(DEFAULT_CREATED_AT)
            .updatedAt(DEFAULT_UPDATED_AT);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PhieuDieuPhoi createUpdatedEntity() {
        return new PhieuDieuPhoi()
            .nhaCungCapId(UPDATED_NHA_CUNG_CAP_ID)
            .nguoiNhanId(UPDATED_NGUOI_NHAN_ID)
            .tinhNguyenVienId(UPDATED_TINH_NGUYEN_VIEN_ID)
            .trangThaiId(UPDATED_TRANG_THAI_ID)
            .thoiGianDen(UPDATED_THOI_GIAN_DEN)
            .thoiGianCho(UPDATED_THOI_GIAN_CHO)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);
    }

    @BeforeEach
    public void initTest() {
        phieuDieuPhoi = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedPhieuDieuPhoi != null) {
            phieuDieuPhoiRepository.delete(insertedPhieuDieuPhoi);
            insertedPhieuDieuPhoi = null;
        }
    }

    @Test
    @Transactional
    void createPhieuDieuPhoi() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the PhieuDieuPhoi
        PhieuDieuPhoiDTO phieuDieuPhoiDTO = phieuDieuPhoiMapper.toDto(phieuDieuPhoi);
        var returnedPhieuDieuPhoiDTO = om.readValue(
            restPhieuDieuPhoiMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(phieuDieuPhoiDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            PhieuDieuPhoiDTO.class
        );

        // Validate the PhieuDieuPhoi in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedPhieuDieuPhoi = phieuDieuPhoiMapper.toEntity(returnedPhieuDieuPhoiDTO);
        assertPhieuDieuPhoiUpdatableFieldsEquals(returnedPhieuDieuPhoi, getPersistedPhieuDieuPhoi(returnedPhieuDieuPhoi));

        insertedPhieuDieuPhoi = returnedPhieuDieuPhoi;
    }

    @Test
    @Transactional
    void createPhieuDieuPhoiWithExistingId() throws Exception {
        // Create the PhieuDieuPhoi with an existing ID
        phieuDieuPhoi.setId(1L);
        PhieuDieuPhoiDTO phieuDieuPhoiDTO = phieuDieuPhoiMapper.toDto(phieuDieuPhoi);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restPhieuDieuPhoiMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(phieuDieuPhoiDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PhieuDieuPhoi in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllPhieuDieuPhois() throws Exception {
        // Initialize the database
        insertedPhieuDieuPhoi = phieuDieuPhoiRepository.saveAndFlush(phieuDieuPhoi);

        // Get all the phieuDieuPhoiList
        restPhieuDieuPhoiMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(phieuDieuPhoi.getId().intValue())))
            .andExpect(jsonPath("$.[*].nhaCungCapId").value(hasItem(DEFAULT_NHA_CUNG_CAP_ID)))
            .andExpect(jsonPath("$.[*].nguoiNhanId").value(hasItem(DEFAULT_NGUOI_NHAN_ID)))
            .andExpect(jsonPath("$.[*].tinhNguyenVienId").value(hasItem(DEFAULT_TINH_NGUYEN_VIEN_ID)))
            .andExpect(jsonPath("$.[*].trangThaiId").value(hasItem(DEFAULT_TRANG_THAI_ID)))
            .andExpect(jsonPath("$.[*].thoiGianDen").value(hasItem(sameInstant(DEFAULT_THOI_GIAN_DEN))))
            .andExpect(jsonPath("$.[*].thoiGianCho").value(hasItem(sameInstant(DEFAULT_THOI_GIAN_CHO))))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(sameInstant(DEFAULT_CREATED_AT))))
            .andExpect(jsonPath("$.[*].updatedAt").value(hasItem(sameInstant(DEFAULT_UPDATED_AT))));
    }

    @Test
    @Transactional
    void getPhieuDieuPhoi() throws Exception {
        // Initialize the database
        insertedPhieuDieuPhoi = phieuDieuPhoiRepository.saveAndFlush(phieuDieuPhoi);

        // Get the phieuDieuPhoi
        restPhieuDieuPhoiMockMvc
            .perform(get(ENTITY_API_URL_ID, phieuDieuPhoi.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(phieuDieuPhoi.getId().intValue()))
            .andExpect(jsonPath("$.nhaCungCapId").value(DEFAULT_NHA_CUNG_CAP_ID))
            .andExpect(jsonPath("$.nguoiNhanId").value(DEFAULT_NGUOI_NHAN_ID))
            .andExpect(jsonPath("$.tinhNguyenVienId").value(DEFAULT_TINH_NGUYEN_VIEN_ID))
            .andExpect(jsonPath("$.trangThaiId").value(DEFAULT_TRANG_THAI_ID))
            .andExpect(jsonPath("$.thoiGianDen").value(sameInstant(DEFAULT_THOI_GIAN_DEN)))
            .andExpect(jsonPath("$.thoiGianCho").value(sameInstant(DEFAULT_THOI_GIAN_CHO)))
            .andExpect(jsonPath("$.createdAt").value(sameInstant(DEFAULT_CREATED_AT)))
            .andExpect(jsonPath("$.updatedAt").value(sameInstant(DEFAULT_UPDATED_AT)));
    }

    @Test
    @Transactional
    void getNonExistingPhieuDieuPhoi() throws Exception {
        // Get the phieuDieuPhoi
        restPhieuDieuPhoiMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingPhieuDieuPhoi() throws Exception {
        // Initialize the database
        insertedPhieuDieuPhoi = phieuDieuPhoiRepository.saveAndFlush(phieuDieuPhoi);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the phieuDieuPhoi
        PhieuDieuPhoi updatedPhieuDieuPhoi = phieuDieuPhoiRepository.findById(phieuDieuPhoi.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedPhieuDieuPhoi are not directly saved in db
        em.detach(updatedPhieuDieuPhoi);
        updatedPhieuDieuPhoi
            .nhaCungCapId(UPDATED_NHA_CUNG_CAP_ID)
            .nguoiNhanId(UPDATED_NGUOI_NHAN_ID)
            .tinhNguyenVienId(UPDATED_TINH_NGUYEN_VIEN_ID)
            .trangThaiId(UPDATED_TRANG_THAI_ID)
            .thoiGianDen(UPDATED_THOI_GIAN_DEN)
            .thoiGianCho(UPDATED_THOI_GIAN_CHO)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);
        PhieuDieuPhoiDTO phieuDieuPhoiDTO = phieuDieuPhoiMapper.toDto(updatedPhieuDieuPhoi);

        restPhieuDieuPhoiMockMvc
            .perform(
                put(ENTITY_API_URL_ID, phieuDieuPhoiDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(phieuDieuPhoiDTO))
            )
            .andExpect(status().isOk());

        // Validate the PhieuDieuPhoi in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedPhieuDieuPhoiToMatchAllProperties(updatedPhieuDieuPhoi);
    }

    @Test
    @Transactional
    void putNonExistingPhieuDieuPhoi() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        phieuDieuPhoi.setId(longCount.incrementAndGet());

        // Create the PhieuDieuPhoi
        PhieuDieuPhoiDTO phieuDieuPhoiDTO = phieuDieuPhoiMapper.toDto(phieuDieuPhoi);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPhieuDieuPhoiMockMvc
            .perform(
                put(ENTITY_API_URL_ID, phieuDieuPhoiDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(phieuDieuPhoiDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PhieuDieuPhoi in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchPhieuDieuPhoi() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        phieuDieuPhoi.setId(longCount.incrementAndGet());

        // Create the PhieuDieuPhoi
        PhieuDieuPhoiDTO phieuDieuPhoiDTO = phieuDieuPhoiMapper.toDto(phieuDieuPhoi);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPhieuDieuPhoiMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(phieuDieuPhoiDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PhieuDieuPhoi in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamPhieuDieuPhoi() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        phieuDieuPhoi.setId(longCount.incrementAndGet());

        // Create the PhieuDieuPhoi
        PhieuDieuPhoiDTO phieuDieuPhoiDTO = phieuDieuPhoiMapper.toDto(phieuDieuPhoi);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPhieuDieuPhoiMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(phieuDieuPhoiDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the PhieuDieuPhoi in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdatePhieuDieuPhoiWithPatch() throws Exception {
        // Initialize the database
        insertedPhieuDieuPhoi = phieuDieuPhoiRepository.saveAndFlush(phieuDieuPhoi);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the phieuDieuPhoi using partial update
        PhieuDieuPhoi partialUpdatedPhieuDieuPhoi = new PhieuDieuPhoi();
        partialUpdatedPhieuDieuPhoi.setId(phieuDieuPhoi.getId());

        partialUpdatedPhieuDieuPhoi.trangThaiId(UPDATED_TRANG_THAI_ID).thoiGianDen(UPDATED_THOI_GIAN_DEN).createdAt(UPDATED_CREATED_AT);

        restPhieuDieuPhoiMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPhieuDieuPhoi.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedPhieuDieuPhoi))
            )
            .andExpect(status().isOk());

        // Validate the PhieuDieuPhoi in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPhieuDieuPhoiUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedPhieuDieuPhoi, phieuDieuPhoi),
            getPersistedPhieuDieuPhoi(phieuDieuPhoi)
        );
    }

    @Test
    @Transactional
    void fullUpdatePhieuDieuPhoiWithPatch() throws Exception {
        // Initialize the database
        insertedPhieuDieuPhoi = phieuDieuPhoiRepository.saveAndFlush(phieuDieuPhoi);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the phieuDieuPhoi using partial update
        PhieuDieuPhoi partialUpdatedPhieuDieuPhoi = new PhieuDieuPhoi();
        partialUpdatedPhieuDieuPhoi.setId(phieuDieuPhoi.getId());

        partialUpdatedPhieuDieuPhoi
            .nhaCungCapId(UPDATED_NHA_CUNG_CAP_ID)
            .nguoiNhanId(UPDATED_NGUOI_NHAN_ID)
            .tinhNguyenVienId(UPDATED_TINH_NGUYEN_VIEN_ID)
            .trangThaiId(UPDATED_TRANG_THAI_ID)
            .thoiGianDen(UPDATED_THOI_GIAN_DEN)
            .thoiGianCho(UPDATED_THOI_GIAN_CHO)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);

        restPhieuDieuPhoiMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPhieuDieuPhoi.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedPhieuDieuPhoi))
            )
            .andExpect(status().isOk());

        // Validate the PhieuDieuPhoi in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPhieuDieuPhoiUpdatableFieldsEquals(partialUpdatedPhieuDieuPhoi, getPersistedPhieuDieuPhoi(partialUpdatedPhieuDieuPhoi));
    }

    @Test
    @Transactional
    void patchNonExistingPhieuDieuPhoi() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        phieuDieuPhoi.setId(longCount.incrementAndGet());

        // Create the PhieuDieuPhoi
        PhieuDieuPhoiDTO phieuDieuPhoiDTO = phieuDieuPhoiMapper.toDto(phieuDieuPhoi);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPhieuDieuPhoiMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, phieuDieuPhoiDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(phieuDieuPhoiDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PhieuDieuPhoi in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchPhieuDieuPhoi() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        phieuDieuPhoi.setId(longCount.incrementAndGet());

        // Create the PhieuDieuPhoi
        PhieuDieuPhoiDTO phieuDieuPhoiDTO = phieuDieuPhoiMapper.toDto(phieuDieuPhoi);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPhieuDieuPhoiMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(phieuDieuPhoiDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PhieuDieuPhoi in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamPhieuDieuPhoi() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        phieuDieuPhoi.setId(longCount.incrementAndGet());

        // Create the PhieuDieuPhoi
        PhieuDieuPhoiDTO phieuDieuPhoiDTO = phieuDieuPhoiMapper.toDto(phieuDieuPhoi);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPhieuDieuPhoiMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(phieuDieuPhoiDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the PhieuDieuPhoi in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deletePhieuDieuPhoi() throws Exception {
        // Initialize the database
        insertedPhieuDieuPhoi = phieuDieuPhoiRepository.saveAndFlush(phieuDieuPhoi);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the phieuDieuPhoi
        restPhieuDieuPhoiMockMvc
            .perform(delete(ENTITY_API_URL_ID, phieuDieuPhoi.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return phieuDieuPhoiRepository.count();
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

    protected PhieuDieuPhoi getPersistedPhieuDieuPhoi(PhieuDieuPhoi phieuDieuPhoi) {
        return phieuDieuPhoiRepository.findById(phieuDieuPhoi.getId()).orElseThrow();
    }

    protected void assertPersistedPhieuDieuPhoiToMatchAllProperties(PhieuDieuPhoi expectedPhieuDieuPhoi) {
        assertPhieuDieuPhoiAllPropertiesEquals(expectedPhieuDieuPhoi, getPersistedPhieuDieuPhoi(expectedPhieuDieuPhoi));
    }

    protected void assertPersistedPhieuDieuPhoiToMatchUpdatableProperties(PhieuDieuPhoi expectedPhieuDieuPhoi) {
        assertPhieuDieuPhoiAllUpdatablePropertiesEquals(expectedPhieuDieuPhoi, getPersistedPhieuDieuPhoi(expectedPhieuDieuPhoi));
    }
}
