package com.qualityproject;

import com.qualityproject.repository.InMemoryOrderRepository;
import com.qualityproject.repository.OrderRepository;
import com.qualityproject.service.OrderService;
import com.qualityproject.service.OrderServiceImpl;
import com.qualityproject.ui.OrderConsoleView;

/**
 * Clase principal de la aplicacion.
 * Principio SRP: Solo se encarga de ensamblar dependencias e iniciar la app.
 * Aqui se realiza la inyeccion de dependencias (Composition Root).
 */
public class OrderManagerApp {

  /**
   * Punto de entrada de la aplicacion.
   *
   * @param args argumentos de linea de comandos
   */
  public static void main(String[] args) {
    // Composicion de dependencias (DIP aplicado)
    OrderRepository repository = new InMemoryOrderRepository();
    OrderService service = new OrderServiceImpl(repository);
    OrderConsoleView view = new OrderConsoleView(service);

    // Ejecucion de la aplicacion
    view.addOrder("Alice", "Laptop");
    view.addOrder("Bob", "Phone");
    view.displayAllOrders();
  }
}
