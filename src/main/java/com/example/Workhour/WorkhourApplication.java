package com.example.Workhour;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.Workhour.domain.AppUserRepository;
import com.example.Workhour.domain.WorkHour;
import com.example.Workhour.domain.WorkHourRepository;
import com.example.Workhour.domain.AppUser;

;

@SpringBootApplication
public class WorkhourApplication {
	private static final Logger log = LoggerFactory.getLogger(WorkhourApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(WorkhourApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner workhourDemo(WorkHourRepository repository, AppUserRepository arepository) {
	    return (args) -> {
	        log.info("save a couple of workhours");
	        repository.save(new WorkHour(null, "2023-01-01", 8));
	        repository.save(new WorkHour(null, "2023-01-02", 6));   
	        
			AppUser user1 = new AppUser("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
			AppUser user2 = new AppUser("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
			arepository.save(user1);
			arepository.save(user2);
			
	        log.info("fetch all workhours");
	        for (WorkHour workhour : repository.findAll()) {
	            log.info(workhour.toString());
	        }
	    };
	}


	};
