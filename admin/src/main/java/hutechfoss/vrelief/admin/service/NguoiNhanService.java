package hutechfoss.vrelief.admin.service;

import hutechfoss.vrelief.admin.domain.NguoiNhan;
import hutechfoss.vrelief.admin.repository.NguoiNhanRepository;
import hutechfoss.vrelief.admin.service.dto.NguoiNhanDTO;
import hutechfoss.vrelief.admin.service.mapper.NguoiNhanMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link hutechfoss.vrelief.admin.domain.NguoiNhan}.
 */
@Service
@Transactional
public class NguoiNhanService {

    private static final Logger LOG = LoggerFactory.getLogger(NguoiNhanService.class);

    private final NguoiNhanRepository nguoiNhanRepository;

    private final NguoiNhanMapper nguoiNhanMapper;

    public NguoiNhanService(NguoiNhanRepository nguoiNhanRepository, NguoiNhanMapper nguoiNhanMapper) {
        this.nguoiNhanRepository = nguoiNhanRepository;
        this.nguoiNhanMapper = nguoiNhanMapper;
    }

    /**
     * Save a nguoiNhan.
     *
     * @param nguoiNhanDTO the entity to save.
     * @return the persisted entity.
     */
    public NguoiNhanDTO save(NguoiNhanDTO nguoiNhanDTO) {
        LOG.debug("Request to save NguoiNhan : {}", nguoiNhanDTO);
        NguoiNhan nguoiNhan = nguoiNhanMapper.toEntity(nguoiNhanDTO);
        nguoiNhan = nguoiNhanRepository.save(nguoiNhan);
        return nguoiNhanMapper.toDto(nguoiNhan);
    }

    /**
     * Update a nguoiNhan.
     *
     * @param nguoiNhanDTO the entity to save.
     * @return the persisted entity.
     */
    public NguoiNhanDTO update(NguoiNhanDTO nguoiNhanDTO) {
        LOG.debug("Request to update NguoiNhan : {}", nguoiNhanDTO);
        NguoiNhan nguoiNhan = nguoiNhanMapper.toEntity(nguoiNhanDTO);
        nguoiNhan = nguoiNhanRepository.save(nguoiNhan);
        return nguoiNhanMapper.toDto(nguoiNhan);
    }

    /**
     * Partially update a nguoiNhan.
     *
     * @param nguoiNhanDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<NguoiNhanDTO> partialUpdate(NguoiNhanDTO nguoiNhanDTO) {
        LOG.debug("Request to partially update NguoiNhan : {}", nguoiNhanDTO);

        return nguoiNhanRepository
            .findById(nguoiNhanDTO.getId())
            .map(existingNguoiNhan -> {
                nguoiNhanMapper.partialUpdate(existingNguoiNhan, nguoiNhanDTO);

                return existingNguoiNhan;
            })
            .map(nguoiNhanRepository::save)
            .map(nguoiNhanMapper::toDto);
    }

    /**
     * Get all the nguoiNhans.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<NguoiNhanDTO> findAll(Pageable pageable) {
        LOG.debug("Request to get all NguoiNhans");
        return nguoiNhanRepository.findAll(pageable).map(nguoiNhanMapper::toDto);
    }

    /**
     * Get one nguoiNhan by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<NguoiNhanDTO> findOne(Long id) {
        LOG.debug("Request to get NguoiNhan : {}", id);
        return nguoiNhanRepository.findById(id).map(nguoiNhanMapper::toDto);
    }

    /**
     * Delete the nguoiNhan by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete NguoiNhan : {}", id);
        nguoiNhanRepository.deleteById(id);
    }
}
