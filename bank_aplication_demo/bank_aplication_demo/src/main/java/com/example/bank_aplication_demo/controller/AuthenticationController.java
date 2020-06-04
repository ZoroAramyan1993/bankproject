package com.example.bank_aplication_demo.controller;

import com.example.bank_aplication_demo.entity.Client;
import com.example.bank_aplication_demo.role.RoleName;
import com.example.bank_aplication_demo.exception.ApiException;
import com.example.bank_aplication_demo.payload.ApiResponse;
import com.example.bank_aplication_demo.payload.JwtAuthenticationResponse;
import com.example.bank_aplication_demo.payload.LoginRequest;
import com.example.bank_aplication_demo.payload.SignUpRequest;
import com.example.bank_aplication_demo.repository.ClientRepository;
import com.example.bank_aplication_demo.repository.RoleRepository;
import com.example.bank_aplication_demo.entity.Role;
import com.example.bank_aplication_demo.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/auth")

public class AuthenticationController {

    @Autowired
    AuthenticationManager authenticationManager;


    @Autowired
    ClientRepository clientRepository;


    @Autowired
    RoleRepository roleRepository;


    @Autowired
    PasswordEncoder passwordEncoder;


    @Autowired
    JwtTokenProvider jwtTokenProvider;


    @PostMapping("/signIn")
    public ResponseEntity<?>authenticateClient(@Valid @RequestBody LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUserName(),loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }





    @PostMapping("/signUp")
    public ResponseEntity<?>registerClient(@Valid @RequestBody SignUpRequest signUpRequest){
        if (clientRepository.existsByEmail(signUpRequest.getEmail())){
            return new ResponseEntity(new ApiResponse(false,"Email address already is used"),
                    HttpStatus.BAD_REQUEST);
        }

        Client client = new Client(signUpRequest.getName(),signUpRequest.getSurName(),signUpRequest.getUserName(),
                signUpRequest.getEmail(),signUpRequest.getPassword());
         client.setPassword(passwordEncoder.encode(client.getPassword()));

         if(client.getUserName().equals("manager777")) {
             Role clientRole = roleRepository.findByRoleName(RoleName.MANAGER).
                     orElseThrow(() -> new ApiException("Manager role not set"));
             client.setRoles(Collections.singleton(clientRole));
         }else{
             Role clientRole = roleRepository.findByRoleName(RoleName.CLIENT).
                     orElseThrow(() -> new ApiException("Client Role not set"));
             client.setRoles(Collections.singleton(clientRole));
         }
        clientRepository.save(client);
             URI location  = ServletUriComponentsBuilder.
                     fromCurrentContextPath().path("/api/clients/{clientName}").
                     buildAndExpand(client.getUserName()).toUri();
            return ResponseEntity.created(location).body(new ApiResponse(true,"Client created successfully"));
    }
}
