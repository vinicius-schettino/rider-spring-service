package com.rider.driver.repositories;

import com.rider.driver.entities.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface DriverRepository extends JpaRepository<Driver, Integer> {
    void deleteById(UUID id);
    Optional<Driver>findById(UUID id);

}
