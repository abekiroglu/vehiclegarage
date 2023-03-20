package com.abekiroglu.vehicle.api.dto.response;

import com.abekiroglu.vehicle.api.model.GarageVehicle;
import com.abekiroglu.vehicle.api.model.Vehicle;

public class LeaveResponse {
    private String message;
    private GarageVehicle left;

    public LeaveResponse(String message, GarageVehicle left) {
        this.message = message;
        this.left = left;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public GarageVehicle getLeft() {
        return left;
    }

    public void setLeft(GarageVehicle left) {
        this.left = left;
    }
}
