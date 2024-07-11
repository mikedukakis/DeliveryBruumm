package com.imf.orders;

import com.imf.customers.Customer;
import com.imf.delivery.DeliveryPerson;
import com.imf.products.Product;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private static int nextId = 0;
    private int orderId;
    private boolean delivered;
    private Customer customer;
    private DeliveryPerson deliveryPerson;
    private List<Product> products;
    private float totalPrice;
    private static List<Order> pendingOrders;
    private static List<Order> deliveredOrders;

    public Order(boolean delivered, Customer customer, DeliveryPerson deliveryPerson, List<Product> products,
                 float totalPrice) {
        this.orderId = ++nextId;
        nextId = this.orderId;
        this.delivered = delivered;
        this.customer = customer;
        this.deliveryPerson = deliveryPerson;
        this.products = products;
        this.totalPrice = totalPrice;
        pendingOrders = new ArrayList<>();
        deliveredOrders = new ArrayList<>();
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public boolean isDelivered() {
        return delivered;
    }

    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public DeliveryPerson getDeliveryPerson() {
        return deliveryPerson;
    }

    public void setDeliveryPerson(DeliveryPerson deliveryPerson) {
        this.deliveryPerson = deliveryPerson;
    }

    public static List<Order> getPendingOrders() {
        return pendingOrders;
    }

    public static List<Order> getDeliveredOrders() {
        return deliveredOrders;
    }

    @Override
    public String toString() {
        return "Order: \n" +
                "OrderId: " + orderId + "\n" +
                "Delivered: " + delivered + "\n" +
                "Customer: " + customer + "\n" +
                "Delivery Person: " + this.deliveryPerson.getFullName() + "\n" +
                "Products: " + this.products.toString() + "\n" +
                "Total Price: " + this.totalPrice;
    }
}
