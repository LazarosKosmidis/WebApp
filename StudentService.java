package com.texnologia_2022.service;

import java.util.List;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import com.texnologia_2022.entity.Student;

public interface StudentService {
	
	public List<Student> from_course(long id);
	
    public List<Student> listAllStudents();
     
    public void save_student(Student student);
     
    public Student get_student(long id);
     
    public void delete_student(long id);
    
    public List<Double> get_grades(long id);
    
    public void calculate_final(Student student,float project_p,float exam_p);
    
	public DescriptiveStatistics performOperation(List<Double> values);
}