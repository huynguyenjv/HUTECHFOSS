package hutechfoss.vrelief.admin.service;

import hutechfoss.vrelief.admin.domain.LoaiNhuYeuPham;
import hutechfoss.vrelief.admin.repository.LoaiNhuYeuPhamRepository;
import hutechfoss.vrelief.admin.service.dto.LoaiNhuYeuPhamDTO;
import hutechfoss.vrelief.admin.service.mapper.LoaiNhuYeuPhamMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link hutechfoss.vrelief.admin.domain.LoaiNhuYeuPham}.
 */
@Service
@Transactional
public class LoaiNhuYeuPhamService {

    private static final Logger LOG = LoggerFactory.getLogger(LoaiNhuYeuPhamService.class);

    private final LoaiNhuYeuPhamRepository loaiNhuYeuPhamRepository;

    private final LoaiNhuYeuPhamMapper loaiNhuYeuPhamMapper;

    public LoaiNhuYeuPhamService(LoaiNhuYeuPhamRepository loaiNhuYeuPhamRepository, LoaiNhuYeuPhamMapper loaiNhuYeuPhamMapper) {
        this.loaiNhuYeuPhamRepository = loaiNhuYeuPhamRepository;
        this.loaiNhuYeuPhamMapper = loaiNhuYeuPhamMapper;
    }

    /**
     * Save a loaiNhuYeuPham.
     *
     * @param loaiNhuYeuPhamDTO the entity to save.
     * @return the persisted entity.
     */
    public LoaiNhuYeuPhamDTO save(LoaiNhuYeuPhamDTO loaiNhuYeuPhamDTO) {
        LOG.debug("Request to save LoaiNhuYeuPham : {}", loaiNhuYeuPhamDTO);
        LoaiNhuYeuPham loaiNhuYeuPham = loaiNhuYeuPhamMapper.toEntity(loaiNhuYeuPhamDTO);
        loaiNhuYeuPham = loaiNhuYeuPhamRepository.save(loaiNhuYeuPham);
        return loaiNhuYeuPhamMapper.toDto(loaiNhuYeuPham);
    }

    /**
     * Update a loaiNhuYeuPham.
     *
     * @param loaiNhuYeuPhamDTO the entity to save.
     * @return the persisted entity.
     */
    public LoaiNhuYeuPhamDTO update(LoaiNhuYeuPhamDTO loaiNhuYeuPhamDTO) {
        LOG.debug("Request to update LoaiNhuYeuPham : {}", loaiNhuYeuPhamDTO);
        LoaiNhuYeuPham loaiNhuYeuPham = loaiNhuYeuPhamMapper.toEntity(loaiNhuYeuPhamDTO);
        loaiNhuYeuPham = loaiNhuYeuPhamRepository.save(loaiNhuYeuPham);
        return loaiNhuYeuPhamMapper.toDto(loaiNhuYeuPham);
    }

    /**
     * Partially update a loaiNhuYeuPham.
     *
     * @param loaiNhuYeuPhamDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<LoaiNhuYeuPhamDTO> partialUpdate(LoaiNhuYeuPhamDTO loaiNhuYeuPhamDTO) {
        LOG.debug("Request to partially update LoaiNhuYeuPham : {}", loaiNhuYeuPhamDTO);

        return loaiNhuYeuPhamRepository
            .findById(loaiNhuYeuPhamDTO.getId())
            .map(existingLoaiNhuYeuPham -> {
                loaiNhuYeuPhamMapper.partialUpdate(existingLoaiNhuYeuPham, loaiNhuYeuPhamDTO);

                return existingLoaiNhuYeuPham;
            })
            .map(loaiNhuYeuPhamRepository::save)
            .map(loaiNhuYeuPhamMapper::toDto);
    }

    /**
     * Get all the loaiNhuYeuPhams.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<LoaiNhuYeuPhamDTO> findAll(Pageable pageable) {
        LOG.debug("Request to get all LoaiNhuYeuPhams");
        return loaiNhuYeuPhamRepository.findAll(pageable).map(loaiNhuYeuPhamMapper::toDto);
    }

    /**
     * Get one loaiNhuYeuPham by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<LoaiNhuYeuPhamDTO> findOne(Long id) {
        LOG.debug("Request to get LoaiNhuYeuPham : {}", id);
        return loaiNhuYeuPhamRepository.findById(id).map(loaiNhuYeuPhamMapper::toDto);
    }

    /**
     * Delete the loaiNhuYeuPham by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete LoaiNhuYeuPham : {}", id);
        loaiNhuYeuPhamRepository.deleteById(id);
    }
}
