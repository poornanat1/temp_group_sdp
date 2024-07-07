package edu.gatech.seclass.jobcompare6300;

public class Location {
    private String city;
    private String state;
    Location(String state, String city) {
        this.state = state;
        this.city = city;
    }
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}