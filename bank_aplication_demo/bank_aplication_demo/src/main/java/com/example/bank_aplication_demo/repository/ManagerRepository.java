package com.example.bank_aplication_demo.repository;

import com.example.bank_aplication_demo.entity.Cart;
import com.example.bank_aplication_demo.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepository extends JpaRepository<Cart,Long> {
}
