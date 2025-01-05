package org.example.adminmicroservice.services;

import feign.FeignException;
import org.example.adminmicroservice.dtos.BillDTO;
import org.example.adminmicroservice.feignClients.*;
import org.example.adminmicroservice.models.Account;
import org.example.adminmicroservice.models.Monopatin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service("AdminService")
public class AdminService {
    @Autowired
    UserFeignClient usersFeignClient;
    @Autowired
    MonopatinFeignClient monopatinFeignClient;
    @Autowired
    ReportsFeignClient reportsFeignClient;
    @Autowired
    BillingFeignClient billingFeignClient;
    @Autowired
    AccountsFeignClient accountsFeignClient;

    public ResponseEntity<?> getMonopatinesPorViajesPorAnio(Integer anio, Integer xViajes) {
        return this.monopatinFeignClient.getMonopatinesPorViajesPorAnio(anio, xViajes);
    }

    public ResponseEntity<?> anullateAccount(Integer id){
        return this.accountsFeignClient.anullateAccount(id);
    }

    public Object getTotalBilled(String o, String e){
        LocalDate origin = LocalDate.parse(o);
        LocalDate end = LocalDate.parse(e);
        if (origin.isAfter(end)) { //pasar al controller
            return null;
        }
        System.out.println("hola??");
        return this.reportsFeignClient.getTotalBilled(o, e).getBody();
    }

    public ResponseEntity<?> getUsersByRole(String r){
        if(!Objects.equals(r, "admin") && !Objects.equals(r, "usuario") && !Objects.equals(r, "mantenimiento")){
            return ResponseEntity.badRequest().body("Invalid role");
        }
        return this.usersFeignClient.getUsersByRole(r);
    }

    public ResponseEntity<?> getReporteMonopatinesSegunEstado(){
        return this.reportsFeignClient.getReporteMonopatinesActivosVsMantenidos();
    }

    public ResponseEntity<?> setNewBill(BillDTO b){
        ResponseEntity<?> response = this.billingFeignClient.setNewBill(b);
        try {
            if (Objects.equals(response.getStatusCode().toString(), "201 CREATED")){
                return response;
            } else{
                return ResponseEntity.status(404).body("Wasn't possible to set a new bill, wrong date");
            }
        } catch (FeignException.FeignClientException exception){
            throw new RuntimeException("Fallo al comunicarse con el otro servicio: " + exception.getMessage(), exception);
        }
    }
}
