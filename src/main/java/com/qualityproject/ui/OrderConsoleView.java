package com.qualityproject.ui;

import com.qualityproject.model.Order;
import com.qualityproject.service.OrderService;
import java.util.List;

/**
 * Clase para la presentacion de ordenes por consola.
 * Principio SRP: Solo se encarga de la salida por consola.
 * Principio DIP: Depende de la abstraccion OrderService.
 */
public class OrderConsoleView {

  private final OrderService orderService;

  /**
   * Constructor con inyeccion de dependencias.
   *
   * @param orderService servicio de ordenes
   * @throws IllegalArgumentException si orderService es nulo
   */
  public OrderConsoleView(OrderService orderService) {
    if (orderService == null) {
      throw new IllegalArgumentException("OrderService cannot be null");
    }
    this.orderService = orderService;
  }

  /**
   * Registra una orden y muestra confirmacion por consola.
   *
   * @param customerName nombre del cliente
   * @param productName  nombre del producto
   */
  public void addOrder(String customerName, String productName) {
    Order order = orderService.registerOrder(customerName, productName);
    System.out.println("Order added for " + order.getCustomerName());
  }

  /**
   * Muestra todas las ordenes por consola.
   */
  public void displayAllOrders() {
    List<Order> orders = orderService.getAllOrders();
    for (Order order : orders) {
      System.out.println(order);
    }
  }

  /**
   * Muestra las ordenes de un cliente especifico.
   *
   * @param customerName nombre del cliente
   */
  public void displayOrdersByCustomer(String customerName) {
    List<Order> orders = orderService.getOrdersByCustomer(customerName);
    System.out.println("Orders for " + customerName + ":");
    for (Order order : orders) {
      System.out.println("  - " + order.getProductName());
    }
  }
}
