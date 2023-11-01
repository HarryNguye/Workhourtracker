package com.example.Workhour.domain;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface WorkHourRepository extends CrudRepository<WorkHour, Long> {
	List<WorkHour> findByUserAndDateBetween(User user, LocalDate start, LocalDate end);
}