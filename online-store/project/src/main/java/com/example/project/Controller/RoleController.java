package com.example.project.Controller;

import com.example.project.Entity.Role;
import com.example.project.Exception.ResourceNotFoundException;
import com.example.project.Service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @PostMapping
    public Role createRole(@RequestBody Role role) {
        return roleService.createRole(role);
    }

    @GetMapping
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    @GetMapping("/{id}")
    public Role getRoleById(@PathVariable Long id) throws ResourceNotFoundException {
        return roleService.getRole(id);
    }

    @PutMapping("/{id}")
    public Role updateRoleById(@RequestBody Role role, @PathVariable Long id) throws ResourceNotFoundException {
        return roleService.updateRoleById(id, role);
    }

    @DeleteMapping("/{id}")
    public void deleteRoleById(@PathVariable Long id) {
        roleService.deleteRoleById(id);
    }

}
