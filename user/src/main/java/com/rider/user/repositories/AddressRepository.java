package com.rider.user.repositories;

import com.rider.user.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer>{
}
