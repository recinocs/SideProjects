package com.chrisrecinos.model.data.repository;

import com.chrisrecinos.model.data.entity.CardYear;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author - Christopher Recinos
 */

@Repository
public interface CardYearRepository extends CrudRepository<CardYear, Long> {
    CardYear findByCardYear(int cardYear);
    List<CardYear> findAllByOrderByCardYear();
}
