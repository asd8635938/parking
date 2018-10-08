package com.huajiao.parkingsystem.Ben;

public class ParkingDetailsData {
    private String serial;
    private String parkingSerial;
    private boolean isCanReservation;

    public boolean isCanReservation() {
        return isCanReservation;
    }

    public void setCanReservation(boolean canReservation) {
        isCanReservation = canReservation;
    }

    public String getParkingSerial() {
        return parkingSerial;
    }

    public void setParkingSerial(String parkingSerial) {
        this.parkingSerial = parkingSerial;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }
}
