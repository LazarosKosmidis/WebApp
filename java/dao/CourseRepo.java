package com.texnologia_2022.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.texnologia_2022.entity.Course;

import java.util.List;


@Repository
public interface CourseRepo extends JpaRepository<Course, Long> {
	@Modifying  
	@Query("SELECT c FROM Course c WHERE c.username =?1")
	    public List<Course> findByUserid(String username);
	
	@Transactional
	@Modifying  
	@Query("DELETE  FROM Course c WHERE c.id = ?1")
	    public void deleteCourseFromId(@Param("id") Long id);
	
}
