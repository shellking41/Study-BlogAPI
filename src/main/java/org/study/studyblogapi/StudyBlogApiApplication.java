package org.study.studyblogapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class StudyBlogApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudyBlogApiApplication.class, args);
	}

}
