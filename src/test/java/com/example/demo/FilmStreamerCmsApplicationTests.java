package com.example.demo;

import com.example.demo.model.User;

import com.example.demo.service.UserService;
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


}
