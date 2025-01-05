package org.hatice.userjpaqueriesproject.runner;

import org.hatice.userjpaqueriesproject.entity.Address;
import org.hatice.userjpaqueriesproject.entity.User;
import org.hatice.userjpaqueriesproject.enums.Status;
import org.hatice.userjpaqueriesproject.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
/*
@Component, is a parameter used for Spring to recognize a class as a bean and manage it when the application starts.
anotation.
When we create the CommandLineRunner class, we need to create an anotation for this class to be detected and executed
 by Spring.
so Spring needs to be brought into the scope of management. That's why we added @Component.
 */ public class DataLoader implements CommandLineRunner {
	 /*
	Why do we use CommandLineRunner?
CommandLineRunner is used to run some one-off code when the Spring Boot application starts.

Scenarios:
Adding test data.
Testing repositories.
Do logging or startup operations.
CommandLineRunner allows us to test easily in the application.
	  */
	
	
	private final UserRepository userRepository;
	
	public DataLoader(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	
	@Override
	public void run(String... args) throws Exception {
		// Creating User
		User user = new User("Jane", "Doe", "janedoe@example.com", 28, Status.ACTIVE);
		
		// Creating address
		Address address = new Address("123 Main St", "New York", "USA");
		address.setUser(user);
		
		// Adding an address to the user
		user.setAddresses(Collections.singletonList(address));
		
		// Saving to database
		userRepository.save(user);
		
		System.out.println("User and address saved successfully.");
		
		
		System.out.println("The user with addresses:   ");
		System.out.println(userRepository.findUserWithAddresses(4L));
	}
	
	
}