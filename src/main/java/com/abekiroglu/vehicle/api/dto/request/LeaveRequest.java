package com.abekiroglu.vehicle.api.dto.request;

public class LeaveRequest {
    private int leave;

    public LeaveRequest(){

    }

    public LeaveRequest(int leave) {
        this.leave = leave;
    }

    public int getLeave() {
        return leave;
    }

    public void setLeave(int leave) {
        this.leave = leave;
    }
}
