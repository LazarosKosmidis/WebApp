package com.texnologia_2022.test;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import com.texnologia_2022.entity.Course;
import com.texnologia_2022.service.CourseService;

@SpringBootTest
@TestPropertySource(
		  locations = "classpath:application.properties")
public class ServiceCourseTesting {

	@Autowired
	CourseService service;
	
	public Course create_dummy_course() {
		Course dummy = new Course();
		dummy.setcoursename("dummy_course");
		dummy.setusername("dummy_user");
		dummy.setcourseid("myy@test");
		return dummy;
		
	}
	
	@Test
	void testCourseServiceIsNotNull(){
		Assertions.assertNotNull(service);
	}
	@Test
	@Transactional
	@Rollback(true)
	void testFromUserReturnsCourse() {
		Course dummy=create_dummy_course();
		service.save_course(dummy);
		List<Course> exp_courses= service.from_user("dummy_user");
		Assertions.assertNotNull(exp_courses);
		Assertions.assertEquals("dummy_course", exp_courses.get(0).getcoursename());
	}
	
	@Test
	@Transactional
	@Rollback(true)
	void testListAllCoursesReturnsList() {
		Course dummy=create_dummy_course();
		service.save_course(dummy);
		List<Course> exp_courses= service.listAllCourse();
		Assertions.assertNotNull(exp_courses);
		Assertions.assertEquals("TestCourse1", exp_courses.get(0).getcoursename());
	}
	
	@Test
	@Transactional
	@Rollback(true)
	void testSaveCourse() {
		Course dummy=create_dummy_course();
		service.save_course(dummy);
		Course exp_course = service.get_course(dummy.getid());
		Assertions.assertEquals("dummy_course", exp_course.getcoursename());
	}
	
	@Test
	@Transactional
	@Rollback(true)
	void testGetCourseReturnsCourse() {
		Course dummy=create_dummy_course();
		service.save_course(dummy);
		Course exp_course = service.get_course(dummy.getid());
		Assertions.assertNotNull(exp_course);
		Assertions.assertEquals("dummy_course", exp_course.getcoursename());
		
	}
	
	@Test
	@Transactional
	@Rollback(true)
	void testDeleteCourse() {
		Course dummy=create_dummy_course();
		service.save_course(dummy);
		List<Course> exp_courses= service.listAllCourse();
		long database=exp_courses.size();
		service.delete_course(dummy.getid());
		List<Course> del_courses= service.listAllCourse();
		long database_after=del_courses.size();
		Assertions.assertEquals(database,database_after+1);
	}
}
