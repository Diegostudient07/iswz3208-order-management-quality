package com.qualityproject.service;

import com.qualityproject.model.Order;
import com.qualityproject.repository.OrderRepository;
import java.util.List;

/**
 * Implementacion del servicio de gestion de ordenes.
 */
public class OrderServiceImpl implements OrderService {

  private final OrderRepository orderRepository;

  /**
   * Constructor.
   *
   * @param orderRepository repositorio de ordenes
   */
  public OrderServiceImpl(OrderRepository orderRepository) {
    if (orderRepository == null) {
      throw new IllegalArgumentException("OrderRepository cannot be null");
    }
    this.orderRepository = orderRepository;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Order registerOrder(String customerName, String productName) {
    Order order = new Order(customerName, productName);
    orderRepository.save(order);
    return order;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Order> getAllOrders() {
    return orderRepository.findAll();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Order> getOrdersByCustomer(String customerName) {
    return orderRepository.findByCustomerName(customerName);
  }
}
