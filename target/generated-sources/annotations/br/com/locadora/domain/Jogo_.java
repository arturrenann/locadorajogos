package br.com.locadora.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Jogo.class)
public abstract class Jogo_ {

	public static volatile SingularAttribute<Jogo, String> nome;
	public static volatile SetAttribute<Jogo, Reservar> reservars;
	public static volatile SingularAttribute<Jogo, Long> id;

}

