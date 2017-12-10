package com.spring.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.example.model.Wine;

public interface WineInterface extends JpaRepository<Wine, Long> {
	
	
}
