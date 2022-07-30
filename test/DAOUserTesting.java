package com.texnologia_2022.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import com.texnologia_2022.dao.UserRepo;
import com.texnologia_2022.entity.User;

@SpringBootTest
@TestPropertySource(
		  locations = "classpath:application.properties")
public class DAOUserTesting {
	
	@Autowired 
	UserRepo userDAO;
	
	public User create_dummy_user() {
		User dummy = new User();
		dummy.setUsername("dummy_user");
		dummy.setEmail("dummy@gmail.com");
		return dummy;
		
	}
	
	@Test
	void testFindByIdlIsNotNull() {
		Assertions.assertNotNull(userDAO);
	}
	
	@Test
	@Transactional
	@Rollback(true)
	void testFindByIdReturnsUser() {
		User dummy =create_dummy_user();
		userDAO.save(dummy);
		User storeduser = userDAO.findByUsername("dummy_user");
		Assertions.assertNotNull(storeduser);
		Assertions.assertEquals("dummy@gmail.com", storeduser.getEmail());
	}
}
