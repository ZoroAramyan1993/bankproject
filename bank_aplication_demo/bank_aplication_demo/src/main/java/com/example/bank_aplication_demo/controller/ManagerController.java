package com.example.bank_aplication_demo.controller;

import com.example.bank_aplication_demo.entity.Cart;

import com.example.bank_aplication_demo.entity.Client;
import com.example.bank_aplication_demo.exception.ResourceNotFoundException;
import com.example.bank_aplication_demo.repository.CartRepository;
import com.example.bank_aplication_demo.repository.ClientRepository;
import com.example.bank_aplication_demo.repository.ManagerRepository;
import com.example.bank_aplication_demo.repository.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Optional;

@CrossOrigin(value = "*")
@RestController
@RequestMapping("api/auth/clients")
public class ManagerController {


    @Autowired
    ManagerRepository managerRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    CartRepository cartRepository;


    @Autowired
    ClientRepository clientRepository;





    @Transactional
    @PostMapping("/saveCards")
    public void insertWithQuery(@RequestBody Cart cart, @RequestParam("clientsId") Long clientsId) {
//        Client client = clientRepository.getOne(clientsId);
//        cart.setClient(client);
        cartRepository.insertCart( cart.getAccount(), cart.getCartNumber(),
                cart.getMoney(), cart.getSecretNumber(), clientsId);
    }

    @PutMapping("/updateCards")
    public ResponseEntity<Cart> updateCart(@RequestBody Cart cart, @RequestParam("clientsId") Long clientsId){

        Cart cart1 = cartRepository.findByClientId(clientsId);
        if (cart1!=null) {

            cart1.setAccount(cart.getAccount());

            cart1.setSecretNumber(cart.getSecretNumber());
            cart1.setMoney(cart.getMoney());
            cartRepository.save(cart1);
            return  ResponseEntity.ok(cart1);
        }

       return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/deleteCard")
      public ResponseEntity<?>deleteCard(@RequestParam("id") Long id){
         Cart cart = cartRepository.findByClientId(id);
         cartRepository.delete(cart);
         return ResponseEntity.ok().build();
      }
}