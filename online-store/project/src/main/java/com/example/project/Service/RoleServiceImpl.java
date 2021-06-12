package com.example.project.Service;

import com.example.project.Entity.Role;
import com.example.project.Exception.ResourceNotFoundException;
import com.example.project.Repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role getRole(Long id) throws ResourceNotFoundException {
        return roleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Could not find role with id ", id));
    }

    @Override
    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void deleteRoleById(Long id) {
        roleRepository.deleteById(id);
    }

    @Override
    public Role updateRoleById(Long id, Role role) throws ResourceNotFoundException {
        return roleRepository.findById(id)
                .map(newRole -> {
                    newRole.setName(role.getName());

                    return roleRepository.save(newRole);
                }).orElseThrow(() -> new ResourceNotFoundException("Could not find role with id ", id));
    }
}
