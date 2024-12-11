package hutechfoss.vrelief.admin.service;

import hutechfoss.vrelief.admin.domain.TrangThai;
import hutechfoss.vrelief.admin.repository.TrangThaiRepository;
import hutechfoss.vrelief.admin.service.dto.TrangThaiDTO;
import hutechfoss.vrelief.admin.service.mapper.TrangThaiMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link hutechfoss.vrelief.admin.domain.TrangThai}.
 */
@Service
@Transactional
public class TrangThaiService {

    private static final Logger LOG = LoggerFactory.getLogger(TrangThaiService.class);

    private final TrangThaiRepository trangThaiRepository;

    private final TrangThaiMapper trangThaiMapper;

    public TrangThaiService(TrangThaiRepository trangThaiRepository, TrangThaiMapper trangThaiMapper) {
        this.trangThaiRepository = trangThaiRepository;
        this.trangThaiMapper = trangThaiMapper;
    }

    /**
     * Save a trangThai.
     *
     * @param trangThaiDTO the entity to save.
     * @return the persisted entity.
     */
    public TrangThaiDTO save(TrangThaiDTO trangThaiDTO) {
        LOG.debug("Request to save TrangThai : {}", trangThaiDTO);
        TrangThai trangThai = trangThaiMapper.toEntity(trangThaiDTO);
        trangThai = trangThaiRepository.save(trangThai);
        return trangThaiMapper.toDto(trangThai);
    }

    /**
     * Update a trangThai.
     *
     * @param trangThaiDTO the entity to save.
     * @return the persisted entity.
     */
    public TrangThaiDTO update(TrangThaiDTO trangThaiDTO) {
        LOG.debug("Request to update TrangThai : {}", trangThaiDTO);
        TrangThai trangThai = trangThaiMapper.toEntity(trangThaiDTO);
        trangThai = trangThaiRepository.save(trangThai);
        return trangThaiMapper.toDto(trangThai);
    }

    /**
     * Partially update a trangThai.
     *
     * @param trangThaiDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<TrangThaiDTO> partialUpdate(TrangThaiDTO trangThaiDTO) {
        LOG.debug("Request to partially update TrangThai : {}", trangThaiDTO);

        return trangThaiRepository
            .findById(trangThaiDTO.getId())
            .map(existingTrangThai -> {
                trangThaiMapper.partialUpdate(existingTrangThai, trangThaiDTO);

                return existingTrangThai;
            })
            .map(trangThaiRepository::save)
            .map(trangThaiMapper::toDto);
    }

    /**
     * Get all the trangThais.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<TrangThaiDTO> findAll(Pageable pageable) {
        LOG.debug("Request to get all TrangThais");
        return trangThaiRepository.findAll(pageable).map(trangThaiMapper::toDto);
    }

    /**
     * Get one trangThai by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TrangThaiDTO> findOne(Long id) {
        LOG.debug("Request to get TrangThai : {}", id);
        return trangThaiRepository.findById(id).map(trangThaiMapper::toDto);
    }

    /**
     * Delete the trangThai by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete TrangThai : {}", id);
        trangThaiRepository.deleteById(id);
    }
}
