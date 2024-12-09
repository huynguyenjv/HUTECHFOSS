package hutechfoss.vrelief.admin.web.rest;

import hutechfoss.vrelief.admin.repository.NhaCungCapRepository;
import hutechfoss.vrelief.admin.service.NhaCungCapService;
import hutechfoss.vrelief.admin.service.dto.NhaCungCapDTO;
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
 * REST controller for managing {@link hutechfoss.vrelief.admin.domain.NhaCungCap}.
 */
@RestController
@RequestMapping("/api/nha-cung-caps")
public class NhaCungCapResource {

    private static final Logger LOG = LoggerFactory.getLogger(NhaCungCapResource.class);

    private static final String ENTITY_NAME = "nhaCungCap";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NhaCungCapService nhaCungCapService;

    private final NhaCungCapRepository nhaCungCapRepository;

    public NhaCungCapResource(NhaCungCapService nhaCungCapService, NhaCungCapRepository nhaCungCapRepository) {
        this.nhaCungCapService = nhaCungCapService;
        this.nhaCungCapRepository = nhaCungCapRepository;
    }

    /**
     * {@code POST  /nha-cung-caps} : Create a new nhaCungCap.
     *
     * @param nhaCungCapDTO the nhaCungCapDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new nhaCungCapDTO, or with status {@code 400 (Bad Request)} if the nhaCungCap has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<NhaCungCapDTO> createNhaCungCap(@Valid @RequestBody NhaCungCapDTO nhaCungCapDTO) throws URISyntaxException {
        LOG.debug("REST request to save NhaCungCap : {}", nhaCungCapDTO);
        if (nhaCungCapDTO.getId() != null) {
            throw new BadRequestAlertException("A new nhaCungCap cannot already have an ID", ENTITY_NAME, "idexists");
        }
        nhaCungCapDTO = nhaCungCapService.save(nhaCungCapDTO);
        return ResponseEntity.created(new URI("/api/nha-cung-caps/" + nhaCungCapDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, nhaCungCapDTO.getId().toString()))
            .body(nhaCungCapDTO);
    }

    /**
     * {@code PUT  /nha-cung-caps/:id} : Updates an existing nhaCungCap.
     *
     * @param id the id of the nhaCungCapDTO to save.
     * @param nhaCungCapDTO the nhaCungCapDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated nhaCungCapDTO,
     * or with status {@code 400 (Bad Request)} if the nhaCungCapDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the nhaCungCapDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<NhaCungCapDTO> updateNhaCungCap(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody NhaCungCapDTO nhaCungCapDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update NhaCungCap : {}, {}", id, nhaCungCapDTO);
        if (nhaCungCapDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, nhaCungCapDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!nhaCungCapRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        nhaCungCapDTO = nhaCungCapService.update(nhaCungCapDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, nhaCungCapDTO.getId().toString()))
            .body(nhaCungCapDTO);
    }

    /**
     * {@code PATCH  /nha-cung-caps/:id} : Partial updates given fields of an existing nhaCungCap, field will ignore if it is null
     *
     * @param id the id of the nhaCungCapDTO to save.
     * @param nhaCungCapDTO the nhaCungCapDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated nhaCungCapDTO,
     * or with status {@code 400 (Bad Request)} if the nhaCungCapDTO is not valid,
     * or with status {@code 404 (Not Found)} if the nhaCungCapDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the nhaCungCapDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<NhaCungCapDTO> partialUpdateNhaCungCap(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody NhaCungCapDTO nhaCungCapDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update NhaCungCap partially : {}, {}", id, nhaCungCapDTO);
        if (nhaCungCapDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, nhaCungCapDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!nhaCungCapRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<NhaCungCapDTO> result = nhaCungCapService.partialUpdate(nhaCungCapDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, nhaCungCapDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /nha-cung-caps} : get all the nhaCungCaps.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of nhaCungCaps in body.
     */
    @GetMapping("")
    public ResponseEntity<List<NhaCungCapDTO>> getAllNhaCungCaps(@org.springdoc.core.annotations.ParameterObject Pageable pageable) {
        LOG.debug("REST request to get a page of NhaCungCaps");
        Page<NhaCungCapDTO> page = nhaCungCapService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /nha-cung-caps/:id} : get the "id" nhaCungCap.
     *
     * @param id the id of the nhaCungCapDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the nhaCungCapDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<NhaCungCapDTO> getNhaCungCap(@PathVariable("id") Long id) {
        LOG.debug("REST request to get NhaCungCap : {}", id);
        Optional<NhaCungCapDTO> nhaCungCapDTO = nhaCungCapService.findOne(id);
        return ResponseUtil.wrapOrNotFound(nhaCungCapDTO);
    }

    /**
     * {@code DELETE  /nha-cung-caps/:id} : delete the "id" nhaCungCap.
     *
     * @param id the id of the nhaCungCapDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNhaCungCap(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete NhaCungCap : {}", id);
        nhaCungCapService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
