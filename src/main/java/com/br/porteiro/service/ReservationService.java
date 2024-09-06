package com.br.porteiro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.br.porteiro.form.ReservationForm;
import com.br.porteiro.models.Cota;
import com.br.porteiro.models.Reservation;
import com.br.porteiro.repository.ReservationRepository;

@Service
public class ReservationService {

	@Autowired
	private ReservationRepository reservationRepository;

	@Autowired
	private CotaService cotaService;

	@Transactional
	public void createReservation(ReservationForm form) {
		Reservation reservation = new Reservation(form);
		
		reservationRepository.save(reservation);
		
		form.getQuotasId().forEach(q -> {
			Cota cota = cotaService.findById(q, form.getRifaId());
			cota.setReservation(reservation);
			cota.setSold(true);

		});

	}

}
