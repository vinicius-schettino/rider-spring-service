package com.rider.driver.service;


import com.rider.driver.entities.Vehicle;
import com.rider.driver.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {
    @Autowired
    private VehicleRepository vehicleRepository;

    public Vehicle salvar(Vehicle vehicle){
        return vehicleRepository.save(vehicle);
    }
    public List<Vehicle> listarVehicle(){
        return vehicleRepository.findAll();
    }
    public Optional<Vehicle> buscarPorId(Integer id){
        return vehicleRepository.findById(id);
    }
    public void removerDriver(Integer id){
        vehicleRepository.deleteById(id);
    }
    public Vehicle atualizarVehicle(Vehicle vehicle){
        return vehicleRepository.save(vehicle);
    }
}
