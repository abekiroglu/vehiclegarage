package com.abekiroglu.vehicle.api.dto.request;

public class ParkRequest {
    private String park;

    public ParkRequest(){

    }

    public ParkRequest(String park) {
        this.park = park;
    }

    public String getPark() {
        return park;
    }

    public void setPark(String park) {
        this.park = park;
    }
}
