package com.abekiroglu.vehicle.api.dto.request;

public class QueueGeneratorRequest {
    private int size;

    public QueueGeneratorRequest(){

    }

    public QueueGeneratorRequest(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
