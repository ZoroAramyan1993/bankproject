package com.example.bank_aplication_demo.payload;

import com.sun.istack.NotNull;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SignUpRequest {
    @NotBlank
    @Size(min = 3,max = 17)
    private String name;

    @NotBlank
    @Size(min = 3,max = 18)
    private String surName;

    @NotBlank
    @Size(min = 3,max = 12)
    private String userName;

    @NotBlank
    @Size(max = 35)
    @Email
    private String email;

    @NotBlank
    @Size(min = 8,max = 15)
    private String password;

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
}
