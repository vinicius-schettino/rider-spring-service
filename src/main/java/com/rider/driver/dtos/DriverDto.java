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
public class DriverDto{

    private String name;
    @Id
    private String email;
    private String status;
    private double latitude = -21.762577517841127; // valor provisorio
    private double longitude = -43.35308238981895; // valor provisorio
    private String vehicleMake;
    private String vehicleModel;
    private String vehiclePlate;

    public DriverDto(Driver driver){
        name = driver.getName();
        email = driver.getEmail();
        status = driver.getStatus().toString();
        latitude = driver.getLatitude();
        longitude = driver.getLongitude();
        vehicleMake = driver.getVehicle().getMake();
        vehicleModel = driver.getVehicle().getModel();
        vehiclePlate = driver.getVehicle().getPlate();
    }
}

