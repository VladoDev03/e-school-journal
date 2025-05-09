package com.informatics.e_school_journal;

import com.informatics.e_school_journal.data.entity.School;
import com.informatics.e_school_journal.data.repo.SchoolRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;


@SpringBootApplication
@EnableR2dbcRepositories
public class ESchoolJournalApplication {

	public static void main(String[] args) {
		SpringApplication.run(ESchoolJournalApplication.class, args);
	}

}
