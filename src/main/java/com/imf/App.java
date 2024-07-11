package com.imf;

import com.imf.customers.Customer;
import com.imf.delivery.DeliveryPerson;
import com.imf.delivery.Transportation;
import com.imf.exceptionsandtools.KeyboardInputHandler;
import com.imf.orders.Order;

public class App {
    public App() {
    }

    public int userOption() {
        int option;
        option = KeyboardInputHandler.readInt("Please, choose an option [0-8]:");
        return option;
    }

    public String menu() {
        return "Choose an option [0-6]:\n" +
                "0. Exit\n" +
                "1. Create customer\n" +
                "2. Create delivery person\n" +
                "3. Create order\n" +
                "4. Mark order as delivered\n" +
                "5. List pending orders\n" +
                "6. List delivered orders\n";
    }

    public boolean optionManager() {
        boolean exit = false;
        System.out.println(menu());
        switch (userOption()) {
            case 0:
                exit = true;
                System.out.println("Thanks for using the Restaurant Manager!");
                break;
            case 1:
                System.out.println(createCustomer());
                break;
            case 2:
                createDeliveryPerson();
                break;
            case 3:
                createOrder();
                break;
        }

        return exit;
    }

    public Transportation transportationMethodManager() {
        Transportation transportation = null;
        System.out.println("Please choose a transportation method:\n" +
                "1. Bycicle\n" +
                "2. Motorbike\n" +
                "3. Walking");
        switch (userOption()) {
            case 1:
                transportation = Transportation.BYCICLE;
                break;
            case 2:
                transportation = Transportation.MOTORBIKE;
                break;
            case 3:
                transportation = Transportation.WALKING;
                break;
            default:
                System.out.println("Please choose a correct option");
        }
        return transportation;
    }

    public Customer createCustomer() {
        System.out.println("Please enter the customer full name:");
        String customerName = KeyboardInputHandler.readString("Enter the customer full name:");
        System.out.println("Please enter the customer full address:");
        String customerAddress = KeyboardInputHandler.readString("Enter customer full address:");

        Customer customer = new Customer(customerName, customerAddress);
        Customer.getCustomers().add(customer);
        System.out.println("Customer created successfully.");

        return customer;
    }

    public Customer findCustomerManager(){
        Customer customer = null;
        System.out.println("Choose an option:\n" +
                "1. Existing customer: Enter name\n" +
                "2. Create new customer");
        switch (userOption()) {
            case 1:
                String customerName = KeyboardInputHandler.readString("Existing customer full name:");
                customer = findCustomer(customerName);
                break;
            case 2:
                customer = createCustomer();
                break;
            default:
                System.out.println("Choose a correct option.");
        }
        return customer;
    }

    public Customer findCustomer(String name) {
        return Customer.getCustomers().stream()
                .filter(customer -> customer.getFullName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    public DeliveryPerson createDeliveryPerson() {
        System.out.println("Please enter the delivery person full name:");
        String deliveryName = KeyboardInputHandler.readString("Enter the delivery person full name:");
        System.out.println("Please enter the transportation method:");
        Transportation transportation = transportationMethodManager();

        DeliveryPerson deliveryPerson = new DeliveryPerson(deliveryName, transportation);
        DeliveryPerson.getDeliveryPeople().add(deliveryPerson);
        System.out.println("Delivery person created successfully.");

        return deliveryPerson;
    }

    public DeliveryPerson findDeliveryManager(){
        DeliveryPerson deliveryPerson = null;
        System.out.println("Choose an option:\n" +
                "1. Existing delivery person: Enter name\n" +
                "2. Create new delivery person");
        switch (userOption()) {
            case 1:
                String deliveryName = KeyboardInputHandler.readString("Existing delivery person full name");
                deliveryPerson = findDeliveryPerson(deliveryName);
                break;
            case 2:
                deliveryPerson = createDeliveryPerson();
                break;
            default:
                System.out.println("Choose a correct option.");
        }
        return deliveryPerson;
    }

    public DeliveryPerson findDeliveryPerson(String name) {
        return DeliveryPerson.getDeliveryPeople().stream()
                .filter(deliveryPerson -> deliveryPerson.getFullName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    public void createOrder() {
        Customer customer;
        customer = findCustomerManager();
        DeliveryPerson deliveryPerson;
        deliveryPerson = findDeliveryManager();

        Order order = new Order(false, customer, deliveryPerson);
        Order.getPendingOrders().add(order);
        System.out.println("Ordered created successfully.");

    }


}
