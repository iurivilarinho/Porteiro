package com.br.porteiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.porteiro.models.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
