package com.rider.payment.repositories;

import com.rider.payment.entities.creditcard.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;


public interface CreditcardRepository extends JpaRepository<CreditCard, UUID> {

    List<CreditCard> findAllByName(String Name);

    List<CreditCard> findAllByHolder(String holder);

}

