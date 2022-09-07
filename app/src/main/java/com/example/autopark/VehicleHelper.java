package com.example.autopark;

public class VehicleHelper {

    String VehNo , entryTime , entryDate , exitTime , exitDate , type, enttiming,exttiming,slotno;

    public VehicleHelper()
    {

    }

    public VehicleHelper(String vehNo, String entryTime, String entryDate, String exitTime, String exitDate, String type, String enttiming, String exttiming, String slotno) {
        VehNo = vehNo;
        this.entryTime = entryTime;
        this.entryDate = entryDate;
        this.exitTime = exitTime;
        this.exitDate = exitDate;
        this.type = type;
        this.enttiming = enttiming;
        this.exttiming = exttiming;
        this.slotno = slotno;
    }

    public String getEnttiming() {
        return enttiming;
    }

    public void setEnttiming(String enttiming) {
        this.enttiming = enttiming;
    }

    public String getExttiming() {
        return exttiming;
    }

    public void setExttiming(String exttiming) {
        this.exttiming = exttiming;
    }

    public String getSlotno() {
        return slotno;
    }

    public void setSlotno(String slotno) {
        this.slotno = slotno;
    }

    public String getVehNo() {
        return VehNo;
    }

    public void setVehNo(String vehNo) {
        VehNo = vehNo;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    public String getExitTime() {
        return exitTime;
    }

    public void setExitTime(String exitTime) {
        this.exitTime = exitTime;
    }

    public String getExitDate() {
        return exitDate;
    }

    public void setExitDate(String exitDate) {
        this.exitDate = exitDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
