package com.example.bank_aplication_demo.entity;


import com.example.bank_aplication_demo.audit.DateAudit;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "clients",uniqueConstraints = {
        @UniqueConstraint(columnNames = "userName"),
        @UniqueConstraint(columnNames = "email")
})
public class Client extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 35)
    private String name;

    @NotBlank
    @Size(max = 45)
    private String surName;


    @NotBlank
    @Size(max = 35)
    private String userName;


    @NaturalId
    @Email
    @NotBlank
//    @Size(max = 45)
    private String email;


    @NotBlank
    @Size(max = 150)
    private String password;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "client_roles",joinColumns = @JoinColumn(columnDefinition = "client_id"),inverseJoinColumns = @JoinColumn(columnDefinition = "role_id"))
     private Set<Role> roles = new HashSet<>();

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "client")
    private Cart cart;


    public Client(){

    }

public Client(String name,String surName,String userName,String email,String password){
    this.name = name;
    this.surName = surName;
    this.userName = userName;
    this.email = email;
    this.password = password;
}




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
