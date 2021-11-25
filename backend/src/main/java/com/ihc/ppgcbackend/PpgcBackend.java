package com.ihc.ppgcbackend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootApplication
public class PpgcBackend {
	@Bean
	CommandLineRunner runner(MongoTemplate mongoTemplate){
		return null;
	};

	public static void main(String[] args) {
		SpringApplication.run(PpgcBackend.class, args);
	}

}
