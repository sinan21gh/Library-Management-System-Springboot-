package com.devtiro.LibraryCrud.controllers;

import com.devtiro.LibraryCrud.Repository.UserRepo;
import com.devtiro.LibraryCrud.Services.JWTService;
import com.devtiro.LibraryCrud.Services.UserService;
import com.devtiro.LibraryCrud.domain.Dto.UserDto;
import com.devtiro.LibraryCrud.domain.Users;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserService userService;

    @Autowired
    private JWTService jwtService;

    @CrossOrigin(origins = "http://localhost:5173")
    @PutMapping("/register")
    public Users register(@RequestBody Users users){
        return userService.register(users);
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/login")
    public String login(@RequestBody Users users){
        return userService.verify(users);
    }


    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/me")
    public ResponseEntity<UserDto> getProfile(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String token = authHeader.substring(7);
        String username = jwtService.extractUserName(token);

        Users user = userRepo.findByUsername(username);

        UserDto dto = UserDto.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .imageUrl(user.getImageUrl())
                .build();

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

}
