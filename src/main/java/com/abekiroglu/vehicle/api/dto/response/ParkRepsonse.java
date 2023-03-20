package com.abekiroglu.vehicle.api.dto.response;

import com.abekiroglu.vehicle.api.model.Vehicle;

public class ParkRepsonse {

    private String message;
    private Vehicle created;
    private Vehicle parked;


    public ParkRepsonse(String message, Vehicle created, Vehicle parked) {
        this.message = message;
        this.created = created;
        this.parked = parked;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Vehicle getCreated() {
        return created;
    }

    public void setCreated(Vehicle created) {
        this.created = created;
    }

    public Vehicle getParked() {
        return parked;
    }

    public void setParked(Vehicle parked) {
        this.parked = parked;
    }
}
