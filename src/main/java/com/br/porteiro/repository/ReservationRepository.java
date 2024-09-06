package com.br.porteiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.porteiro.models.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}
