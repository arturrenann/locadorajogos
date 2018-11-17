package br.com.locadora.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.locadora.domain.Plataforma;
import br.com.locadora.repository.PlataformaRepository;
import br.com.locadora.web.rest.errors.BadRequestAlertException;
import br.com.locadora.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Plataforma.
 */
@RestController
@RequestMapping("/api")
public class PlataformaResource {

    private final Logger log = LoggerFactory.getLogger(PlataformaResource.class);

    private static final String ENTITY_NAME = "plataforma";

    private PlataformaRepository plataformaRepository;

    public PlataformaResource(PlataformaRepository plataformaRepository) {
        this.plataformaRepository = plataformaRepository;
    }

    /**
     * POST  /plataformas : Create a new plataforma.
     *
     * @param plataforma the plataforma to create
     * @return the ResponseEntity with status 201 (Created) and with body the new plataforma, or with status 400 (Bad Request) if the plataforma has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/plataformas")
    @Timed
    public ResponseEntity<Plataforma> createPlataforma(@RequestBody Plataforma plataforma) throws URISyntaxException {
        log.debug("REST request to save Plataforma : {}", plataforma);
        if (plataforma.getId() != null) {
            throw new BadRequestAlertException("A new plataforma cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Plataforma result = plataformaRepository.save(plataforma);
        return ResponseEntity.created(new URI("/api/plataformas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /plataformas : Updates an existing plataforma.
     *
     * @param plataforma the plataforma to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated plataforma,
     * or with status 400 (Bad Request) if the plataforma is not valid,
     * or with status 500 (Internal Server Error) if the plataforma couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/plataformas")
    @Timed
    public ResponseEntity<Plataforma> updatePlataforma(@RequestBody Plataforma plataforma) throws URISyntaxException {
        log.debug("REST request to update Plataforma : {}", plataforma);
        if (plataforma.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Plataforma result = plataformaRepository.save(plataforma);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, plataforma.getId().toString()))
            .body(result);
    }

    /**
     * GET  /plataformas : get all the plataformas.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of plataformas in body
     */
    @GetMapping("/plataformas")
    @Timed
    public List<Plataforma> getAllPlataformas() {
        log.debug("REST request to get all Plataformas");
        return plataformaRepository.findAll();
    }

    /**
     * GET  /plataformas/:id : get the "id" plataforma.
     *
     * @param id the id of the plataforma to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the plataforma, or with status 404 (Not Found)
     */
    @GetMapping("/plataformas/{id}")
    @Timed
    public ResponseEntity<Plataforma> getPlataforma(@PathVariable Long id) {
        log.debug("REST request to get Plataforma : {}", id);
        Optional<Plataforma> plataforma = plataformaRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(plataforma);
    }

    /**
     * DELETE  /plataformas/:id : delete the "id" plataforma.
     *
     * @param id the id of the plataforma to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/plataformas/{id}")
    @Timed
    public ResponseEntity<Void> deletePlataforma(@PathVariable Long id) {
        log.debug("REST request to delete Plataforma : {}", id);

        plataformaRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
