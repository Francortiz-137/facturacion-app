package com.example.full.controller;

import com.example.full.model.Client;
import com.example.full.model.Region;
import com.example.full.service.ClientService;
import com.example.full.service.IUploadFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.net.MalformedURLException;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins= {"http://localhost:4200"})
@RequestMapping("/api/clientes")
public class ClientController {

    @Autowired
    private static ClientService clientService;

    @Autowired
    private IUploadFileService uploadService;

    private final Logger log = LoggerFactory.getLogger(ClientController.class);

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("")
    public List<Client> listClients(){
        return  clientService.findAll();
    }

    @GetMapping("/page/{page}")
    public Page<Client> pageClients(@PathVariable int page){
        return  clientService.findAll(PageRequest.of(page, 4));
    }


    @Secured({"ROLE_ADMIN","ROLE_USER"})
    @GetMapping("/{id}")
    public ResponseEntity<?> readClient(@PathVariable Long id){
        Client client = null;
        Map<String,Object> response = new HashMap<>();
        try {
            client = clientService.findById(id);
        } catch(DataAccessException e){
            response.put("mensaje","error al realizar consulta en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

            if (client == null) {
                response.put("mensaje","el cliente id: ".concat(id.toString()).concat(" no existe en la base de datos"));
                return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
            }

        return new ResponseEntity<Client>(client, HttpStatus.OK);
    }

    @Secured({"ROLE_ADMIN"})
    @PostMapping("")
    public ResponseEntity<?> create(@Valid @RequestBody Client client, BindingResult result){
        Client newClient = null;
        Map<String,Object> response = new HashMap<>();

        if (result.hasErrors()){
            List<String> errors = result.getFieldErrors().stream()
                    .map(err ->  "El campo '" + err.getField() + "' "+ err.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("errors",errors);
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            newClient = clientService.save(client);
        }
        catch(DataAccessException e){
            response.put("mensaje","error al realizar el insert en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        client.setCreatedAt(Date.from(Instant.now()));
        response.put("mensaje","El cliente ha sido creado con exito");
        response.put("cliente",newClient);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
    }
    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Map<String,Object> response = new HashMap<>();
        try {
            Client client = clientService.findById(id);
            String oldImgName = client.getImg();
            uploadService.delete(oldImgName);

            clientService.delete(id);
        }catch(DataAccessException e){
            response.put("mensaje","error al eliminar al cliente de la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "el cliente ha sido eliminado con exito");
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
    }
    @Secured({"ROLE_ADMIN"})
    @PutMapping("{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Client client, BindingResult result ,@PathVariable Long id){
        Client currentClient = clientService.findById(id);
        Client updatedClient = null;
        Map<String,Object> response = new HashMap<>();

        if (result.hasErrors()){
            List<String> errors = result.getFieldErrors().stream()
                    .map(err ->  "El campo '" + err.getField() + "' "+ err.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("errors",errors);
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.BAD_REQUEST);
        }

        if (currentClient == null) {
            response.put("mensaje","Error: no se pudo editar, el cliente id: ".concat(id.toString()).concat(" no existe en la base de datos"));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
        }

        try {
            currentClient.setName(client.getName());
            currentClient.setEmail(client.getEmail());
            currentClient.setLastName(client.getLastName());
            currentClient.setRegion(client.getRegion());
            updatedClient = clientService.save(currentClient);
        }catch(DataAccessException e){
            response.put("mensaje","error al actualizar el cliente en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje","El cliente ha sido actualizado con exito");
        response.put("cliente",updatedClient);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
    }

    @Secured({"ROLE_ADMIN","ROLE_USER"})
    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile file, @RequestParam("id") Long id){

        Map<String,Object> response = new HashMap<>();


        Client client = clientService.findById(id);

        if(!file.isEmpty()){
            String fileName = null;

            try {
              fileName =  uploadService.save(file);
            } catch (IOException e) {
                response.put("mensaje","error al subir la imagen");
                response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
                return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            String oldImgName = client.getImg();
            uploadService.delete(oldImgName);

            client.setImg(fileName);

            clientService.save(client);

            response.put("cliente", client);
            response.put("mensaje", "Has subido correctamente la imagen: " + fileName);


        }

        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
    }

    @GetMapping("/uploads/img/{imgName:.+}")
    public ResponseEntity<Resource> showImg(@PathVariable String imgName){

        Resource resource = null;
        try {
            resource = uploadService.upload(imgName);
        }catch(MalformedURLException e) {
            e.printStackTrace();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+ resource.getFilename()+"\"");

        return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
    }
    @Secured({"ROLE_ADMIN"})
    @GetMapping("/regiones")
    public List<Region> listRegions(){
        return  clientService.findAllRegiones();
    }


}
