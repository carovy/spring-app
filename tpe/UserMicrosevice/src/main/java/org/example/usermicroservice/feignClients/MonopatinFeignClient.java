package org.example.usermicroservice.feignClients;

import org.example.usermicroservice.models.Monopatin;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;

@FeignClient(name="MonopatinMicroservice", url="http://localhost:8009/monopatin")
public interface MonopatinFeignClient {

    @GetMapping("/location/{posx}/{posy}")
    ResponseEntity<?> getClosestMonopatins(@PathVariable("posx") int posx, @PathVariable("posy") int posy);
}
