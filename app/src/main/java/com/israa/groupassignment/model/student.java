package com.israa.groupassignment.model;

public class student {
    private  String Rooms;
    private  String Sname;
    private  String rate;
    private  String email;
    private  String familyinfo;


    public student(){}
    public student(String Rooms,String Sname,String rate,String email,String familyinfo){
        this.Rooms =Rooms;
        this.Sname = Sname;
        this.rate = rate;
        this.email = email;
        this.familyinfo = familyinfo ;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public void setFamilyinfo(String familyinfo) {
        this.familyinfo = familyinfo;
    }

    public String getRooms() {
        return Rooms;
    }

    public void setRooms(String rooms) {
        Rooms = rooms;
    }

    public void setSname(String sname) {
        Sname = sname;
    }

    public String getEmail() {
        return email;
    }

    public String getFamilyinfo() {
        return familyinfo;
    }

    public String getRate() {
        return rate;
    }

    public String getSname() {
        return Sname;
    }

    @Override
    public String toString() {
        return "student{" +
                "Rooms='" + Rooms + '\'' +
                ", Sname='" + Sname + '\'' +
                ", rate='" + rate + '\'' +
                ", email='" + email + '\'' +
                ", familyinfo='" + familyinfo + '\'' +
                '}';
    }
}
