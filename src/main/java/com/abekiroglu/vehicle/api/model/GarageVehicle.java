package com.abekiroglu.vehicle.api.model;

public class GarageVehicle {
    private boolean isHead;
    private String plateNo;
    private int parkIndex;
    private String color;
    private String type;
    private Integer size;
    public GarageVehicle(Vehicle vehicle, int parkIndex) {
        this.plateNo = vehicle.getPlateNo();
        this.color = vehicle.getColor();
        this.type = vehicle.getType();
        this.size = vehicle.getSize();
        this.isHead = false;
        this.parkIndex = parkIndex;
    }
    public GarageVehicle(Vehicle vehicle, int parkIndex, boolean isHead) {
        this.plateNo = vehicle.getPlateNo();
        this.color = vehicle.getColor();
        this.type = vehicle.getType();
        this.size = vehicle.getSize();
        this.isHead = isHead;
        this.parkIndex = parkIndex;
    }
    public boolean isHead() {
        return isHead;
    }

    public int getParkIndex() {
        return parkIndex;
    }

    public void setParkIndex(int parkIndex) {
        this.parkIndex = parkIndex;
    }

    public void setHead(boolean head) {
        isHead = head;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
