package com.example.full;

import com.example.full.service.ClientService;
import com.example.full.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class FullApplication implements CommandLineRunner {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(FullApplication.class, args);
	}

	@Autowired
	private ClientService clientService;

	@Autowired
	private IUserService userService;

	@Override
	public void run(String... args) throws Exception {
		String password = "12345";

		for(int i=0; i<4; i++){
			String passwordBcrypt = passwordEncoder.encode(password);
			System.out.println(passwordBcrypt);
		}



	}
}
