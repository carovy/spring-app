package org.example.reportsmicroservice.controllers;

import org.example.reportsmicroservice.entities.ReporteFacturacion;
import org.example.reportsmicroservice.entities.ReporteMonopatinesEstado;
import org.example.reportsmicroservice.entities.ReporteMonopatinesUso;
import org.example.reportsmicroservice.services.ReportsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

@RestController
@RequestMapping("/reports")
public class ReportsController {
    @Autowired
    private ReportsService reportService;

    @GetMapping("")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.status(200).body("Todo ok andando en reports");
    }

    @GetMapping("/totalBilled/origen/{fechaOrigen}/fin/{fechaFin}")
    public ResponseEntity<?> getTotalBilled(@PathVariable("fechaOrigen") String origin, @PathVariable("fechaFin") String end) {
            ReporteFacturacion reporteTotalFacturadoEntreFechas = reportService.getTotalBilled(origin, end);
            if (reporteTotalFacturadoEntreFechas == null) {
                return ResponseEntity.status(500).build();
            }
            return ResponseEntity.status(HttpStatus.OK).body(reporteTotalFacturadoEntreFechas);
    }

            //ANDA
    @GetMapping("/activosVsMantenimiento")
     public ResponseEntity<?> getReporteMonopatinesActivosVsMantenidos(){
         try{
             ReporteMonopatinesEstado reporte = reportService.getReporteMonopatinesEstado();
             String response = reporte.toString();
             return ResponseEntity.status(200).body(Collections.singletonMap("report", response));
         } catch (Exception e) {
             return ResponseEntity.internalServerError().build();
         }
     }

     //ANDAAA
     @GetMapping("/usoMonopatinesKm/pausa/{pausa}") //reporte de uso de monopatines con un max de km
     public ResponseEntity<?> getReporteUsoMonopatinKm(@PathVariable("pausa") boolean pausa){
         try{
              ArrayList<Object> reports = this.reportService.getReporteUsoMonopatinKm(pausa);
              if(reports == null){
                  return ResponseEntity.status(404).build();
              }
              return ResponseEntity.status(200).body(reports);
         } catch (Exception e) {
             return ResponseEntity.internalServerError().build();
         }
     }
}