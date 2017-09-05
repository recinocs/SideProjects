package com.chrisrecinos.model.data.repository;

import com.chrisrecinos.model.data.entity.Team;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * @author - Christopher Recinos
 */

@Repository
public interface TeamRepository extends CrudRepository<Team, Long>{
    Team findByTeamNameIgnoreCase(String teamName);
    List<Team> findByTeamNameIgnoreCaseStartingWithOrderByTeamNameAsc(String teamName);
    List<Team> findByTeamNameIgnoreCaseStartingWithOrderByTeamNameAsc(Character c);
    List<Team> findByCityIgnoreCaseOrderByCityAsc(String city);
    List<Team> findByCityIgnoreCaseStartingWithOrderByTeamNameAsc(String city);
    List<Team> findByCityIgnoreCaseStartingWithOrderByTeamNameAsc(Character c);
    List<Team> findAllByOrderByCityAsc();
}
