package org.example.adminmicroservice.feignClients;

import org.example.adminmicroservice.models.Account;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name="AccountsMicroservice", url="http://localhost:8011/accounts")
public interface AccountsFeignClient {

    @GetMapping
    ResponseEntity<List<Account>> getAllAccounts();

    @GetMapping("/{id}")
    ResponseEntity<Account> getAccountById(@PathVariable("id") Long id);

    @PostMapping
    ResponseEntity<Account> save(@RequestBody Account acc);

    @PutMapping("/null/{id_acc}")
    ResponseEntity<?> anullateAccount(@PathVariable("id_acc") int id_acc );
}
