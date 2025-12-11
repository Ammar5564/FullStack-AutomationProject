package com.example.todobackend.todo.Controller;

import com.example.todobackend.todo.Dto.loginDto;

import com.example.todobackend.todo.model.user;
import com.example.todobackend.todo.respository.userRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Auth")
@CrossOrigin
public class AuthController {

    @Autowired
    private userRepo repo;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody loginDto dto) {
        user user = repo.findByUsername(dto.getUsername())
                .orElse(null);

        if (user == null) return ResponseEntity.status(401).build();
        if (!user.getPassword().equals(dto.getPassword())) return ResponseEntity.status(401).build();

        return ResponseEntity.ok().build();
    }
}
