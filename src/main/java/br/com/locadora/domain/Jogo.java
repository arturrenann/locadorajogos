package br.com.locadora.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Jogo.
 */
@Entity
@Table(name = "jogo")
public class Jogo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @ManyToMany(mappedBy = "jogos")
    @JsonIgnore
    private Set<Reservar> reservars = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public Jogo nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Set<Reservar> getReservars() {
        return reservars;
    }

    public Jogo reservars(Set<Reservar> reservars) {
        this.reservars = reservars;
        return this;
    }

    public Jogo addReservar(Reservar reservar) {
        this.reservars.add(reservar);
        reservar.getJogos().add(this);
        return this;
    }

    public Jogo removeReservar(Reservar reservar) {
        this.reservars.remove(reservar);
        reservar.getJogos().remove(this);
        return this;
    }

    public void setReservars(Set<Reservar> reservars) {
        this.reservars = reservars;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Jogo jogo = (Jogo) o;
        if (jogo.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), jogo.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Jogo{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            "}";
    }
}
