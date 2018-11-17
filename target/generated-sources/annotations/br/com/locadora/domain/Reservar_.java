package br.com.locadora.domain;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Reservar.class)
public abstract class Reservar_ {

	public static volatile SetAttribute<Reservar, Plataforma> plataformas;
	public static volatile SingularAttribute<Reservar, LocalDate> dataEntrega;
	public static volatile SingularAttribute<Reservar, Integer> dias;
	public static volatile SingularAttribute<Reservar, Long> id;
	public static volatile SingularAttribute<Reservar, LocalDate> dataInicio;
	public static volatile SetAttribute<Reservar, Cliente> clientes;
	public static volatile SetAttribute<Reservar, Jogo> jogos;

}

