package com.qualityproject.service;

import com.qualityproject.model.Order;
import java.util.List;

/**
 * Interfaz para el servicio de gestion de ordenes.
 * Principio DIP: Las clases de alto nivel dependen de esta abstraccion.
 * Principio ISP: Interfaz especifica para operaciones de negocio.
 */
public interface OrderService {

  /**
   * Registra una nueva orden.
   *
   * @param customerName nombre del cliente
   * @param productName  nombre del producto
   * @return la orden creada
   */
  Order registerOrder(String customerName, String productName);

  /**
   * Obtiene todas las ordenes.
   *
   * @return lista de todas las ordenes
   */
  List<Order> getAllOrders();

  /**
   * Obtiene las ordenes de un cliente.
   *
   * @param customerName nombre del cliente
   * @return lista de ordenes del cliente
   */
  List<Order> getOrdersByCustomer(String customerName);
}
