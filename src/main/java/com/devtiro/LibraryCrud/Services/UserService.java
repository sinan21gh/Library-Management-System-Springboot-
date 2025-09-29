package com.devtiro.LibraryCrud.Services;

import com.devtiro.LibraryCrud.Repository.UserRepo;
import com.devtiro.LibraryCrud.domain.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private JWTService service;


    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public Users register(Users users){
        users.setPassword(encoder.encode(users.getPassword()));
        return userRepo.save(users);
    }

    public String verify(Users users) {
        Authentication authentication = manager.authenticate(
                new UsernamePasswordAuthenticationToken(users.getUsername(), users.getPassword()));

        return authentication.isAuthenticated() ? service.generateToken(users.getUsername()) : "failed";
    }
}
