package br.com.locadora.repository;

import br.com.locadora.domain.Reservar;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Reservar entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ReservarRepository extends JpaRepository<Reservar, Long> {

    @Query(value = "select distinct reservar from Reservar reservar left join fetch reservar.jogos left join fetch reservar.plataformas",
        countQuery = "select count(distinct reservar) from Reservar reservar")
    Page<Reservar> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct reservar from Reservar reservar left join fetch reservar.jogos left join fetch reservar.plataformas")
    List<Reservar> findAllWithEagerRelationships();

    @Query("select reservar from Reservar reservar left join fetch reservar.jogos left join fetch reservar.plataformas where reservar.id =:id")
    Optional<Reservar> findOneWithEagerRelationships(@Param("id") Long id);

}
