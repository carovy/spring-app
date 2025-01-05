package org.example.usermicroservice.services;

import org.example.usermicroservice.entities.Role;
import org.example.usermicroservice.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    public List<Role> getAll(){
        return roleRepository.findAll();
    }

    public Role save(Role role){
        this.roleRepository.save(role);
        return role;
    }
    public void delete(Role role){

        roleRepository.delete(role);
    }

    public Role getRoleById(Long id){
        return roleRepository.findById(id).orElse(null);
    }
}
