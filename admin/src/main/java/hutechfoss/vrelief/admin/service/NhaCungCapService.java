package hutechfoss.vrelief.admin.service;

import hutechfoss.vrelief.admin.domain.NhaCungCap;
import hutechfoss.vrelief.admin.repository.NhaCungCapRepository;
import hutechfoss.vrelief.admin.service.dto.NhaCungCapDTO;
import hutechfoss.vrelief.admin.service.mapper.NhaCungCapMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link hutechfoss.vrelief.admin.domain.NhaCungCap}.
 */
@Service
@Transactional
public class NhaCungCapService {

    private static final Logger LOG = LoggerFactory.getLogger(NhaCungCapService.class);

    private final NhaCungCapRepository nhaCungCapRepository;

    private final NhaCungCapMapper nhaCungCapMapper;

    public NhaCungCapService(NhaCungCapRepository nhaCungCapRepository, NhaCungCapMapper nhaCungCapMapper) {
        this.nhaCungCapRepository = nhaCungCapRepository;
        this.nhaCungCapMapper = nhaCungCapMapper;
    }

    /**
     * Save a nhaCungCap.
     *
     * @param nhaCungCapDTO the entity to save.
     * @return the persisted entity.
     */
    public NhaCungCapDTO save(NhaCungCapDTO nhaCungCapDTO) {
        LOG.debug("Request to save NhaCungCap : {}", nhaCungCapDTO);
        NhaCungCap nhaCungCap = nhaCungCapMapper.toEntity(nhaCungCapDTO);
        nhaCungCap = nhaCungCapRepository.save(nhaCungCap);
        return nhaCungCapMapper.toDto(nhaCungCap);
    }

    /**
     * Update a nhaCungCap.
     *
     * @param nhaCungCapDTO the entity to save.
     * @return the persisted entity.
     */
    public NhaCungCapDTO update(NhaCungCapDTO nhaCungCapDTO) {
        LOG.debug("Request to update NhaCungCap : {}", nhaCungCapDTO);
        NhaCungCap nhaCungCap = nhaCungCapMapper.toEntity(nhaCungCapDTO);
        nhaCungCap = nhaCungCapRepository.save(nhaCungCap);
        return nhaCungCapMapper.toDto(nhaCungCap);
    }

    /**
     * Partially update a nhaCungCap.
     *
     * @param nhaCungCapDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<NhaCungCapDTO> partialUpdate(NhaCungCapDTO nhaCungCapDTO) {
        LOG.debug("Request to partially update NhaCungCap : {}", nhaCungCapDTO);

        return nhaCungCapRepository
            .findById(nhaCungCapDTO.getId())
            .map(existingNhaCungCap -> {
                nhaCungCapMapper.partialUpdate(existingNhaCungCap, nhaCungCapDTO);

                return existingNhaCungCap;
            })
            .map(nhaCungCapRepository::save)
            .map(nhaCungCapMapper::toDto);
    }

    /**
     * Get all the nhaCungCaps.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<NhaCungCapDTO> findAll(Pageable pageable) {
        LOG.debug("Request to get all NhaCungCaps");
        return nhaCungCapRepository.findAll(pageable).map(nhaCungCapMapper::toDto);
    }

    /**
     * Get one nhaCungCap by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<NhaCungCapDTO> findOne(Long id) {
        LOG.debug("Request to get NhaCungCap : {}", id);
        return nhaCungCapRepository.findById(id).map(nhaCungCapMapper::toDto);
    }

    /**
     * Delete the nhaCungCap by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete NhaCungCap : {}", id);
        nhaCungCapRepository.deleteById(id);
    }
}
