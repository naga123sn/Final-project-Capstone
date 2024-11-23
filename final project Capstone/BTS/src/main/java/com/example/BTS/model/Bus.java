package com.example.BTS.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "buses")
public class Bus {

    @Id
    private String id; // Unique identifier for each bus
    private String busName;
    private String busNumber;
    private String fromLocation;
    private String toLocation;
    private String timing;
    private int seatsAvailable; // Total number of seats available

    public Bus() {}

    public Bus(String busName, String busNumber, String fromLocation, String toLocation, String timing, int seatsAvailable) {
        this.busName = busName;
        this.busNumber = busNumber;
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
        this.timing = timing;
        this.seatsAvailable = seatsAvailable;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBusName() {
        return busName;
    }

    public void setBusName(String busName) {
        this.busName = busName;
    }

    public String getBusNumber() {
        return busNumber;
    }

    public void setBusNumber(String busNumber) {
        this.busNumber = busNumber;
    }

    public String getFromLocation() {
        return fromLocation;
    }

    public void setFromLocation(String fromLocation) {
        this.fromLocation = fromLocation;
    }

    public String getToLocation() {
        return toLocation;
    }

    public void setToLocation(String toLocation) {
        this.toLocation = toLocation;
    }

    public String getTiming() {
        return timing;
    }

    public void setTiming(String timing) {
        this.timing = timing;
    }

    public int getSeatsAvailable() {
        return seatsAvailable;
    }

    public void setSeatsAvailable(int seatsAvailable) {
        this.seatsAvailable = seatsAvailable;
    }
}
