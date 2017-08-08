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
    Team findByTeamName(String teamName);
    List<Team> findByCity(String city);
    List<Team> findAll();
}
