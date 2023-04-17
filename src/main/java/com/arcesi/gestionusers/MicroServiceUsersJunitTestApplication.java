package com.arcesi.gestionusers;

import java.time.Instant;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.arcesi.gestionusers.entities.UserEntity;
import com.arcesi.gestionusers.repositories.UserRepository;

@SpringBootApplication
public class MicroServiceUsersJunitTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroServiceUsersJunitTestApplication.class, args);
	}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public CommandLineRunner run(final UserRepository userRepository) {
		return args->{
			
			for(int i=0;i<20;i++) {
				UserEntity user=UserEntity.builder()
						.codeUnique(UUID.randomUUID().toString())
						.firstName("tibari"+i)
						.lastName("zeroual"+i)
						.age(42+i)
						.passWord("boudarga1980")
						.email("tibarinewdzign"+i+"@gmail.com")
						.isActive(Boolean.TRUE)
						.createdAt(Instant.now())
						.build();
				
				userRepository.save(user);
			}
			userRepository.findAll().forEach(u->{System.out.println(u.toString());});
		};
	}
}
