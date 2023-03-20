package com.abekiroglu.vehicle.api.utils;

import com.abekiroglu.vehicle.api.model.GarageVehicle;

public class GarageUtils {

    public static int findSuitableParkingSpot(int vehicleSize, GarageVehicle[] garage){
        // fit in vehicles
        for(int i = 0; i < garage.length; i++){
            // 1 empty spot found, check the adjacent tiles for larger vehicles if the vehicle can fit in the remainder
            if(garage[i] == null && i + vehicleSize <= garage.length){
                for(int j = 0; j < vehicleSize; j++){
                    // if end of garage and there are no empty spots, garage is full
                    if(garage[i + j] != null && i == garage.length - 1){
                        return -1;
                    }
                }
                return i;
            }
        }
        return -1;
    }
}
