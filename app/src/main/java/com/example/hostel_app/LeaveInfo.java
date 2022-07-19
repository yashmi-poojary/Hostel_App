package com.example.hostel_app;

public class LeaveInfo {

    public String name, usn, roomNo, reason, dateOut, dateIn, adv, warden;

    public LeaveInfo(String name, String usn, String roomNo, String reason, String dateOut, String dateIn, String adv, String warden) {
        this.name = name;
        this.usn = usn;
        this.roomNo = roomNo;
        this.reason = reason;
        this.dateOut = dateOut;
        this.dateIn = dateIn;
        this.adv = adv;
        this.warden = warden;
    }
}

