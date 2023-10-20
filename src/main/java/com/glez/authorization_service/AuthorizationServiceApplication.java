package com.glez.authorization_service;

import com.glez.authorization_service.entities.RoleEntity;
import com.glez.authorization_service.entities.UserEntity;
import com.glez.authorization_service.repositories.RoleRepository;
import com.glez.authorization_service.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@EnableFeignClients
@SpringBootApplication
public class AuthorizationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthorizationServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(UserRepository repository, RoleRepository roleRepository) {
		return (args) -> {
			if (repository.count() > 0) {
				return;
			}
			UserEntity user = new UserEntity();
			UserEntity user2 = new UserEntity();
			RoleEntity adminRole = new RoleEntity();
			RoleEntity userRole = new RoleEntity();

			adminRole.setName("ROLE_ADMIN");
			userRole.setName("ROLE_USER");

			adminRole = roleRepository.save(adminRole);
			userRole = roleRepository.save(userRole);

			user.setUsername("admin");
			user.setPassword("12345");
			user.setEmail("admin@domain.com");
			user.setFirstName("Admin");
			user.setLastName("Administrator");
			user.setEnabled(true);
			user.getRoles().add(adminRole);
			user.getRoles().add(userRole);
			repository.save(user);

			user2.setUsername("user");
			user2.setPassword("12345");
			user2.setEmail("user@domain.com");
			user2.setFirstName("User");
			user2.setLastName("Username");
			user2.setEnabled(true);
			user2.getRoles().add(userRole);
			repository.save(user2);
		};
	}

}
