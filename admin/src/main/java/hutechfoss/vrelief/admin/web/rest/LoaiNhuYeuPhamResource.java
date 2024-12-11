package hutechfoss.vrelief.admin.web.rest;

import hutechfoss.vrelief.admin.repository.LoaiNhuYeuPhamRepository;
import hutechfoss.vrelief.admin.service.LoaiNhuYeuPhamService;
import hutechfoss.vrelief.admin.service.dto.LoaiNhuYeuPhamDTO;
import hutechfoss.vrelief.admin.web.rest.errors.BadRequestAlertException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link hutechfoss.vrelief.admin.domain.LoaiNhuYeuPham}.
 */
@RestController
@RequestMapping("/api/loai-nhu-yeu-phams")
public class LoaiNhuYeuPhamResource {

    private static final Logger LOG = LoggerFactory.getLogger(LoaiNhuYeuPhamResource.class);

    private static final String ENTITY_NAME = "loaiNhuYeuPham";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LoaiNhuYeuPhamService loaiNhuYeuPhamService;

    private final LoaiNhuYeuPhamRepository loaiNhuYeuPhamRepository;

    public LoaiNhuYeuPhamResource(LoaiNhuYeuPhamService loaiNhuYeuPhamService, LoaiNhuYeuPhamRepository loaiNhuYeuPhamRepository) {
        this.loaiNhuYeuPhamService = loaiNhuYeuPhamService;
        this.loaiNhuYeuPhamRepository = loaiNhuYeuPhamRepository;
    }

    /**
     * {@code POST  /loai-nhu-yeu-phams} : Create a new loaiNhuYeuPham.
     *
     * @param loaiNhuYeuPhamDTO the loaiNhuYeuPhamDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new loaiNhuYeuPhamDTO, or with status {@code 400 (Bad Request)} if the loaiNhuYeuPham has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<LoaiNhuYeuPhamDTO> createLoaiNhuYeuPham(@Valid @RequestBody LoaiNhuYeuPhamDTO loaiNhuYeuPhamDTO)
        throws URISyntaxException {
        LOG.debug("REST request to save LoaiNhuYeuPham : {}", loaiNhuYeuPhamDTO);
        if (loaiNhuYeuPhamDTO.getId() != null) {
            throw new BadRequestAlertException("A new loaiNhuYeuPham cannot already have an ID", ENTITY_NAME, "idexists");
        }
        loaiNhuYeuPhamDTO = loaiNhuYeuPhamService.save(loaiNhuYeuPhamDTO);
        return ResponseEntity.created(new URI("/api/loai-nhu-yeu-phams/" + loaiNhuYeuPhamDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, loaiNhuYeuPhamDTO.getId().toString()))
            .body(loaiNhuYeuPhamDTO);
    }

    /**
     * {@code PUT  /loai-nhu-yeu-phams/:id} : Updates an existing loaiNhuYeuPham.
     *
     * @param id the id of the loaiNhuYeuPhamDTO to save.
     * @param loaiNhuYeuPhamDTO the loaiNhuYeuPhamDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated loaiNhuYeuPhamDTO,
     * or with status {@code 400 (Bad Request)} if the loaiNhuYeuPhamDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the loaiNhuYeuPhamDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<LoaiNhuYeuPhamDTO> updateLoaiNhuYeuPham(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody LoaiNhuYeuPhamDTO loaiNhuYeuPhamDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update LoaiNhuYeuPham : {}, {}", id, loaiNhuYeuPhamDTO);
        if (loaiNhuYeuPhamDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, loaiNhuYeuPhamDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!loaiNhuYeuPhamRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        loaiNhuYeuPhamDTO = loaiNhuYeuPhamService.update(loaiNhuYeuPhamDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, loaiNhuYeuPhamDTO.getId().toString()))
            .body(loaiNhuYeuPhamDTO);
    }

    /**
     * {@code PATCH  /loai-nhu-yeu-phams/:id} : Partial updates given fields of an existing loaiNhuYeuPham, field will ignore if it is null
     *
     * @param id the id of the loaiNhuYeuPhamDTO to save.
     * @param loaiNhuYeuPhamDTO the loaiNhuYeuPhamDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated loaiNhuYeuPhamDTO,
     * or with status {@code 400 (Bad Request)} if the loaiNhuYeuPhamDTO is not valid,
     * or with status {@code 404 (Not Found)} if the loaiNhuYeuPhamDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the loaiNhuYeuPhamDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<LoaiNhuYeuPhamDTO> partialUpdateLoaiNhuYeuPham(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody LoaiNhuYeuPhamDTO loaiNhuYeuPhamDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update LoaiNhuYeuPham partially : {}, {}", id, loaiNhuYeuPhamDTO);
        if (loaiNhuYeuPhamDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, loaiNhuYeuPhamDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!loaiNhuYeuPhamRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<LoaiNhuYeuPhamDTO> result = loaiNhuYeuPhamService.partialUpdate(loaiNhuYeuPhamDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, loaiNhuYeuPhamDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /loai-nhu-yeu-phams} : get all the loaiNhuYeuPhams.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of loaiNhuYeuPhams in body.
     */
    @GetMapping("")
    public ResponseEntity<List<LoaiNhuYeuPhamDTO>> getAllLoaiNhuYeuPhams(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        LOG.debug("REST request to get a page of LoaiNhuYeuPhams");
        Page<LoaiNhuYeuPhamDTO> page = loaiNhuYeuPhamService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /loai-nhu-yeu-phams/:id} : get the "id" loaiNhuYeuPham.
     *
     * @param id the id of the loaiNhuYeuPhamDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the loaiNhuYeuPhamDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<LoaiNhuYeuPhamDTO> getLoaiNhuYeuPham(@PathVariable("id") Long id) {
        LOG.debug("REST request to get LoaiNhuYeuPham : {}", id);
        Optional<LoaiNhuYeuPhamDTO> loaiNhuYeuPhamDTO = loaiNhuYeuPhamService.findOne(id);
        return ResponseUtil.wrapOrNotFound(loaiNhuYeuPhamDTO);
    }

    /**
     * {@code DELETE  /loai-nhu-yeu-phams/:id} : delete the "id" loaiNhuYeuPham.
     *
     * @param id the id of the loaiNhuYeuPhamDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLoaiNhuYeuPham(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete LoaiNhuYeuPham : {}", id);
        loaiNhuYeuPhamService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
