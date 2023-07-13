package com.nagarro.community;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication()
@EnableWebSecurity
public class CommunityApplication  {
	public static void main(String[] args) {
		SpringApplication.run(CommunityApplication.class, args);
	}

}
