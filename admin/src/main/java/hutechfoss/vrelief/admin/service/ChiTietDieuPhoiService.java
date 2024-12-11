package hutechfoss.vrelief.admin.service;

import hutechfoss.vrelief.admin.domain.ChiTietDieuPhoi;
import hutechfoss.vrelief.admin.repository.ChiTietDieuPhoiRepository;
import hutechfoss.vrelief.admin.service.dto.ChiTietDieuPhoiDTO;
import hutechfoss.vrelief.admin.service.mapper.ChiTietDieuPhoiMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link hutechfoss.vrelief.admin.domain.ChiTietDieuPhoi}.
 */
@Service
@Transactional
public class ChiTietDieuPhoiService {

    private static final Logger LOG = LoggerFactory.getLogger(ChiTietDieuPhoiService.class);

    private final ChiTietDieuPhoiRepository chiTietDieuPhoiRepository;

    private final ChiTietDieuPhoiMapper chiTietDieuPhoiMapper;

    public ChiTietDieuPhoiService(ChiTietDieuPhoiRepository chiTietDieuPhoiRepository, ChiTietDieuPhoiMapper chiTietDieuPhoiMapper) {
        this.chiTietDieuPhoiRepository = chiTietDieuPhoiRepository;
        this.chiTietDieuPhoiMapper = chiTietDieuPhoiMapper;
    }

    /**
     * Save a chiTietDieuPhoi.
     *
     * @param chiTietDieuPhoiDTO the entity to save.
     * @return the persisted entity.
     */
    public ChiTietDieuPhoiDTO save(ChiTietDieuPhoiDTO chiTietDieuPhoiDTO) {
        LOG.debug("Request to save ChiTietDieuPhoi : {}", chiTietDieuPhoiDTO);
        ChiTietDieuPhoi chiTietDieuPhoi = chiTietDieuPhoiMapper.toEntity(chiTietDieuPhoiDTO);
        chiTietDieuPhoi = chiTietDieuPhoiRepository.save(chiTietDieuPhoi);
        return chiTietDieuPhoiMapper.toDto(chiTietDieuPhoi);
    }

    /**
     * Update a chiTietDieuPhoi.
     *
     * @param chiTietDieuPhoiDTO the entity to save.
     * @return the persisted entity.
     */
    public ChiTietDieuPhoiDTO update(ChiTietDieuPhoiDTO chiTietDieuPhoiDTO) {
        LOG.debug("Request to update ChiTietDieuPhoi : {}", chiTietDieuPhoiDTO);
        ChiTietDieuPhoi chiTietDieuPhoi = chiTietDieuPhoiMapper.toEntity(chiTietDieuPhoiDTO);
        chiTietDieuPhoi = chiTietDieuPhoiRepository.save(chiTietDieuPhoi);
        return chiTietDieuPhoiMapper.toDto(chiTietDieuPhoi);
    }

    /**
     * Partially update a chiTietDieuPhoi.
     *
     * @param chiTietDieuPhoiDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ChiTietDieuPhoiDTO> partialUpdate(ChiTietDieuPhoiDTO chiTietDieuPhoiDTO) {
        LOG.debug("Request to partially update ChiTietDieuPhoi : {}", chiTietDieuPhoiDTO);

        return chiTietDieuPhoiRepository
            .findById(chiTietDieuPhoiDTO.getId())
            .map(existingChiTietDieuPhoi -> {
                chiTietDieuPhoiMapper.partialUpdate(existingChiTietDieuPhoi, chiTietDieuPhoiDTO);

                return existingChiTietDieuPhoi;
            })
            .map(chiTietDieuPhoiRepository::save)
            .map(chiTietDieuPhoiMapper::toDto);
    }

    /**
     * Get all the chiTietDieuPhois.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ChiTietDieuPhoiDTO> findAll(Pageable pageable) {
        LOG.debug("Request to get all ChiTietDieuPhois");
        return chiTietDieuPhoiRepository.findAll(pageable).map(chiTietDieuPhoiMapper::toDto);
    }

    /**
     * Get one chiTietDieuPhoi by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ChiTietDieuPhoiDTO> findOne(Long id) {
        LOG.debug("Request to get ChiTietDieuPhoi : {}", id);
        return chiTietDieuPhoiRepository.findById(id).map(chiTietDieuPhoiMapper::toDto);
    }

    /**
     * Delete the chiTietDieuPhoi by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete ChiTietDieuPhoi : {}", id);
        chiTietDieuPhoiRepository.deleteById(id);
    }
}
