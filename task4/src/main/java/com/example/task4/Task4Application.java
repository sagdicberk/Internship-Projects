package com.example.task4;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class Task4Application {

	public static void main(String[] args) {
		SpringApplication.run(Task4Application.class, args);
	}

	@Bean // IoC'ye bu nesneyi eklemek için bu motasyon kullanılır.
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
