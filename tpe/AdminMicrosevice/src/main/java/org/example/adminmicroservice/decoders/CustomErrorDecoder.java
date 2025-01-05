package org.example.adminmicroservice.decoders;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;

public class CustomErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        // Si el código de estado es 400 (Bad Request), no lanzamos excepción
        if (response.status() == HttpStatus.BAD_REQUEST.value()) {
            // Devolvemos una excepción personalizada si se necesita
            return new BadRequestException("No existen viajes con esos parámetros");
        }
        // Si es otro tipo de error, lanzamos una excepción genérica
        return new Exception("Error no manejado: " + response.status());
    }
}
