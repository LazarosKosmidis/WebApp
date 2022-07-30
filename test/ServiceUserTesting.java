package com.texnologia_2022.test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import com.texnologia_2022.service.UserService;
import com.texnologia_2022.service.CustomUserDetails;
import com.texnologia_2022.entity.User;

@SpringBootTest
@TestPropertySource(
		  locations = "classpath:application.properties")
public class ServiceUserTesting {
	@Autowired 
	UserService service;
	
	public User create_dummy_user() {
		User dummy = new User();
		dummy.setUsername("dummy_user");
		dummy.setEmail("dummy@gmail.com");
		return dummy;
		
	}
	@Test
	void testLoadUserByUsernameIsNotNull() {
		Assertions.assertNotNull(service);
	}

	@Test
	@Transactional
	@Rollback(true)
	void testLoadUserByUsernameReturnsUser() {
		User dummy =create_dummy_user();
		service.save_user(dummy);
		CustomUserDetails temp_user = service.loadUserByUsername("dummy_user");
		Assertions.assertEquals("dummy@gmail.com", temp_user.getEmail());
	}

}
