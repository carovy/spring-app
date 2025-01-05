package org.example.reportsmicroservice.feignClients;

import org.springframework.cglib.core.Local;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.Date;

@FeignClient(name="BillingMicroservice", url="http://localhost:8007/bills")
public interface BillingFeignClient {

    @GetMapping("/totalBilled/origen/{fechaOrigen}/fin/{fechaFin}")
    ResponseEntity<?> getTotalBilled(@PathVariable("fechaOrigen") String d1, @PathVariable("fechaFin") String d2);

}