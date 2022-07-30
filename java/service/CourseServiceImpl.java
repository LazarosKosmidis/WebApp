package com.texnologia_2022.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.texnologia_2022.dao.CourseRepo;
import com.texnologia_2022.entity.Course;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepo repo;
    
    public CourseServiceImpl() {
		super();
	}
    
    @Override
	@Transactional
	public List<Course> from_user(String string){
		return repo.findByUserid(string);
	} 
    
    @Override
	@Transactional
    public List<Course> listAllCourse() {
        return repo.findAll();
    }
    
    @Override
	@Transactional
    public void save_course(Course course) {
        repo.save(course);
    }
    
    @Override
	@Transactional
    public Course get_course(long id) {
        return repo.findById(id).get();
    }
    
    @Override
	@Transactional
    public void delete_course(long id) {
        repo.deleteById(id);
    }
}