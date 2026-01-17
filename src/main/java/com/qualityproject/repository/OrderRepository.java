package com.qualityproject.repository;

import com.qualityproject.model.Order;
import java.util.List;

/**
 * Interfaz para el repositorio de ordenes.
 * Principio DIP: Las clases de alto nivel dependen de esta abstraccion.
 * Principio ISP: Interfaz pequena y especifica.
 */
public interface OrderRepository {

    /**
     * Guarda una orden en el repositorio.
     *
     * @param order la orden a guardar
     */
    void save(Order order);

    /**
     * Obtiene todas las ordenes almacenadas.
     *
     * @return lista de todas las ordenes
     */
    List<Order> findAll();

    /**
     * Busca ordenes por nombre de cliente.
     *
     * @param customerName nombre del cliente
     * @return lista de ordenes del cliente
     */
    List<Order> findByCustomerName(String customerName);
}
