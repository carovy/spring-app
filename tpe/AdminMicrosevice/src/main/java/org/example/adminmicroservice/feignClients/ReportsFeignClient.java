package org.example.adminmicroservice.feignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;

@FeignClient(name="ReportsMicroservice", url="http://localhost:8010/reports")
public interface ReportsFeignClient {

    @GetMapping("/totalBilled/origen/{fechaOrigen}/fin/{fechaFin}")
    ResponseEntity<?> getTotalBilled(@PathVariable("fechaOrigen") String origin, @PathVariable("fechaFin") String end);

    @GetMapping("/activosVsMantenimiento")
    ResponseEntity<?> getReporteMonopatinesActivosVsMantenidos();
}