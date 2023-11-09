package com.example.Workhour;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.Workhour.domain.WorkHour;
import com.example.Workhour.domain.WorkHourRepository;

;

@SpringBootApplication
public class WorkhourApplication {
	private static final Logger log = LoggerFactory.getLogger(WorkhourApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(WorkhourApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner workhourDemo(WorkHourRepository repository) {
	    return (args) -> {
	        log.info("save a couple of workhours");
	        repository.save(new WorkHour(null, "2023-01-01", 8));
	        repository.save(new WorkHour(null, "2023-01-02", 6));   
	        
	        log.info("fetch all workhours");
	        for (WorkHour workhour : repository.findAll()) {
	            log.info(workhour.toString());
	        }
	    };
	}


	};
