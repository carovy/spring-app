package org.example.usermicroservice.controllers;

import org.example.usermicroservice.entities.Role;
import org.example.usermicroservice.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping //Andando
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> users = roleService.getAll();
        if (users.isEmpty()) {
            return  ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}") //Andando
    public ResponseEntity<Role> getRoleById(@PathVariable("id") Long id) {
        Role role = roleService.getRoleById(id);
        if (role == null) {
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(role);
    }

    @PostMapping
    public ResponseEntity<Role> save(@RequestBody Role user) {
        Role newRole = roleService.save(user);
        return ResponseEntity.ok(newRole);
    }
}
