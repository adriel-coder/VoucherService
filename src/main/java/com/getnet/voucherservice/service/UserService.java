package com.getnet.voucherservice.service;

import com.getnet.voucherservice.model.User; 
import com.getnet.voucherservice.repository.UserRepository; // Certifique-se de ter um repositório
import com.getnet.voucherservice.security.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private UserDetailsService userDetailsService;
    
    @Autowired
    private JwtUtil jwtUtil;


    public User registerUser(String username, String password, String role) {
        if (userRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("User " + username + " already exists.");
        }
        
        if (username == null || username.trim().isEmpty() || username.contains(" ")) {
            throw new IllegalArgumentException("Username cannot be null, empty or contain spaces.");
        }
        
        if (password == null || password.trim().isEmpty() || password.contains(" ")) {
            throw new IllegalArgumentException("Password cannot be null, empty or contain spaces.");
        }
        
        if (role == null || role.trim().isEmpty() || role.contains(" ")) {
            throw new IllegalArgumentException("Role cannot be null, empty or contain spaces.");
        }
        
        String encodedPassword = passwordEncoder.encode(password); // Codificando a senha
        User user = new User();
        user.setUsername(username);
        user.setPassword(encodedPassword); // Definindo a senha codificada
        user.setRole(role);
        
        return userRepository.save(user);
    }


    public String authenticate(String username, String password) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username, password);
        
        authenticationManager.authenticate(authenticationToken);
        System.out.println("AuthenticationManager: "+authenticationToken);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        
        return jwtUtil.generateToken(userDetails);
    }
}
