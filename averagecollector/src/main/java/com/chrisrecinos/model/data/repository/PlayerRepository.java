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
    Player findByFirstNameAndLastNameAndDob(String firstName, String lastName, Date dob);
    List<Player> findByFirstNameAndLastName(String firstName, String lastName);
    List<Player> findAll();
}
