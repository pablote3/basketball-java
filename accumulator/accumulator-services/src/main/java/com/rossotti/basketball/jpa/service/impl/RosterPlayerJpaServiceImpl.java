package com.rossotti.basketball.jpa.service.impl;

import com.rossotti.basketball.jpa.model.AbstractDomainClass.StatusCodeDAO;
import com.rossotti.basketball.jpa.model.RosterPlayer;
import com.rossotti.basketball.jpa.repository.RosterPlayerRepository;
import com.rossotti.basketball.jpa.service.RosterPlayerJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class RosterPlayerJpaServiceImpl implements RosterPlayerJpaService {

	private RosterPlayerRepository rosterPlayerRepository;

	@Autowired
	public void setRosterPlayerRepository(RosterPlayerRepository rosterPlayerRepository) {
		this.rosterPlayerRepository = rosterPlayerRepository;
	}

	@Override
	public RosterPlayer findByLastNameAndFirstNameAndBirthdateAndAsOfDate(String lastName, String firstName, LocalDate birthdate, LocalDate asOfDate) {
		RosterPlayer rosterPlayer = rosterPlayerRepository.findByLastNameAndFirstNameAndBirthdateAndAsOfDate(lastName, firstName, birthdate, asOfDate);
		if (rosterPlayer != null) {
			rosterPlayer.setStatusCode(StatusCodeDAO.Found);
		}
		else {
			rosterPlayer = new RosterPlayer(StatusCodeDAO.NotFound);
		}
		return rosterPlayer;
	}

	@Override
	public RosterPlayer findByLastNameAndFirstNameAndTeamKeyAndAsOfDate(String lastName, String firstName, String teamKey, LocalDate asOfDate) {
		RosterPlayer rosterPlayer = rosterPlayerRepository.findByLastNameAndFirstNameAndTeamKeyAndAsOfDate(lastName, firstName, teamKey, asOfDate);
		if (rosterPlayer != null) {
			rosterPlayer.setStatusCode(StatusCodeDAO.Found);
		}
		else {
			rosterPlayer = new RosterPlayer(StatusCodeDAO.NotFound);
		}
		return rosterPlayer;
	}

	@Override
	public List<RosterPlayer> findByLastNameAndFirstNameAndBirthdate(String lastName, String firstName, LocalDate birthdate) {
		return rosterPlayerRepository.findByLastNameAndFirstNameAndBirthdate(lastName, firstName, birthdate);
	}

	@Override
	public List<RosterPlayer> findByTeamKeyAndAsOfDate(String teamKey, LocalDate asOfDate) {
		return rosterPlayerRepository.findByTeamKeyAndAsOfDate(teamKey, asOfDate);
	}

	@Override
	public List<?> listAll() {
        return new ArrayList<>(rosterPlayerRepository.findAll());
	}

	@Override
	public RosterPlayer getById(Long id) {
		return rosterPlayerRepository.findById(id);
	}

	@Override
	public RosterPlayer create(RosterPlayer createRosterPlayer) {
		RosterPlayer rosterPlayer = findByLastNameAndFirstNameAndBirthdateAndAsOfDate(createRosterPlayer.getPlayer().getLastName(), createRosterPlayer.getPlayer().getFirstName(), createRosterPlayer.getPlayer().getBirthdate(), createRosterPlayer.getFromDate());
		if (rosterPlayer.isNotFound()) {
			rosterPlayerRepository.save(createRosterPlayer);
			createRosterPlayer.setStatusCode(StatusCodeDAO.Created);
			return createRosterPlayer;
		}
		else {
			return rosterPlayer;
		}
	}

	@Override
	public RosterPlayer update(RosterPlayer updateRosterPlayer) {
		RosterPlayer rosterPlayer = findByLastNameAndFirstNameAndBirthdateAndAsOfDate(updateRosterPlayer.getPlayer().getLastName(), updateRosterPlayer.getPlayer().getFirstName(), updateRosterPlayer.getPlayer().getBirthdate(), updateRosterPlayer.getFromDate());
		if (rosterPlayer.isFound()) {
			rosterPlayer.setFromDate(updateRosterPlayer.getFromDate());
			rosterPlayer.setToDate(updateRosterPlayer.getToDate());
			rosterPlayer.setNumber(updateRosterPlayer.getNumber());
			rosterPlayer.setPosition(updateRosterPlayer.getPosition());
			rosterPlayerRepository.save(rosterPlayer);
			rosterPlayer.setStatusCode(StatusCodeDAO.Updated);
		}
		return rosterPlayer;
	}

	@Override
	public RosterPlayer delete(Long id) {
		RosterPlayer findRosterPlayer = getById(id);
		if (findRosterPlayer != null && findRosterPlayer.isFound()) {
			rosterPlayerRepository.deleteById(findRosterPlayer.getId());
			findRosterPlayer.setStatusCode(StatusCodeDAO.Deleted);
			return findRosterPlayer;
		}
		else {
			return new RosterPlayer(StatusCodeDAO.NotFound);
		}
	}
}