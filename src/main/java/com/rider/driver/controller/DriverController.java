package com.rider.driver.controller;

import com.rider.driver.entities.Driver;
import com.rider.driver.entities.DriverStatus;
import com.rider.driver.service.DriverService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        return driverService.buscarPorId(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Motorista não encontrado."));
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarDriverPorId(@PathVariable("id")Integer id){
        driverService.buscarPorId(id)
                .map(driver -> {
                    driverService.removerDriver(driver.getId());
                        return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Motorista não encontrado."));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Driver atualizarDriver(@PathVariable("id") Integer id, @RequestBody Driver driverAtualizado){
        return driverService.buscarPorId(id)
                .map(driver -> {
                    if(driverAtualizado.getName() != null){
                        driver.setName(driverAtualizado.getName());
                    }
                    return driverService.atualizarDriver(driver);
                }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Motorista não encontrado."));
    }

    @PostMapping("/start/{id}") // pq não usar um put? - usar id nesse metodo é o correto mesmo? se não, como fazer? - como vamos ter varios caminhos diferentes para mudar para o mesmo estado não seria melhor deixar a maior parte da logica no service?
    @ResponseStatus(HttpStatus.OK)
    public Driver makeAvailableFromOffline(@PathVariable("id") Integer id){
        return driverService.buscarPorId(id)
                .map(driver -> {
                        if (driver.getStatus() == DriverStatus.OFFLINE){
                            driver.setStatus(DriverStatus.AVAILABLE);
                        }
                        return driverService.atualizarDriver(driver);
                }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Motorista não encontrado."));
    }

    @PostMapping("/stop/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Driver makeOfflineFromAvailable(@PathVariable Integer id) {
        return driverService.buscarPorId(id)
                .map(driver -> {
                    if (driver.getStatus() == DriverStatus.AVAILABLE) {
                        driver.setStatus(DriverStatus.OFFLINE);
                    }
                    return driverService.salvar(driver);
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Motorista não encontrado."));
    }

    @PostMapping("/accept/{id}") // Eu deveria lançar uma exception caso o usuario tente ir de busy direto para offline e vice versa?
    @ResponseStatus(HttpStatus.OK)
    public Driver makeBusyFromAvailable(@PathVariable Integer id) {
        return driverService.buscarPorId(id)
                .map(driver -> {
                    if (driver.getStatus() == DriverStatus.AVAILABLE){
                        driver.setStatus(DriverStatus.BUSY);
                    }
                    return driverService.salvar(driver);
                }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Motorista não encontrado."));
    }

    @PostMapping("/finish/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Driver makeAvailableFromBusy(@PathVariable Integer id){
        return driverService.buscarPorId(id)
                .map(driver -> {
                    if (driver.getStatus() == DriverStatus.BUSY){
                        driver.setStatus(DriverStatus.AVAILABLE);
                    }
                    return driverService.salvar(driver);
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Motorista não encontrado"));
    }
}


