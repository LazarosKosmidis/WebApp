package com.texnologia_2022.test;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import com.texnologia_2022.dao.StudentRepo;
import com.texnologia_2022.entity.Student;

@SpringBootTest
@TestPropertySource(
		  locations = "classpath:application.properties")
public class DAOStudentTesting {
	@Autowired 
	StudentRepo studentDAO;
	
	public Student create_dummy_student() {
		Student dummy = new Student();
		dummy.setfirst_name("dummy_student");
		dummy.setam((long) 1111);
		dummy.setcourse((long) 1);
		dummy.setfinal_grade((float) 10.0);
		return dummy;
		
	}
	
	@Test
	void testFindByCourseIsNotNull() {
		Assertions.assertNotNull(studentDAO);
	}
	
	@Test
	@Transactional
	@Rollback(true)
	void testFindByCourseReturnsStudent() {
		Student dummy=create_dummy_student();
		studentDAO.save(dummy);
		
		List<Student> temp_students = studentDAO.findByCourse((long) 1);
		Assertions.assertNotNull(temp_students);
		Assertions.assertEquals(1111,temp_students.get(temp_students.size()-1).getam());
	}
	
	@Test
	@Transactional
	@Rollback(true)
	void testFind_gradesReturnsGrade() {
		Student dummy=create_dummy_student();
		studentDAO.save(dummy);
		List<Double> temp_grades = studentDAO.find_grades(1);
		Assertions.assertNotNull(temp_grades);
		Assertions.assertEquals(10.0,temp_grades.get(temp_grades.size()-1));
	}
}
