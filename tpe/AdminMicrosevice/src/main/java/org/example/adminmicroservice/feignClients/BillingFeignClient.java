package org.example.adminmicroservice.feignClients;

import org.example.adminmicroservice.dtos.BillDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;

@FeignClient(name="BillingMicroservice", url="http://localhost:8007/bills")
public interface BillingFeignClient {
    @PostMapping
    ResponseEntity<?> setNewBill(@RequestBody BillDTO b);

    @GetMapping("/totalBilled/origen/{fechaOrigen}/fin/{fechaFin}")
    ResponseEntity<?> getTotalBilled(@PathVariable("fechaOrigen") LocalDate origin, @PathVariable("fechaFin") LocalDate end);

}