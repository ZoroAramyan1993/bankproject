package com.example.bank_aplication_demo.repository;

import com.example.bank_aplication_demo.entity.Cart;
import com.example.bank_aplication_demo.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {
   Optional<Client> findByEmail(String email);
    Optional<Client>findByUserNameOrEmail(String userName,String email);
    List<Client> findByIdIn(List<Long>clientIds);
    Optional<Client>findByName(String clientName);
    Optional<Client>existsByName(String clientName);
    Boolean existsByEmail(String email);
}
