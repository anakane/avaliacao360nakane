package com.chiote.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A AvaliacaoModelo.
 */
@Entity
@Table(name = "avaliacao_modelo")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class AvaliacaoModelo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(max = 20)
    @Column(name = "nome", length = 20, nullable = false)
    private String nome;

    @NotNull
    @Size(max = 200)
    @Column(name = "descricao", length = 200, nullable = false)
    private String descricao;

    @ManyToOne
    @NotNull
    private Equipe equipe;

    @OneToMany(mappedBy = "avaliacaoModelo")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<PerguntaLayout> perguntaLayouts = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public AvaliacaoModelo nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public AvaliacaoModelo descricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Equipe getEquipe() {
        return equipe;
    }

    public AvaliacaoModelo equipe(Equipe equipe) {
        this.equipe = equipe;
        return this;
    }

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }

    public Set<PerguntaLayout> getPerguntaLayouts() {
        return perguntaLayouts;
    }

    public AvaliacaoModelo perguntaLayouts(Set<PerguntaLayout> perguntaLayouts) {
        this.perguntaLayouts = perguntaLayouts;
        return this;
    }

    public AvaliacaoModelo addPerguntaLayout(PerguntaLayout perguntaLayout) {
        perguntaLayouts.add(perguntaLayout);
        perguntaLayout.setAvaliacaoModelo(this);
        return this;
    }

    public AvaliacaoModelo removePerguntaLayout(PerguntaLayout perguntaLayout) {
        perguntaLayouts.remove(perguntaLayout);
        perguntaLayout.setAvaliacaoModelo(null);
        return this;
    }

    public void setPerguntaLayouts(Set<PerguntaLayout> perguntaLayouts) {
        this.perguntaLayouts = perguntaLayouts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AvaliacaoModelo avaliacaoModelo = (AvaliacaoModelo) o;
        if (avaliacaoModelo.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, avaliacaoModelo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "AvaliacaoModelo{" +
            "id=" + id +
            ", nome='" + nome + "'" +
            ", descricao='" + descricao + "'" +
            '}';
    }
}
