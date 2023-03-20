package com.abekiroglu.vehicle.api.dto.request;

public class VehicleRequest {
    private String type;
    private String plateNo;
    private String color;

    public String getType() {
        return type;
    }

    public VehicleRequest() {
    }

    public VehicleRequest(String type, String plateNo, String color) {
        this.type = type;
        this.plateNo = plateNo;
        this.color = color;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPlateNo() {
        return plateNo;
    }

    public void setPlateNo(String plateNo) {
        this.plateNo = plateNo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
