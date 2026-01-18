package com.qualityproject;

import com.qualityproject.repository.InMemoryOrderRepository;
import com.qualityproject.repository.OrderRepository;
import com.qualityproject.service.OrderService;
import com.qualityproject.service.OrderServiceImpl;
import com.qualityproject.ui.OrderConsoleView;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * Clase principal de la aplicacion.
 * Principio SRP: Solo se encarga de ensamblar dependencias e iniciar la app.
 * Aqui se realiza la inyeccion de dependencias (Composition Root).
 */
public class OrderManagerApp {

  private final OrderConsoleView view;
  private final Scanner scanner;
  private final PrintStream out;
  private boolean running;

  /**
   * Constructor con dependencias inyectadas para testing.
   *
   * @param view vista de consola
   * @param input flujo de entrada
   * @param out flujo de salida
   */
  public OrderManagerApp(final OrderConsoleView view, final InputStream input, final PrintStream out) {
    if (view == null || input == null || out == null) {
      throw new IllegalArgumentException("Arguments cannot be null");
    }
    this.view = view;
    this.scanner = new Scanner(input, StandardCharsets.UTF_8);
    this.out = out;
    this.running = true;
  }

  /**
   * Muestra el menu principal.
   */
  public void showMenu() {
    out.println("\n--- Menu ---");
    out.println("1. Registrar orden");
    out.println("2. Listar todas las ordenes");
    out.println("3. Buscar ordenes por cliente");
    out.println("4. Salir");
    out.print("Seleccione una opcion: ");
  }

  /**
   * Procesa la opcion seleccionada.
   *
   * @param option opcion del menu
   */
  public void processOption(final String option) {
    switch (option) {
      case "1":
        out.print("Ingrese nombre del cliente: ");
        final String customer = scanner.nextLine().trim();
        out.print("Ingrese nombre del producto: ");
        final String product = scanner.nextLine().trim();
        if (customer.isEmpty() || product.isEmpty()) {
          out.println("Error: Cliente y producto no pueden estar vacios.");
        } else {
          view.addOrder(customer, product);
        }
        break;
      case "2":
        out.println("\n--- Todas las Ordenes ---");
        view.displayAllOrders();
        break;
      case "3":
        out.print("Ingrese nombre del cliente a buscar: ");
        final String searchCustomer = scanner.nextLine().trim();
        if (searchCustomer.isEmpty()) {
          out.println("Error: El nombre del cliente no puede estar vacio.");
        } else {
          view.displayOrdersByCustomer(searchCustomer);
        }
        break;
      case "4":
        running = false;
        out.println("Gracias por usar el sistema. Adios!");
        break;
      default:
        out.println("Opcion no valida. Intente de nuevo.");
        break;
    }
  }

  /**
   * Ejecuta el bucle principal de la aplicacion.
   */
  public void run() {
    out.println("=================================");
    out.println("  Sistema de Gestion de Ordenes  ");
    out.println("=================================");

    while (running) {
      showMenu();
      final String option = scanner.nextLine().trim();
      processOption(option);
    }
  }

  /**
   * Indica si la aplicacion sigue corriendo.
   *
   * @return true si sigue corriendo
   */
  public boolean isRunning() {
    return running;
  }

  /**
   * Punto de entrada de la aplicacion.
   *
   * @param args argumentos de linea de comandos
   */
  public static void main(final String[] args) {
    final OrderRepository repository = new InMemoryOrderRepository();
    final OrderService service = new OrderServiceImpl(repository);
    final OrderConsoleView view = new OrderConsoleView(service);

    final OrderManagerApp app = new OrderManagerApp(view, System.in, System.out);
    app.run();
  }
}
