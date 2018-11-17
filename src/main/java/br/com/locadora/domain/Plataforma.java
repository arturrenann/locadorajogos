package br.com.locadora.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Plataforma.
 */
@Entity
@Table(name = "plataforma")
public class Plataforma implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "plataforma")
    private String plataforma;

    @Column(name = "valor")
    private Integer valor;

    @ManyToMany(mappedBy = "plataformas")
    @JsonIgnore
    private Set<Reservar> reservars = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public Plataforma plataforma(String plataforma) {
        this.plataforma = plataforma;
        return this;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    public Integer getValor() {
        return valor;
    }

    public Plataforma valor(Integer valor) {
        this.valor = valor;
        return this;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public Set<Reservar> getReservars() {
        return reservars;
    }

    public Plataforma reservars(Set<Reservar> reservars) {
        this.reservars = reservars;
        return this;
    }

    public Plataforma addReservar(Reservar reservar) {
        this.reservars.add(reservar);
        reservar.getPlataformas().add(this);
        return this;
    }

    public Plataforma removeReservar(Reservar reservar) {
        this.reservars.remove(reservar);
        reservar.getPlataformas().remove(this);
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
        Plataforma plataforma = (Plataforma) o;
        if (plataforma.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), plataforma.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Plataforma{" +
            "id=" + getId() +
            ", plataforma='" + getPlataforma() + "'" +
            ", valor=" + getValor() +
            "}";
    }
}
