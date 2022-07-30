package com.texnologia_2022.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.texnologia_2022.entity.Student;

public interface StudentRepo extends JpaRepository<Student, Long> {
	@Modifying  
	@Query("SELECT s FROM Student s WHERE s.course =?1")
	    public List<Student> findByCourse(Long courseid);
	
	@Modifying  
	@Query("SELECT final_grade FROM Student students WHERE course=?1")
	    public List<Double> find_grades(long id);
}
