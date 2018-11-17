package br.com.locadora.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Reservar.
 */
@Entity
@Table(name = "reservar")
public class Reservar implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "dias")
    private Integer dias;

    @Column(name = "data_inicio")
    private LocalDate dataInicio;

    @Column(name = "data_entrega")
    private LocalDate dataEntrega;

    @ManyToMany
    @JoinTable(name = "reservar_jogo",
               joinColumns = @JoinColumn(name = "reservars_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "jogos_id", referencedColumnName = "id"))
    private Set<Jogo> jogos = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "reservar_plataformas",
               joinColumns = @JoinColumn(name = "reservars_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "plataformas_id", referencedColumnName = "id"))
    private Set<Plataforma> plataformas = new HashSet<>();

    @ManyToMany(mappedBy = "reservas")
    @JsonIgnore
    private Set<Cliente> clientes = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDias() {
        return dias;
    }

    public Reservar dias(Integer dias) {
        this.dias = dias;
        return this;
    }

    public void setDias(Integer dias) {
        this.dias = dias;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public Reservar dataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
        return this;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataEntrega() {
        return dataEntrega;
    }

    public Reservar dataEntrega(LocalDate dataEntrega) {
        this.dataEntrega = dataEntrega;
        return this;
    }

    public void setDataEntrega(LocalDate dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public Set<Jogo> getJogos() {
        return jogos;
    }

    public Reservar jogos(Set<Jogo> jogos) {
        this.jogos = jogos;
        return this;
    }

    public Reservar addJogo(Jogo jogo) {
        this.jogos.add(jogo);
        jogo.getReservars().add(this);
        return this;
    }

    public Reservar removeJogo(Jogo jogo) {
        this.jogos.remove(jogo);
        jogo.getReservars().remove(this);
        return this;
    }

    public void setJogos(Set<Jogo> jogos) {
        this.jogos = jogos;
    }

    public Set<Plataforma> getPlataformas() {
        return plataformas;
    }

    public Reservar plataformas(Set<Plataforma> plataformas) {
        this.plataformas = plataformas;
        return this;
    }

    public Reservar addPlataformas(Plataforma plataforma) {
        this.plataformas.add(plataforma);
        plataforma.getReservars().add(this);
        return this;
    }

    public Reservar removePlataformas(Plataforma plataforma) {
        this.plataformas.remove(plataforma);
        plataforma.getReservars().remove(this);
        return this;
    }

    public void setPlataformas(Set<Plataforma> plataformas) {
        this.plataformas = plataformas;
    }

    public Set<Cliente> getClientes() {
        return clientes;
    }

    public Reservar clientes(Set<Cliente> clientes) {
        this.clientes = clientes;
        return this;
    }

    public Reservar addCliente(Cliente cliente) {
        this.clientes.add(cliente);
        cliente.getReservas().add(this);
        return this;
    }

    public Reservar removeCliente(Cliente cliente) {
        this.clientes.remove(cliente);
        cliente.getReservas().remove(this);
        return this;
    }

    public void setClientes(Set<Cliente> clientes) {
        this.clientes = clientes;
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
        Reservar reservar = (Reservar) o;
        if (reservar.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), reservar.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Reservar{" +
            "id=" + getId() +
            ", dias=" + getDias() +
            ", dataInicio='" + getDataInicio() + "'" +
            ", dataEntrega='" + getDataEntrega() + "'" +
            "}";
    }
}
