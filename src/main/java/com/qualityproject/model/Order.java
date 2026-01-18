package com.qualityproject.model;

/**
 * Representa una orden que asocia un cliente con un producto.
 * Principio SRP: Esta clase solo se encarga de representar los datos de una
 * orden.
 */
public class Order {
  private final String customerName;
  private final String productName;

  /**
   * Constructor de Order.
   *
   * @param customerName nombre del cliente
   * @param productName  nombre del producto
   * @throws IllegalArgumentException si customerName o productName es nulo o vacio
   */
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

  /**
   * Obtiene el nombre del cliente.
   *
   * @return nombre del cliente
   */
  public String getCustomerName() {
    return customerName;
  }

  /**
   * Obtiene el nombre del producto.
   *
   * @return nombre del producto
   */
  public String getProductName() {
    return productName;
  }

  /**
   * Representacion en cadena de la orden.
   *
   * @return cadena con formato "Customer: X, Order: Y"
   */
  @Override
  public String toString() {
    return "Customer: " + customerName + ", Order: " + productName;
  }
}
