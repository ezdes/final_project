package com.example.project.Controller;

import com.example.project.Entity.User;
import com.example.project.Exception.ResourceNotFoundException;
import com.example.project.Model.UserModel;
import com.example.project.Service.UserService;
import com.example.project.Util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/sign-up")
    public User createUser(@RequestBody User user) throws ResourceNotFoundException {
        return userService.createUser(user);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) throws ResourceNotFoundException {
        return userService.getUser(id);
    }

    @PutMapping("/{id}")
    public User updateUserById(@PathVariable Long id, @RequestBody User user) throws ResourceNotFoundException {
        return userService.updateUserById(id, user);
    }

    @PostMapping ("/sign-in")
    @ResponseBody
    public String generateToken(@RequestBody UserModel userModel) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userModel.getLogin(), userModel.getPassword())
            );
        } catch (Exception ex) {
            return "Invalid login/password";
        }
        return jwtUtil.generateToken(userModel.getLogin());
    }

    @DeleteMapping("/{id}")
    public void userDeleteById(@PathVariable Long id) {
        userService.deleteUserById(id);
    }
}
