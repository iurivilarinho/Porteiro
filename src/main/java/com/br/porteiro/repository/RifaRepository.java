package com.br.porteiro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.porteiro.models.Rifa;

@Repository
public interface RifaRepository extends JpaRepository<Rifa, Long> {

	List<Rifa> findByCotas_Reservation_UserPurchase_Cpf(String cpf);

}
