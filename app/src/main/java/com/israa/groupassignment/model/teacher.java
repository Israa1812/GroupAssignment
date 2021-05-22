package com.israa.groupassignment.model;

public class teacher {
    private  String material;
    private  String Tname;
    private  String salary;
    private  String email;
    private  String educationalInfo;


    public teacher(){}
    public teacher(String material,String Tname,String salary,String email,String educationalInfo){
        this.material =material;
        this.Tname = Tname;
        this.salary = salary;
        this.email = email;
        this.educationalInfo = educationalInfo ;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEducationalInfo(String educationalInfo) {
        this.educationalInfo = educationalInfo;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public void setTname(String tname) {
        Tname = tname;
    }

    public String getEmail() {
        return email;
    }

    public String getEducationalInfo() {
        return educationalInfo;
    }

    public String getMaterial() {
        return material;
    }

    public String getSalary() {
        return salary;
    }

    public String getTname() {
        return Tname;
    }

    @Override
    public String toString() {
        return "teacher{" +
                "material='" + material + '\'' +
                ", Tname='" + Tname + '\'' +
                ", salary='" + salary + '\'' +
                ", email='" + email + '\'' +
                ", educationalInfo='" + educationalInfo + '\'' +
                '}';
    }
}
