package com.example.project.Controller;

import com.example.project.Entity.User;
import com.example.project.Exception.JwtUserException;
import com.example.project.Exception.ResourceNotFoundException;
import com.example.project.Exception.UserAuthException;
import com.example.project.Model.UserModel;
import com.example.project.Repository.UserRepository;
import com.example.project.Service.UserService;
import com.example.project.Util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Encoder;

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
    @Autowired
    private UserRepository userRepository;

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
    public User generateToken(@RequestBody UserModel userModel) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userModel.getLogin(), userModel.getPassword())
            );

        } catch (Exception ex) {
            throw new UserAuthException("Invalid login or password");
        }
        User user = userRepository.findByLogin(userModel.getLogin());
        user.setToken(jwtUtil.generateToken(userModel.getLogin()));
        userService.updateUserById(user.getId(), user);
        return user;
    }

    @GetMapping("/jwtGetUser")
    public User getUserByJwt(@RequestHeader("jwt") String jwt) throws JwtUserException {
        User user = userRepository.findByToken(jwt);


        if (user == null) throw new JwtUserException("Couldn't find user with token: " + jwt);

        return user;
    }

    @DeleteMapping("/{id}")
    public void userDeleteById(@PathVariable Long id) {
        userService.deleteUserById(id);
    }
}
