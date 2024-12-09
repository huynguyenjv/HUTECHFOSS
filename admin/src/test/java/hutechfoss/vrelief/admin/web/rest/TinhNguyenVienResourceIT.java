package hutechfoss.vrelief.admin.web.rest;

import static hutechfoss.vrelief.admin.domain.TinhNguyenVienAsserts.*;
import static hutechfoss.vrelief.admin.web.rest.TestUtil.createUpdateProxyForBean;
import static hutechfoss.vrelief.admin.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import hutechfoss.vrelief.admin.IntegrationTest;
import hutechfoss.vrelief.admin.domain.TinhNguyenVien;
import hutechfoss.vrelief.admin.repository.TinhNguyenVienRepository;
import hutechfoss.vrelief.admin.service.dto.TinhNguyenVienDTO;
import hutechfoss.vrelief.admin.service.mapper.TinhNguyenVienMapper;
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
 * Integration tests for the {@link TinhNguyenVienResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TinhNguyenVienResourceIT {

    private static final String DEFAULT_TEN_TINH_NGUYEN_VIEN = "AAAAAAAAAA";
    private static final String UPDATED_TEN_TINH_NGUYEN_VIEN = "BBBBBBBBBB";

    private static final String DEFAULT_SO_DIEN_THOAI = "AAAAAAAAAA";
    private static final String UPDATED_SO_DIEN_THOAI = "BBBBBBBBBB";

    private static final String DEFAULT_DIA_CHI = "AAAAAAAAAA";
    private static final String UPDATED_DIA_CHI = "BBBBBBBBBB";

    private static final Integer DEFAULT_TRANG_THAI_ID = 1;
    private static final Integer UPDATED_TRANG_THAI_ID = 2;

    private static final ZonedDateTime DEFAULT_CREATED_AT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATED_AT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_UPDATED_AT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_UPDATED_AT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String ENTITY_API_URL = "/api/tinh-nguyen-viens";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private TinhNguyenVienRepository tinhNguyenVienRepository;

    @Autowired
    private TinhNguyenVienMapper tinhNguyenVienMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTinhNguyenVienMockMvc;

    private TinhNguyenVien tinhNguyenVien;

    private TinhNguyenVien insertedTinhNguyenVien;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TinhNguyenVien createEntity() {
        return new TinhNguyenVien()
            .tenTinhNguyenVien(DEFAULT_TEN_TINH_NGUYEN_VIEN)
            .soDienThoai(DEFAULT_SO_DIEN_THOAI)
            .diaChi(DEFAULT_DIA_CHI)
            .trangThaiId(DEFAULT_TRANG_THAI_ID)
            .createdAt(DEFAULT_CREATED_AT)
            .updatedAt(DEFAULT_UPDATED_AT);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TinhNguyenVien createUpdatedEntity() {
        return new TinhNguyenVien()
            .tenTinhNguyenVien(UPDATED_TEN_TINH_NGUYEN_VIEN)
            .soDienThoai(UPDATED_SO_DIEN_THOAI)
            .diaChi(UPDATED_DIA_CHI)
            .trangThaiId(UPDATED_TRANG_THAI_ID)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);
    }

    @BeforeEach
    public void initTest() {
        tinhNguyenVien = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedTinhNguyenVien != null) {
            tinhNguyenVienRepository.delete(insertedTinhNguyenVien);
            insertedTinhNguyenVien = null;
        }
    }

    @Test
    @Transactional
    void createTinhNguyenVien() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the TinhNguyenVien
        TinhNguyenVienDTO tinhNguyenVienDTO = tinhNguyenVienMapper.toDto(tinhNguyenVien);
        var returnedTinhNguyenVienDTO = om.readValue(
            restTinhNguyenVienMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(tinhNguyenVienDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            TinhNguyenVienDTO.class
        );

        // Validate the TinhNguyenVien in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedTinhNguyenVien = tinhNguyenVienMapper.toEntity(returnedTinhNguyenVienDTO);
        assertTinhNguyenVienUpdatableFieldsEquals(returnedTinhNguyenVien, getPersistedTinhNguyenVien(returnedTinhNguyenVien));

        insertedTinhNguyenVien = returnedTinhNguyenVien;
    }

    @Test
    @Transactional
    void createTinhNguyenVienWithExistingId() throws Exception {
        // Create the TinhNguyenVien with an existing ID
        tinhNguyenVien.setId(1L);
        TinhNguyenVienDTO tinhNguyenVienDTO = tinhNguyenVienMapper.toDto(tinhNguyenVien);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTinhNguyenVienMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(tinhNguyenVienDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TinhNguyenVien in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkTenTinhNguyenVienIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        tinhNguyenVien.setTenTinhNguyenVien(null);

        // Create the TinhNguyenVien, which fails.
        TinhNguyenVienDTO tinhNguyenVienDTO = tinhNguyenVienMapper.toDto(tinhNguyenVien);

        restTinhNguyenVienMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(tinhNguyenVienDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllTinhNguyenViens() throws Exception {
        // Initialize the database
        insertedTinhNguyenVien = tinhNguyenVienRepository.saveAndFlush(tinhNguyenVien);

        // Get all the tinhNguyenVienList
        restTinhNguyenVienMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tinhNguyenVien.getId().intValue())))
            .andExpect(jsonPath("$.[*].tenTinhNguyenVien").value(hasItem(DEFAULT_TEN_TINH_NGUYEN_VIEN)))
            .andExpect(jsonPath("$.[*].soDienThoai").value(hasItem(DEFAULT_SO_DIEN_THOAI)))
            .andExpect(jsonPath("$.[*].diaChi").value(hasItem(DEFAULT_DIA_CHI)))
            .andExpect(jsonPath("$.[*].trangThaiId").value(hasItem(DEFAULT_TRANG_THAI_ID)))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(sameInstant(DEFAULT_CREATED_AT))))
            .andExpect(jsonPath("$.[*].updatedAt").value(hasItem(sameInstant(DEFAULT_UPDATED_AT))));
    }

    @Test
    @Transactional
    void getTinhNguyenVien() throws Exception {
        // Initialize the database
        insertedTinhNguyenVien = tinhNguyenVienRepository.saveAndFlush(tinhNguyenVien);

        // Get the tinhNguyenVien
        restTinhNguyenVienMockMvc
            .perform(get(ENTITY_API_URL_ID, tinhNguyenVien.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tinhNguyenVien.getId().intValue()))
            .andExpect(jsonPath("$.tenTinhNguyenVien").value(DEFAULT_TEN_TINH_NGUYEN_VIEN))
            .andExpect(jsonPath("$.soDienThoai").value(DEFAULT_SO_DIEN_THOAI))
            .andExpect(jsonPath("$.diaChi").value(DEFAULT_DIA_CHI))
            .andExpect(jsonPath("$.trangThaiId").value(DEFAULT_TRANG_THAI_ID))
            .andExpect(jsonPath("$.createdAt").value(sameInstant(DEFAULT_CREATED_AT)))
            .andExpect(jsonPath("$.updatedAt").value(sameInstant(DEFAULT_UPDATED_AT)));
    }

    @Test
    @Transactional
    void getNonExistingTinhNguyenVien() throws Exception {
        // Get the tinhNguyenVien
        restTinhNguyenVienMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingTinhNguyenVien() throws Exception {
        // Initialize the database
        insertedTinhNguyenVien = tinhNguyenVienRepository.saveAndFlush(tinhNguyenVien);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the tinhNguyenVien
        TinhNguyenVien updatedTinhNguyenVien = tinhNguyenVienRepository.findById(tinhNguyenVien.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedTinhNguyenVien are not directly saved in db
        em.detach(updatedTinhNguyenVien);
        updatedTinhNguyenVien
            .tenTinhNguyenVien(UPDATED_TEN_TINH_NGUYEN_VIEN)
            .soDienThoai(UPDATED_SO_DIEN_THOAI)
            .diaChi(UPDATED_DIA_CHI)
            .trangThaiId(UPDATED_TRANG_THAI_ID)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);
        TinhNguyenVienDTO tinhNguyenVienDTO = tinhNguyenVienMapper.toDto(updatedTinhNguyenVien);

        restTinhNguyenVienMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tinhNguyenVienDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(tinhNguyenVienDTO))
            )
            .andExpect(status().isOk());

        // Validate the TinhNguyenVien in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedTinhNguyenVienToMatchAllProperties(updatedTinhNguyenVien);
    }

    @Test
    @Transactional
    void putNonExistingTinhNguyenVien() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        tinhNguyenVien.setId(longCount.incrementAndGet());

        // Create the TinhNguyenVien
        TinhNguyenVienDTO tinhNguyenVienDTO = tinhNguyenVienMapper.toDto(tinhNguyenVien);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTinhNguyenVienMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tinhNguyenVienDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(tinhNguyenVienDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TinhNguyenVien in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTinhNguyenVien() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        tinhNguyenVien.setId(longCount.incrementAndGet());

        // Create the TinhNguyenVien
        TinhNguyenVienDTO tinhNguyenVienDTO = tinhNguyenVienMapper.toDto(tinhNguyenVien);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTinhNguyenVienMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(tinhNguyenVienDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TinhNguyenVien in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTinhNguyenVien() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        tinhNguyenVien.setId(longCount.incrementAndGet());

        // Create the TinhNguyenVien
        TinhNguyenVienDTO tinhNguyenVienDTO = tinhNguyenVienMapper.toDto(tinhNguyenVien);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTinhNguyenVienMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(tinhNguyenVienDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the TinhNguyenVien in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTinhNguyenVienWithPatch() throws Exception {
        // Initialize the database
        insertedTinhNguyenVien = tinhNguyenVienRepository.saveAndFlush(tinhNguyenVien);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the tinhNguyenVien using partial update
        TinhNguyenVien partialUpdatedTinhNguyenVien = new TinhNguyenVien();
        partialUpdatedTinhNguyenVien.setId(tinhNguyenVien.getId());

        partialUpdatedTinhNguyenVien.soDienThoai(UPDATED_SO_DIEN_THOAI).diaChi(UPDATED_DIA_CHI).createdAt(UPDATED_CREATED_AT);

        restTinhNguyenVienMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTinhNguyenVien.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedTinhNguyenVien))
            )
            .andExpect(status().isOk());

        // Validate the TinhNguyenVien in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertTinhNguyenVienUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedTinhNguyenVien, tinhNguyenVien),
            getPersistedTinhNguyenVien(tinhNguyenVien)
        );
    }

    @Test
    @Transactional
    void fullUpdateTinhNguyenVienWithPatch() throws Exception {
        // Initialize the database
        insertedTinhNguyenVien = tinhNguyenVienRepository.saveAndFlush(tinhNguyenVien);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the tinhNguyenVien using partial update
        TinhNguyenVien partialUpdatedTinhNguyenVien = new TinhNguyenVien();
        partialUpdatedTinhNguyenVien.setId(tinhNguyenVien.getId());

        partialUpdatedTinhNguyenVien
            .tenTinhNguyenVien(UPDATED_TEN_TINH_NGUYEN_VIEN)
            .soDienThoai(UPDATED_SO_DIEN_THOAI)
            .diaChi(UPDATED_DIA_CHI)
            .trangThaiId(UPDATED_TRANG_THAI_ID)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);

        restTinhNguyenVienMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTinhNguyenVien.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedTinhNguyenVien))
            )
            .andExpect(status().isOk());

        // Validate the TinhNguyenVien in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertTinhNguyenVienUpdatableFieldsEquals(partialUpdatedTinhNguyenVien, getPersistedTinhNguyenVien(partialUpdatedTinhNguyenVien));
    }

    @Test
    @Transactional
    void patchNonExistingTinhNguyenVien() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        tinhNguyenVien.setId(longCount.incrementAndGet());

        // Create the TinhNguyenVien
        TinhNguyenVienDTO tinhNguyenVienDTO = tinhNguyenVienMapper.toDto(tinhNguyenVien);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTinhNguyenVienMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tinhNguyenVienDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(tinhNguyenVienDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TinhNguyenVien in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTinhNguyenVien() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        tinhNguyenVien.setId(longCount.incrementAndGet());

        // Create the TinhNguyenVien
        TinhNguyenVienDTO tinhNguyenVienDTO = tinhNguyenVienMapper.toDto(tinhNguyenVien);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTinhNguyenVienMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(tinhNguyenVienDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TinhNguyenVien in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTinhNguyenVien() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        tinhNguyenVien.setId(longCount.incrementAndGet());

        // Create the TinhNguyenVien
        TinhNguyenVienDTO tinhNguyenVienDTO = tinhNguyenVienMapper.toDto(tinhNguyenVien);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTinhNguyenVienMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(tinhNguyenVienDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the TinhNguyenVien in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTinhNguyenVien() throws Exception {
        // Initialize the database
        insertedTinhNguyenVien = tinhNguyenVienRepository.saveAndFlush(tinhNguyenVien);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the tinhNguyenVien
        restTinhNguyenVienMockMvc
            .perform(delete(ENTITY_API_URL_ID, tinhNguyenVien.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return tinhNguyenVienRepository.count();
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

    protected TinhNguyenVien getPersistedTinhNguyenVien(TinhNguyenVien tinhNguyenVien) {
        return tinhNguyenVienRepository.findById(tinhNguyenVien.getId()).orElseThrow();
    }

    protected void assertPersistedTinhNguyenVienToMatchAllProperties(TinhNguyenVien expectedTinhNguyenVien) {
        assertTinhNguyenVienAllPropertiesEquals(expectedTinhNguyenVien, getPersistedTinhNguyenVien(expectedTinhNguyenVien));
    }

    protected void assertPersistedTinhNguyenVienToMatchUpdatableProperties(TinhNguyenVien expectedTinhNguyenVien) {
        assertTinhNguyenVienAllUpdatablePropertiesEquals(expectedTinhNguyenVien, getPersistedTinhNguyenVien(expectedTinhNguyenVien));
    }
}
