package com.imf.orders;

import com.imf.customers.Customer;
import com.imf.delivery.DeliveryPerson;

import java.util.List;

public class Order {
    private static int nextId = 0;
    private int orderId;
    private boolean delivered;
    private Customer customer;
    private DeliveryPerson deliveryPerson;
    private static List<Order> pendingOrders;
    private static List<Order> deliveredOrders;


}
