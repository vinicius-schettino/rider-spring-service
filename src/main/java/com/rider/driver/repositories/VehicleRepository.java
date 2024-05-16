package com.rider.driver.repositories;

import com.rider.driver.entities.Driver;
import com.rider.driver.entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository  extends JpaRepository<Vehicle, Integer> {

}
