package com.example.project.Service;

import com.example.project.Entity.Role;
import com.example.project.Exception.ResourceNotFoundException;

import java.util.List;

public interface RoleService {
    List<Role> getAllRoles();
    Role getRole(Long id) throws ResourceNotFoundException;
    Role createRole(Role role);
    void deleteRoleById(Long id);
    Role updateRoleById(Long id, Role role) throws ResourceNotFoundException;
}
