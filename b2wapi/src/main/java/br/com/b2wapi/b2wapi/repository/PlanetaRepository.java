package br.com.b2wapi.b2wapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.b2wapi.b2wapi.entity.Planeta;

@Repository
public interface PlanetaRepository extends JpaRepository<Planeta, Long> {
    
}
