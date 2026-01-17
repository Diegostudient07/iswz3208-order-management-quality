package com.qualityproject.model;

/**
 * Representa una orden que asocia un cliente con un producto.
 * Principio SRP: Esta clase solo se encarga de representar los datos de una orden.
 */
public class Order {
    private final String customerName;
    private final String productName;

    public Order(String customerName, String productName) {
        if (customerName == null || customerName.trim().isEmpty()) {
            throw new IllegalArgumentException("Customer name cannot be null or empty");
        }
        if (productName == null || productName.trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be null or empty");
        }
        this.customerName = customerName;
        this.productName = productName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getProductName() {
        return productName;
    }

    @Override
    public String toString() {
        return "Customer: " + customerName + ", Order: " + productName;
    }
}
