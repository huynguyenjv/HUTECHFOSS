package hutechfoss.vrelief.admin.service;

import hutechfoss.vrelief.admin.domain.PhieuDieuPhoi;
import hutechfoss.vrelief.admin.repository.PhieuDieuPhoiRepository;
import hutechfoss.vrelief.admin.service.dto.PhieuDieuPhoiDTO;
import hutechfoss.vrelief.admin.service.mapper.PhieuDieuPhoiMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link hutechfoss.vrelief.admin.domain.PhieuDieuPhoi}.
 */
@Service
@Transactional
public class PhieuDieuPhoiService {

    private static final Logger LOG = LoggerFactory.getLogger(PhieuDieuPhoiService.class);

    private final PhieuDieuPhoiRepository phieuDieuPhoiRepository;

    private final PhieuDieuPhoiMapper phieuDieuPhoiMapper;

    public PhieuDieuPhoiService(PhieuDieuPhoiRepository phieuDieuPhoiRepository, PhieuDieuPhoiMapper phieuDieuPhoiMapper) {
        this.phieuDieuPhoiRepository = phieuDieuPhoiRepository;
        this.phieuDieuPhoiMapper = phieuDieuPhoiMapper;
    }

    /**
     * Save a phieuDieuPhoi.
     *
     * @param phieuDieuPhoiDTO the entity to save.
     * @return the persisted entity.
     */
    public PhieuDieuPhoiDTO save(PhieuDieuPhoiDTO phieuDieuPhoiDTO) {
        LOG.debug("Request to save PhieuDieuPhoi : {}", phieuDieuPhoiDTO);
        PhieuDieuPhoi phieuDieuPhoi = phieuDieuPhoiMapper.toEntity(phieuDieuPhoiDTO);
        phieuDieuPhoi = phieuDieuPhoiRepository.save(phieuDieuPhoi);
        return phieuDieuPhoiMapper.toDto(phieuDieuPhoi);
    }

    /**
     * Update a phieuDieuPhoi.
     *
     * @param phieuDieuPhoiDTO the entity to save.
     * @return the persisted entity.
     */
    public PhieuDieuPhoiDTO update(PhieuDieuPhoiDTO phieuDieuPhoiDTO) {
        LOG.debug("Request to update PhieuDieuPhoi : {}", phieuDieuPhoiDTO);
        PhieuDieuPhoi phieuDieuPhoi = phieuDieuPhoiMapper.toEntity(phieuDieuPhoiDTO);
        phieuDieuPhoi = phieuDieuPhoiRepository.save(phieuDieuPhoi);
        return phieuDieuPhoiMapper.toDto(phieuDieuPhoi);
    }

    /**
     * Partially update a phieuDieuPhoi.
     *
     * @param phieuDieuPhoiDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<PhieuDieuPhoiDTO> partialUpdate(PhieuDieuPhoiDTO phieuDieuPhoiDTO) {
        LOG.debug("Request to partially update PhieuDieuPhoi : {}", phieuDieuPhoiDTO);

        return phieuDieuPhoiRepository
            .findById(phieuDieuPhoiDTO.getId())
            .map(existingPhieuDieuPhoi -> {
                phieuDieuPhoiMapper.partialUpdate(existingPhieuDieuPhoi, phieuDieuPhoiDTO);

                return existingPhieuDieuPhoi;
            })
            .map(phieuDieuPhoiRepository::save)
            .map(phieuDieuPhoiMapper::toDto);
    }

    /**
     * Get all the phieuDieuPhois.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<PhieuDieuPhoiDTO> findAll(Pageable pageable) {
        LOG.debug("Request to get all PhieuDieuPhois");
        return phieuDieuPhoiRepository.findAll(pageable).map(phieuDieuPhoiMapper::toDto);
    }

    /**
     * Get one phieuDieuPhoi by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PhieuDieuPhoiDTO> findOne(Long id) {
        LOG.debug("Request to get PhieuDieuPhoi : {}", id);
        return phieuDieuPhoiRepository.findById(id).map(phieuDieuPhoiMapper::toDto);
    }

    /**
     * Delete the phieuDieuPhoi by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete PhieuDieuPhoi : {}", id);
        phieuDieuPhoiRepository.deleteById(id);
    }
}
