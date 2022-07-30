package com.texnologia_2022.controller;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import com.texnologia_2022.entity.Course;
import com.texnologia_2022.entity.Student;
import com.texnologia_2022.entity.User;
import com.texnologia_2022.service.CourseService;
import com.texnologia_2022.service.StudentService;
import com.texnologia_2022.service.UserService;

import java.util.List;
import java.util.Optional;


@Controller
public class AppController {
	@Autowired
    private UserService user_service;
	@Autowired
    private CourseService course_service;
	@Autowired
	private StudentService student_service;
	
	@GetMapping("")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        
        return "login_page";
    }
    @GetMapping("/login_page")
    public String showRegistrationForm1(Model model) {
        model.addAttribute("user", new User());
         
        return "login_page";
    }
    @PostMapping("/process_register")
    public String processRegister(User user, Model model) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user_service.save_user(user);
        
        return "login_page";
    }	 
    @GetMapping("/home_page")
    public String listcourses(Model model) {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        List<Course> listcourses = course_service.from_user(name);
        model.addAttribute("listcourses", listcourses);
    
        return "home_page";
    }
    @GetMapping("/new_course")
    public String newCourse(Model model) {
    	
    	return "addcourse";
    }
    @PostMapping("/register_course")
    public String registerCourse(Course course) {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
    	course.setusername(name);
        course_service.save_course(course);
        
    	return "redirect:/home_page";
    }
    @RequestMapping("/delete_course/{id}")
    public String deleteCourse(@PathVariable(name = "id") Long id) {
    	course_service.delete_course(id);
    	
    	return "redirect:/home_page";
    }
    @RequestMapping("/edit_course/{id}")
    public ModelAndView showEditCoursePage(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("edit_course");
        Course course = course_service.get_course(id);
        mav.addObject("course", course);
        
        return mav;
    }
    @RequestMapping("/save_course/{id}")
    public String saveCourse(@ModelAttribute("course") Course course,@PathVariable(name = "id") Long id) {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
    	course.setusername(name);
    	course.setid(id);
    	course_service.save_course(course);   
    	
        return "redirect:/home_page";
    }
    
    @RequestMapping("/details/{id}")
    public String liststudents(Model model,@PathVariable(name = "id") Long id) {
        List<Student> liststudents = student_service.from_course(id);
        model.addAttribute("liststudents", liststudents);
        
        Course course=course_service.get_course(id);
        model.addAttribute("course",course);
        
        List<Double> grades=student_service.get_grades(id);
        DescriptiveStatistics ds=student_service.performOperation(grades);
        model.addAttribute("ds",ds);
        
        return "details";
    }
    @RequestMapping("/new_student{id}")
    public String newStudent(Model model,@PathVariable(name = "id") Long id) {
    	Course course=course_service.get_course(id);
    	model.addAttribute("course",course);
    	
    	return "addstudent";
    }
    @PostMapping("/register_student{c_id}")
    public String registerStudent(Student student,@PathVariable(name = "c_id") Long id) {
    	student.setcourse(id);
    	Course course=course_service.get_course(id);
    	student_service.calculate_final(student,course.getproject_per(),course.getexams_per());
    	
    	return "redirect:/details/"+ id;
    }
    @RequestMapping("/delete_student/{id}")
    public String deletestudent(@PathVariable(name = "id") Long id) {
    	Student student = student_service.get_student(id);
    	Long temp=student.getcourse();
    	student_service.delete_student(id);
    	
    	return "redirect:/details/"+ temp;
    }
    @RequestMapping("/edit_student/{id}")
    public ModelAndView showEditStudent(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("edit_student_p");
        Student student = student_service.get_student(id);
        mav.addObject("student", student);
        
        return mav;
    }
    @RequestMapping("/save_student/{id}")
    public String saveStudent(@ModelAttribute("student") Student student,@PathVariable(name = "id") Long id) {
    	Student student_temp=student_service.get_student(id);
    	long course_id=student_temp.getcourse();
    	student.setid(id);
    	student.setcourse(course_id);
    	Course course=course_service.get_course(course_id);
    	student_service.calculate_final(student,course.getproject_per(),course.getexams_per());  
    	
        return "redirect:/details/" + course_id;
    }
}
