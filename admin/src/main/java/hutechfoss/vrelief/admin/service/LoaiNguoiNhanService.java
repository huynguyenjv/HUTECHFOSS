package hutechfoss.vrelief.admin.service;

import hutechfoss.vrelief.admin.domain.LoaiNguoiNhan;
import hutechfoss.vrelief.admin.repository.LoaiNguoiNhanRepository;
import hutechfoss.vrelief.admin.service.dto.LoaiNguoiNhanDTO;
import hutechfoss.vrelief.admin.service.mapper.LoaiNguoiNhanMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link hutechfoss.vrelief.admin.domain.LoaiNguoiNhan}.
 */
@Service
@Transactional
public class LoaiNguoiNhanService {

    private static final Logger LOG = LoggerFactory.getLogger(LoaiNguoiNhanService.class);

    private final LoaiNguoiNhanRepository loaiNguoiNhanRepository;

    private final LoaiNguoiNhanMapper loaiNguoiNhanMapper;

    public LoaiNguoiNhanService(LoaiNguoiNhanRepository loaiNguoiNhanRepository, LoaiNguoiNhanMapper loaiNguoiNhanMapper) {
        this.loaiNguoiNhanRepository = loaiNguoiNhanRepository;
        this.loaiNguoiNhanMapper = loaiNguoiNhanMapper;
    }

    /**
     * Save a loaiNguoiNhan.
     *
     * @param loaiNguoiNhanDTO the entity to save.
     * @return the persisted entity.
     */
    public LoaiNguoiNhanDTO save(LoaiNguoiNhanDTO loaiNguoiNhanDTO) {
        LOG.debug("Request to save LoaiNguoiNhan : {}", loaiNguoiNhanDTO);
        LoaiNguoiNhan loaiNguoiNhan = loaiNguoiNhanMapper.toEntity(loaiNguoiNhanDTO);
        loaiNguoiNhan = loaiNguoiNhanRepository.save(loaiNguoiNhan);
        return loaiNguoiNhanMapper.toDto(loaiNguoiNhan);
    }

    /**
     * Update a loaiNguoiNhan.
     *
     * @param loaiNguoiNhanDTO the entity to save.
     * @return the persisted entity.
     */
    public LoaiNguoiNhanDTO update(LoaiNguoiNhanDTO loaiNguoiNhanDTO) {
        LOG.debug("Request to update LoaiNguoiNhan : {}", loaiNguoiNhanDTO);
        LoaiNguoiNhan loaiNguoiNhan = loaiNguoiNhanMapper.toEntity(loaiNguoiNhanDTO);
        loaiNguoiNhan = loaiNguoiNhanRepository.save(loaiNguoiNhan);
        return loaiNguoiNhanMapper.toDto(loaiNguoiNhan);
    }

    /**
     * Partially update a loaiNguoiNhan.
     *
     * @param loaiNguoiNhanDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<LoaiNguoiNhanDTO> partialUpdate(LoaiNguoiNhanDTO loaiNguoiNhanDTO) {
        LOG.debug("Request to partially update LoaiNguoiNhan : {}", loaiNguoiNhanDTO);

        return loaiNguoiNhanRepository
            .findById(loaiNguoiNhanDTO.getId())
            .map(existingLoaiNguoiNhan -> {
                loaiNguoiNhanMapper.partialUpdate(existingLoaiNguoiNhan, loaiNguoiNhanDTO);

                return existingLoaiNguoiNhan;
            })
            .map(loaiNguoiNhanRepository::save)
            .map(loaiNguoiNhanMapper::toDto);
    }

    /**
     * Get all the loaiNguoiNhans.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<LoaiNguoiNhanDTO> findAll(Pageable pageable) {
        LOG.debug("Request to get all LoaiNguoiNhans");
        return loaiNguoiNhanRepository.findAll(pageable).map(loaiNguoiNhanMapper::toDto);
    }

    /**
     * Get one loaiNguoiNhan by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<LoaiNguoiNhanDTO> findOne(Long id) {
        LOG.debug("Request to get LoaiNguoiNhan : {}", id);
        return loaiNguoiNhanRepository.findById(id).map(loaiNguoiNhanMapper::toDto);
    }

    /**
     * Delete the loaiNguoiNhan by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete LoaiNguoiNhan : {}", id);
        loaiNguoiNhanRepository.deleteById(id);
    }
}
