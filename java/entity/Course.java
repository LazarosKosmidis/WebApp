package com.texnologia_2022.entity;

import java.util.Set;

import javax.persistence.*;



@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String courseid;
     
    @Column
    private String coursename;
    
    @Column
    private String syllabus;
    
    @Column
    private String year;
    
    @Column
    private String semester;
    
    @Column
    private String username;
    
    @Column
    private Float project_per;
    
    @Column
    private Float exams_per;
    
	@OneToMany(mappedBy="course",fetch=FetchType.LAZY,
			cascade =CascadeType.ALL)
	private Set<Student> student;
    //getters
    public Long getid() {
    	return id;
    }
    public String getcourseid() {
    	return courseid;
    }
    public String getcoursename() {
    	return coursename;
    }
    public String getsyllabus() {
    	return syllabus;
    }
    public String getyear() {
    	return year;
    }
    public String getsemester() {
    	return semester;
    }
    public String getusername() {
    	return username;
    }
    public Float getproject_per() {
    	return project_per;
    }
    public Float getexams_per() {
    	return exams_per;
    }
    
    //setters
    public void setid(Long inp) {
    	this.id=inp;
    }
    public void setcourseid(String inp) {
    	this.courseid=inp;
    }
    public void setcoursename(String inp) {
    	this.coursename=inp;
    }
    public void setsyllabus(String inp) {
    	this.syllabus=inp;
    }
    public void setyear(String inp) {
    	this.year=inp;
    }
    public void setsemester(String inp) {
    	this.semester=inp;
    }
    public void setusername(String inp) {
    	this.username=inp;
    }
    public void setproject_per(Float inp) {
    	this.project_per=inp;
    }
    public void setexams_per(Float inp) {
    	this.exams_per=inp;
    }
}
