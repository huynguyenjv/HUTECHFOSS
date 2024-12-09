package hutechfoss.vrelief.admin.service;

import hutechfoss.vrelief.admin.domain.LoaiThienTai;
import hutechfoss.vrelief.admin.repository.LoaiThienTaiRepository;
import hutechfoss.vrelief.admin.service.dto.LoaiThienTaiDTO;
import hutechfoss.vrelief.admin.service.mapper.LoaiThienTaiMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link hutechfoss.vrelief.admin.domain.LoaiThienTai}.
 */
@Service
@Transactional
public class LoaiThienTaiService {

    private static final Logger LOG = LoggerFactory.getLogger(LoaiThienTaiService.class);

    private final LoaiThienTaiRepository loaiThienTaiRepository;

    private final LoaiThienTaiMapper loaiThienTaiMapper;

    public LoaiThienTaiService(LoaiThienTaiRepository loaiThienTaiRepository, LoaiThienTaiMapper loaiThienTaiMapper) {
        this.loaiThienTaiRepository = loaiThienTaiRepository;
        this.loaiThienTaiMapper = loaiThienTaiMapper;
    }

    /**
     * Save a loaiThienTai.
     *
     * @param loaiThienTaiDTO the entity to save.
     * @return the persisted entity.
     */
    public LoaiThienTaiDTO save(LoaiThienTaiDTO loaiThienTaiDTO) {
        LOG.debug("Request to save LoaiThienTai : {}", loaiThienTaiDTO);
        LoaiThienTai loaiThienTai = loaiThienTaiMapper.toEntity(loaiThienTaiDTO);
        loaiThienTai = loaiThienTaiRepository.save(loaiThienTai);
        return loaiThienTaiMapper.toDto(loaiThienTai);
    }

    /**
     * Update a loaiThienTai.
     *
     * @param loaiThienTaiDTO the entity to save.
     * @return the persisted entity.
     */
    public LoaiThienTaiDTO update(LoaiThienTaiDTO loaiThienTaiDTO) {
        LOG.debug("Request to update LoaiThienTai : {}", loaiThienTaiDTO);
        LoaiThienTai loaiThienTai = loaiThienTaiMapper.toEntity(loaiThienTaiDTO);
        loaiThienTai = loaiThienTaiRepository.save(loaiThienTai);
        return loaiThienTaiMapper.toDto(loaiThienTai);
    }

    /**
     * Partially update a loaiThienTai.
     *
     * @param loaiThienTaiDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<LoaiThienTaiDTO> partialUpdate(LoaiThienTaiDTO loaiThienTaiDTO) {
        LOG.debug("Request to partially update LoaiThienTai : {}", loaiThienTaiDTO);

        return loaiThienTaiRepository
            .findById(loaiThienTaiDTO.getId())
            .map(existingLoaiThienTai -> {
                loaiThienTaiMapper.partialUpdate(existingLoaiThienTai, loaiThienTaiDTO);

                return existingLoaiThienTai;
            })
            .map(loaiThienTaiRepository::save)
            .map(loaiThienTaiMapper::toDto);
    }

    /**
     * Get all the loaiThienTais.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<LoaiThienTaiDTO> findAll(Pageable pageable) {
        LOG.debug("Request to get all LoaiThienTais");
        return loaiThienTaiRepository.findAll(pageable).map(loaiThienTaiMapper::toDto);
    }

    /**
     * Get one loaiThienTai by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<LoaiThienTaiDTO> findOne(Long id) {
        LOG.debug("Request to get LoaiThienTai : {}", id);
        return loaiThienTaiRepository.findById(id).map(loaiThienTaiMapper::toDto);
    }

    /**
     * Delete the loaiThienTai by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete LoaiThienTai : {}", id);
        loaiThienTaiRepository.deleteById(id);
    }
}
