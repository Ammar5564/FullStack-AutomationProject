package com.example.todobackend.todo.respository;

import com.example.todobackend.todo.model.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface userRepo extends JpaRepository<user, Long> {
    Optional<user> findByUsername(String username);
}

