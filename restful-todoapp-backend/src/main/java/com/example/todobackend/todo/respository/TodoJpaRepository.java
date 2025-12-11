package com.example.todobackend.todo.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.todobackend.todo.Todo;

public interface TodoJpaRepository extends JpaRepository<Todo, Integer> {
	

	List<Todo> findByUsername(String username);

}
