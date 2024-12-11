package hutechfoss.vrelief.admin.service;

import hutechfoss.vrelief.admin.domain.Vung;
import hutechfoss.vrelief.admin.repository.VungRepository;
import hutechfoss.vrelief.admin.service.dto.VungDTO;
import hutechfoss.vrelief.admin.service.mapper.VungMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link hutechfoss.vrelief.admin.domain.Vung}.
 */
@Service
@Transactional
public class VungService {

    private static final Logger LOG = LoggerFactory.getLogger(VungService.class);

    private final VungRepository vungRepository;

    private final VungMapper vungMapper;

    public VungService(VungRepository vungRepository, VungMapper vungMapper) {
        this.vungRepository = vungRepository;
        this.vungMapper = vungMapper;
    }

    /**
     * Save a vung.
     *
     * @param vungDTO the entity to save.
     * @return the persisted entity.
     */
    public VungDTO save(VungDTO vungDTO) {
        LOG.debug("Request to save Vung : {}", vungDTO);
        Vung vung = vungMapper.toEntity(vungDTO);
        vung = vungRepository.save(vung);
        return vungMapper.toDto(vung);
    }

    /**
     * Update a vung.
     *
     * @param vungDTO the entity to save.
     * @return the persisted entity.
     */
    public VungDTO update(VungDTO vungDTO) {
        LOG.debug("Request to update Vung : {}", vungDTO);
        Vung vung = vungMapper.toEntity(vungDTO);
        vung = vungRepository.save(vung);
        return vungMapper.toDto(vung);
    }

    /**
     * Partially update a vung.
     *
     * @param vungDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<VungDTO> partialUpdate(VungDTO vungDTO) {
        LOG.debug("Request to partially update Vung : {}", vungDTO);

        return vungRepository
            .findById(vungDTO.getId())
            .map(existingVung -> {
                vungMapper.partialUpdate(existingVung, vungDTO);

                return existingVung;
            })
            .map(vungRepository::save)
            .map(vungMapper::toDto);
    }

    /**
     * Get all the vungs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<VungDTO> findAll(Pageable pageable) {
        LOG.debug("Request to get all Vungs");
        return vungRepository.findAll(pageable).map(vungMapper::toDto);
    }

    /**
     * Get one vung by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<VungDTO> findOne(Long id) {
        LOG.debug("Request to get Vung : {}", id);
        return vungRepository.findById(id).map(vungMapper::toDto);
    }

    /**
     * Delete the vung by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete Vung : {}", id);
        vungRepository.deleteById(id);
    }
}
