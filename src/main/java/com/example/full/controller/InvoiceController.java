package com.example.full.controller;

import com.example.full.model.Invoice;
import com.example.full.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins= {"http://localhost:4200"})
@RequestMapping("/api")
public class InvoiceController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/facturas/{id}")
    public ResponseEntity<?> show(@PathVariable Long id){
        return new ResponseEntity<Invoice>( clientService.findInvoiceById(id), HttpStatus.OK);
    }

    @DeleteMapping("/facturas/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        clientService.deleteInvoiceById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
