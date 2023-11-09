package com.example.Workhour.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface WorkHourRepository extends CrudRepository<WorkHour, Long> {

    List<WorkHour> findByHours(int Hours);
    
}