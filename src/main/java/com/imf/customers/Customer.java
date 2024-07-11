package com.imf.customers;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String fullName;
    private String address;
    private static List<Customer> customers;

    public Customer(String fullName, String address) {
        this.fullName = fullName;
        this.address = address;
        customers = new ArrayList<>();
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public static List<Customer> getCustomers() {
        return customers;
    }
}
