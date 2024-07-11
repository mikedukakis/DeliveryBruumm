package com.imf.delivery;

import java.util.ArrayList;
import java.util.List;

public class DeliveryPerson {
    private String fullName;
    private boolean availability;
    private Transportation transportation;
    private static List<DeliveryPerson> deliveryPeople;

    public DeliveryPerson(String fullName, boolean availability, Transportation transportation) {
        this.fullName = fullName;
        this.availability = availability;
        this.transportation = transportation;
        deliveryPeople = new ArrayList<>();
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public Transportation getTransportation() {
        return transportation;
    }

    public void setTransportation(Transportation transportation) {
        this.transportation = transportation;
    }
}
