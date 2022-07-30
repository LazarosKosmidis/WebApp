package com.texnologia_2022.service;

import java.util.List;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.texnologia_2022.dao.StudentRepo;
import com.texnologia_2022.entity.Student;

@Service
public class StudentServiceImpl implements StudentService {
	@Autowired
    private StudentRepo repo;
	
    public StudentServiceImpl() {
		super();
	}
	public List<Student> from_course(long id){
		return repo.findByCourse(id);
	} 
    public List<Student> listAllStudents() {
        return repo.findAll();
    }
     
    public void save_student(Student student) {
        repo.save(student);
    }
     
    public Student get_student(long id) {
        return repo.findById(id).get();
    }
     
    public void delete_student(long id) {
        repo.deleteById(id);
    }
    public List<Double> get_grades(long id) {
        return repo.find_grades(id);
    }
    public void calculate_final(Student student,float project_p,float exam_p) {
    	float grade = project_p*student.getproject() + exam_p*student.getf_exam();
    	student.setfinal_grade(grade);
        save_student(student);
    	
    }
	public DescriptiveStatistics performOperation(List<Double> values) {
		DescriptiveStatistics ds = new DescriptiveStatistics();
		values.forEach(ds::addValue);
		//int windowSize = ds.getWindowSize(); 
		//double max = ds.getMax();
		//double min = ds.getMin();
		//double mean = ds.getMean(); 
		//double median = ds.getPercentile(50);
		//double SD = ds.getStandardDeviation();
		//double variance = ds.getVariance();
		//double skewness = ds.getSkewness();
		//double kurtosis = ds.getKurtosis();
		return ds;
	}

}
