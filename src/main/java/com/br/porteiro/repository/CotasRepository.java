package com.br.porteiro.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.porteiro.models.Cota;

@Repository
public interface CotasRepository extends JpaRepository<Cota, Long> {

	Optional<Cota> findByNumberAndRifa_Id(Long number, Long rifaId);

}
