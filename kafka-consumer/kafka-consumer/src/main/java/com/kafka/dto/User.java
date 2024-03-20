package com.kafka.dto;

import java.io.Serializable;

public class User implements Serializable {

    private int id;
    private int firstName;
    private String lastName;
    private String ipAddress;

    public User() {
    }

    public User(int id, int firstName, String lastName, String ipAddress) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.ipAddress = ipAddress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFirstName() {
        return firstName;
    }

    public void setFirstName(int firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    /*@Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName=" + firstName +
                ", lastName='" + lastName + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                '}';
    }*/
}
