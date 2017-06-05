package com.chiote.web.rest;

import com.chiote.Avaliacao360ChioteApp;

import com.chiote.domain.PerguntaLayout;
import com.chiote.repository.PerguntaLayoutRepository;
import com.chiote.service.PerguntaLayoutService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the PerguntaLayoutResource REST controller.
 *
 * @see PerguntaLayoutResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Avaliacao360ChioteApp.class)
public class PerguntaLayoutResourceIntTest {

    private static final String DEFAULT_ASSUNTO = "AAAAAAAAAA";
    private static final String UPDATED_ASSUNTO = "BBBBBBBBBB";

    private static final String DEFAULT_TEXTO = "AAAAAAAAAA";
    private static final String UPDATED_TEXTO = "BBBBBBBBBB";

    @Inject
    private PerguntaLayoutRepository perguntaLayoutRepository;

    @Inject
    private PerguntaLayoutService perguntaLayoutService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Inject
    private EntityManager em;

    private MockMvc restPerguntaLayoutMockMvc;

    private PerguntaLayout perguntaLayout;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        PerguntaLayoutResource perguntaLayoutResource = new PerguntaLayoutResource();
        ReflectionTestUtils.setField(perguntaLayoutResource, "perguntaLayoutService", perguntaLayoutService);
        this.restPerguntaLayoutMockMvc = MockMvcBuilders.standaloneSetup(perguntaLayoutResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PerguntaLayout createEntity(EntityManager em) {
        PerguntaLayout perguntaLayout = new PerguntaLayout()
                .assunto(DEFAULT_ASSUNTO)
                .texto(DEFAULT_TEXTO);
        return perguntaLayout;
    }

    @Before
    public void initTest() {
        perguntaLayout = createEntity(em);
    }

    @Test
    @Transactional
    public void createPerguntaLayout() throws Exception {
        int databaseSizeBeforeCreate = perguntaLayoutRepository.findAll().size();

        // Create the PerguntaLayout

        restPerguntaLayoutMockMvc.perform(post("/api/pergunta-layouts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(perguntaLayout)))
            .andExpect(status().isCreated());

        // Validate the PerguntaLayout in the database
        List<PerguntaLayout> perguntaLayoutList = perguntaLayoutRepository.findAll();
        assertThat(perguntaLayoutList).hasSize(databaseSizeBeforeCreate + 1);
        PerguntaLayout testPerguntaLayout = perguntaLayoutList.get(perguntaLayoutList.size() - 1);
        assertThat(testPerguntaLayout.getAssunto()).isEqualTo(DEFAULT_ASSUNTO);
        assertThat(testPerguntaLayout.getTexto()).isEqualTo(DEFAULT_TEXTO);
    }

    @Test
    @Transactional
    public void createPerguntaLayoutWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = perguntaLayoutRepository.findAll().size();

        // Create the PerguntaLayout with an existing ID
        PerguntaLayout existingPerguntaLayout = new PerguntaLayout();
        existingPerguntaLayout.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPerguntaLayoutMockMvc.perform(post("/api/pergunta-layouts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingPerguntaLayout)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<PerguntaLayout> perguntaLayoutList = perguntaLayoutRepository.findAll();
        assertThat(perguntaLayoutList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkAssuntoIsRequired() throws Exception {
        int databaseSizeBeforeTest = perguntaLayoutRepository.findAll().size();
        // set the field null
        perguntaLayout.setAssunto(null);

        // Create the PerguntaLayout, which fails.

        restPerguntaLayoutMockMvc.perform(post("/api/pergunta-layouts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(perguntaLayout)))
            .andExpect(status().isBadRequest());

        List<PerguntaLayout> perguntaLayoutList = perguntaLayoutRepository.findAll();
        assertThat(perguntaLayoutList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTextoIsRequired() throws Exception {
        int databaseSizeBeforeTest = perguntaLayoutRepository.findAll().size();
        // set the field null
        perguntaLayout.setTexto(null);

        // Create the PerguntaLayout, which fails.

        restPerguntaLayoutMockMvc.perform(post("/api/pergunta-layouts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(perguntaLayout)))
            .andExpect(status().isBadRequest());

        List<PerguntaLayout> perguntaLayoutList = perguntaLayoutRepository.findAll();
        assertThat(perguntaLayoutList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPerguntaLayouts() throws Exception {
        // Initialize the database
        perguntaLayoutRepository.saveAndFlush(perguntaLayout);

        // Get all the perguntaLayoutList
        restPerguntaLayoutMockMvc.perform(get("/api/pergunta-layouts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(perguntaLayout.getId().intValue())))
            .andExpect(jsonPath("$.[*].assunto").value(hasItem(DEFAULT_ASSUNTO.toString())))
            .andExpect(jsonPath("$.[*].texto").value(hasItem(DEFAULT_TEXTO.toString())));
    }

    @Test
    @Transactional
    public void getPerguntaLayout() throws Exception {
        // Initialize the database
        perguntaLayoutRepository.saveAndFlush(perguntaLayout);

        // Get the perguntaLayout
        restPerguntaLayoutMockMvc.perform(get("/api/pergunta-layouts/{id}", perguntaLayout.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(perguntaLayout.getId().intValue()))
            .andExpect(jsonPath("$.assunto").value(DEFAULT_ASSUNTO.toString()))
            .andExpect(jsonPath("$.texto").value(DEFAULT_TEXTO.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingPerguntaLayout() throws Exception {
        // Get the perguntaLayout
        restPerguntaLayoutMockMvc.perform(get("/api/pergunta-layouts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePerguntaLayout() throws Exception {
        // Initialize the database
        perguntaLayoutService.save(perguntaLayout);

        int databaseSizeBeforeUpdate = perguntaLayoutRepository.findAll().size();

        // Update the perguntaLayout
        PerguntaLayout updatedPerguntaLayout = perguntaLayoutRepository.findOne(perguntaLayout.getId());
        updatedPerguntaLayout
                .assunto(UPDATED_ASSUNTO)
                .texto(UPDATED_TEXTO);

        restPerguntaLayoutMockMvc.perform(put("/api/pergunta-layouts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedPerguntaLayout)))
            .andExpect(status().isOk());

        // Validate the PerguntaLayout in the database
        List<PerguntaLayout> perguntaLayoutList = perguntaLayoutRepository.findAll();
        assertThat(perguntaLayoutList).hasSize(databaseSizeBeforeUpdate);
        PerguntaLayout testPerguntaLayout = perguntaLayoutList.get(perguntaLayoutList.size() - 1);
        assertThat(testPerguntaLayout.getAssunto()).isEqualTo(UPDATED_ASSUNTO);
        assertThat(testPerguntaLayout.getTexto()).isEqualTo(UPDATED_TEXTO);
    }

    @Test
    @Transactional
    public void updateNonExistingPerguntaLayout() throws Exception {
        int databaseSizeBeforeUpdate = perguntaLayoutRepository.findAll().size();

        // Create the PerguntaLayout

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restPerguntaLayoutMockMvc.perform(put("/api/pergunta-layouts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(perguntaLayout)))
            .andExpect(status().isCreated());

        // Validate the PerguntaLayout in the database
        List<PerguntaLayout> perguntaLayoutList = perguntaLayoutRepository.findAll();
        assertThat(perguntaLayoutList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deletePerguntaLayout() throws Exception {
        // Initialize the database
        perguntaLayoutService.save(perguntaLayout);

        int databaseSizeBeforeDelete = perguntaLayoutRepository.findAll().size();

        // Get the perguntaLayout
        restPerguntaLayoutMockMvc.perform(delete("/api/pergunta-layouts/{id}", perguntaLayout.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<PerguntaLayout> perguntaLayoutList = perguntaLayoutRepository.findAll();
        assertThat(perguntaLayoutList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
