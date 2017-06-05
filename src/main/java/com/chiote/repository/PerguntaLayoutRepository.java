package com.chiote.repository;

import com.chiote.domain.PerguntaLayout;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the PerguntaLayout entity.
 */
@SuppressWarnings("unused")
public interface PerguntaLayoutRepository extends JpaRepository<PerguntaLayout,Long> {

}
