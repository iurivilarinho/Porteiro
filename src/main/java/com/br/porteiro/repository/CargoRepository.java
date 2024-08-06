package com.br.porteiro.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.porteiro.models.Cargo;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Long> {

	Page<Cargo> findByStatus(Boolean status, Pageable page);

}
