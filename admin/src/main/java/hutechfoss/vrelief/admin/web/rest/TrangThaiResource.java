package hutechfoss.vrelief.admin.web.rest;

import hutechfoss.vrelief.admin.repository.TrangThaiRepository;
import hutechfoss.vrelief.admin.service.TrangThaiService;
import hutechfoss.vrelief.admin.service.dto.TrangThaiDTO;
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
 * REST controller for managing {@link hutechfoss.vrelief.admin.domain.TrangThai}.
 */
@RestController
@RequestMapping("/api/trang-thais")
public class TrangThaiResource {

    private static final Logger LOG = LoggerFactory.getLogger(TrangThaiResource.class);

    private static final String ENTITY_NAME = "trangThai";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TrangThaiService trangThaiService;

    private final TrangThaiRepository trangThaiRepository;

    public TrangThaiResource(TrangThaiService trangThaiService, TrangThaiRepository trangThaiRepository) {
        this.trangThaiService = trangThaiService;
        this.trangThaiRepository = trangThaiRepository;
    }

    /**
     * {@code POST  /trang-thais} : Create a new trangThai.
     *
     * @param trangThaiDTO the trangThaiDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new trangThaiDTO, or with status {@code 400 (Bad Request)} if the trangThai has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<TrangThaiDTO> createTrangThai(@Valid @RequestBody TrangThaiDTO trangThaiDTO) throws URISyntaxException {
        LOG.debug("REST request to save TrangThai : {}", trangThaiDTO);
        if (trangThaiDTO.getId() != null) {
            throw new BadRequestAlertException("A new trangThai cannot already have an ID", ENTITY_NAME, "idexists");
        }
        trangThaiDTO = trangThaiService.save(trangThaiDTO);
        return ResponseEntity.created(new URI("/api/trang-thais/" + trangThaiDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, trangThaiDTO.getId().toString()))
            .body(trangThaiDTO);
    }

    /**
     * {@code PUT  /trang-thais/:id} : Updates an existing trangThai.
     *
     * @param id the id of the trangThaiDTO to save.
     * @param trangThaiDTO the trangThaiDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated trangThaiDTO,
     * or with status {@code 400 (Bad Request)} if the trangThaiDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the trangThaiDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<TrangThaiDTO> updateTrangThai(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody TrangThaiDTO trangThaiDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update TrangThai : {}, {}", id, trangThaiDTO);
        if (trangThaiDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, trangThaiDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!trangThaiRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        trangThaiDTO = trangThaiService.update(trangThaiDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, trangThaiDTO.getId().toString()))
            .body(trangThaiDTO);
    }

    /**
     * {@code PATCH  /trang-thais/:id} : Partial updates given fields of an existing trangThai, field will ignore if it is null
     *
     * @param id the id of the trangThaiDTO to save.
     * @param trangThaiDTO the trangThaiDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated trangThaiDTO,
     * or with status {@code 400 (Bad Request)} if the trangThaiDTO is not valid,
     * or with status {@code 404 (Not Found)} if the trangThaiDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the trangThaiDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<TrangThaiDTO> partialUpdateTrangThai(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody TrangThaiDTO trangThaiDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update TrangThai partially : {}, {}", id, trangThaiDTO);
        if (trangThaiDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, trangThaiDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!trangThaiRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TrangThaiDTO> result = trangThaiService.partialUpdate(trangThaiDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, trangThaiDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /trang-thais} : get all the trangThais.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of trangThais in body.
     */
    @GetMapping("")
    public ResponseEntity<List<TrangThaiDTO>> getAllTrangThais(@org.springdoc.core.annotations.ParameterObject Pageable pageable) {
        LOG.debug("REST request to get a page of TrangThais");
        Page<TrangThaiDTO> page = trangThaiService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /trang-thais/:id} : get the "id" trangThai.
     *
     * @param id the id of the trangThaiDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the trangThaiDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<TrangThaiDTO> getTrangThai(@PathVariable("id") Long id) {
        LOG.debug("REST request to get TrangThai : {}", id);
        Optional<TrangThaiDTO> trangThaiDTO = trangThaiService.findOne(id);
        return ResponseUtil.wrapOrNotFound(trangThaiDTO);
    }

    /**
     * {@code DELETE  /trang-thais/:id} : delete the "id" trangThai.
     *
     * @param id the id of the trangThaiDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrangThai(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete TrangThai : {}", id);
        trangThaiService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
