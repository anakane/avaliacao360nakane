package com.chiote.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.chiote.domain.PerguntaLayout;
import com.chiote.service.PerguntaLayoutService;
import com.chiote.web.rest.util.HeaderUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing PerguntaLayout.
 */
@RestController
@RequestMapping("/api")
public class PerguntaLayoutResource {

    private final Logger log = LoggerFactory.getLogger(PerguntaLayoutResource.class);
        
    @Inject
    private PerguntaLayoutService perguntaLayoutService;

    /**
     * POST  /pergunta-layouts : Create a new perguntaLayout.
     *
     * @param perguntaLayout the perguntaLayout to create
     * @return the ResponseEntity with status 201 (Created) and with body the new perguntaLayout, or with status 400 (Bad Request) if the perguntaLayout has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/pergunta-layouts")
    @Timed
    public ResponseEntity<PerguntaLayout> createPerguntaLayout(@Valid @RequestBody PerguntaLayout perguntaLayout) throws URISyntaxException {
        log.debug("REST request to save PerguntaLayout : {}", perguntaLayout);
        if (perguntaLayout.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("perguntaLayout", "idexists", "A new perguntaLayout cannot already have an ID")).body(null);
        }
        PerguntaLayout result = perguntaLayoutService.save(perguntaLayout);
        return ResponseEntity.created(new URI("/api/pergunta-layouts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("perguntaLayout", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /pergunta-layouts : Updates an existing perguntaLayout.
     *
     * @param perguntaLayout the perguntaLayout to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated perguntaLayout,
     * or with status 400 (Bad Request) if the perguntaLayout is not valid,
     * or with status 500 (Internal Server Error) if the perguntaLayout couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/pergunta-layouts")
    @Timed
    public ResponseEntity<PerguntaLayout> updatePerguntaLayout(@Valid @RequestBody PerguntaLayout perguntaLayout) throws URISyntaxException {
        log.debug("REST request to update PerguntaLayout : {}", perguntaLayout);
        if (perguntaLayout.getId() == null) {
            return createPerguntaLayout(perguntaLayout);
        }
        PerguntaLayout result = perguntaLayoutService.save(perguntaLayout);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("perguntaLayout", perguntaLayout.getId().toString()))
            .body(result);
    }

    /**
     * GET  /pergunta-layouts : get all the perguntaLayouts.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of perguntaLayouts in body
     */
    @GetMapping("/pergunta-layouts")
    @Timed
    public List<PerguntaLayout> getAllPerguntaLayouts() {
        log.debug("REST request to get all PerguntaLayouts");
        return perguntaLayoutService.findAll();
    }

    /**
     * GET  /pergunta-layouts/:id : get the "id" perguntaLayout.
     *
     * @param id the id of the perguntaLayout to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the perguntaLayout, or with status 404 (Not Found)
     */
    @GetMapping("/pergunta-layouts/{id}")
    @Timed
    public ResponseEntity<PerguntaLayout> getPerguntaLayout(@PathVariable Long id) {
        log.debug("REST request to get PerguntaLayout : {}", id);
        PerguntaLayout perguntaLayout = perguntaLayoutService.findOne(id);
        return Optional.ofNullable(perguntaLayout)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /pergunta-layouts/:id : delete the "id" perguntaLayout.
     *
     * @param id the id of the perguntaLayout to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/pergunta-layouts/{id}")
    @Timed
    public ResponseEntity<Void> deletePerguntaLayout(@PathVariable Long id) {
        log.debug("REST request to delete PerguntaLayout : {}", id);
        perguntaLayoutService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("perguntaLayout", id.toString())).build();
    }

}
