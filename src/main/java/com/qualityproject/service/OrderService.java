package com.qualityproject.service;

import com.qualityproject.model.Order;
import java.util.List;

/**
 * Interfaz para el servicio de gestion de ordenes.
 * Principio DIP: Las clases de alto nivel dependen de esta abstraccion.
 * Principio ISP: Interfaz especifica para operaciones de negocio.
 */
public interface OrderService {
    
    Order registerOrder(String customerName, String productName);
    
    List<Order> getAllOrders();
    
    List<Order> getOrdersByCustomer(String customerName);
}
