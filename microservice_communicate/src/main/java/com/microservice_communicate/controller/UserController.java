package com.microservice_communicate.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.microservice_communicate.dto.User;

@RestController
public class UserController {
	
	private final RestTemplate restTemplete;
	
	@Autowired
	public UserController(RestTemplate restTemplete) {
		this.restTemplete = restTemplete;
	}
	
	
	@RequestMapping(value = "/getUserById/{userId}", method = RequestMethod.GET)
    public Map<String, Object> getUserById(@PathVariable("userId") Long userId) {

		String url = "http://localhost:8080/findById/" + userId;
		
        Map<String, Object> response = restTemplete.getForObject(url, Map.class);

        return response;
		
	}

}
