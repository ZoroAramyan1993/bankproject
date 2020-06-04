package com.example.bank_aplication_demo.entity;

import com.example.bank_aplication_demo.security.UserPrincipal;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;


import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "carts")

public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;


    private Long cartNumber;


    private Long secretNumber;


   private Long account;

    private Long money;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY,optional = false)
            @JoinColumn(name = "client_id",nullable = false)
   private Client client;


    public Cart() {
    }

    public Cart(Long account, Long cardNumber, Long money, Long secretNumber) {
        this.account = account;
        this.cartNumber = cardNumber;
        this.money = money;
        this.secretNumber = secretNumber;
    }


    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public Long getCartNumber() {
        return cartNumber;
    }

    public void setCartNumber(Long cartNumber) {
        this.cartNumber = cartNumber;
    }

    public Long getSecretNumber() {
        return secretNumber;
    }

    public void setSecretNumber(Long secretNumber) {
        this.secretNumber = secretNumber;
    }

    public Client getClient() {
        return client;
    }

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
    }

    public Long getAccount() {
        return account;
    }

    public void setAccount(Long account) {
        this.account = account;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public boolean equals(Object o) {
        if (this==o)return true;
        if (o==null|| this.getClass()!=o.getClass())return false;
       Cart cart = (Cart)o;
        return Objects.equals(cartNumber,cart.cartNumber)&&Objects.equals(secretNumber,cart.secretNumber);
    }



}
