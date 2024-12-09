package hutechfoss.vrelief.admin.web.rest;

import hutechfoss.vrelief.admin.repository.VungRepository;
import hutechfoss.vrelief.admin.service.VungService;
import hutechfoss.vrelief.admin.service.dto.VungDTO;
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
 * REST controller for managing {@link hutechfoss.vrelief.admin.domain.Vung}.
 */
@RestController
@RequestMapping("/api/vungs")
public class VungResource {

    private static final Logger LOG = LoggerFactory.getLogger(VungResource.class);

    private static final String ENTITY_NAME = "vung";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final VungService vungService;

    private final VungRepository vungRepository;

    public VungResource(VungService vungService, VungRepository vungRepository) {
        this.vungService = vungService;
        this.vungRepository = vungRepository;
    }

    /**
     * {@code POST  /vungs} : Create a new vung.
     *
     * @param vungDTO the vungDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new vungDTO, or with status {@code 400 (Bad Request)} if the vung has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<VungDTO> createVung(@Valid @RequestBody VungDTO vungDTO) throws URISyntaxException {
        LOG.debug("REST request to save Vung : {}", vungDTO);
        if (vungDTO.getId() != null) {
            throw new BadRequestAlertException("A new vung cannot already have an ID", ENTITY_NAME, "idexists");
        }
        vungDTO = vungService.save(vungDTO);
        return ResponseEntity.created(new URI("/api/vungs/" + vungDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, vungDTO.getId().toString()))
            .body(vungDTO);
    }

    /**
     * {@code PUT  /vungs/:id} : Updates an existing vung.
     *
     * @param id the id of the vungDTO to save.
     * @param vungDTO the vungDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated vungDTO,
     * or with status {@code 400 (Bad Request)} if the vungDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the vungDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<VungDTO> updateVung(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody VungDTO vungDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update Vung : {}, {}", id, vungDTO);
        if (vungDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, vungDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!vungRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        vungDTO = vungService.update(vungDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, vungDTO.getId().toString()))
            .body(vungDTO);
    }

    /**
     * {@code PATCH  /vungs/:id} : Partial updates given fields of an existing vung, field will ignore if it is null
     *
     * @param id the id of the vungDTO to save.
     * @param vungDTO the vungDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated vungDTO,
     * or with status {@code 400 (Bad Request)} if the vungDTO is not valid,
     * or with status {@code 404 (Not Found)} if the vungDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the vungDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<VungDTO> partialUpdateVung(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody VungDTO vungDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update Vung partially : {}, {}", id, vungDTO);
        if (vungDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, vungDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!vungRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<VungDTO> result = vungService.partialUpdate(vungDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, vungDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /vungs} : get all the vungs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of vungs in body.
     */
    @GetMapping("")
    public ResponseEntity<List<VungDTO>> getAllVungs(@org.springdoc.core.annotations.ParameterObject Pageable pageable) {
        LOG.debug("REST request to get a page of Vungs");
        Page<VungDTO> page = vungService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /vungs/:id} : get the "id" vung.
     *
     * @param id the id of the vungDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the vungDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<VungDTO> getVung(@PathVariable("id") Long id) {
        LOG.debug("REST request to get Vung : {}", id);
        Optional<VungDTO> vungDTO = vungService.findOne(id);
        return ResponseUtil.wrapOrNotFound(vungDTO);
    }

    /**
     * {@code DELETE  /vungs/:id} : delete the "id" vung.
     *
     * @param id the id of the vungDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVung(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete Vung : {}", id);
        vungService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
