package com.example.full.controller;

import com.example.full.model.Invoice;
import com.example.full.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins= {"http://localhost:4200"})
@RequestMapping("/api")
public class InvoiceController {

    @Autowired
    private ClientService clientService;

    @Secured({"ROLE_ADMIN","ROLE_USER"})
    @GetMapping("/facturas/{id}")
    public ResponseEntity<?> show(@PathVariable Long id){
        return new ResponseEntity<Invoice>( clientService.findInvoiceById(id), HttpStatus.OK);
    }

    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("/facturas/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        clientService.deleteInvoiceById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @Secured({"ROLE_ADMIN"})
    @GetMapping("facturas/filtrar-productos/{term}")
    public ResponseEntity<?> filterProducts(@PathVariable String term){

        return new ResponseEntity<>(clientService.findProductByName(term),HttpStatus.OK);
    }

    @Secured({"ROLE_ADMIN"})
    @PostMapping("/facturas")
    public ResponseEntity<?> create(@RequestBody Invoice invoice){
        return new ResponseEntity<>(clientService.saveInvoice(invoice),HttpStatus.CREATED);
    }
}
