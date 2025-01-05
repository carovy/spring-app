package org.example.monopatinmicroservice.feignClients;

import org.example.monopatinmicroservice.entities.Monopatin;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@FeignClient(name="MaintenanceMicroservice", url="http://localhost:8008/mantenimiento")
public interface ManteinanceFeignClient {

    @GetMapping("/estado/{status}")
    ResponseEntity<?> getAllManteinanceByStatus(@PathVariable("status") String status);

    @PostMapping("/{id_monopatin}")
    ResponseEntity<?> saveManteinance(@PathVariable Long id_monopatin);

    @PutMapping("id/{id}/estado/{status}")
    ResponseEntity<?> updateStatus(@PathVariable("id") int id, @PathVariable String status);

    @GetMapping("/{id_monopatin}")
    ResponseEntity<?> getManteinanceByMonopatinId(@PathVariable Long id_monopatin);
}
