package com.example.demo;

import com.example.demo.model.User;

import com.example.demo.service.UserService;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.util.UUID;


@SpringBootTest
class FilmStreamerCmsApplicationTests {

	@Autowired
	UserService userService;

	@Test
	void contextLoads() {
	}

	@Test
	void createUser() {
		String date = LocalDate.now().toString();
		String uuid = UUID.randomUUID().toString();
		User user = new User(uuid, "john@doe.edu", "1234", "user",
				"John", "Doe", date);
		userService.createUser(user);
	}

	@Test
	void createUsersFaker(){
		Faker faker = new Faker();
		for (int i = 1; i <= 100; i++) {
			String uuid = UUID.randomUUID().toString();
			String[] name = faker.book().author().split(" ");
			String userFirstName = name[0];
			String userLastName = name[1];
			String birthDate = LocalDate.now().toString();
			String userEmail = userLastName + "@movie.edu";
			String password = "1234";
			String userRol = "user";
			User user = new User(uuid, userEmail, password, userRol, userFirstName, userLastName, birthDate);
			userService.createUser(user);
		}
	}

}
