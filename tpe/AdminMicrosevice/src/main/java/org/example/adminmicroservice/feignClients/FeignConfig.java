package org.example.adminmicroservice.feignClients;

import feign.codec.ErrorDecoder;
import org.example.adminmicroservice.decoders.CustomErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Bean
    public ErrorDecoder errorDecoder() {
        return new CustomErrorDecoder();
    }
}
