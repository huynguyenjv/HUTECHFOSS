package hutechfoss.vrelief.admin.web.rest;

import hutechfoss.vrelief.admin.repository.LoaiNguoiNhanRepository;
import hutechfoss.vrelief.admin.service.LoaiNguoiNhanService;
import hutechfoss.vrelief.admin.service.dto.LoaiNguoiNhanDTO;
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
 * REST controller for managing {@link hutechfoss.vrelief.admin.domain.LoaiNguoiNhan}.
 */
@RestController
@RequestMapping("/api/loai-nguoi-nhans")
public class LoaiNguoiNhanResource {

    private static final Logger LOG = LoggerFactory.getLogger(LoaiNguoiNhanResource.class);

    private static final String ENTITY_NAME = "loaiNguoiNhan";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LoaiNguoiNhanService loaiNguoiNhanService;

    private final LoaiNguoiNhanRepository loaiNguoiNhanRepository;

    public LoaiNguoiNhanResource(LoaiNguoiNhanService loaiNguoiNhanService, LoaiNguoiNhanRepository loaiNguoiNhanRepository) {
        this.loaiNguoiNhanService = loaiNguoiNhanService;
        this.loaiNguoiNhanRepository = loaiNguoiNhanRepository;
    }

    /**
     * {@code POST  /loai-nguoi-nhans} : Create a new loaiNguoiNhan.
     *
     * @param loaiNguoiNhanDTO the loaiNguoiNhanDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new loaiNguoiNhanDTO, or with status {@code 400 (Bad Request)} if the loaiNguoiNhan has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<LoaiNguoiNhanDTO> createLoaiNguoiNhan(@Valid @RequestBody LoaiNguoiNhanDTO loaiNguoiNhanDTO)
        throws URISyntaxException {
        LOG.debug("REST request to save LoaiNguoiNhan : {}", loaiNguoiNhanDTO);
        if (loaiNguoiNhanDTO.getId() != null) {
            throw new BadRequestAlertException("A new loaiNguoiNhan cannot already have an ID", ENTITY_NAME, "idexists");
        }
        loaiNguoiNhanDTO = loaiNguoiNhanService.save(loaiNguoiNhanDTO);
        return ResponseEntity.created(new URI("/api/loai-nguoi-nhans/" + loaiNguoiNhanDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, loaiNguoiNhanDTO.getId().toString()))
            .body(loaiNguoiNhanDTO);
    }

    /**
     * {@code PUT  /loai-nguoi-nhans/:id} : Updates an existing loaiNguoiNhan.
     *
     * @param id the id of the loaiNguoiNhanDTO to save.
     * @param loaiNguoiNhanDTO the loaiNguoiNhanDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated loaiNguoiNhanDTO,
     * or with status {@code 400 (Bad Request)} if the loaiNguoiNhanDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the loaiNguoiNhanDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<LoaiNguoiNhanDTO> updateLoaiNguoiNhan(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody LoaiNguoiNhanDTO loaiNguoiNhanDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update LoaiNguoiNhan : {}, {}", id, loaiNguoiNhanDTO);
        if (loaiNguoiNhanDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, loaiNguoiNhanDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!loaiNguoiNhanRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        loaiNguoiNhanDTO = loaiNguoiNhanService.update(loaiNguoiNhanDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, loaiNguoiNhanDTO.getId().toString()))
            .body(loaiNguoiNhanDTO);
    }

    /**
     * {@code PATCH  /loai-nguoi-nhans/:id} : Partial updates given fields of an existing loaiNguoiNhan, field will ignore if it is null
     *
     * @param id the id of the loaiNguoiNhanDTO to save.
     * @param loaiNguoiNhanDTO the loaiNguoiNhanDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated loaiNguoiNhanDTO,
     * or with status {@code 400 (Bad Request)} if the loaiNguoiNhanDTO is not valid,
     * or with status {@code 404 (Not Found)} if the loaiNguoiNhanDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the loaiNguoiNhanDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<LoaiNguoiNhanDTO> partialUpdateLoaiNguoiNhan(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody LoaiNguoiNhanDTO loaiNguoiNhanDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update LoaiNguoiNhan partially : {}, {}", id, loaiNguoiNhanDTO);
        if (loaiNguoiNhanDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, loaiNguoiNhanDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!loaiNguoiNhanRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<LoaiNguoiNhanDTO> result = loaiNguoiNhanService.partialUpdate(loaiNguoiNhanDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, loaiNguoiNhanDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /loai-nguoi-nhans} : get all the loaiNguoiNhans.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of loaiNguoiNhans in body.
     */
    @GetMapping("")
    public ResponseEntity<List<LoaiNguoiNhanDTO>> getAllLoaiNguoiNhans(@org.springdoc.core.annotations.ParameterObject Pageable pageable) {
        LOG.debug("REST request to get a page of LoaiNguoiNhans");
        Page<LoaiNguoiNhanDTO> page = loaiNguoiNhanService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /loai-nguoi-nhans/:id} : get the "id" loaiNguoiNhan.
     *
     * @param id the id of the loaiNguoiNhanDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the loaiNguoiNhanDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<LoaiNguoiNhanDTO> getLoaiNguoiNhan(@PathVariable("id") Long id) {
        LOG.debug("REST request to get LoaiNguoiNhan : {}", id);
        Optional<LoaiNguoiNhanDTO> loaiNguoiNhanDTO = loaiNguoiNhanService.findOne(id);
        return ResponseUtil.wrapOrNotFound(loaiNguoiNhanDTO);
    }

    /**
     * {@code DELETE  /loai-nguoi-nhans/:id} : delete the "id" loaiNguoiNhan.
     *
     * @param id the id of the loaiNguoiNhanDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLoaiNguoiNhan(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete LoaiNguoiNhan : {}", id);
        loaiNguoiNhanService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
