package hutechfoss.vrelief.admin.web.rest;

import hutechfoss.vrelief.admin.repository.ChiTietDieuPhoiRepository;
import hutechfoss.vrelief.admin.service.ChiTietDieuPhoiService;
import hutechfoss.vrelief.admin.service.dto.ChiTietDieuPhoiDTO;
import hutechfoss.vrelief.admin.web.rest.errors.BadRequestAlertException;
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
 * REST controller for managing {@link hutechfoss.vrelief.admin.domain.ChiTietDieuPhoi}.
 */
@RestController
@RequestMapping("/api/chi-tiet-dieu-phois")
public class ChiTietDieuPhoiResource {

    private static final Logger LOG = LoggerFactory.getLogger(ChiTietDieuPhoiResource.class);

    private static final String ENTITY_NAME = "chiTietDieuPhoi";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ChiTietDieuPhoiService chiTietDieuPhoiService;

    private final ChiTietDieuPhoiRepository chiTietDieuPhoiRepository;

    public ChiTietDieuPhoiResource(ChiTietDieuPhoiService chiTietDieuPhoiService, ChiTietDieuPhoiRepository chiTietDieuPhoiRepository) {
        this.chiTietDieuPhoiService = chiTietDieuPhoiService;
        this.chiTietDieuPhoiRepository = chiTietDieuPhoiRepository;
    }

    /**
     * {@code POST  /chi-tiet-dieu-phois} : Create a new chiTietDieuPhoi.
     *
     * @param chiTietDieuPhoiDTO the chiTietDieuPhoiDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new chiTietDieuPhoiDTO, or with status {@code 400 (Bad Request)} if the chiTietDieuPhoi has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<ChiTietDieuPhoiDTO> createChiTietDieuPhoi(@RequestBody ChiTietDieuPhoiDTO chiTietDieuPhoiDTO)
        throws URISyntaxException {
        LOG.debug("REST request to save ChiTietDieuPhoi : {}", chiTietDieuPhoiDTO);
        if (chiTietDieuPhoiDTO.getId() != null) {
            throw new BadRequestAlertException("A new chiTietDieuPhoi cannot already have an ID", ENTITY_NAME, "idexists");
        }
        chiTietDieuPhoiDTO = chiTietDieuPhoiService.save(chiTietDieuPhoiDTO);
        return ResponseEntity.created(new URI("/api/chi-tiet-dieu-phois/" + chiTietDieuPhoiDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, chiTietDieuPhoiDTO.getId().toString()))
            .body(chiTietDieuPhoiDTO);
    }

    /**
     * {@code PUT  /chi-tiet-dieu-phois/:id} : Updates an existing chiTietDieuPhoi.
     *
     * @param id the id of the chiTietDieuPhoiDTO to save.
     * @param chiTietDieuPhoiDTO the chiTietDieuPhoiDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated chiTietDieuPhoiDTO,
     * or with status {@code 400 (Bad Request)} if the chiTietDieuPhoiDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the chiTietDieuPhoiDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ChiTietDieuPhoiDTO> updateChiTietDieuPhoi(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ChiTietDieuPhoiDTO chiTietDieuPhoiDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update ChiTietDieuPhoi : {}, {}", id, chiTietDieuPhoiDTO);
        if (chiTietDieuPhoiDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, chiTietDieuPhoiDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!chiTietDieuPhoiRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        chiTietDieuPhoiDTO = chiTietDieuPhoiService.update(chiTietDieuPhoiDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, chiTietDieuPhoiDTO.getId().toString()))
            .body(chiTietDieuPhoiDTO);
    }

    /**
     * {@code PATCH  /chi-tiet-dieu-phois/:id} : Partial updates given fields of an existing chiTietDieuPhoi, field will ignore if it is null
     *
     * @param id the id of the chiTietDieuPhoiDTO to save.
     * @param chiTietDieuPhoiDTO the chiTietDieuPhoiDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated chiTietDieuPhoiDTO,
     * or with status {@code 400 (Bad Request)} if the chiTietDieuPhoiDTO is not valid,
     * or with status {@code 404 (Not Found)} if the chiTietDieuPhoiDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the chiTietDieuPhoiDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ChiTietDieuPhoiDTO> partialUpdateChiTietDieuPhoi(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ChiTietDieuPhoiDTO chiTietDieuPhoiDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update ChiTietDieuPhoi partially : {}, {}", id, chiTietDieuPhoiDTO);
        if (chiTietDieuPhoiDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, chiTietDieuPhoiDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!chiTietDieuPhoiRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ChiTietDieuPhoiDTO> result = chiTietDieuPhoiService.partialUpdate(chiTietDieuPhoiDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, chiTietDieuPhoiDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /chi-tiet-dieu-phois} : get all the chiTietDieuPhois.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of chiTietDieuPhois in body.
     */
    @GetMapping("")
    public ResponseEntity<List<ChiTietDieuPhoiDTO>> getAllChiTietDieuPhois(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        LOG.debug("REST request to get a page of ChiTietDieuPhois");
        Page<ChiTietDieuPhoiDTO> page = chiTietDieuPhoiService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /chi-tiet-dieu-phois/:id} : get the "id" chiTietDieuPhoi.
     *
     * @param id the id of the chiTietDieuPhoiDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the chiTietDieuPhoiDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ChiTietDieuPhoiDTO> getChiTietDieuPhoi(@PathVariable("id") Long id) {
        LOG.debug("REST request to get ChiTietDieuPhoi : {}", id);
        Optional<ChiTietDieuPhoiDTO> chiTietDieuPhoiDTO = chiTietDieuPhoiService.findOne(id);
        return ResponseUtil.wrapOrNotFound(chiTietDieuPhoiDTO);
    }

    /**
     * {@code DELETE  /chi-tiet-dieu-phois/:id} : delete the "id" chiTietDieuPhoi.
     *
     * @param id the id of the chiTietDieuPhoiDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChiTietDieuPhoi(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete ChiTietDieuPhoi : {}", id);
        chiTietDieuPhoiService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
