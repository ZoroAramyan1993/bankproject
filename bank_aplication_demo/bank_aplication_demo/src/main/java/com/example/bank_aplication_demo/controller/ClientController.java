package com.example.bank_aplication_demo.controller;

import com.example.bank_aplication_demo.entity.Client;
import com.example.bank_aplication_demo.exception.ResourceNotFoundException;
import com.example.bank_aplication_demo.payload.ClientRequest;
import com.example.bank_aplication_demo.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class ClientController {
    @Autowired
  private ClientRepository clientRepository;

    @GetMapping("/clients")
    public Page<Client>getAllClients(Pageable pageable){
        return clientRepository.findAll(pageable);
    }


    @PutMapping("api/clients/{id}")
    public Client updateClient(@PathVariable Long id, ClientRequest clientRequest){
        return clientRepository.findById(id).map(client -> {
            client.setName(clientRequest.getName());
            client.setSurName(clientRequest.getSurName());
            return clientRepository.save(client);
        }).orElseThrow(()->new ResourceNotFoundException("ClientId" + id + "not found"));
    }


    @DeleteMapping("clients/id")
   public ResponseEntity<?>deleteClient(@PathVariable Long id){
        return clientRepository.findById(id).map(client->{
            clientRepository.delete(client);
            return ResponseEntity.ok().build();
        }).orElseThrow(()->new ResourceNotFoundException("clientId" + id + "not found"));
   }
    @GetMapping("api/clients/")
    public ResponseEntity<Client>getClient(@RequestParam String username){
        Optional<Client> client = clientRepository.findByName(username);
        if(client.isPresent()){
            return ResponseEntity.ok(client.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}
