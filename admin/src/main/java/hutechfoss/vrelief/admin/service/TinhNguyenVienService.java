package hutechfoss.vrelief.admin.service;

import hutechfoss.vrelief.admin.domain.TinhNguyenVien;
import hutechfoss.vrelief.admin.repository.TinhNguyenVienRepository;
import hutechfoss.vrelief.admin.service.dto.TinhNguyenVienDTO;
import hutechfoss.vrelief.admin.service.mapper.TinhNguyenVienMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link hutechfoss.vrelief.admin.domain.TinhNguyenVien}.
 */
@Service
@Transactional
public class TinhNguyenVienService {

    private static final Logger LOG = LoggerFactory.getLogger(TinhNguyenVienService.class);

    private final TinhNguyenVienRepository tinhNguyenVienRepository;

    private final TinhNguyenVienMapper tinhNguyenVienMapper;

    public TinhNguyenVienService(TinhNguyenVienRepository tinhNguyenVienRepository, TinhNguyenVienMapper tinhNguyenVienMapper) {
        this.tinhNguyenVienRepository = tinhNguyenVienRepository;
        this.tinhNguyenVienMapper = tinhNguyenVienMapper;
    }

    /**
     * Save a tinhNguyenVien.
     *
     * @param tinhNguyenVienDTO the entity to save.
     * @return the persisted entity.
     */
    public TinhNguyenVienDTO save(TinhNguyenVienDTO tinhNguyenVienDTO) {
        LOG.debug("Request to save TinhNguyenVien : {}", tinhNguyenVienDTO);
        TinhNguyenVien tinhNguyenVien = tinhNguyenVienMapper.toEntity(tinhNguyenVienDTO);
        tinhNguyenVien = tinhNguyenVienRepository.save(tinhNguyenVien);
        return tinhNguyenVienMapper.toDto(tinhNguyenVien);
    }

    /**
     * Update a tinhNguyenVien.
     *
     * @param tinhNguyenVienDTO the entity to save.
     * @return the persisted entity.
     */
    public TinhNguyenVienDTO update(TinhNguyenVienDTO tinhNguyenVienDTO) {
        LOG.debug("Request to update TinhNguyenVien : {}", tinhNguyenVienDTO);
        TinhNguyenVien tinhNguyenVien = tinhNguyenVienMapper.toEntity(tinhNguyenVienDTO);
        tinhNguyenVien = tinhNguyenVienRepository.save(tinhNguyenVien);
        return tinhNguyenVienMapper.toDto(tinhNguyenVien);
    }

    /**
     * Partially update a tinhNguyenVien.
     *
     * @param tinhNguyenVienDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<TinhNguyenVienDTO> partialUpdate(TinhNguyenVienDTO tinhNguyenVienDTO) {
        LOG.debug("Request to partially update TinhNguyenVien : {}", tinhNguyenVienDTO);

        return tinhNguyenVienRepository
            .findById(tinhNguyenVienDTO.getId())
            .map(existingTinhNguyenVien -> {
                tinhNguyenVienMapper.partialUpdate(existingTinhNguyenVien, tinhNguyenVienDTO);

                return existingTinhNguyenVien;
            })
            .map(tinhNguyenVienRepository::save)
            .map(tinhNguyenVienMapper::toDto);
    }

    /**
     * Get all the tinhNguyenViens.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<TinhNguyenVienDTO> findAll(Pageable pageable) {
        LOG.debug("Request to get all TinhNguyenViens");
        return tinhNguyenVienRepository.findAll(pageable).map(tinhNguyenVienMapper::toDto);
    }

    /**
     * Get one tinhNguyenVien by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TinhNguyenVienDTO> findOne(Long id) {
        LOG.debug("Request to get TinhNguyenVien : {}", id);
        return tinhNguyenVienRepository.findById(id).map(tinhNguyenVienMapper::toDto);
    }

    /**
     * Delete the tinhNguyenVien by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete TinhNguyenVien : {}", id);
        tinhNguyenVienRepository.deleteById(id);
    }
}
