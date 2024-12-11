package hutechfoss.vrelief.admin.web.rest;

import hutechfoss.vrelief.admin.repository.PhieuDieuPhoiRepository;
import hutechfoss.vrelief.admin.service.PhieuDieuPhoiService;
import hutechfoss.vrelief.admin.service.dto.PhieuDieuPhoiDTO;
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
 * REST controller for managing {@link hutechfoss.vrelief.admin.domain.PhieuDieuPhoi}.
 */
@RestController
@RequestMapping("/api/phieu-dieu-phois")
public class PhieuDieuPhoiResource {

    private static final Logger LOG = LoggerFactory.getLogger(PhieuDieuPhoiResource.class);

    private static final String ENTITY_NAME = "phieuDieuPhoi";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PhieuDieuPhoiService phieuDieuPhoiService;

    private final PhieuDieuPhoiRepository phieuDieuPhoiRepository;

    public PhieuDieuPhoiResource(PhieuDieuPhoiService phieuDieuPhoiService, PhieuDieuPhoiRepository phieuDieuPhoiRepository) {
        this.phieuDieuPhoiService = phieuDieuPhoiService;
        this.phieuDieuPhoiRepository = phieuDieuPhoiRepository;
    }

    /**
     * {@code POST  /phieu-dieu-phois} : Create a new phieuDieuPhoi.
     *
     * @param phieuDieuPhoiDTO the phieuDieuPhoiDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new phieuDieuPhoiDTO, or with status {@code 400 (Bad Request)} if the phieuDieuPhoi has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<PhieuDieuPhoiDTO> createPhieuDieuPhoi(@RequestBody PhieuDieuPhoiDTO phieuDieuPhoiDTO) throws URISyntaxException {
        LOG.debug("REST request to save PhieuDieuPhoi : {}", phieuDieuPhoiDTO);
        if (phieuDieuPhoiDTO.getId() != null) {
            throw new BadRequestAlertException("A new phieuDieuPhoi cannot already have an ID", ENTITY_NAME, "idexists");
        }
        phieuDieuPhoiDTO = phieuDieuPhoiService.save(phieuDieuPhoiDTO);
        return ResponseEntity.created(new URI("/api/phieu-dieu-phois/" + phieuDieuPhoiDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, phieuDieuPhoiDTO.getId().toString()))
            .body(phieuDieuPhoiDTO);
    }

    /**
     * {@code PUT  /phieu-dieu-phois/:id} : Updates an existing phieuDieuPhoi.
     *
     * @param id the id of the phieuDieuPhoiDTO to save.
     * @param phieuDieuPhoiDTO the phieuDieuPhoiDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated phieuDieuPhoiDTO,
     * or with status {@code 400 (Bad Request)} if the phieuDieuPhoiDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the phieuDieuPhoiDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<PhieuDieuPhoiDTO> updatePhieuDieuPhoi(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody PhieuDieuPhoiDTO phieuDieuPhoiDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update PhieuDieuPhoi : {}, {}", id, phieuDieuPhoiDTO);
        if (phieuDieuPhoiDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, phieuDieuPhoiDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!phieuDieuPhoiRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        phieuDieuPhoiDTO = phieuDieuPhoiService.update(phieuDieuPhoiDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, phieuDieuPhoiDTO.getId().toString()))
            .body(phieuDieuPhoiDTO);
    }

    /**
     * {@code PATCH  /phieu-dieu-phois/:id} : Partial updates given fields of an existing phieuDieuPhoi, field will ignore if it is null
     *
     * @param id the id of the phieuDieuPhoiDTO to save.
     * @param phieuDieuPhoiDTO the phieuDieuPhoiDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated phieuDieuPhoiDTO,
     * or with status {@code 400 (Bad Request)} if the phieuDieuPhoiDTO is not valid,
     * or with status {@code 404 (Not Found)} if the phieuDieuPhoiDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the phieuDieuPhoiDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<PhieuDieuPhoiDTO> partialUpdatePhieuDieuPhoi(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody PhieuDieuPhoiDTO phieuDieuPhoiDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update PhieuDieuPhoi partially : {}, {}", id, phieuDieuPhoiDTO);
        if (phieuDieuPhoiDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, phieuDieuPhoiDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!phieuDieuPhoiRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<PhieuDieuPhoiDTO> result = phieuDieuPhoiService.partialUpdate(phieuDieuPhoiDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, phieuDieuPhoiDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /phieu-dieu-phois} : get all the phieuDieuPhois.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of phieuDieuPhois in body.
     */
    @GetMapping("")
    public ResponseEntity<List<PhieuDieuPhoiDTO>> getAllPhieuDieuPhois(@org.springdoc.core.annotations.ParameterObject Pageable pageable) {
        LOG.debug("REST request to get a page of PhieuDieuPhois");
        Page<PhieuDieuPhoiDTO> page = phieuDieuPhoiService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /phieu-dieu-phois/:id} : get the "id" phieuDieuPhoi.
     *
     * @param id the id of the phieuDieuPhoiDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the phieuDieuPhoiDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<PhieuDieuPhoiDTO> getPhieuDieuPhoi(@PathVariable("id") Long id) {
        LOG.debug("REST request to get PhieuDieuPhoi : {}", id);
        Optional<PhieuDieuPhoiDTO> phieuDieuPhoiDTO = phieuDieuPhoiService.findOne(id);
        return ResponseUtil.wrapOrNotFound(phieuDieuPhoiDTO);
    }

    /**
     * {@code DELETE  /phieu-dieu-phois/:id} : delete the "id" phieuDieuPhoi.
     *
     * @param id the id of the phieuDieuPhoiDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePhieuDieuPhoi(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete PhieuDieuPhoi : {}", id);
        phieuDieuPhoiService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
