package com.getnet.voucherservice.controller;

import com.getnet.voucherservice.model.LoginRequest;
import com.getnet.voucherservice.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
    	
        System.out.println("Login:");
        System.out.println("Username: "+request.getUsername());
        System.out.println("Password: "+request.getPassword());
    	String token = userService.authenticate(request.getUsername(), request.getPassword());
    	return ResponseEntity.ok(token);
    }
}
