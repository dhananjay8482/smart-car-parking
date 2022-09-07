package com.example.autopark;

public class SlotHelper {

    String slotno,avail,vehno;

    public SlotHelper() {
    }

    public SlotHelper(String slotno, String avail, String vehno) {
        this.slotno = slotno;
        this.avail = avail;
        this.vehno = vehno;
    }

    public String getSlotno() {
        return slotno;
    }

    public void setSlotno(String slotno) {
        this.slotno = slotno;
    }

    public String getAvail() {
        return avail;
    }

    public void setAvail(String avail) {
        this.avail = avail;
    }

    public String getVehno() {
        return vehno;
    }

    public void setVehno(String vehno) {
        this.vehno = vehno;
    }
}
