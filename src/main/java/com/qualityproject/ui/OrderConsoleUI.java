package com.qualityproject.ui;

import com.qualityproject.model.Order;
import com.qualityproject.service.OrderService;
import java.util.List;

/**
 * Clase para la presentacion de ordenes por consola.
 * Principio SRP: Solo se encarga de la salida por consola.
 * Principio DIP: Depende de la abstraccion OrderService.
 */
public class OrderConsoleUI {
    
    private final OrderService orderService;

    public OrderConsoleUI(OrderService orderService) {
        if (orderService == null) {
            throw new IllegalArgumentException("OrderService cannot be null");
        }
        this.orderService = orderService;
    }

    public void addOrder(String customerName, String productName) {
        Order order = orderService.registerOrder(customerName, productName);
        System.out.println("Order added for " + order.getCustomerName());
    }

    public void displayAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        for (Order order : orders) {
            System.out.println(order);
        }
    }

    public void displayOrdersByCustomer(String customerName) {
        List<Order> orders = orderService.getOrdersByCustomer(customerName);
        System.out.println("Orders for " + customerName + ":");
        for (Order order : orders) {
            System.out.println("  - " + order.getProductName());
        }
    }
}
