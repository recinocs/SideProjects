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
    List<Player> findByFirstNameIgnoreCaseAndLastNameIgnoreCase(String firstName, String lastName);
    List<Player> findByFirstNameIgnoreCaseOrderByLastNameAsc(String firstName);
    List<Player> findByLastNameIgnoreCaseOrderByFirstNameAsc(String lastName);
    List<Player> findByFirstNameIgnoreCaseStartingWithOrderByLastNameAsc(String firstName);
    List<Player> findByLastNameIgnoreCaseStartingWithOrderByFirstNameAsc(String lastName);
    List<Player> findByFirstNameIgnoreCaseStartingWithOrderByFirstNameAsc(Character c);
    List<Player> findByLastNameIgnoreCaseStartingWithOrderByLastNameAsc(Character c);
    List<Player> findAllByOrderByFirstNameAscLastNameAsc();
}

