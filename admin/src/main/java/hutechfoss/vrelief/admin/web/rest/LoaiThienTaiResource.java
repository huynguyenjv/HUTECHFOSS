package hutechfoss.vrelief.admin.web.rest;

import hutechfoss.vrelief.admin.repository.LoaiThienTaiRepository;
import hutechfoss.vrelief.admin.service.LoaiThienTaiService;
import hutechfoss.vrelief.admin.service.dto.LoaiThienTaiDTO;
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
 * REST controller for managing {@link hutechfoss.vrelief.admin.domain.LoaiThienTai}.
 */
@RestController
@RequestMapping("/api/loai-thien-tais")
public class LoaiThienTaiResource {

    private static final Logger LOG = LoggerFactory.getLogger(LoaiThienTaiResource.class);

    private static final String ENTITY_NAME = "loaiThienTai";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LoaiThienTaiService loaiThienTaiService;

    private final LoaiThienTaiRepository loaiThienTaiRepository;

    public LoaiThienTaiResource(LoaiThienTaiService loaiThienTaiService, LoaiThienTaiRepository loaiThienTaiRepository) {
        this.loaiThienTaiService = loaiThienTaiService;
        this.loaiThienTaiRepository = loaiThienTaiRepository;
    }

    /**
     * {@code POST  /loai-thien-tais} : Create a new loaiThienTai.
     *
     * @param loaiThienTaiDTO the loaiThienTaiDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new loaiThienTaiDTO, or with status {@code 400 (Bad Request)} if the loaiThienTai has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<LoaiThienTaiDTO> createLoaiThienTai(@Valid @RequestBody LoaiThienTaiDTO loaiThienTaiDTO)
        throws URISyntaxException {
        LOG.debug("REST request to save LoaiThienTai : {}", loaiThienTaiDTO);
        if (loaiThienTaiDTO.getId() != null) {
            throw new BadRequestAlertException("A new loaiThienTai cannot already have an ID", ENTITY_NAME, "idexists");
        }
        loaiThienTaiDTO = loaiThienTaiService.save(loaiThienTaiDTO);
        return ResponseEntity.created(new URI("/api/loai-thien-tais/" + loaiThienTaiDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, loaiThienTaiDTO.getId().toString()))
            .body(loaiThienTaiDTO);
    }

    /**
     * {@code PUT  /loai-thien-tais/:id} : Updates an existing loaiThienTai.
     *
     * @param id the id of the loaiThienTaiDTO to save.
     * @param loaiThienTaiDTO the loaiThienTaiDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated loaiThienTaiDTO,
     * or with status {@code 400 (Bad Request)} if the loaiThienTaiDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the loaiThienTaiDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<LoaiThienTaiDTO> updateLoaiThienTai(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody LoaiThienTaiDTO loaiThienTaiDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update LoaiThienTai : {}, {}", id, loaiThienTaiDTO);
        if (loaiThienTaiDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, loaiThienTaiDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!loaiThienTaiRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        loaiThienTaiDTO = loaiThienTaiService.update(loaiThienTaiDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, loaiThienTaiDTO.getId().toString()))
            .body(loaiThienTaiDTO);
    }

    /**
     * {@code PATCH  /loai-thien-tais/:id} : Partial updates given fields of an existing loaiThienTai, field will ignore if it is null
     *
     * @param id the id of the loaiThienTaiDTO to save.
     * @param loaiThienTaiDTO the loaiThienTaiDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated loaiThienTaiDTO,
     * or with status {@code 400 (Bad Request)} if the loaiThienTaiDTO is not valid,
     * or with status {@code 404 (Not Found)} if the loaiThienTaiDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the loaiThienTaiDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<LoaiThienTaiDTO> partialUpdateLoaiThienTai(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody LoaiThienTaiDTO loaiThienTaiDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update LoaiThienTai partially : {}, {}", id, loaiThienTaiDTO);
        if (loaiThienTaiDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, loaiThienTaiDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!loaiThienTaiRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<LoaiThienTaiDTO> result = loaiThienTaiService.partialUpdate(loaiThienTaiDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, loaiThienTaiDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /loai-thien-tais} : get all the loaiThienTais.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of loaiThienTais in body.
     */
    @GetMapping("")
    public ResponseEntity<List<LoaiThienTaiDTO>> getAllLoaiThienTais(@org.springdoc.core.annotations.ParameterObject Pageable pageable) {
        LOG.debug("REST request to get a page of LoaiThienTais");
        Page<LoaiThienTaiDTO> page = loaiThienTaiService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /loai-thien-tais/:id} : get the "id" loaiThienTai.
     *
     * @param id the id of the loaiThienTaiDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the loaiThienTaiDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<LoaiThienTaiDTO> getLoaiThienTai(@PathVariable("id") Long id) {
        LOG.debug("REST request to get LoaiThienTai : {}", id);
        Optional<LoaiThienTaiDTO> loaiThienTaiDTO = loaiThienTaiService.findOne(id);
        return ResponseUtil.wrapOrNotFound(loaiThienTaiDTO);
    }

    /**
     * {@code DELETE  /loai-thien-tais/:id} : delete the "id" loaiThienTai.
     *
     * @param id the id of the loaiThienTaiDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLoaiThienTai(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete LoaiThienTai : {}", id);
        loaiThienTaiService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
