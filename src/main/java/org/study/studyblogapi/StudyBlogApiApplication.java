package org.study.studyblogapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.core.context.SecurityContextHolder;

@SpringBootApplication
@EnableJpaAuditing
@EnableAsync
public class StudyBlogApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudyBlogApiApplication.class, args);
	}

}
