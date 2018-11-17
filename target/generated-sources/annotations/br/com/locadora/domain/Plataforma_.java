package br.com.locadora.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Plataforma.class)
public abstract class Plataforma_ {

	public static volatile SingularAttribute<Plataforma, String> plataforma;
	public static volatile SingularAttribute<Plataforma, Integer> valor;
	public static volatile SetAttribute<Plataforma, Reservar> reservars;
	public static volatile SingularAttribute<Plataforma, Long> id;

}

