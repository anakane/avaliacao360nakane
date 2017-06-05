package com.chiote.service;

import com.chiote.domain.PerguntaLayout;
import com.chiote.repository.PerguntaLayoutRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Service Implementation for managing PerguntaLayout.
 */
@Service
@Transactional
public class PerguntaLayoutService {

    private final Logger log = LoggerFactory.getLogger(PerguntaLayoutService.class);
    
    @Inject
    private PerguntaLayoutRepository perguntaLayoutRepository;

    /**
     * Save a perguntaLayout.
     *
     * @param perguntaLayout the entity to save
     * @return the persisted entity
     */
    public PerguntaLayout save(PerguntaLayout perguntaLayout) {
        log.debug("Request to save PerguntaLayout : {}", perguntaLayout);
        PerguntaLayout result = perguntaLayoutRepository.save(perguntaLayout);
        return result;
    }

    /**
     *  Get all the perguntaLayouts.
     *  
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<PerguntaLayout> findAll() {
        log.debug("Request to get all PerguntaLayouts");
        List<PerguntaLayout> result = perguntaLayoutRepository.findAll();

        return result;
    }

    /**
     *  Get one perguntaLayout by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public PerguntaLayout findOne(Long id) {
        log.debug("Request to get PerguntaLayout : {}", id);
        PerguntaLayout perguntaLayout = perguntaLayoutRepository.findOne(id);
        return perguntaLayout;
    }

    /**
     *  Delete the  perguntaLayout by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete PerguntaLayout : {}", id);
        perguntaLayoutRepository.delete(id);
    }
}
