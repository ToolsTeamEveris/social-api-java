package com.social.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.social.entity.Person;
import com.social.helper.AuthService;


@RestController
public class AuthController {
	private final AuthService authService;
	
	@Autowired
	public AuthController(final AuthService authService) {
		this.authService = authService;
	}
	
	@GetMapping(value="/auth/login/{id}")
	@ResponseBody
	public String getById(@PathVariable Long id) {
		return this.authService.createToken(id);
	}
	
	@PostMapping(value="/auth/verify")
	@ResponseBody
	public Person verify(@RequestHeader String token) {
		return this.authService.verifyToken(token);
	}
}
