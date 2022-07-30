package com.texnologia_2022.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.texnologia_2022.dao.UserRepo;
import com.texnologia_2022.entity.User;

@Service
public class UserService implements UserDetailsService {
 
    @Autowired
    private UserRepo userRepo;
     
    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new CustomUserDetails(user);
    }
    public void save_user(User user) {
    	userRepo.save(user);
    }
    public void delete_user(Long id) {
    	userRepo.deleteById(id);
    }
    
}
