package com.example.Workhour.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface WorkHourRepository extends CrudRepository<WorkHour, Long> {
    List<WorkHour> findByHours(int Hours);

    // Laskee kaikki tunnit yhteensä.
    @Query("SELECT SUM(wh.hours) FROM WorkHour wh")
    Integer sumTotalHours();
}
