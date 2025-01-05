package org.example.billingmicroservice.controllers;

import org.example.billingmicroservice.dtos.BillDTO;
import org.example.billingmicroservice.entities.Bill;
import org.example.billingmicroservice.services.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/bills")
public class BillController{
    @Autowired
    private BillService billService;

    @GetMapping //Andando
    public ResponseEntity<?> getAllBills(){
        ArrayList<Bill> bills = (ArrayList<Bill>) billService.getAll();
        return ResponseEntity.ok(bills);
    }

    @GetMapping("/totalBilled/origen/{fechaOrigen}/fin/{fechaFin}")
    public ResponseEntity<?> getTotalBilled(@PathVariable("fechaOrigen") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate origin, @PathVariable("fechaFin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end){
            double reporteTotalFacturadoEntreFechas = billService.getTotalBilled(origin, end);
            if (reporteTotalFacturadoEntreFechas != -1){
                return ResponseEntity.ok(reporteTotalFacturadoEntreFechas);
            }
            else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro una facturacion para las fechas solicitadas.");
            }
    }

    @PostMapping()
    public ResponseEntity<?> setNewBill(@RequestBody BillDTO bill){ //Definir precio y 3 F
        try{
            Bill b = billService.setNewBill(bill.getFechaInicioFacturacionNueva(), bill.getPrecioFijo(), bill.getPrecioExtra());
            if(b != null){
                return ResponseEntity.status(201).body(b);
            } else{
                return ResponseEntity.status(400).body("Fecha inválida");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Server Error");
        }
    }
}