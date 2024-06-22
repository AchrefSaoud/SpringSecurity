package com.example.demo;

import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.example.demo.Security.entities.AppUser;
import com.example.demo.Security.entities.RoleUser;
import com.example.demo.Security.services.AccountService;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	@Bean
	CommandLineRunner start(AccountService accountService){
		return args->{
			accountService.addNewRole(new RoleUser(null,"USER"));
			accountService.addNewRole(new RoleUser(null,"ADMIN"));

			accountService.addNewUser(new AppUser(null, "ACHRAF", "PASSWORD",new ArrayList<>()));

			accountService.addRoleToUser("ACHRAF", "ADMIN");
		};
	}
}
