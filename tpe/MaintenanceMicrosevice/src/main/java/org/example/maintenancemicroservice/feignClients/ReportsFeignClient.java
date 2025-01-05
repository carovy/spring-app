package org.example.maintenancemicroservice.feignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name="ReportsMicroservice", url="http://localhost:8010/reports")
public interface ReportsFeignClient {

    @GetMapping("/usoMonopatinesKm/pausa/{pausa}")
    ResponseEntity<?> getReporteUsoMonopatinKm(@PathVariable("pausa") boolean pausa);
}
