package hutechfoss.vrelief.admin.web.rest;

import hutechfoss.vrelief.admin.repository.NhuYeuPhamRepository;
import hutechfoss.vrelief.admin.service.NhuYeuPhamService;
import hutechfoss.vrelief.admin.service.dto.NhuYeuPhamDTO;
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
 * REST controller for managing {@link hutechfoss.vrelief.admin.domain.NhuYeuPham}.
 */
@RestController
@RequestMapping("/api/nhu-yeu-phams")
public class NhuYeuPhamResource {

    private static final Logger LOG = LoggerFactory.getLogger(NhuYeuPhamResource.class);

    private static final String ENTITY_NAME = "nhuYeuPham";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NhuYeuPhamService nhuYeuPhamService;

    private final NhuYeuPhamRepository nhuYeuPhamRepository;

    public NhuYeuPhamResource(NhuYeuPhamService nhuYeuPhamService, NhuYeuPhamRepository nhuYeuPhamRepository) {
        this.nhuYeuPhamService = nhuYeuPhamService;
        this.nhuYeuPhamRepository = nhuYeuPhamRepository;
    }

    /**
     * {@code POST  /nhu-yeu-phams} : Create a new nhuYeuPham.
     *
     * @param nhuYeuPhamDTO the nhuYeuPhamDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new nhuYeuPhamDTO, or with status {@code 400 (Bad Request)} if the nhuYeuPham has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<NhuYeuPhamDTO> createNhuYeuPham(@Valid @RequestBody NhuYeuPhamDTO nhuYeuPhamDTO) throws URISyntaxException {
        LOG.debug("REST request to save NhuYeuPham : {}", nhuYeuPhamDTO);
        if (nhuYeuPhamDTO.getId() != null) {
            throw new BadRequestAlertException("A new nhuYeuPham cannot already have an ID", ENTITY_NAME, "idexists");
        }
        nhuYeuPhamDTO = nhuYeuPhamService.save(nhuYeuPhamDTO);
        return ResponseEntity.created(new URI("/api/nhu-yeu-phams/" + nhuYeuPhamDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, nhuYeuPhamDTO.getId().toString()))
            .body(nhuYeuPhamDTO);
    }

    /**
     * {@code PUT  /nhu-yeu-phams/:id} : Updates an existing nhuYeuPham.
     *
     * @param id the id of the nhuYeuPhamDTO to save.
     * @param nhuYeuPhamDTO the nhuYeuPhamDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated nhuYeuPhamDTO,
     * or with status {@code 400 (Bad Request)} if the nhuYeuPhamDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the nhuYeuPhamDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<NhuYeuPhamDTO> updateNhuYeuPham(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody NhuYeuPhamDTO nhuYeuPhamDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update NhuYeuPham : {}, {}", id, nhuYeuPhamDTO);
        if (nhuYeuPhamDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, nhuYeuPhamDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!nhuYeuPhamRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        nhuYeuPhamDTO = nhuYeuPhamService.update(nhuYeuPhamDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, nhuYeuPhamDTO.getId().toString()))
            .body(nhuYeuPhamDTO);
    }

    /**
     * {@code PATCH  /nhu-yeu-phams/:id} : Partial updates given fields of an existing nhuYeuPham, field will ignore if it is null
     *
     * @param id the id of the nhuYeuPhamDTO to save.
     * @param nhuYeuPhamDTO the nhuYeuPhamDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated nhuYeuPhamDTO,
     * or with status {@code 400 (Bad Request)} if the nhuYeuPhamDTO is not valid,
     * or with status {@code 404 (Not Found)} if the nhuYeuPhamDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the nhuYeuPhamDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<NhuYeuPhamDTO> partialUpdateNhuYeuPham(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody NhuYeuPhamDTO nhuYeuPhamDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update NhuYeuPham partially : {}, {}", id, nhuYeuPhamDTO);
        if (nhuYeuPhamDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, nhuYeuPhamDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!nhuYeuPhamRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<NhuYeuPhamDTO> result = nhuYeuPhamService.partialUpdate(nhuYeuPhamDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, nhuYeuPhamDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /nhu-yeu-phams} : get all the nhuYeuPhams.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of nhuYeuPhams in body.
     */
    @GetMapping("")
    public ResponseEntity<List<NhuYeuPhamDTO>> getAllNhuYeuPhams(@org.springdoc.core.annotations.ParameterObject Pageable pageable) {
        LOG.debug("REST request to get a page of NhuYeuPhams");
        Page<NhuYeuPhamDTO> page = nhuYeuPhamService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /nhu-yeu-phams/:id} : get the "id" nhuYeuPham.
     *
     * @param id the id of the nhuYeuPhamDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the nhuYeuPhamDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<NhuYeuPhamDTO> getNhuYeuPham(@PathVariable("id") Long id) {
        LOG.debug("REST request to get NhuYeuPham : {}", id);
        Optional<NhuYeuPhamDTO> nhuYeuPhamDTO = nhuYeuPhamService.findOne(id);
        return ResponseUtil.wrapOrNotFound(nhuYeuPhamDTO);
    }

    /**
     * {@code DELETE  /nhu-yeu-phams/:id} : delete the "id" nhuYeuPham.
     *
     * @param id the id of the nhuYeuPhamDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNhuYeuPham(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete NhuYeuPham : {}", id);
        nhuYeuPhamService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
