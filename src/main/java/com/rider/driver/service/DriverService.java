package com.rider.driver.service;

import com.rider.driver.entities.Driver;
import com.rider.driver.repositories.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DriverService {
    @Autowired
    private DriverRepository driverRepository;

    public Driver salvar(Driver driver){
        return driverRepository.save(driver);
    }
    public List<Driver> listarDrivers(){
        return driverRepository.findAll();
    }
    public Optional<Driver> buscarPorId(Integer id){
        return driverRepository.findById(id);
    }
    public void removerDriver(Integer id){
        driverRepository.deleteById(id);
    }
}
