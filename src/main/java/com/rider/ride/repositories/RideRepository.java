package com.rider.ride.repositories;

import com.rider.ride.entities.Ride;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RideRepository extends JpaRepository<Ride, Integer> {
    Iterable<Ride> findAllById(Integer id);
}
