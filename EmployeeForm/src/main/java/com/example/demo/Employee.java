package com.example.demo;

public class Employee {

    private String firstName;
    private String lastName;
    private String dob;
    private int ssn;

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDob() {
        return this.dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public int getSsn() {
        return this.ssn;
    }

    public void setSsn(int ssn) {
        this.ssn = ssn;
    }
}
