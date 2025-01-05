package org.example.reportsmicroservice.services;

import feign.FeignException;
import org.example.reportsmicroservice.dtos.MonopatinKmDTO;
import org.example.reportsmicroservice.entities.ReporteFacturacion;
import org.example.reportsmicroservice.entities.ReporteMonopatinesEstado;
import org.example.reportsmicroservice.entities.ReporteMonopatinesSinPausa;
import org.example.reportsmicroservice.entities.ReporteMonopatinesUso;
import org.example.reportsmicroservice.feignClients.BillingFeignClient;
import org.example.reportsmicroservice.feignClients.MonopatinFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ReportsService {
    @Autowired
    BillingFeignClient billingFeignClient;
    @Autowired
    MonopatinFeignClient monopatinFeignClient;


    public ReporteFacturacion getTotalBilled(String origin, String end){
        try {
            ResponseEntity<?> response = this.billingFeignClient.getTotalBilled(origin, end);
            Double totalBilled = (Double) response.getBody();
            return new ReporteFacturacion("Reporte de facturacion", "Ganancias hechas en los viajes entre fechas.", totalBilled, origin, end);
        } catch (Exception e) {
            return null;
        }
    }

    public ReporteMonopatinesEstado getReporteMonopatinesEstado(){
        ResponseEntity<?> responseActivos = this.monopatinFeignClient.getMonopatinesActivos();
        ResponseEntity<?> responseMantenim = this.monopatinFeignClient.getMonopatinesEnMantenimiento();
        try {
            if (Objects.equals(responseActivos.getStatusCode().toString(), "200 OK") && Objects.equals(responseMantenim.getStatusCode().toString(), "200 OK")){
                ArrayList<Object> mantenimiento = (ArrayList<Object>) responseMantenim.getBody();
                ArrayList<Object> activos = (ArrayList<Object>) responseActivos.getBody();
                assert activos != null;
                assert mantenimiento != null;
                return new ReporteMonopatinesEstado(activos.size(), mantenimiento.size());
            } else {
                // Manejar una respuesta no exitosa
                throw new IllegalStateException("Error al llamar al otro servicio");
            }
        } catch (FeignException.FeignClientException exception){
            throw new RuntimeException("Fallo al comunicarse con el otro servicio: " + exception.getMessage(), exception);
        }
    }

    public ArrayList<Object> getReporteUsoMonopatinKm(boolean pausa){
        try {
            ResponseEntity<ArrayList<MonopatinKmDTO>> result = this.monopatinFeignClient.getMonopatinesPorKM(pausa);
            if (Objects.equals(result.getStatusCode().toString(), "200 OK")){
                ArrayList<MonopatinKmDTO> listaMonopatinesPorKm = (ArrayList<MonopatinKmDTO>) result.getBody();
                ArrayList<Object> listaReportes = new ArrayList<>();
                if(pausa){
                    assert listaMonopatinesPorKm != null;
                    for(MonopatinKmDTO monopatinKmDTO : listaMonopatinesPorKm) {
                        Long id = monopatinKmDTO.getId();
                        Float kms = monopatinKmDTO.getKmRecorridos();
                        Integer tiempo = monopatinKmDTO.getTiempoUso();
                        Integer pausas = monopatinKmDTO.getTiempoPausas();
                        listaReportes.add(new ReporteMonopatinesUso(id, kms, tiempo, pausas));
                    }
                    return listaReportes;
                }else{
                    System.out.println("acá debería");
                    assert listaMonopatinesPorKm != null;
                    for(MonopatinKmDTO monopatinKmDTO : listaMonopatinesPorKm){
                        System.out.println("entré acá?");
                        Long id = monopatinKmDTO.getId();
                        Float kms = monopatinKmDTO.getKmRecorridos();
                        Integer tiempo = monopatinKmDTO.getTiempoUso();
                        listaReportes.add(new ReporteMonopatinesSinPausa(id, kms, tiempo));
                    }
                    return listaReportes;
                }
            } else {
                return null;
            }
        } catch (FeignException.FeignClientException exception){
            throw new RuntimeException("Fallo al comunicarse con el otro servicio: " + exception.getMessage(), exception);
        }
    }
}