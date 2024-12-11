package hutechfoss.vrelief.admin.service;

import hutechfoss.vrelief.admin.domain.NhuYeuPham;
import hutechfoss.vrelief.admin.repository.NhuYeuPhamRepository;
import hutechfoss.vrelief.admin.service.dto.NhuYeuPhamDTO;
import hutechfoss.vrelief.admin.service.mapper.NhuYeuPhamMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link hutechfoss.vrelief.admin.domain.NhuYeuPham}.
 */
@Service
@Transactional
public class NhuYeuPhamService {

    private static final Logger LOG = LoggerFactory.getLogger(NhuYeuPhamService.class);

    private final NhuYeuPhamRepository nhuYeuPhamRepository;

    private final NhuYeuPhamMapper nhuYeuPhamMapper;

    public NhuYeuPhamService(NhuYeuPhamRepository nhuYeuPhamRepository, NhuYeuPhamMapper nhuYeuPhamMapper) {
        this.nhuYeuPhamRepository = nhuYeuPhamRepository;
        this.nhuYeuPhamMapper = nhuYeuPhamMapper;
    }

    /**
     * Save a nhuYeuPham.
     *
     * @param nhuYeuPhamDTO the entity to save.
     * @return the persisted entity.
     */
    public NhuYeuPhamDTO save(NhuYeuPhamDTO nhuYeuPhamDTO) {
        LOG.debug("Request to save NhuYeuPham : {}", nhuYeuPhamDTO);
        NhuYeuPham nhuYeuPham = nhuYeuPhamMapper.toEntity(nhuYeuPhamDTO);
        nhuYeuPham = nhuYeuPhamRepository.save(nhuYeuPham);
        return nhuYeuPhamMapper.toDto(nhuYeuPham);
    }

    /**
     * Update a nhuYeuPham.
     *
     * @param nhuYeuPhamDTO the entity to save.
     * @return the persisted entity.
     */
    public NhuYeuPhamDTO update(NhuYeuPhamDTO nhuYeuPhamDTO) {
        LOG.debug("Request to update NhuYeuPham : {}", nhuYeuPhamDTO);
        NhuYeuPham nhuYeuPham = nhuYeuPhamMapper.toEntity(nhuYeuPhamDTO);
        nhuYeuPham = nhuYeuPhamRepository.save(nhuYeuPham);
        return nhuYeuPhamMapper.toDto(nhuYeuPham);
    }

    /**
     * Partially update a nhuYeuPham.
     *
     * @param nhuYeuPhamDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<NhuYeuPhamDTO> partialUpdate(NhuYeuPhamDTO nhuYeuPhamDTO) {
        LOG.debug("Request to partially update NhuYeuPham : {}", nhuYeuPhamDTO);

        return nhuYeuPhamRepository
            .findById(nhuYeuPhamDTO.getId())
            .map(existingNhuYeuPham -> {
                nhuYeuPhamMapper.partialUpdate(existingNhuYeuPham, nhuYeuPhamDTO);

                return existingNhuYeuPham;
            })
            .map(nhuYeuPhamRepository::save)
            .map(nhuYeuPhamMapper::toDto);
    }

    /**
     * Get all the nhuYeuPhams.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<NhuYeuPhamDTO> findAll(Pageable pageable) {
        LOG.debug("Request to get all NhuYeuPhams");
        return nhuYeuPhamRepository.findAll(pageable).map(nhuYeuPhamMapper::toDto);
    }

    /**
     * Get one nhuYeuPham by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<NhuYeuPhamDTO> findOne(Long id) {
        LOG.debug("Request to get NhuYeuPham : {}", id);
        return nhuYeuPhamRepository.findById(id).map(nhuYeuPhamMapper::toDto);
    }

    /**
     * Delete the nhuYeuPham by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete NhuYeuPham : {}", id);
        nhuYeuPhamRepository.deleteById(id);
    }
}
