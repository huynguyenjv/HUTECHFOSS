package hutechfoss.vrelief.admin.web.rest;

import hutechfoss.vrelief.admin.repository.NguoiNhanRepository;
import hutechfoss.vrelief.admin.service.NguoiNhanService;
import hutechfoss.vrelief.admin.service.dto.NguoiNhanDTO;
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
 * REST controller for managing {@link hutechfoss.vrelief.admin.domain.NguoiNhan}.
 */
@RestController
@RequestMapping("/api/nguoi-nhans")
public class NguoiNhanResource {

    private static final Logger LOG = LoggerFactory.getLogger(NguoiNhanResource.class);

    private static final String ENTITY_NAME = "nguoiNhan";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NguoiNhanService nguoiNhanService;

    private final NguoiNhanRepository nguoiNhanRepository;

    public NguoiNhanResource(NguoiNhanService nguoiNhanService, NguoiNhanRepository nguoiNhanRepository) {
        this.nguoiNhanService = nguoiNhanService;
        this.nguoiNhanRepository = nguoiNhanRepository;
    }

    /**
     * {@code POST  /nguoi-nhans} : Create a new nguoiNhan.
     *
     * @param nguoiNhanDTO the nguoiNhanDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new nguoiNhanDTO, or with status {@code 400 (Bad Request)} if the nguoiNhan has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<NguoiNhanDTO> createNguoiNhan(@Valid @RequestBody NguoiNhanDTO nguoiNhanDTO) throws URISyntaxException {
        LOG.debug("REST request to save NguoiNhan : {}", nguoiNhanDTO);
        if (nguoiNhanDTO.getId() != null) {
            throw new BadRequestAlertException("A new nguoiNhan cannot already have an ID", ENTITY_NAME, "idexists");
        }
        nguoiNhanDTO = nguoiNhanService.save(nguoiNhanDTO);
        return ResponseEntity.created(new URI("/api/nguoi-nhans/" + nguoiNhanDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, nguoiNhanDTO.getId().toString()))
            .body(nguoiNhanDTO);
    }

    /**
     * {@code PUT  /nguoi-nhans/:id} : Updates an existing nguoiNhan.
     *
     * @param id the id of the nguoiNhanDTO to save.
     * @param nguoiNhanDTO the nguoiNhanDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated nguoiNhanDTO,
     * or with status {@code 400 (Bad Request)} if the nguoiNhanDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the nguoiNhanDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<NguoiNhanDTO> updateNguoiNhan(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody NguoiNhanDTO nguoiNhanDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update NguoiNhan : {}, {}", id, nguoiNhanDTO);
        if (nguoiNhanDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, nguoiNhanDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!nguoiNhanRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        nguoiNhanDTO = nguoiNhanService.update(nguoiNhanDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, nguoiNhanDTO.getId().toString()))
            .body(nguoiNhanDTO);
    }

    /**
     * {@code PATCH  /nguoi-nhans/:id} : Partial updates given fields of an existing nguoiNhan, field will ignore if it is null
     *
     * @param id the id of the nguoiNhanDTO to save.
     * @param nguoiNhanDTO the nguoiNhanDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated nguoiNhanDTO,
     * or with status {@code 400 (Bad Request)} if the nguoiNhanDTO is not valid,
     * or with status {@code 404 (Not Found)} if the nguoiNhanDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the nguoiNhanDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<NguoiNhanDTO> partialUpdateNguoiNhan(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody NguoiNhanDTO nguoiNhanDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update NguoiNhan partially : {}, {}", id, nguoiNhanDTO);
        if (nguoiNhanDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, nguoiNhanDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!nguoiNhanRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<NguoiNhanDTO> result = nguoiNhanService.partialUpdate(nguoiNhanDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, nguoiNhanDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /nguoi-nhans} : get all the nguoiNhans.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of nguoiNhans in body.
     */
    @GetMapping("")
    public ResponseEntity<List<NguoiNhanDTO>> getAllNguoiNhans(@org.springdoc.core.annotations.ParameterObject Pageable pageable) {
        LOG.debug("REST request to get a page of NguoiNhans");
        Page<NguoiNhanDTO> page = nguoiNhanService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /nguoi-nhans/:id} : get the "id" nguoiNhan.
     *
     * @param id the id of the nguoiNhanDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the nguoiNhanDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<NguoiNhanDTO> getNguoiNhan(@PathVariable("id") Long id) {
        LOG.debug("REST request to get NguoiNhan : {}", id);
        Optional<NguoiNhanDTO> nguoiNhanDTO = nguoiNhanService.findOne(id);
        return ResponseUtil.wrapOrNotFound(nguoiNhanDTO);
    }

    /**
     * {@code DELETE  /nguoi-nhans/:id} : delete the "id" nguoiNhan.
     *
     * @param id the id of the nguoiNhanDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNguoiNhan(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete NguoiNhan : {}", id);
        nguoiNhanService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
