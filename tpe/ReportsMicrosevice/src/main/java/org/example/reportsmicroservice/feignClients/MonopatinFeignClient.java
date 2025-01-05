package org.example.reportsmicroservice.feignClients;

import org.example.reportsmicroservice.dtos.MonopatinKmDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;

@FeignClient(name="MonopatinMicroservice", url="http://localhost:8009/monopatin")
public interface MonopatinFeignClient {

   @GetMapping("/activos")
   ResponseEntity<?> getMonopatinesActivos();
   @GetMapping("/mantenimiento")
   ResponseEntity<?> getMonopatinesEnMantenimiento();

   @GetMapping("/tiemposDePausa/{pausa}")
   ResponseEntity<ArrayList<MonopatinKmDTO>> getMonopatinesPorKM(@PathVariable("pausa") boolean pausa);
}
