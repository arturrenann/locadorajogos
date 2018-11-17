package br.com.locadora.web.rest;

import br.com.locadora.LocadoraApp;

import br.com.locadora.domain.Plataforma;
import br.com.locadora.repository.PlataformaRepository;
import br.com.locadora.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;


import static br.com.locadora.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the PlataformaResource REST controller.
 *
 * @see PlataformaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = LocadoraApp.class)
public class PlataformaResourceIntTest {

    private static final String DEFAULT_PLATAFORMA = "AAAAAAAAAA";
    private static final String UPDATED_PLATAFORMA = "BBBBBBBBBB";

    private static final Integer DEFAULT_VALOR = 1;
    private static final Integer UPDATED_VALOR = 2;

    @Autowired
    private PlataformaRepository plataformaRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restPlataformaMockMvc;

    private Plataforma plataforma;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PlataformaResource plataformaResource = new PlataformaResource(plataformaRepository);
        this.restPlataformaMockMvc = MockMvcBuilders.standaloneSetup(plataformaResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Plataforma createEntity(EntityManager em) {
        Plataforma plataforma = new Plataforma()
            .plataforma(DEFAULT_PLATAFORMA)
            .valor(DEFAULT_VALOR);
        return plataforma;
    }

    @Before
    public void initTest() {
        plataforma = createEntity(em);
    }

    @Test
    @Transactional
    public void createPlataforma() throws Exception {
        int databaseSizeBeforeCreate = plataformaRepository.findAll().size();

        // Create the Plataforma
        restPlataformaMockMvc.perform(post("/api/plataformas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(plataforma)))
            .andExpect(status().isCreated());

        // Validate the Plataforma in the database
        List<Plataforma> plataformaList = plataformaRepository.findAll();
        assertThat(plataformaList).hasSize(databaseSizeBeforeCreate + 1);
        Plataforma testPlataforma = plataformaList.get(plataformaList.size() - 1);
        assertThat(testPlataforma.getPlataforma()).isEqualTo(DEFAULT_PLATAFORMA);
        assertThat(testPlataforma.getValor()).isEqualTo(DEFAULT_VALOR);
    }

    @Test
    @Transactional
    public void createPlataformaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = plataformaRepository.findAll().size();

        // Create the Plataforma with an existing ID
        plataforma.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPlataformaMockMvc.perform(post("/api/plataformas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(plataforma)))
            .andExpect(status().isBadRequest());

        // Validate the Plataforma in the database
        List<Plataforma> plataformaList = plataformaRepository.findAll();
        assertThat(plataformaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllPlataformas() throws Exception {
        // Initialize the database
        plataformaRepository.saveAndFlush(plataforma);

        // Get all the plataformaList
        restPlataformaMockMvc.perform(get("/api/plataformas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(plataforma.getId().intValue())))
            .andExpect(jsonPath("$.[*].plataforma").value(hasItem(DEFAULT_PLATAFORMA.toString())))
            .andExpect(jsonPath("$.[*].valor").value(hasItem(DEFAULT_VALOR)));
    }
    
    @Test
    @Transactional
    public void getPlataforma() throws Exception {
        // Initialize the database
        plataformaRepository.saveAndFlush(plataforma);

        // Get the plataforma
        restPlataformaMockMvc.perform(get("/api/plataformas/{id}", plataforma.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(plataforma.getId().intValue()))
            .andExpect(jsonPath("$.plataforma").value(DEFAULT_PLATAFORMA.toString()))
            .andExpect(jsonPath("$.valor").value(DEFAULT_VALOR));
    }

    @Test
    @Transactional
    public void getNonExistingPlataforma() throws Exception {
        // Get the plataforma
        restPlataformaMockMvc.perform(get("/api/plataformas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePlataforma() throws Exception {
        // Initialize the database
        plataformaRepository.saveAndFlush(plataforma);

        int databaseSizeBeforeUpdate = plataformaRepository.findAll().size();

        // Update the plataforma
        Plataforma updatedPlataforma = plataformaRepository.findById(plataforma.getId()).get();
        // Disconnect from session so that the updates on updatedPlataforma are not directly saved in db
        em.detach(updatedPlataforma);
        updatedPlataforma
            .plataforma(UPDATED_PLATAFORMA)
            .valor(UPDATED_VALOR);

        restPlataformaMockMvc.perform(put("/api/plataformas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedPlataforma)))
            .andExpect(status().isOk());

        // Validate the Plataforma in the database
        List<Plataforma> plataformaList = plataformaRepository.findAll();
        assertThat(plataformaList).hasSize(databaseSizeBeforeUpdate);
        Plataforma testPlataforma = plataformaList.get(plataformaList.size() - 1);
        assertThat(testPlataforma.getPlataforma()).isEqualTo(UPDATED_PLATAFORMA);
        assertThat(testPlataforma.getValor()).isEqualTo(UPDATED_VALOR);
    }

    @Test
    @Transactional
    public void updateNonExistingPlataforma() throws Exception {
        int databaseSizeBeforeUpdate = plataformaRepository.findAll().size();

        // Create the Plataforma

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPlataformaMockMvc.perform(put("/api/plataformas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(plataforma)))
            .andExpect(status().isBadRequest());

        // Validate the Plataforma in the database
        List<Plataforma> plataformaList = plataformaRepository.findAll();
        assertThat(plataformaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePlataforma() throws Exception {
        // Initialize the database
        plataformaRepository.saveAndFlush(plataforma);

        int databaseSizeBeforeDelete = plataformaRepository.findAll().size();

        // Get the plataforma
        restPlataformaMockMvc.perform(delete("/api/plataformas/{id}", plataforma.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Plataforma> plataformaList = plataformaRepository.findAll();
        assertThat(plataformaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Plataforma.class);
        Plataforma plataforma1 = new Plataforma();
        plataforma1.setId(1L);
        Plataforma plataforma2 = new Plataforma();
        plataforma2.setId(plataforma1.getId());
        assertThat(plataforma1).isEqualTo(plataforma2);
        plataforma2.setId(2L);
        assertThat(plataforma1).isNotEqualTo(plataforma2);
        plataforma1.setId(null);
        assertThat(plataforma1).isNotEqualTo(plataforma2);
    }
}
