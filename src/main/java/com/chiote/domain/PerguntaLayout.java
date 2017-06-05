package com.chiote.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A PerguntaLayout.
 */
@Entity
@Table(name = "pergunta_layout")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PerguntaLayout implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(max = 20)
    @Column(name = "assunto", length = 20, nullable = false)
    private String assunto;

    @NotNull
    @Size(max = 900)
    @Column(name = "texto", length = 900, nullable = false)
    private String texto;

    @ManyToOne
    private AvaliacaoModelo avaliacaoModelo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAssunto() {
        return assunto;
    }

    public PerguntaLayout assunto(String assunto) {
        this.assunto = assunto;
        return this;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getTexto() {
        return texto;
    }

    public PerguntaLayout texto(String texto) {
        this.texto = texto;
        return this;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public AvaliacaoModelo getAvaliacaoModelo() {
        return avaliacaoModelo;
    }

    public PerguntaLayout avaliacaoModelo(AvaliacaoModelo avaliacaoModelo) {
        this.avaliacaoModelo = avaliacaoModelo;
        return this;
    }

    public void setAvaliacaoModelo(AvaliacaoModelo avaliacaoModelo) {
        this.avaliacaoModelo = avaliacaoModelo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PerguntaLayout perguntaLayout = (PerguntaLayout) o;
        if (perguntaLayout.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, perguntaLayout.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "PerguntaLayout{" +
            "id=" + id +
            ", assunto='" + assunto + "'" +
            ", texto='" + texto + "'" +
            '}';
    }
}
