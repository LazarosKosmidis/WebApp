package com.texnologia_2022.entity;

import javax.persistence.*;


@Entity
@Table(name = "users")
public class User {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
     
    @Column
    private String email;
     
    @Column
    private String username;
    
    @Column
    private String password;
    
    
	//getters
    public Long getid() {
    	return id;
    }
    public String getEmail() {
    	return email;
    }
    public String getUsername() {
    	return username;
    }
    public String getPassword() {
    	return password;
    }
    
    //setters
    public void setEmail(String inp) {
    	this.email=inp;
    }
    public void setUsername(String inp) {
    	this.username=inp;
    }
    public void setPassword(String inp) {
    	this.password=inp;
    }
}
