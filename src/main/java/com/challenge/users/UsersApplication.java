package com.challenge.users;

import com.challenge.users.repositories.PhonesRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableJpaRepositories("com.challenge.users.repositories")
@EnableJpaRepositories(basePackageClasses = {PhonesRepository.class})
public class UsersApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsersApplication.class, args);
	}

}
