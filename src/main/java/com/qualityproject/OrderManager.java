package com.qualityproject;

import java.util.ArrayList;
import java.util.List;

/**
 * Gestor de ordenes original (codigo legacy con problemas de calidad).
 * Este archivo se mantiene como referencia del codigo original.
 *
 * @deprecated Usar OrderManagerApp con la nueva arquitectura SOLID.
 */
@Deprecated
public class OrderManager {
  private final List<String> customers = new ArrayList<>();
  private final List<String> orders = new ArrayList<>();

  /**
   * Agrega una orden para un cliente.
   *
   * @param customer nombre del cliente
   * @param order    nombre del producto
   */
  public void addOrder(final String customer, final String order) {
    customers.add(customer);
    orders.add(order);
    System.out.println("Order added for " + customer);
  }

  /**
   * Lista todas las ordenes por consola.
   */
  public void listOrders() {
    for (int i = 0; i < orders.size(); i++) {
      System.out.println("Customer: " + customers.get(i) + ", Order: " + orders.get(i));
    }
  }

  /**
   * Punto de entrada principal.
   *
   * @param args argumentos de linea de comandos
   */
  public static void main(final String[] args) {
    final OrderManager orderManager = new OrderManager();
    orderManager.addOrder("Alice", "Laptop");
    orderManager.addOrder("Bob", "Phone");
    orderManager.listOrders();
  }
}
