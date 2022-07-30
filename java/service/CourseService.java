package com.texnologia_2022.service;
 
import java.util.List;
import com.texnologia_2022.entity.Course;

public interface CourseService {
    
	public List<Course> from_user(String string);
     
    public List<Course> listAllCourse();
     
    public void save_course(Course course);
     
    public Course get_course(long id);
     
    public void delete_course(long id);
}