package br.com.locadora.web.rest;

import br.com.locadora.LocadoraApp;

import br.com.locadora.domain.Jogo;
import br.com.locadora.repository.JogoRepository;
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
 * Test class for the JogoResource REST controller.
 *
 * @see JogoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = LocadoraApp.class)
public class JogoResourceIntTest {

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    @Autowired
    private JogoRepository jogoRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restJogoMockMvc;

    private Jogo jogo;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final JogoResource jogoResource = new JogoResource(jogoRepository);
        this.restJogoMockMvc = MockMvcBuilders.standaloneSetup(jogoResource)
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
    public static Jogo createEntity(EntityManager em) {
        Jogo jogo = new Jogo()
            .nome(DEFAULT_NOME);
        return jogo;
    }

    @Before
    public void initTest() {
        jogo = createEntity(em);
    }

    @Test
    @Transactional
    public void createJogo() throws Exception {
        int databaseSizeBeforeCreate = jogoRepository.findAll().size();

        // Create the Jogo
        restJogoMockMvc.perform(post("/api/jogos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(jogo)))
            .andExpect(status().isCreated());

        // Validate the Jogo in the database
        List<Jogo> jogoList = jogoRepository.findAll();
        assertThat(jogoList).hasSize(databaseSizeBeforeCreate + 1);
        Jogo testJogo = jogoList.get(jogoList.size() - 1);
        assertThat(testJogo.getNome()).isEqualTo(DEFAULT_NOME);
    }

    @Test
    @Transactional
    public void createJogoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = jogoRepository.findAll().size();

        // Create the Jogo with an existing ID
        jogo.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restJogoMockMvc.perform(post("/api/jogos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(jogo)))
            .andExpect(status().isBadRequest());

        // Validate the Jogo in the database
        List<Jogo> jogoList = jogoRepository.findAll();
        assertThat(jogoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllJogos() throws Exception {
        // Initialize the database
        jogoRepository.saveAndFlush(jogo);

        // Get all the jogoList
        restJogoMockMvc.perform(get("/api/jogos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(jogo.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME.toString())));
    }
    
    @Test
    @Transactional
    public void getJogo() throws Exception {
        // Initialize the database
        jogoRepository.saveAndFlush(jogo);

        // Get the jogo
        restJogoMockMvc.perform(get("/api/jogos/{id}", jogo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(jogo.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingJogo() throws Exception {
        // Get the jogo
        restJogoMockMvc.perform(get("/api/jogos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateJogo() throws Exception {
        // Initialize the database
        jogoRepository.saveAndFlush(jogo);

        int databaseSizeBeforeUpdate = jogoRepository.findAll().size();

        // Update the jogo
        Jogo updatedJogo = jogoRepository.findById(jogo.getId()).get();
        // Disconnect from session so that the updates on updatedJogo are not directly saved in db
        em.detach(updatedJogo);
        updatedJogo
            .nome(UPDATED_NOME);

        restJogoMockMvc.perform(put("/api/jogos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedJogo)))
            .andExpect(status().isOk());

        // Validate the Jogo in the database
        List<Jogo> jogoList = jogoRepository.findAll();
        assertThat(jogoList).hasSize(databaseSizeBeforeUpdate);
        Jogo testJogo = jogoList.get(jogoList.size() - 1);
        assertThat(testJogo.getNome()).isEqualTo(UPDATED_NOME);
    }

    @Test
    @Transactional
    public void updateNonExistingJogo() throws Exception {
        int databaseSizeBeforeUpdate = jogoRepository.findAll().size();

        // Create the Jogo

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restJogoMockMvc.perform(put("/api/jogos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(jogo)))
            .andExpect(status().isBadRequest());

        // Validate the Jogo in the database
        List<Jogo> jogoList = jogoRepository.findAll();
        assertThat(jogoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteJogo() throws Exception {
        // Initialize the database
        jogoRepository.saveAndFlush(jogo);

        int databaseSizeBeforeDelete = jogoRepository.findAll().size();

        // Get the jogo
        restJogoMockMvc.perform(delete("/api/jogos/{id}", jogo.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Jogo> jogoList = jogoRepository.findAll();
        assertThat(jogoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Jogo.class);
        Jogo jogo1 = new Jogo();
        jogo1.setId(1L);
        Jogo jogo2 = new Jogo();
        jogo2.setId(jogo1.getId());
        assertThat(jogo1).isEqualTo(jogo2);
        jogo2.setId(2L);
        assertThat(jogo1).isNotEqualTo(jogo2);
        jogo1.setId(null);
        assertThat(jogo1).isNotEqualTo(jogo2);
    }
}
