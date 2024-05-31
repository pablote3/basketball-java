package com.rossotti.basketball.jpa.repository;

import com.rossotti.basketball.jpa.model.Player;
import org.springframework.data.repository.Repository;

import java.time.LocalDate;
import java.util.List;

public interface PlayerRepository extends Repository<Player, Long> {

	List<Player> findAll();

	Player findById(Long id);

	void save(Player player);

	void deleteById(Long id);

	List<Player> findByLastNameAndFirstName(String lastName, String firstName);

	Player findByLastNameAndFirstNameAndBirthdate(String lastName, String firstName, LocalDate birthdate);
}