package org.example.billingmicroservice.feignClient;

import org.example.billingmicroservice.dtos.ViajeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.List;

@FeignClient(name="ViajeMicroservice", url="http://localhost:8009/viaje")
public interface ViajeFeignClient {

    @GetMapping("/totalBilled/origen/{fechaOrigen}/fin/{fechaFin}")
    ResponseEntity<List<ViajeDTO>> getViajesBetween(@PathVariable("fechaOrigen") String d1, @PathVariable("fechaFin") String d2);

}
