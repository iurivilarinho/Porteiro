package com.br.porteiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.porteiro.models.Documento;

@Repository
public interface DocumentoRepository extends JpaRepository<Documento, Long> {

}
