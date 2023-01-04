package com.example.full.controller;

import com.example.full.model.Client;
import com.example.full.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Timestamp;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins= {"http://localhost:4200"})
@RequestMapping("/api/clientes")
public class ClientController {

    @Autowired
    private static ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("")
    public List<Client> listClients(){
        return  clientService.findAll();
    }

    @GetMapping("/{id}")
    public Client readClient(@PathVariable Long id){
        return clientService.findById(id);
    }

    @PostMapping("")
    public Client create(@RequestBody Client client){
        client.setCreatedAt(Date.from(Instant.now()));
        return clientService.save(client);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        clientService.delete(id);
    }

    @PutMapping("{id}")
    public Client update(@RequestBody Client client, @PathVariable Long id){
        Client currentClient = clientService.findById(id);
        currentClient.setName(client.getName());
        currentClient.setEmail(client.getEmail());
        currentClient.setLastName(client.getLastName());

        return clientService.save(currentClient);
    }
}
