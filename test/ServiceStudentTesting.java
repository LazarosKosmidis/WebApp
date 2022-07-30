package com.texnologia_2022.test;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import com.texnologia_2022.entity.Student;
import com.texnologia_2022.service.StudentService;


@SpringBootTest
@TestPropertySource(
		  locations = "classpath:application.properties")
public class ServiceStudentTesting {
	@Autowired 
	StudentService service;
	
	public Student create_dummy_student() {
		Student dummy = new Student();
		dummy.setfirst_name("dummy_student");
		dummy.setam((long) 1111);
		dummy.setcourse((long) 1);
		dummy.setfinal_grade((float) 10.0);
		return dummy;
		
	}
	@Test
	void testStudentServiceIsNotNull(){
		Assertions.assertNotNull(service);
	}
	
	@Test
	@Transactional
	@Rollback(true)
	void testFromCourseReturnsStudents() {
		Student dummy=create_dummy_student();
		service.save_student(dummy);
		List<Student> temp_students = service.from_course(1);
		Assertions.assertNotNull(temp_students);
		Assertions.assertEquals(1111,temp_students.get(temp_students.size()-1).getam());
	}
	
	@Test
	@Transactional
	@Rollback(true)
	void testListAllStudentsReturnsAllStudents() {
		List<Student> temp_students = service.listAllStudents();
		Assertions.assertNotNull(temp_students);
		Assertions.assertEquals(4119,temp_students.get(0).getam());
		
	}
	
	@Test
	@Transactional
	@Rollback(true)
	void testSaveStudent() {
		Student dummy=create_dummy_student();
		service.save_student(dummy);
		List<Student> temp_students = service.listAllStudents();
		Assertions.assertEquals(1111,temp_students.get(temp_students.size()-1).getam());
		
	}
		
	@Test
	@Transactional
	@Rollback(true)
	void testGetStudentReturnsStudent() {
		Student dummy=create_dummy_student();
		service.save_student(dummy);
		Student temp_student = service.get_student(dummy.getid());
		Assertions.assertEquals(1111,temp_student.getam());	
	}
     
	@Test
	@Transactional
	@Rollback(true)
	void testDeleteStudent() {
		Student dummy=create_dummy_student();
		service.save_student(dummy);
		List<Student> exp_students= service.listAllStudents();
		long database=exp_students.size();
		service.delete_student(dummy.getid());
		List<Student> del_students= service.listAllStudents();
		long database_after=del_students.size();
		Assertions.assertEquals(database,database_after+1);

	}
	
	@Test
	@Transactional
	@Rollback(true)
	void testGetGradesReturnsGrades() {
		Student dummy=create_dummy_student();
		service.save_student(dummy);
		List<Double> exp_grades= service.get_grades(1);
		Assertions.assertEquals(10.0,exp_grades.get(exp_grades.size()-1));	
	}
	@Test
	@Transactional
	@Rollback(true)
	void testCalculateFinalReturnsFinal() {
		Student dummy=create_dummy_student();
		dummy.setproject((long) 5);
		dummy.setf_exam((long) 5);
		service.save_student(dummy);
		service.calculate_final(dummy, (float) 0.5, (float) 0.5);
		Student dummy1 = service.get_student(dummy.getid());
		Assertions.assertEquals(5,dummy1.getfinal_grade());
				
	}

	@Test
	void testPerformOperation() {
		
		
	}

		
}
