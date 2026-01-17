package com.qualityproject.repository;

import com.qualityproject.model.Order;
import java.util.List;

/**
 * Interfaz para el repositorio de ordenes.
 * Principio DIP: Las clases de alto nivel dependen de esta abstraccion.
 * Principio ISP: Interfaz pequena y especifica.
 */
public interface OrderRepository {
    
    void save(Order order);
    
    List<Order> findAll();
    
    List<Order> findByCustomerName(String customerName);
}
