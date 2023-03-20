package com.abekiroglu.vehicle.api.dto.response;

public class StatusResponse {
    private String vehicles;

    public StatusResponse(String vehicles) {
        this.vehicles = vehicles;
    }

    public String getVehicles() {
        return vehicles;
    }

    public void setVehicles(String vehicles) {
        this.vehicles = vehicles;
    }
}
