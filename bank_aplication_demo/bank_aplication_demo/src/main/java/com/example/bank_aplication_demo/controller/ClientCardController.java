package com.example.bank_aplication_demo.controller;

import com.example.bank_aplication_demo.entity.Cart;
import com.example.bank_aplication_demo.entity.Client;
import com.example.bank_aplication_demo.exception.ResourceNotFoundException;
import com.example.bank_aplication_demo.payload.ClientCardRequest;
import com.example.bank_aplication_demo.repository.CartRepository;
import com.example.bank_aplication_demo.repository.ClientRepository;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("api/auth")

public class ClientCardController {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ClientRepository clientRepository;


    @GetMapping("/clients/{clientId}/cards")
    public Cart getCartFromClientId(@PathVariable(value = "clientId") Long clientId) {
        return cartRepository.findByClientId(clientId);
    }


    @GetMapping("/clients/{clientName}/cart")
    public ResponseEntity<Cart> getCartFromClientId(@PathVariable(value = "clientName") String clientName, @RequestParam(value = "cartNumber") Long cartNumber,
                                                    @RequestParam(value = "secretNumber") Long secretNumber) {
        Optional<Client> client = clientRepository.findByEmail(clientName);
        if (client.isPresent()) {
            Cart cart = cartRepository.findByClientId(client.get().getId());
            //  Cart cart1 = cartRepository.findCartBySecretNumberAndCartNumber(secretNumber, cartNumber);
            if (cart.getCartNumber().longValue() == cartNumber.longValue() && cart.getSecretNumber().longValue() == secretNumber.longValue()) {
                return ResponseEntity.ok(cart);
            }
        }
        return ResponseEntity.notFound().build();
    }


    @GetMapping("/clients/cart")
    public Cart getCartFromClientId(@RequestParam(value = "cartNumber") Long cartNumber,
                                    @RequestParam(value = "secretNumber") Long secretNumber) {
        // Cart cart =
        return cartRepository.findCartBySecretNumberAndCartNumber(secretNumber, cartNumber);
//        if (cart!=null) {
//            return ResponseEntity.ok(cart);
//        }
//        return ResponseEntity.notFound().build();
    }


    @PostMapping("/clients/{clientId}/cards")
    public Cart createCard(@PathVariable(value = "clientId") Long clientId,
                           @Valid @RequestBody
                                   Cart cartRequest) {
        return clientRepository.findById(clientId).map(client -> {
            cartRequest.setClient(client);
            return cartRepository.save(cartRequest);
        }).orElseThrow(() -> new ResourceNotFoundException("ClientId" + clientId));
    }






    @PutMapping("/clients/{clientId}/cards/{cardId}")
    public Cart updateCard(@PathVariable(value = "cardId") Long cardId,
                           @PathVariable("clientId") Long clientId,
                           @Valid @RequestBody ClientCardRequest clientCardRequest) {
        if (!clientRepository.existsById(clientId)) {
            throw new ResourceNotFoundException("client id" + clientId + "not found");
        }

        return cartRepository.findById(cardId).map(cart -> {
            cart.setAccount(clientCardRequest.getAccount());
            cart.setCartNumber(clientCardRequest.getCardNumber());
            cart.setSecretNumber(clientCardRequest.getCardNumber());
            return cartRepository.save(cart);
        }).orElseThrow(() -> new ResourceNotFoundException("cart id" + cardId + "not found"));
    }






    @GetMapping("clients/cards")
    public ResponseEntity<Cart> updateMoney(@RequestParam(value = "money") Long money,
                                            @RequestParam(value = "account") Long account,
                                            @RequestParam(value = "userName") String userName) throws Exception {



       Optional<Client> client = clientRepository.findByEmail(userName);

        Cart cart = cartRepository.findByClientId(client.get().getId());
        if (cart.getMoney() < money){
            throw new Exception("transaction failed");
        }
        cart.setMoney(cart.getMoney() - money);
        cartRepository.save(cart);


        Cart cart1 = cartRepository.findCartByAccount(account);
        cart1.setMoney(cart1.getMoney() + money);
        cartRepository.save(cart1);


        return ResponseEntity.ok().build();
    }



    }



