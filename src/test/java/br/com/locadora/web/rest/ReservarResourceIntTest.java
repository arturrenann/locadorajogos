package br.com.locadora.web.rest;

import br.com.locadora.LocadoraApp;

import br.com.locadora.domain.Reservar;
import br.com.locadora.repository.ReservarRepository;
import br.com.locadora.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;


import static br.com.locadora.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ReservarResource REST controller.
 *
 * @see ReservarResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = LocadoraApp.class)
public class ReservarResourceIntTest {

    private static final Integer DEFAULT_DIAS = 1;
    private static final Integer UPDATED_DIAS = 2;

    private static final LocalDate DEFAULT_DATA_INICIO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATA_INICIO = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATA_ENTREGA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATA_ENTREGA = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private ReservarRepository reservarRepository;

    @Mock
    private ReservarRepository reservarRepositoryMock;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restReservarMockMvc;

    private Reservar reservar;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ReservarResource reservarResource = new ReservarResource(reservarRepository);
        this.restReservarMockMvc = MockMvcBuilders.standaloneSetup(reservarResource)
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
    public static Reservar createEntity(EntityManager em) {
        Reservar reservar = new Reservar()
            .dias(DEFAULT_DIAS)
            .dataInicio(DEFAULT_DATA_INICIO)
            .dataEntrega(DEFAULT_DATA_ENTREGA);
        return reservar;
    }

    @Before
    public void initTest() {
        reservar = createEntity(em);
    }

    @Test
    @Transactional
    public void createReservar() throws Exception {
        int databaseSizeBeforeCreate = reservarRepository.findAll().size();

        // Create the Reservar
        restReservarMockMvc.perform(post("/api/reservars")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(reservar)))
            .andExpect(status().isCreated());

        // Validate the Reservar in the database
        List<Reservar> reservarList = reservarRepository.findAll();
        assertThat(reservarList).hasSize(databaseSizeBeforeCreate + 1);
        Reservar testReservar = reservarList.get(reservarList.size() - 1);
        assertThat(testReservar.getDias()).isEqualTo(DEFAULT_DIAS);
        assertThat(testReservar.getDataInicio()).isEqualTo(DEFAULT_DATA_INICIO);
        assertThat(testReservar.getDataEntrega()).isEqualTo(DEFAULT_DATA_ENTREGA);
    }

    @Test
    @Transactional
    public void createReservarWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = reservarRepository.findAll().size();

        // Create the Reservar with an existing ID
        reservar.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restReservarMockMvc.perform(post("/api/reservars")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(reservar)))
            .andExpect(status().isBadRequest());

        // Validate the Reservar in the database
        List<Reservar> reservarList = reservarRepository.findAll();
        assertThat(reservarList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllReservars() throws Exception {
        // Initialize the database
        reservarRepository.saveAndFlush(reservar);

        // Get all the reservarList
        restReservarMockMvc.perform(get("/api/reservars?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(reservar.getId().intValue())))
            .andExpect(jsonPath("$.[*].dias").value(hasItem(DEFAULT_DIAS)))
            .andExpect(jsonPath("$.[*].dataInicio").value(hasItem(DEFAULT_DATA_INICIO.toString())))
            .andExpect(jsonPath("$.[*].dataEntrega").value(hasItem(DEFAULT_DATA_ENTREGA.toString())));
    }
    
    public void getAllReservarsWithEagerRelationshipsIsEnabled() throws Exception {
        ReservarResource reservarResource = new ReservarResource(reservarRepositoryMock);
        when(reservarRepositoryMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        MockMvc restReservarMockMvc = MockMvcBuilders.standaloneSetup(reservarResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restReservarMockMvc.perform(get("/api/reservars?eagerload=true"))
        .andExpect(status().isOk());

        verify(reservarRepositoryMock, times(1)).findAllWithEagerRelationships(any());
    }

    public void getAllReservarsWithEagerRelationshipsIsNotEnabled() throws Exception {
        ReservarResource reservarResource = new ReservarResource(reservarRepositoryMock);
            when(reservarRepositoryMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));
            MockMvc restReservarMockMvc = MockMvcBuilders.standaloneSetup(reservarResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restReservarMockMvc.perform(get("/api/reservars?eagerload=true"))
        .andExpect(status().isOk());

            verify(reservarRepositoryMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getReservar() throws Exception {
        // Initialize the database
        reservarRepository.saveAndFlush(reservar);

        // Get the reservar
        restReservarMockMvc.perform(get("/api/reservars/{id}", reservar.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(reservar.getId().intValue()))
            .andExpect(jsonPath("$.dias").value(DEFAULT_DIAS))
            .andExpect(jsonPath("$.dataInicio").value(DEFAULT_DATA_INICIO.toString()))
            .andExpect(jsonPath("$.dataEntrega").value(DEFAULT_DATA_ENTREGA.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingReservar() throws Exception {
        // Get the reservar
        restReservarMockMvc.perform(get("/api/reservars/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateReservar() throws Exception {
        // Initialize the database
        reservarRepository.saveAndFlush(reservar);

        int databaseSizeBeforeUpdate = reservarRepository.findAll().size();

        // Update the reservar
        Reservar updatedReservar = reservarRepository.findById(reservar.getId()).get();
        // Disconnect from session so that the updates on updatedReservar are not directly saved in db
        em.detach(updatedReservar);
        updatedReservar
            .dias(UPDATED_DIAS)
            .dataInicio(UPDATED_DATA_INICIO)
            .dataEntrega(UPDATED_DATA_ENTREGA);

        restReservarMockMvc.perform(put("/api/reservars")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedReservar)))
            .andExpect(status().isOk());

        // Validate the Reservar in the database
        List<Reservar> reservarList = reservarRepository.findAll();
        assertThat(reservarList).hasSize(databaseSizeBeforeUpdate);
        Reservar testReservar = reservarList.get(reservarList.size() - 1);
        assertThat(testReservar.getDias()).isEqualTo(UPDATED_DIAS);
        assertThat(testReservar.getDataInicio()).isEqualTo(UPDATED_DATA_INICIO);
        assertThat(testReservar.getDataEntrega()).isEqualTo(UPDATED_DATA_ENTREGA);
    }

    @Test
    @Transactional
    public void updateNonExistingReservar() throws Exception {
        int databaseSizeBeforeUpdate = reservarRepository.findAll().size();

        // Create the Reservar

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restReservarMockMvc.perform(put("/api/reservars")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(reservar)))
            .andExpect(status().isBadRequest());

        // Validate the Reservar in the database
        List<Reservar> reservarList = reservarRepository.findAll();
        assertThat(reservarList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteReservar() throws Exception {
        // Initialize the database
        reservarRepository.saveAndFlush(reservar);

        int databaseSizeBeforeDelete = reservarRepository.findAll().size();

        // Get the reservar
        restReservarMockMvc.perform(delete("/api/reservars/{id}", reservar.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Reservar> reservarList = reservarRepository.findAll();
        assertThat(reservarList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Reservar.class);
        Reservar reservar1 = new Reservar();
        reservar1.setId(1L);
        Reservar reservar2 = new Reservar();
        reservar2.setId(reservar1.getId());
        assertThat(reservar1).isEqualTo(reservar2);
        reservar2.setId(2L);
        assertThat(reservar1).isNotEqualTo(reservar2);
        reservar1.setId(null);
        assertThat(reservar1).isNotEqualTo(reservar2);
    }
}
