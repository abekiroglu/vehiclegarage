package com.abekiroglu.vehicle.api.repository;

import com.abekiroglu.vehicle.api.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {
    Vehicle findVehicleByPlateNo(String plateNo);
}
