package com.example.bank_aplication_demo.repository;

import com.example.bank_aplication_demo.entity.Cart;
import com.example.bank_aplication_demo.entity.Client;
import org.hibernate.sql.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;


import javax.transaction.Transactional;
import java.util.Optional;


@Repository


public interface CartRepository extends JpaRepository<Cart,Long> {

    Cart findByClientId(Long clientId);
    Optional<Cart>findByCartId(Long cartId);

    @Modifying
    @Query(
            value =
                    "insert into carts(account, cart_number, money, secret_number, client_id) values (:account, :cartNumber, :money, :secretNumber, :clientsId)",
            nativeQuery = true)

    @Transactional
    void insertCart(@Param("account") Long account,
                    @Param("cartNumber") Long cartNumber, @Param("money") Long money, @Param("secretNumber") Long secretNumber, @Param("clientsId") Long clientId);


      Cart findCartBySecretNumberAndCartNumber(Long secretNumber,Long cartNumber);

    Cart findCartByAccount(Long account);




    }




