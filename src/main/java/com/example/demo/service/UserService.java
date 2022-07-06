package com.example.demo.service;

import java.util.*;

import com.example.demo.model.User;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {

    RestTemplate restTemplate = new RestTemplate();

    public Optional<Iterable<User>> getAllUsers() {
        ResponseEntity<List<User>> response = restTemplate.exchange("http://localhost:8083/api/users/users",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {
                });
        List<User> users = response.getBody();
        return Optional.of(users);
    }

    public Optional<User> findUserById(UUID userUUID) {
        ResponseEntity<User> response = restTemplate.exchange("http://localhost:8083/api/users/getUser/" + userUUID,
                HttpMethod.GET, null, new ParameterizedTypeReference<User>() {
                });
        User user = response.getBody();
        return Optional.of(user);
    }

    public Optional<User> deleteUserById(UUID userUUID) {
        ResponseEntity<User> response = restTemplate.exchange("http://localhost:8083/api/users/deleteUser/" + userUUID,
                HttpMethod.DELETE, null, new ParameterizedTypeReference<User>() {
                });
        User user = response.getBody();
        return Optional.of(user);
    }

    public Optional<User> createUser(User user) {
        String url = "http://localhost:8083/api/users/addUser";
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.ALL));

        HttpEntity<User> request = new HttpEntity<>(user, headers);

        ResponseEntity<User> response = restTemplate.postForEntity(url, request, User.class);

        User newUser = response.getBody();
        return Optional.of(newUser);
    }

}