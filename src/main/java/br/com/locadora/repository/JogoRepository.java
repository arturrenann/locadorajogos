package br.com.locadora.repository;

import br.com.locadora.domain.Jogo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Jogo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface JogoRepository extends JpaRepository<Jogo, Long> {

}
