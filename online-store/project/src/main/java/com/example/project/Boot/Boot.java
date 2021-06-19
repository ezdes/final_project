package com.example.project.Boot;

import com.example.project.Entity.*;
import com.example.project.Repository.RoleRepository;
import com.example.project.Repository.UserRepository;
import com.example.project.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;


@Component
public class Boot implements CommandLineRunner {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {

        String nameUser = "ROLE_USER";
        String nameAdmin = "ROLE_ADMIN";


        if (roleRepository.findByName(nameAdmin) == null) {
            roleService.createRole(Role.builder().name("ROLE_ADMIN").build());
        }

        if (roleRepository.findByName(nameUser) == null) {
            roleService.createRole(Role.builder().name("ROLE_USER").build());
        }

//        Role roleAdmin = roleService.createRole(Role.builder().name("ROLE_ADMIN").build());
//        roleService.createRole(Role.builder().name("ROLE_USER").build());

        String loginUser = "user";
        String loginAdmin = "admin";

        if (userRepository.findByLogin(loginAdmin) == null) {
            userService.createAdmin(User.builder().login("admin").fullName("Admin").email("admin@gmail.com").
                    password(passwordEncoder.encode("admin123")).status(1).build());
        }

        if (userRepository.findByLogin(loginUser) == null) {
            userService.createUser(User.builder().email("user@gmail.com").fullName("User").login("user").
                    password("user123").status(1).build());
        }


//        userService.createUser(User.builder().email("user@gmail.com").fullName("User").login("user").
//                password("user123").status(1).build());
//        userService.createAdmin(User.builder().login("admin").fullName("Admin").email("admin@gmail.com").
//                password(passwordEncoder.encode("admin123")).roles(Collections.singleton(roleAdmin)).status(1).build());

    }
}
