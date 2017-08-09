package com.chrisrecinos.model.data.repository;

import com.chrisrecinos.model.data.entity.Player;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author - Christopher Recinos
 */

@Repository
public interface PlayerRepository extends CrudRepository<Player, Long> {
    Player findByFirstNameIgnoreCaseAndLastNameIgnoreCaseAndSuffixIgnoreCase(String firstName, String lastName, String suffix);
    Player findByFirstNameIgnoreCaseAndLastNameIgnoreCaseAndDob(String firstName, String lastName, Date dob);
    List<Player> findByFirstNameIgnoreCaseAndLastNameIgnoreCase(String firstName, String lastName);
    List<Player> findByFirstNameIgnoreCase(String firstName);
    List<Player> findByLastNameIgnoreCase(String lastName);
    List<Player> findAll();
}

