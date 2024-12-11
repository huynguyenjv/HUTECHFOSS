package hutechfoss.vrelief.admin.web.rest;

import hutechfoss.vrelief.admin.repository.TinhNguyenVienRepository;
import hutechfoss.vrelief.admin.service.TinhNguyenVienService;
import hutechfoss.vrelief.admin.service.dto.TinhNguyenVienDTO;
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
 * REST controller for managing {@link hutechfoss.vrelief.admin.domain.TinhNguyenVien}.
 */
@RestController
@RequestMapping("/api/tinh-nguyen-viens")
public class TinhNguyenVienResource {

    private static final Logger LOG = LoggerFactory.getLogger(TinhNguyenVienResource.class);

    private static final String ENTITY_NAME = "tinhNguyenVien";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TinhNguyenVienService tinhNguyenVienService;

    private final TinhNguyenVienRepository tinhNguyenVienRepository;

    public TinhNguyenVienResource(TinhNguyenVienService tinhNguyenVienService, TinhNguyenVienRepository tinhNguyenVienRepository) {
        this.tinhNguyenVienService = tinhNguyenVienService;
        this.tinhNguyenVienRepository = tinhNguyenVienRepository;
    }

    /**
     * {@code POST  /tinh-nguyen-viens} : Create a new tinhNguyenVien.
     *
     * @param tinhNguyenVienDTO the tinhNguyenVienDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tinhNguyenVienDTO, or with status {@code 400 (Bad Request)} if the tinhNguyenVien has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<TinhNguyenVienDTO> createTinhNguyenVien(@Valid @RequestBody TinhNguyenVienDTO tinhNguyenVienDTO)
        throws URISyntaxException {
        LOG.debug("REST request to save TinhNguyenVien : {}", tinhNguyenVienDTO);
        if (tinhNguyenVienDTO.getId() != null) {
            throw new BadRequestAlertException("A new tinhNguyenVien cannot already have an ID", ENTITY_NAME, "idexists");
        }
        tinhNguyenVienDTO = tinhNguyenVienService.save(tinhNguyenVienDTO);
        return ResponseEntity.created(new URI("/api/tinh-nguyen-viens/" + tinhNguyenVienDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, tinhNguyenVienDTO.getId().toString()))
            .body(tinhNguyenVienDTO);
    }

    /**
     * {@code PUT  /tinh-nguyen-viens/:id} : Updates an existing tinhNguyenVien.
     *
     * @param id the id of the tinhNguyenVienDTO to save.
     * @param tinhNguyenVienDTO the tinhNguyenVienDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tinhNguyenVienDTO,
     * or with status {@code 400 (Bad Request)} if the tinhNguyenVienDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tinhNguyenVienDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<TinhNguyenVienDTO> updateTinhNguyenVien(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody TinhNguyenVienDTO tinhNguyenVienDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update TinhNguyenVien : {}, {}", id, tinhNguyenVienDTO);
        if (tinhNguyenVienDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tinhNguyenVienDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tinhNguyenVienRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        tinhNguyenVienDTO = tinhNguyenVienService.update(tinhNguyenVienDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tinhNguyenVienDTO.getId().toString()))
            .body(tinhNguyenVienDTO);
    }

    /**
     * {@code PATCH  /tinh-nguyen-viens/:id} : Partial updates given fields of an existing tinhNguyenVien, field will ignore if it is null
     *
     * @param id the id of the tinhNguyenVienDTO to save.
     * @param tinhNguyenVienDTO the tinhNguyenVienDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tinhNguyenVienDTO,
     * or with status {@code 400 (Bad Request)} if the tinhNguyenVienDTO is not valid,
     * or with status {@code 404 (Not Found)} if the tinhNguyenVienDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the tinhNguyenVienDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<TinhNguyenVienDTO> partialUpdateTinhNguyenVien(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody TinhNguyenVienDTO tinhNguyenVienDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update TinhNguyenVien partially : {}, {}", id, tinhNguyenVienDTO);
        if (tinhNguyenVienDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tinhNguyenVienDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tinhNguyenVienRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TinhNguyenVienDTO> result = tinhNguyenVienService.partialUpdate(tinhNguyenVienDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tinhNguyenVienDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /tinh-nguyen-viens} : get all the tinhNguyenViens.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tinhNguyenViens in body.
     */
    @GetMapping("")
    public ResponseEntity<List<TinhNguyenVienDTO>> getAllTinhNguyenViens(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        LOG.debug("REST request to get a page of TinhNguyenViens");
        Page<TinhNguyenVienDTO> page = tinhNguyenVienService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /tinh-nguyen-viens/:id} : get the "id" tinhNguyenVien.
     *
     * @param id the id of the tinhNguyenVienDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tinhNguyenVienDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<TinhNguyenVienDTO> getTinhNguyenVien(@PathVariable("id") Long id) {
        LOG.debug("REST request to get TinhNguyenVien : {}", id);
        Optional<TinhNguyenVienDTO> tinhNguyenVienDTO = tinhNguyenVienService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tinhNguyenVienDTO);
    }

    /**
     * {@code DELETE  /tinh-nguyen-viens/:id} : delete the "id" tinhNguyenVien.
     *
     * @param id the id of the tinhNguyenVienDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTinhNguyenVien(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete TinhNguyenVien : {}", id);
        tinhNguyenVienService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
