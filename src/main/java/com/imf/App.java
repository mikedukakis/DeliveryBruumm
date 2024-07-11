package com.imf;

import com.imf.customers.Customer;
import com.imf.delivery.DeliveryPerson;
import com.imf.delivery.Transportation;
import com.imf.exceptionsandtools.DeliveryUnavailableException;
import com.imf.exceptionsandtools.KeyboardInputHandler;
import com.imf.orders.Order;
import com.imf.products.Burger;
import com.imf.products.*;

import java.util.ArrayList;
import java.util.List;

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
            case 4:
                System.out.println("Please enter the order ID to change status to delivered:");
                int orderiId = KeyboardInputHandler.readInt("ID of order:");
                if (findOrder(orderiId) == null) {
                    System.out.println("There isn't an order with this orderId " + orderiId);
                } else {
                    markAsDelivered(findOrder(orderiId));
                }
                break;
            case 5:
                showPendingOrders();
                break;
            case 6:
                showDeliveredOrders();
                break;
            default:
                System.out.println("Please choose a correct option");
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

    public List<Product> productOrderMenu() {
        List<Product> products = new ArrayList<>();
        Product product;
        boolean exit = false;
        do {
            System.out.println("Please choose a product to add to your shopping cart:\n" +
                    "0. Finish with the order\n" +
                    "1. Burger\n" +
                    "2. Burrito\n" +
                    "3. Kebab\n" +
                    "4. Pizza");
            switch (userOption()) {
                case 0:
                    exit = true;
                    System.out.println("Leaving the product selection");
                case 1:
                    product = new Burger();
                    products.add(product);
                    break;
                case 2:
                    product = new Burrito();
                    products.add(product);
                    break;
                case 3:
                    product = new Kebab();
                    products.add(product);
                    break;
                case 4:
                    product = new Pizza();
                    products.add(product);
                    break;
                default:
                    System.out.println("Please choose a correct option");
            }
        } while (!exit);

        return products;
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

    public Customer findCustomerManager() {
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

    public DeliveryPerson findDeliveryAvailable() throws DeliveryUnavailableException {
        DeliveryPerson deliveryPerson = null;
        for (DeliveryPerson element : DeliveryPerson.getDeliveryPeople()) {
            if (element.getAvailability() == true) {
                deliveryPerson = element;
            }
        }
        if (deliveryPerson == null) {
            throw new DeliveryUnavailableException();
        }
        return deliveryPerson;
    }

    public void createOrder() {
        Customer customer;
        customer = findCustomerManager();
        DeliveryPerson deliveryPerson = null;
        List<Product> products;
        try {
            deliveryPerson = findDeliveryAvailable();
        } catch (DeliveryUnavailableException e) {
            System.out.println(e.getMessage());
        }
        Transportation transportation = deliveryPerson.getTransportation();
        float fee = calculateFee(transportation);
        products = productOrderMenu();
        float totalPrice = calculatePrice(products) * fee;

        Order order = new Order(false, customer, deliveryPerson, products, totalPrice);
        Order.getPendingOrders().add(order);
        System.out.println("Ordered created successfully.");
    }

    public Order findOrder(int id) {
        Order order = null;
        for (Order element : Order.getPendingOrders()) {
            if (element.getOrderId() == id) {
                order = element;
            }
        }
        return order;
    }

    public float calculatePrice(List<Product> products) {
        float billSum = 0;
        for (Product element : products) {
            billSum += element.getPrice();
        }
        return billSum;
    }

    public float calculateFee(Transportation transportationMethod) {
        float fee;
        if (transportationMethod.equals(Transportation.BYCICLE)) {
            fee = 1.1F;
        } else if (transportationMethod.equals(Transportation.MOTORBIKE)) {
            fee = 1.2F;
        } else {
            fee = 1F;
        }
        return fee;
    }

    public void markAsDelivered(Order order) {
        order.setDelivered(true);
        Order.getPendingOrders().remove(order);
        Order.getDeliveredOrders().add(order);
        System.out.println("Order marked as delivered");
    }

    public void showPendingOrders() {
        if(Order.getPendingOrders() == null || Order.getPendingOrders().isEmpty()) {
            System.out.println("No orders pending delivery.");
        } else {
            for (Order element: Order.getPendingOrders()){
                System.out.println(element);
            }
        }
    }

    public void showDeliveredOrders() {
        if(Order.getDeliveredOrders() == null || Order.getDeliveredOrders().isEmpty()) {
            System.out.println("No orders delivered.");
        } else {
            for (Order element: Order.getDeliveredOrders()){
                System.out.println(element);
            }
        }

    }

    public void run() {
        boolean exit;
        do {
            exit = optionManager();
        } while (!exit);
    }

}
