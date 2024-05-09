package com.rider.driver.controller;

import com.rider.driver.entities.Driver;
import com.rider.driver.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/driver")
public class DriverController {
    @Autowired
    private DriverService driverService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Driver salvarDriver(@RequestBody Driver driver){
        return driverService.salvar(driver);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public  List<Driver> listarDrivers(){
        return driverService.listarDrivers();
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Driver buscarDriverPorId(@PathVariable("id") Integer id){
        return driverService.bsucarPorId(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Motorista não encontrado."));
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarDriverPorId(@PathVariable("id")Integer id){
        driverService.bsucarPorId(id)
                .map(driver -> {
                    driverService.removerDriver(driver.getId());
                        return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Motorista não encontrado."));
    }


}
