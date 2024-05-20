package com.rider.driver.dtos;

import com.rider.driver.entities.Driver;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DriverDto {

    private String name;
    @Id
    private String email;
    private String status;
    private String vehcileMake;
    private String vehicleModel;
    private String vehicleplate;

    public DriverDto(Driver driver){
        name = driver.getName();
        email = driver.getEmail();
        status = driver.getStatus().toString();
        vehcileMake = driver.getVehicle().getMake();
        vehicleModel = driver.getVehicle().getModel();
        vehicleplate = driver.getVehicle().getPlate();
    }
}

