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
    List<Player> findByFirstNameIgnoreCaseAndLastNameIgnoreCaseOrderByDobAsc(String firstName, String lastName);
    List<Player> findByFirstNameIgnoreCaseOrderByLastNameAscDobAsc(String firstName);
    List<Player> findByLastNameIgnoreCaseOrderByFirstNameAscDobAsc(String lastName);
    List<Player> findByFirstNameIgnoreCaseStartingWithOrderByLastNameAscDobAsc(String firstName);
    List<Player> findByLastNameIgnoreCaseStartingWithOrderByFirstNameAscDobAsc(String lastName);
    List<Player> findByFirstNameIgnoreCaseStartingWithOrderByFirstNameAscDobAsc(Character c);
    List<Player> findByLastNameIgnoreCaseStartingWithOrderByLastNameAscDobAsc(Character c);
    List<Player> findAllByOrderByFirstNameAscLastNameAscDobAsc();
}

