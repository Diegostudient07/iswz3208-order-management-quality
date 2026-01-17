package com.qualityproject.repository;

import com.qualityproject.model.Order;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementacion en memoria del repositorio de ordenes.
 * Principio SRP: Solo se encarga del almacenamiento de datos.
 * Principio OCP: Puede ser reemplazada por otras implementaciones.
 */
public class InMemoryOrderRepository implements OrderRepository {
    
    private final List<Order> orders = new ArrayList<>();

    @Override
    public void save(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }
        orders.add(order);
    }

    @Override
    public List<Order> findAll() {
        return new ArrayList<>(orders);
    }

    @Override
    public List<Order> findByCustomerName(String customerName) {
        if (customerName == null) {
            throw new IllegalArgumentException("Customer name cannot be null");
        }
        return orders.stream()
                .filter(order -> order.getCustomerName().equals(customerName))
                .collect(Collectors.toList());
    }
}
