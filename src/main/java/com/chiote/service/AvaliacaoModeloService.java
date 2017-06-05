package com.chiote.service;

import com.chiote.domain.AvaliacaoControle;
import com.chiote.domain.AvaliacaoModelo;
import com.chiote.domain.Pergunta;
import com.chiote.domain.PerguntaLayout;
import com.chiote.domain.User;
import com.chiote.repository.AvaliacaoModeloRepository;
import com.chiote.repository.PerguntaRepository;
import com.chiote.repository.AvaliacaoControleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;


import javax.inject.Inject;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Service Implementation for managing AvaliacaoModelo.
 */
@Service
@Transactional
public class AvaliacaoModeloService {

    private final Logger log = LoggerFactory.getLogger(AvaliacaoModeloService.class);
    
    @Inject
    private AvaliacaoModeloRepository avaliacaoModeloRepository;
    private PerguntaRepository perguntaRepository;
    private AvaliacaoControleRepository avaliacaoControleRepository;

    /**
     * Save a avaliacaoModelo.
     *
     * @param avaliacaoModelo the entity to save
     * @return the persisted entity
     */
    public AvaliacaoModelo save(AvaliacaoModelo avaliacaoModelo) {
        log.debug("Request to save AvaliacaoModelo : {}", avaliacaoModelo);
        AvaliacaoModelo result = avaliacaoModeloRepository.save(avaliacaoModelo);
        return result;
    }


    /**
     * Save a avaliacaoModelo.
     *
     * @param avaliacaoModelo the entity to save
     * @return the persisted entity
     */
    public AvaliacaoControle submit(Long  avaliacaoModeloId) {
        log.debug("Request to submit AvaliacaoModelo : {}", avaliacaoModeloId);
        AvaliacaoControle result = null;
        final AvaliacaoModelo avaliacaoModeloBanco = this.avaliacaoModeloRepository.findOne(avaliacaoModeloId);

        log.debug("Request to findOne AvaliacaoModelo : {}", avaliacaoModeloBanco);
        
        for (User user : avaliacaoModeloBanco.getEquipe().getMembros()) {
        	AvaliacaoControle novaAvaliacaoControle = new AvaliacaoControle();
            log.debug("USER avaliacaoModeloBanco : {}", user);
        	novaAvaliacaoControle.setAvaliador(user);
        	novaAvaliacaoControle.setSituacao(true);
            log.debug("Lider avaliacaoModeloBanco : {}", avaliacaoModeloBanco.getEquipe().getLider());
        	novaAvaliacaoControle.setAvaliado(avaliacaoModeloBanco.getEquipe().getLider());
        	for (PerguntaLayout pergunta : avaliacaoModeloBanco.getPerguntaLayouts()) {
        		Pergunta novaPergunta = new Pergunta();
        		novaPergunta.setPerguntaLayout(pergunta);
        		novaAvaliacaoControle.addPergunta(novaPergunta);
        		novaAvaliacaoControle.setPerguntas(novaAvaliacaoControle.getPerguntas());
                log.debug("Perguntas  novaAvaliacaoControle.getPerguntas() : {}", novaAvaliacaoControle.getPerguntas());
        	}

            log.debug("Result novaAvaliacaoControle : {}", novaAvaliacaoControle.toString());
            this.avaliacaoControleRepository.save(novaAvaliacaoControle);
            log.debug("Result to save AvaliacaoModelo : {}", result);
        }

        
        log.debug("Equipe: {}", avaliacaoModeloBanco.getEquipe());
        log.debug("Passou do print de equipe");
        log.debug("Membros: {}", avaliacaoModeloBanco.getEquipe().getMembros());
        log.debug("Passou do print de membros");
        log.debug("Avaliacao modelo {}", avaliacaoModeloBanco);
        //AvaliacaoModelo result = avaliacaoModeloRepository.save(avaliacaoModelo);
        //String result = 'teste'
        
        return result;
    }


    /**
     *  Get all the avaliacaoModelos.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<AvaliacaoModelo> findAll(Pageable pageable) {
        log.debug("Request to get all AvaliacaoModelos");
        Page<AvaliacaoModelo> result = avaliacaoModeloRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one avaliacaoModelo by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public AvaliacaoModelo findOne(Long id) {
        log.debug("Request to get AvaliacaoModelo : {}", id);
        AvaliacaoModelo avaliacaoModelo = avaliacaoModeloRepository.findOne(id);
        return avaliacaoModelo;
    }

    /**
     *  Delete the  avaliacaoModelo by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete AvaliacaoModelo : {}", id);
        avaliacaoModeloRepository.delete(id);
    }
}
