package com.texnologia_2022.test;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import com.texnologia_2022.dao.CourseRepo;
import com.texnologia_2022.entity.Course;

@SpringBootTest
@TestPropertySource(
		  locations = "classpath:application.properties")
public class DAOCourseTesting {
	@Autowired 
	CourseRepo courseDAO;
	
	public Course create_dummy_course(String id) {
		Course dummy = new Course();
		dummy.setcoursename("dummy_course");
		dummy.setusername("dummy_user");
		dummy.setcourseid(id);
		return dummy;
		
	}
	@Test
	void testFindByUserIdIsNotNull() {
		Assertions.assertNotNull(courseDAO);
	}
	
	@Test
	@Transactional
	@Rollback(true)
	void testFindByUserIdReturnsCourse() {
		Course dummy=create_dummy_course("myy");
		courseDAO.save(dummy);
		
		List<Course> storedcourses = courseDAO.findByUserid("dummy_user");
		Assertions.assertNotNull(storedcourses);
		Assertions.assertEquals(1, storedcourses.size());
		Assertions.assertEquals("dummy_course", storedcourses.get(0).getcoursename());
	}
	
	@Test
	@Transactional
	@Rollback(true)
	void testDeleteCourseFromId() {
		
		Course dummy=create_dummy_course("myy@1");
		Course dummy1=create_dummy_course("myy@2");
		courseDAO.save(dummy);
		courseDAO.save(dummy1);
		
		List<Course> storedcourses = courseDAO.findByUserid("dummy_user");
		long database=storedcourses.size();
		
		courseDAO.deleteCourseFromId(dummy1.getid());
		List<Course> deletedcourses = courseDAO.findByUserid("dummy_user");
		long del_database=deletedcourses.size();
		
		Assertions.assertEquals(database, del_database + 1);
	}
}
