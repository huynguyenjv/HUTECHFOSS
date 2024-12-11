package hutechfoss.vrelief.admin.web.rest;

import static hutechfoss.vrelief.admin.domain.LoaiNhuYeuPhamAsserts.*;
import static hutechfoss.vrelief.admin.web.rest.TestUtil.createUpdateProxyForBean;
import static hutechfoss.vrelief.admin.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import hutechfoss.vrelief.admin.IntegrationTest;
import hutechfoss.vrelief.admin.domain.LoaiNhuYeuPham;
import hutechfoss.vrelief.admin.repository.LoaiNhuYeuPhamRepository;
import hutechfoss.vrelief.admin.service.dto.LoaiNhuYeuPhamDTO;
import hutechfoss.vrelief.admin.service.mapper.LoaiNhuYeuPhamMapper;
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
 * Integration tests for the {@link LoaiNhuYeuPhamResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class LoaiNhuYeuPhamResourceIT {

    private static final String DEFAULT_TEN_LOAI = "AAAAAAAAAA";
    private static final String UPDATED_TEN_LOAI = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_CREATED_AT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATED_AT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_UPDATED_AT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_UPDATED_AT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String ENTITY_API_URL = "/api/loai-nhu-yeu-phams";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private LoaiNhuYeuPhamRepository loaiNhuYeuPhamRepository;

    @Autowired
    private LoaiNhuYeuPhamMapper loaiNhuYeuPhamMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restLoaiNhuYeuPhamMockMvc;

    private LoaiNhuYeuPham loaiNhuYeuPham;

    private LoaiNhuYeuPham insertedLoaiNhuYeuPham;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LoaiNhuYeuPham createEntity() {
        return new LoaiNhuYeuPham().tenLoai(DEFAULT_TEN_LOAI).createdAt(DEFAULT_CREATED_AT).updatedAt(DEFAULT_UPDATED_AT);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LoaiNhuYeuPham createUpdatedEntity() {
        return new LoaiNhuYeuPham().tenLoai(UPDATED_TEN_LOAI).createdAt(UPDATED_CREATED_AT).updatedAt(UPDATED_UPDATED_AT);
    }

    @BeforeEach
    public void initTest() {
        loaiNhuYeuPham = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedLoaiNhuYeuPham != null) {
            loaiNhuYeuPhamRepository.delete(insertedLoaiNhuYeuPham);
            insertedLoaiNhuYeuPham = null;
        }
    }

    @Test
    @Transactional
    void createLoaiNhuYeuPham() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the LoaiNhuYeuPham
        LoaiNhuYeuPhamDTO loaiNhuYeuPhamDTO = loaiNhuYeuPhamMapper.toDto(loaiNhuYeuPham);
        var returnedLoaiNhuYeuPhamDTO = om.readValue(
            restLoaiNhuYeuPhamMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(loaiNhuYeuPhamDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            LoaiNhuYeuPhamDTO.class
        );

        // Validate the LoaiNhuYeuPham in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedLoaiNhuYeuPham = loaiNhuYeuPhamMapper.toEntity(returnedLoaiNhuYeuPhamDTO);
        assertLoaiNhuYeuPhamUpdatableFieldsEquals(returnedLoaiNhuYeuPham, getPersistedLoaiNhuYeuPham(returnedLoaiNhuYeuPham));

        insertedLoaiNhuYeuPham = returnedLoaiNhuYeuPham;
    }

    @Test
    @Transactional
    void createLoaiNhuYeuPhamWithExistingId() throws Exception {
        // Create the LoaiNhuYeuPham with an existing ID
        loaiNhuYeuPham.setId(1L);
        LoaiNhuYeuPhamDTO loaiNhuYeuPhamDTO = loaiNhuYeuPhamMapper.toDto(loaiNhuYeuPham);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restLoaiNhuYeuPhamMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(loaiNhuYeuPhamDTO)))
            .andExpect(status().isBadRequest());

        // Validate the LoaiNhuYeuPham in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkTenLoaiIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        loaiNhuYeuPham.setTenLoai(null);

        // Create the LoaiNhuYeuPham, which fails.
        LoaiNhuYeuPhamDTO loaiNhuYeuPhamDTO = loaiNhuYeuPhamMapper.toDto(loaiNhuYeuPham);

        restLoaiNhuYeuPhamMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(loaiNhuYeuPhamDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllLoaiNhuYeuPhams() throws Exception {
        // Initialize the database
        insertedLoaiNhuYeuPham = loaiNhuYeuPhamRepository.saveAndFlush(loaiNhuYeuPham);

        // Get all the loaiNhuYeuPhamList
        restLoaiNhuYeuPhamMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(loaiNhuYeuPham.getId().intValue())))
            .andExpect(jsonPath("$.[*].tenLoai").value(hasItem(DEFAULT_TEN_LOAI)))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(sameInstant(DEFAULT_CREATED_AT))))
            .andExpect(jsonPath("$.[*].updatedAt").value(hasItem(sameInstant(DEFAULT_UPDATED_AT))));
    }

    @Test
    @Transactional
    void getLoaiNhuYeuPham() throws Exception {
        // Initialize the database
        insertedLoaiNhuYeuPham = loaiNhuYeuPhamRepository.saveAndFlush(loaiNhuYeuPham);

        // Get the loaiNhuYeuPham
        restLoaiNhuYeuPhamMockMvc
            .perform(get(ENTITY_API_URL_ID, loaiNhuYeuPham.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(loaiNhuYeuPham.getId().intValue()))
            .andExpect(jsonPath("$.tenLoai").value(DEFAULT_TEN_LOAI))
            .andExpect(jsonPath("$.createdAt").value(sameInstant(DEFAULT_CREATED_AT)))
            .andExpect(jsonPath("$.updatedAt").value(sameInstant(DEFAULT_UPDATED_AT)));
    }

    @Test
    @Transactional
    void getNonExistingLoaiNhuYeuPham() throws Exception {
        // Get the loaiNhuYeuPham
        restLoaiNhuYeuPhamMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingLoaiNhuYeuPham() throws Exception {
        // Initialize the database
        insertedLoaiNhuYeuPham = loaiNhuYeuPhamRepository.saveAndFlush(loaiNhuYeuPham);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the loaiNhuYeuPham
        LoaiNhuYeuPham updatedLoaiNhuYeuPham = loaiNhuYeuPhamRepository.findById(loaiNhuYeuPham.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedLoaiNhuYeuPham are not directly saved in db
        em.detach(updatedLoaiNhuYeuPham);
        updatedLoaiNhuYeuPham.tenLoai(UPDATED_TEN_LOAI).createdAt(UPDATED_CREATED_AT).updatedAt(UPDATED_UPDATED_AT);
        LoaiNhuYeuPhamDTO loaiNhuYeuPhamDTO = loaiNhuYeuPhamMapper.toDto(updatedLoaiNhuYeuPham);

        restLoaiNhuYeuPhamMockMvc
            .perform(
                put(ENTITY_API_URL_ID, loaiNhuYeuPhamDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(loaiNhuYeuPhamDTO))
            )
            .andExpect(status().isOk());

        // Validate the LoaiNhuYeuPham in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedLoaiNhuYeuPhamToMatchAllProperties(updatedLoaiNhuYeuPham);
    }

    @Test
    @Transactional
    void putNonExistingLoaiNhuYeuPham() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        loaiNhuYeuPham.setId(longCount.incrementAndGet());

        // Create the LoaiNhuYeuPham
        LoaiNhuYeuPhamDTO loaiNhuYeuPhamDTO = loaiNhuYeuPhamMapper.toDto(loaiNhuYeuPham);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLoaiNhuYeuPhamMockMvc
            .perform(
                put(ENTITY_API_URL_ID, loaiNhuYeuPhamDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(loaiNhuYeuPhamDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the LoaiNhuYeuPham in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchLoaiNhuYeuPham() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        loaiNhuYeuPham.setId(longCount.incrementAndGet());

        // Create the LoaiNhuYeuPham
        LoaiNhuYeuPhamDTO loaiNhuYeuPhamDTO = loaiNhuYeuPhamMapper.toDto(loaiNhuYeuPham);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLoaiNhuYeuPhamMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(loaiNhuYeuPhamDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the LoaiNhuYeuPham in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamLoaiNhuYeuPham() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        loaiNhuYeuPham.setId(longCount.incrementAndGet());

        // Create the LoaiNhuYeuPham
        LoaiNhuYeuPhamDTO loaiNhuYeuPhamDTO = loaiNhuYeuPhamMapper.toDto(loaiNhuYeuPham);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLoaiNhuYeuPhamMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(loaiNhuYeuPhamDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the LoaiNhuYeuPham in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateLoaiNhuYeuPhamWithPatch() throws Exception {
        // Initialize the database
        insertedLoaiNhuYeuPham = loaiNhuYeuPhamRepository.saveAndFlush(loaiNhuYeuPham);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the loaiNhuYeuPham using partial update
        LoaiNhuYeuPham partialUpdatedLoaiNhuYeuPham = new LoaiNhuYeuPham();
        partialUpdatedLoaiNhuYeuPham.setId(loaiNhuYeuPham.getId());

        partialUpdatedLoaiNhuYeuPham.updatedAt(UPDATED_UPDATED_AT);

        restLoaiNhuYeuPhamMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedLoaiNhuYeuPham.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedLoaiNhuYeuPham))
            )
            .andExpect(status().isOk());

        // Validate the LoaiNhuYeuPham in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertLoaiNhuYeuPhamUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedLoaiNhuYeuPham, loaiNhuYeuPham),
            getPersistedLoaiNhuYeuPham(loaiNhuYeuPham)
        );
    }

    @Test
    @Transactional
    void fullUpdateLoaiNhuYeuPhamWithPatch() throws Exception {
        // Initialize the database
        insertedLoaiNhuYeuPham = loaiNhuYeuPhamRepository.saveAndFlush(loaiNhuYeuPham);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the loaiNhuYeuPham using partial update
        LoaiNhuYeuPham partialUpdatedLoaiNhuYeuPham = new LoaiNhuYeuPham();
        partialUpdatedLoaiNhuYeuPham.setId(loaiNhuYeuPham.getId());

        partialUpdatedLoaiNhuYeuPham.tenLoai(UPDATED_TEN_LOAI).createdAt(UPDATED_CREATED_AT).updatedAt(UPDATED_UPDATED_AT);

        restLoaiNhuYeuPhamMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedLoaiNhuYeuPham.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedLoaiNhuYeuPham))
            )
            .andExpect(status().isOk());

        // Validate the LoaiNhuYeuPham in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertLoaiNhuYeuPhamUpdatableFieldsEquals(partialUpdatedLoaiNhuYeuPham, getPersistedLoaiNhuYeuPham(partialUpdatedLoaiNhuYeuPham));
    }

    @Test
    @Transactional
    void patchNonExistingLoaiNhuYeuPham() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        loaiNhuYeuPham.setId(longCount.incrementAndGet());

        // Create the LoaiNhuYeuPham
        LoaiNhuYeuPhamDTO loaiNhuYeuPhamDTO = loaiNhuYeuPhamMapper.toDto(loaiNhuYeuPham);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLoaiNhuYeuPhamMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, loaiNhuYeuPhamDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(loaiNhuYeuPhamDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the LoaiNhuYeuPham in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchLoaiNhuYeuPham() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        loaiNhuYeuPham.setId(longCount.incrementAndGet());

        // Create the LoaiNhuYeuPham
        LoaiNhuYeuPhamDTO loaiNhuYeuPhamDTO = loaiNhuYeuPhamMapper.toDto(loaiNhuYeuPham);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLoaiNhuYeuPhamMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(loaiNhuYeuPhamDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the LoaiNhuYeuPham in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamLoaiNhuYeuPham() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        loaiNhuYeuPham.setId(longCount.incrementAndGet());

        // Create the LoaiNhuYeuPham
        LoaiNhuYeuPhamDTO loaiNhuYeuPhamDTO = loaiNhuYeuPhamMapper.toDto(loaiNhuYeuPham);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLoaiNhuYeuPhamMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(loaiNhuYeuPhamDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the LoaiNhuYeuPham in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteLoaiNhuYeuPham() throws Exception {
        // Initialize the database
        insertedLoaiNhuYeuPham = loaiNhuYeuPhamRepository.saveAndFlush(loaiNhuYeuPham);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the loaiNhuYeuPham
        restLoaiNhuYeuPhamMockMvc
            .perform(delete(ENTITY_API_URL_ID, loaiNhuYeuPham.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return loaiNhuYeuPhamRepository.count();
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

    protected LoaiNhuYeuPham getPersistedLoaiNhuYeuPham(LoaiNhuYeuPham loaiNhuYeuPham) {
        return loaiNhuYeuPhamRepository.findById(loaiNhuYeuPham.getId()).orElseThrow();
    }

    protected void assertPersistedLoaiNhuYeuPhamToMatchAllProperties(LoaiNhuYeuPham expectedLoaiNhuYeuPham) {
        assertLoaiNhuYeuPhamAllPropertiesEquals(expectedLoaiNhuYeuPham, getPersistedLoaiNhuYeuPham(expectedLoaiNhuYeuPham));
    }

    protected void assertPersistedLoaiNhuYeuPhamToMatchUpdatableProperties(LoaiNhuYeuPham expectedLoaiNhuYeuPham) {
        assertLoaiNhuYeuPhamAllUpdatablePropertiesEquals(expectedLoaiNhuYeuPham, getPersistedLoaiNhuYeuPham(expectedLoaiNhuYeuPham));
    }
}
