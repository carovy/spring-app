package org.example.adminmicroservice.feignClients;

import org.example.adminmicroservice.models.Account;
import org.example.adminmicroservice.models.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name="UserMicroservice", url="http://localhost:8011/users")
public interface UserFeignClient {

    @GetMapping
    ResponseEntity<List<User>> getAllUsers();

    @GetMapping("/role/{role}")
    ResponseEntity<?> getUsersByRole(@PathVariable("role") String role);

    @GetMapping("/{id}")
    ResponseEntity<User> getUserById(@PathVariable("id") Long id);

    @PostMapping
    ResponseEntity<User> save(@RequestBody User user);

}
