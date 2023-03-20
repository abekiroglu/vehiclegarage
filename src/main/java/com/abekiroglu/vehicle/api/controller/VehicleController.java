package com.abekiroglu.vehicle.api.controller;

import com.abekiroglu.vehicle.api.dto.request.LeaveRequest;
import com.abekiroglu.vehicle.api.dto.request.ParkRequest;
import com.abekiroglu.vehicle.api.dto.request.QueueGeneratorRequest;
import com.abekiroglu.vehicle.api.dto.request.VehicleRequest;
import com.abekiroglu.vehicle.api.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@EnableAutoConfiguration
@RequestMapping("/v1/vehicles"
)
public class VehicleController {
    private VehicleService service;

    @Autowired
    public VehicleController(VehicleService service){
        this.service = service;
    }

    @PostMapping("/")
    public ResponseEntity postVehicle(@RequestBody VehicleRequest request){
        return service.postVehicle(request);
    }
    @PostMapping("/generate")
    public ResponseEntity generateVehicleQueue(@RequestBody QueueGeneratorRequest request){
        return service.generateVehicleQueue(request);
    }
    @PostMapping("/park")
    public ResponseEntity parkVehicle(@RequestBody ParkRequest request){
        return service.parkVehicle(request);
    }
    @PostMapping("/leave")
    public ResponseEntity leaveVehicle(@RequestBody LeaveRequest request){
        return service.leaveVehicle(request);
    }
    @GetMapping("/status")
    public ResponseEntity getGarageStatus(){
        return service.getGarageStatus();
    }
}
