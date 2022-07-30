package com.texnologia_2022.test;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import com.texnologia_2022.controller.AppController;
import com.texnologia_2022.dao.UserRepo;
import com.texnologia_2022.entity.Course;
import com.texnologia_2022.entity.Student;
import com.texnologia_2022.entity.User;
import com.texnologia_2022.service.CourseService;
import com.texnologia_2022.service.StudentService;

@SpringBootTest
@TestPropertySource(
		  locations = "classpath:application.properties")
@AutoConfigureMockMvc
public class AppControllerTesting {
	@Autowired
    private WebApplicationContext context;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired 
	private UserRepo userDAO;
	
	@Autowired
    private CourseService course_service;
	
	@Autowired
	private StudentService student_service;
	
	
	@Autowired
	AppController appController;
	
	@BeforeEach
    public void setup() {
		mockMvc = MockMvcBuilders
          .webAppContextSetup(context)
          .build();
    }
	
	@Test
	void testEmployeeControllerIsNotNull() {
		Assertions.assertNotNull(appController);
	}
	
	@Test
	void testMockMvcIsNotNull() {
		Assertions.assertNotNull(mockMvc);
	}
	
	@WithMockUser(value = "tester")
	@Test 
	void testShowRegistrationForm() throws Exception {
		mockMvc.perform(get("/")).
		andExpect(status().isOk()).
		andExpect(model().attributeExists("user")).
		andExpect(view().name("login_page"));
	}
		
	@WithMockUser(value = "tester")
	@Test 
	void testShowRegistrationForm1() throws Exception {
		mockMvc.perform(get("/login_page")).
		andExpect(status().isOk()).
		andExpect(model().attributeExists("user")).
		andExpect(view().name("login_page"));		
		
	}
	
	
	@WithMockUser(value = "tester")
	@Test 
	void testProcessRegister() throws Exception {
		User user = new User();
		user.setUsername("dummy_user_test");
		user.setEmail("dummy@gmail");
		user.setPassword("dummy");
        
		MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
		//multiValueMap.add("id", Long.toString(user.getid()));
		multiValueMap.add("username", user.getUsername());
	    multiValueMap.add("email", user.getEmail());
	    multiValueMap.add("password", user.getPassword());
	    
		mockMvc.perform(post("/process_register").
	    params(multiValueMap)).
		andExpect(status().isOk()).
		andExpect(view().name("login_page"));
		
		User temp =userDAO.findByUsername("dummy_user_test");
		long id = temp.getid();
		System.out.println(id);
		userDAO.deleteById(id);
	}
	@WithMockUser(value = "tester")
	@Test 
	void testListcourses() throws Exception {
		mockMvc.perform(get("/home_page")).
		andExpect(status().isOk()).
		andExpect(model().attributeExists("listcourses")).
		andExpect(view().name("home_page"));		
		
	}
	
	@WithMockUser(value = "tester")
	@Test 
	void testNewCourse() throws Exception {
		mockMvc.perform(get("/new_course")).
		andExpect(status().isOk()).
		andExpect(view().name("addcourse"));		
		
	}
	
	@WithMockUser(value = "tester")
	@Test 
	@Rollback(true)
	void testRegisterCourse() throws Exception {
		Course dummy = new Course();
		dummy.setcourseid("myy@dummy");
		MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
		multiValueMap.add("courseid", dummy.getcourseid());
		mockMvc.perform(post("/register_course").
		params(multiValueMap)).
		andExpect(status().isFound()).
		andExpect(view().name("redirect:/home_page"));	
		List<Course> dummy_c = course_service.from_user("tester");
		course_service.delete_course(dummy_c.get(0).getid());
		
	}
	
	@WithMockUser(value = "tester")
	@Test 
	void testDeleteCourse() throws Exception {
		Course dummy = new Course();
		dummy.setcourseid("myy@dummy");
		course_service.save_course(dummy);
		mockMvc.perform(post("/delete_course/{id}",dummy.getid())).
		andExpect(status().isFound()).
		andExpect(view().name("redirect:/home_page"));	
		
	}
	
	@WithMockUser(value = "tester")
	@Test 
	void testEditCourse() throws Exception {
		mockMvc.perform(get("/edit_course/{id}",1)).
		andExpect(status().isOk()).
		andExpect(model().attributeExists("course")).
		andExpect(view().name("edit_course"));
	}
	@WithMockUser(value = "tester")
	@Test 
	void testSaveCourse() throws Exception {
		Course dummy = new Course();
		dummy.setcourseid("myy@dummy");
		course_service.save_course(dummy);
		
		Course dummy1 = new Course();
		dummy1.setcourseid("myy@dummy");
		
		
		MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
		multiValueMap.add("courseid", dummy.getcourseid());
		mockMvc.perform(post("/save_course/{id}",dummy.getid()).
		params(multiValueMap)).
		andExpect(status().isFound()).
		andExpect(view().name("redirect:/home_page"));	
		course_service.delete_course(dummy.getid());
	}
	@WithMockUser(value = "tester")
	@Test 
	void testListStudents() throws Exception {
		mockMvc.perform(get("/details/{id}",1)).
		andExpect(status().isOk()).
		andExpect(model().attributeExists("liststudents","course","ds")).
		andExpect(view().name("details"));		
	}
	
	@WithMockUser(value = "tester")
	@Test 
	void testNewStudent() throws Exception {
		mockMvc.perform(get("/new_student{id}",1)).
		andExpect(status().isOk()).
		andExpect(model().attributeExists("course")).
		andExpect(view().name("addstudent"));	
	}
	
	@WithMockUser(value = "tester")
	@Test 
	void testRegisterStudent() throws Exception {
		Student dummy = new Student();
		dummy.setam((long)9999);
		dummy.setproject((long)5);
		dummy.setf_exam((long)5);
		MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
		multiValueMap.add("am", Long.toString(dummy.getam()));
		multiValueMap.add("project", Long.toString(dummy.getproject()));
		multiValueMap.add("f_exam", Long.toString(dummy.getf_exam()));
		mockMvc.perform(post("/register_student{id}",1).
		params(multiValueMap)).
		andExpect(status().isFound()).
		andExpect(view().name("redirect:/details/"+ 1));
		List<Student> students=student_service.listAllStudents();
		student_service.delete_student(students.get(students.size()-1).getid());
	}
	
	@WithMockUser(value = "tester")
	@Test 
	void testDeleteStudent() throws Exception {
		Student dummy = new Student();
		dummy.setam((long) 1111);
		dummy.setcourse((long)1);
		student_service.save_student(dummy);
		
		mockMvc.perform(post("/delete_student/{id}",dummy.getid())).
		andExpect(status().isFound()).
		andExpect(view().name("redirect:/details/"+ 1));
	}
	
	@WithMockUser(value = "tester")
	@Test 
	void testShowEditStudent() throws Exception {
		mockMvc.perform(get("/edit_student/{id}",1)).
		andExpect(status().isOk()).
		andExpect(model().attributeExists("student")).
		andExpect(view().name("edit_student_p"));
	}
	
	@WithMockUser(value = "tester")
	@Test 
	void testSaveStudent() throws Exception {
		Student dummy = new Student();
		dummy.setam((long)9999);
		dummy.setcourse((long)1);
		dummy.setproject((long)5);
		dummy.setf_exam((long)5);
		student_service.save_student(dummy);
		
		Student dummy1 = new Student();
		dummy1.setam((long)9999);
		dummy.setproject((long)5);
		dummy.setf_exam((long)5);
		
		MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
		multiValueMap.add("am", Long.toString(dummy1.getam()));
		multiValueMap.add("project", Long.toString(dummy.getproject()));
		multiValueMap.add("f_exam", Long.toString(dummy.getf_exam()));
		
		mockMvc.perform(post("/save_student/{id}",dummy.getid()).
		params(multiValueMap)).
		andExpect(status().isFound()).
		andExpect(view().name("redirect:/details/" + 1));
		student_service.delete_student(dummy.getid());

	}
	
}
