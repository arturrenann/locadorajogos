package br.com.locadora.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.locadora.domain.Jogo;
import br.com.locadora.repository.JogoRepository;
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
 * REST controller for managing Jogo.
 */
@RestController
@RequestMapping("/api")
public class JogoResource {

    private final Logger log = LoggerFactory.getLogger(JogoResource.class);

    private static final String ENTITY_NAME = "jogo";

    private JogoRepository jogoRepository;

    public JogoResource(JogoRepository jogoRepository) {
        this.jogoRepository = jogoRepository;
    }

    /**
     * POST  /jogos : Create a new jogo.
     *
     * @param jogo the jogo to create
     * @return the ResponseEntity with status 201 (Created) and with body the new jogo, or with status 400 (Bad Request) if the jogo has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/jogos")
    @Timed
    public ResponseEntity<Jogo> createJogo(@RequestBody Jogo jogo) throws URISyntaxException {
        log.debug("REST request to save Jogo : {}", jogo);
        if (jogo.getId() != null) {
            throw new BadRequestAlertException("A new jogo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Jogo result = jogoRepository.save(jogo);
        return ResponseEntity.created(new URI("/api/jogos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /jogos : Updates an existing jogo.
     *
     * @param jogo the jogo to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated jogo,
     * or with status 400 (Bad Request) if the jogo is not valid,
     * or with status 500 (Internal Server Error) if the jogo couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/jogos")
    @Timed
    public ResponseEntity<Jogo> updateJogo(@RequestBody Jogo jogo) throws URISyntaxException {
        log.debug("REST request to update Jogo : {}", jogo);
        if (jogo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Jogo result = jogoRepository.save(jogo);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, jogo.getId().toString()))
            .body(result);
    }

    /**
     * GET  /jogos : get all the jogos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of jogos in body
     */
    @GetMapping("/jogos")
    @Timed
    public List<Jogo> getAllJogos() {
        log.debug("REST request to get all Jogos");
        return jogoRepository.findAll();
    }

    /**
     * GET  /jogos/:id : get the "id" jogo.
     *
     * @param id the id of the jogo to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the jogo, or with status 404 (Not Found)
     */
    @GetMapping("/jogos/{id}")
    @Timed
    public ResponseEntity<Jogo> getJogo(@PathVariable Long id) {
        log.debug("REST request to get Jogo : {}", id);
        Optional<Jogo> jogo = jogoRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(jogo);
    }

    /**
     * DELETE  /jogos/:id : delete the "id" jogo.
     *
     * @param id the id of the jogo to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/jogos/{id}")
    @Timed
    public ResponseEntity<Void> deleteJogo(@PathVariable Long id) {
        log.debug("REST request to delete Jogo : {}", id);

        jogoRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
