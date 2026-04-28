package com.example.repository;

import com.example.model.Competitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositorio de acceso a datos para Competitor.
 *
 * Spring Data JPA genera automáticamente la implementación
 * de los métodos CRUD al heredar de JpaRepository.
 *
 * Los métodos personalizados (findBy...) también son generados
 * automáticamente por Spring a partir del nombre del método.
 */
@Repository
public interface CompetitorRepository extends JpaRepository<Competitor, Long> {

    /**
     * Busca un competidor por su email.
     * Spring genera la consulta: SELECT * FROM COMPETITOR WHERE email = ?
     */
    Optional<Competitor> findByEmail(String email);

    /**
     * Verifica si ya existe un competidor con ese email.
     * Usado al momento del registro para evitar duplicados.
     */
    boolean existsByEmail(String email);
}
