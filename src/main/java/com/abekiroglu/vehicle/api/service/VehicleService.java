package com.abekiroglu.vehicle.api.service;

import com.abekiroglu.vehicle.api.dto.request.LeaveRequest;
import com.abekiroglu.vehicle.api.dto.request.ParkRequest;
import com.abekiroglu.vehicle.api.dto.request.QueueGeneratorRequest;
import com.abekiroglu.vehicle.api.dto.request.VehicleRequest;
import com.abekiroglu.vehicle.api.dto.response.*;
import com.abekiroglu.vehicle.api.model.GarageVehicle;
import com.abekiroglu.vehicle.api.model.Vehicle;
import com.abekiroglu.vehicle.api.model.VehicleColor;
import com.abekiroglu.vehicle.api.model.VehicleType;
import com.abekiroglu.vehicle.api.repository.VehicleRepository;
import com.abekiroglu.vehicle.api.utils.GarageUtils;
import com.abekiroglu.vehicle.api.utils.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Transactional
@Service
public class VehicleService {
    private VehicleRepository vehicleRepository;
    private GarageVehicle[] garage = new GarageVehicle[10];
    private Map<String, GarageVehicle> garageMap = new HashMap<>(10);
    private int parkIndex = 0;
    @Autowired
    public VehicleService(VehicleRepository vehicleRepository){
        this.vehicleRepository = vehicleRepository;
    }

    public ResponseEntity postVehicle(VehicleRequest request) {
        Vehicle vehicle = new Vehicle();
        try {
            if(request.getColor() == null){
                int index = (int) ThreadLocalRandom.current().nextInt(0, VehicleColor.values().length + 1);
                vehicle.setColor(VehicleColor.from(index).name().toLowerCase());
            }else{
                VehicleColor color = VehicleColor.from(request.getColor().toLowerCase());
                if(color != null){
                    vehicle.setColor(request.getColor().toLowerCase());
                }else{
                    throw new Exception("Invalid color, allowed colors are: " + VehicleColor.stringifyEnum().toLowerCase());
                }
            }
            if(request.getPlateNo() == null){
                int district = ThreadLocalRandom.current().nextInt(1, 81);
                String alphabetic = RandomUtils.getRandomAlphabeticString(2, 3).toUpperCase();
                int numeric = ThreadLocalRandom.current().nextInt(0, 9999);
                vehicle.setPlateNo(String.format("%d-%s-%d", district, alphabetic, numeric));
            }else{
                vehicle.setPlateNo(request.getPlateNo());
            }
            if(request.getType() == null){
                throw new Exception("Type must be provided");
            }else{
                VehicleType type = VehicleType.from(request.getType().toLowerCase());
                if(type != null){
                    vehicle.setType(type.mask);
                    vehicle.setSize(type.size);
                }else{
                    throw new Exception("Invalid type, allowed types are: " + VehicleType.stringifyEnum().toLowerCase());
                }
            }
            vehicle = vehicleRepository.save(vehicle);
            return new Response<>(vehicle, HttpStatus.CREATED);
        } catch (Exception e){
            return new Response<>(new ErrorResponse("500", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity generateVehicleQueue(QueueGeneratorRequest request) {
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        try{
            for(int i = 0; i < request.getSize(); i++){
                Vehicle vehicle = new Vehicle();
                //plateNo generation
                int district = ThreadLocalRandom.current().nextInt(1, 81);
                String alphabetic = RandomUtils.getRandomAlphabeticString(2, 3).toUpperCase();
                int numeric = ThreadLocalRandom.current().nextInt(0, 9999);
                vehicle.setPlateNo(String.format("%d-%s-%d", district, alphabetic, numeric));
                //color generation
                int colorIndex = (int) ThreadLocalRandom.current().nextInt(0, VehicleColor.values().length + 1);
                vehicle.setColor(VehicleColor.from(colorIndex).name().toLowerCase());
                //type and size generation
                int typeIndex = (int) ThreadLocalRandom.current().nextInt(0, VehicleType.values().length + 1);
                vehicle.setSize(VehicleType.from(typeIndex).size);
                vehicle.setType(VehicleType.from(typeIndex).mask);
                vehicles.add(vehicle);
            }
            vehicles = (ArrayList<Vehicle>)vehicleRepository.saveAll(vehicles);
            return new Response<>(vehicles, HttpStatus.CREATED);
        }catch(Exception e){
            return new Response<>(new ErrorResponse("500", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity parkVehicle(ParkRequest request) {
        try{

            // parse park request
            StringTokenizer st = new StringTokenizer(request.getPark(), " ");
            String plateNo;
            VehicleColor color;
            VehicleType type;
            //plate number check
            if(st.hasMoreTokens()){
                plateNo = st.nextToken();
            }else{
                throw new Exception("Plate number is missing!");
            }
            // color check
            if(st.hasMoreTokens()){
                String requestedCarColor = st.nextToken().toLowerCase();
                color = VehicleColor.from(requestedCarColor);
                if(color == null){
                    throw new Exception(String.format("Invalid color '%s', allowed colors are: %s",
                            requestedCarColor,
                            VehicleColor.stringifyEnum().toLowerCase()));
                }
            }else{
                throw new Exception("Color is missing!");
            }
            // type check
            if(st.hasMoreTokens()){
                // if type "car" is provided, choose a random car type
                String requestedCarType = st.nextToken().toLowerCase();
                if(requestedCarType.equalsIgnoreCase("car")){
                    int randomCarType = ThreadLocalRandom.current().nextInt(0, 2);
                    type = VehicleType.from(randomCarType);
                }else{
                    type = VehicleType.from(requestedCarType);
                    if(type == null){
                        throw new Exception(String.format("Invalid type '%s', allowed types are: %s",
                                requestedCarType,
                                VehicleType.stringifyEnum().toLowerCase()));
                    }
                }
            }else{
                throw new Exception("Vehicle type is missing!");
            }
            // check if vehicle already exists
            Vehicle persistentVehicle = vehicleRepository.findVehicleByPlateNo(plateNo.toUpperCase());
            boolean created = false;
            if(persistentVehicle == null){
                // create vehicle
                persistentVehicle = new Vehicle();
                persistentVehicle.setType(type.mask);
                persistentVehicle.setSize(type.size);
                persistentVehicle.setColor(color.mask);
                persistentVehicle.setPlateNo(plateNo);
                created = true;
            }
            // check vehicle car is already parked
            if(garageMap.get(persistentVehicle.getPlateNo()) != null){
                throw new Exception(String.format("Vehicle with plate number '%s' is already parked in the garage!", persistentVehicle.getPlateNo()));
            }else{
             // if not parked, park the vehicle
                int headIndex = GarageUtils.findSuitableParkingSpot(persistentVehicle.getSize(), garage);
                if(headIndex != -1){
                    persistentVehicle = vehicleRepository.saveAndFlush(persistentVehicle);
                    garageMap.put(persistentVehicle.getPlateNo(), new GarageVehicle(persistentVehicle, parkIndex));
                    for(int i = 0; i < persistentVehicle.getSize(); i++){
                        if(i == 0){
                            garage[headIndex + i] = new GarageVehicle(persistentVehicle, parkIndex, true);
                        }else{
                            garage[headIndex + i] = new GarageVehicle(persistentVehicle, parkIndex);
                        }
                    }
                }else{
                    return new Response<>(new ParkRepsonse("Garage is full"
                            ,null, null)
                            , HttpStatus.OK);
                }
            }
            // if car is newly created notify in response
            parkIndex++;
            if(created){
                return new Response<>(new ParkRepsonse(String.format("Created vehicle and allocated %s slot(s)", persistentVehicle.getSize())
                        ,persistentVehicle, persistentVehicle)
                        , HttpStatus.OK);
            }else{
                return new Response<>(new ParkRepsonse(String.format("Allocated %s slot(s)", persistentVehicle.getSize()),
                        null, persistentVehicle),
                        HttpStatus.OK);
            }
        }catch(Exception e){
            return new Response<>(new ErrorResponse("500", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity leaveVehicle(LeaveRequest request) {
        try{
            if(request.getLeave() > garage.length){
                throw new Exception("Leaving vehicle slot should not be greater than 10!");
            }else{
                // Designate leaving vehicle
                GarageVehicle leavingVehicle = null;
                for(int i = 0; i < garage.length; i++){
                    if(garage[i] != null && garage[i].getParkIndex() == (request.getLeave() - 1) && garage[i].isHead()){
                        garageMap.remove(garage[i].getPlateNo());
                        leavingVehicle = garage[i];
                        for(int j = 0; j < leavingVehicle.getSize(); j++){
                            garage[i + j] = null;
                        }
                        break;
                    }
                }
                if(leavingVehicle == null){
                    throw new Exception(String.format("Vehicle %d was not found!", request.getLeave()));
                }else{
                    return new Response<>(
                            new LeaveResponse(String.format("Car with plate No '%s' has left the garage",
                                    leavingVehicle.getPlateNo()),
                                    leavingVehicle),
                            HttpStatus.OK);
                }
            }
        }catch(Exception e){
            return new Response<>(new ErrorResponse("500", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity getGarageStatus() {
        try{
            String statusString = "Status: ";
            for(int i = 0; i < garage.length; i++){
                GarageVehicle vehicle = garage[i];
                if(vehicle != null && vehicle.isHead()){
                    String coverage;
                    switch (vehicle.getType()){
                        case "truck":
                            coverage = String.format("[%d, %d, %d, %d]", i + 1, i + 2, i + 3, i + 4);
                            break;
                        case "jeep":
                            coverage = String.format("[%d, %d]", i + 1, i + 2);
                            break;
                        default:
                            coverage = String.format("[%d]", i + 1);
                            break;
                    }
                    statusString += String.format("%s %s %s ",vehicle.getPlateNo(), vehicle.getColor(), coverage);
                }else if(vehicle == null){
                    statusString += String.format("Empty [%s] ", i + 1);
                }
            }

            return new Response<>(new StatusResponse(statusString), HttpStatus.OK);
        }catch(Exception e){
            return new Response<>(new ErrorResponse("500", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
