package com.example.bank_aplication_demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;


//@EnableSwagger2
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class BankApplicationDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankApplicationDemoApplication.class, args);

	}

//	@Autowired
//	public void configureGlobalAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
//		authenticationManagerBuilder.inMemoryAuthentication().
//				withUser("user").password("password").roles("CLIENT");
//	}
}
