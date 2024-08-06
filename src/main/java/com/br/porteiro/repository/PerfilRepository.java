package com.br.porteiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.br.porteiro.models.Perfil;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long>, JpaSpecificationExecutor<Perfil> {

}
