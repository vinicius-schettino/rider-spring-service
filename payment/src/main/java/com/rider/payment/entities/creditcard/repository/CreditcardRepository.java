package com.rider.payment.entities.creditcard.repository;

import com.rider.payment.entities.creditcard.Creditcard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;


public interface CreditcardRepository extends JpaRepository<Creditcard, UUID> {

    List<Creditcard> findAllByName(String Name);

    List<Creditcard> findAllByHolder(String holder);

}

