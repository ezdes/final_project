package com.example.project.Service;

import com.example.project.Entity.User;
import com.example.project.Exception.ResourceNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<User> getAllUsers();
    User getUser(Long id) throws ResourceNotFoundException;
    User createUser(User user) throws ResourceNotFoundException;
    User createAdmin(User user);
    void deleteUserById(Long id);
    User updateUserById(Long id, User user) throws ResourceNotFoundException;
}
