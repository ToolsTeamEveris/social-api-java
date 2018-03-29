package com.social.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.social.entity.Person;
import com.social.manager.AuthManager;
import java.security.Principal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
public class AuthController {
        private final AuthManager authManager;
	
	@Autowired
	public AuthController(final AuthManager authManager) {
            this.authManager = authManager;
	}
	
	@PostMapping(value="/auth/register")
	@ResponseBody
	public void verify(@RequestBody Person user) {
            authManager.register(user);
	}
        
        @RequestMapping("/auth/google")
        public Principal user(Principal principal) {
            return principal;
        }
}
