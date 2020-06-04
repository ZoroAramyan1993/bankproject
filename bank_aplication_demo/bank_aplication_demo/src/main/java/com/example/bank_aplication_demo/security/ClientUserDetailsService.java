package com.example.bank_aplication_demo.security;

import com.example.bank_aplication_demo.entity.Client;
import com.example.bank_aplication_demo.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
public class ClientUserDetailsService implements UserDetailsService {

    @Autowired
    ClientRepository clientRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userNameOrEmail) throws UsernameNotFoundException {
         Client client = clientRepository.
                 findByUserNameOrEmail(userNameOrEmail,
                         userNameOrEmail).
                 orElseThrow(()->
                         new UsernameNotFoundException("not found width userName or email," + userNameOrEmail));
         return UserPrincipal.createClient(client);
    }


    @Transactional
    public UserDetails finById(Long id){
        Client client = clientRepository.findById(id).
                orElseThrow(()->new UsernameNotFoundException("not found width id" + id));
        return UserPrincipal.createClient(client);
    }
}
