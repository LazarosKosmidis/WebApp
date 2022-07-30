package com.texnologia_2022.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.texnologia_2022.entity.User;

public interface UserRepo extends JpaRepository<User, Long> {
	 @Query("SELECT u FROM User u WHERE u.username = ?1")
	    public User findByUsername(String username); 
}
