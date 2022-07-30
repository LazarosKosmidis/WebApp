package com.texnologia_2022.entity;

import javax.persistence.*;



@Entity
@Table(name = "students")
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String first_name;
	   
	@Column
	private String second_name;
	
	@Column
	private String email;
	
	@Column
	private Long am;
	
	@Column
	private Long f_exam;
	
	@Column
	private Long project;
	
	@Column(nullable=false)
	private Long course;
	
	@Column
	private Float final_grade;

	@ManyToOne(fetch =FetchType.LAZY, optional =false)
	@JoinColumn(name="course",insertable=false, updatable=false)
	private Course courses;
	
	//getters
	public Long getid() {
		return id;
	}
	public String getfirst_name() {
		return first_name;
	}
	public String getsecond_name() {
		return second_name;
	}
	public Long getam() {
		return am;
	}
	public Long getcourse() {
		return course;
	}
	public Long getf_exam() {
		return f_exam;
	}
	public Long getproject() {
		return project;
	}
	public String getemail() {
		return email;
	}
	public Float getfinal_grade() {
		return final_grade;
	}
	
	//setters
	public void setid(Long inp) {
		this.id=inp;
	}
	public void setfirst_name(String inp) {
		this.first_name=inp;
	}
	public void setsecond_name(String inp) {
		this.second_name=inp;
	}
	public void setam(Long inp) {
		this.am=inp;
	}	
	public void setcourse(Long inp) {
		this.course=inp;
	}	
	public void setf_exam(Long inp) {
		this.f_exam=inp;
	}
	public void setproject(Long inp) {
		this.project=inp;
	}
	public void setemail(String inp) {
		this.email=inp;
	}
	public void setfinal_grade(Float inp) {
		this.final_grade=inp;
	}
}