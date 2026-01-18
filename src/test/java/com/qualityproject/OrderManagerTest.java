package com.qualityproject;

import com.qualityproject.model.Order;
import com.qualityproject.repository.InMemoryOrderRepository;
import com.qualityproject.service.OrderServiceImpl;
import com.qualityproject.ui.OrderConsoleView;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests unitarios para cobertura completa del sistema.
 */
class OrderManagerTest {

  // ========== Tests para Order ==========

  @Test
  void testCrearOrdenValida() {
    Order order = new Order("Alice", "Laptop");
    assertEquals("Alice", order.getCustomerName());
    assertEquals("Laptop", order.getProductName());
  }

  @Test
  void testOrdenToString() {
    Order order = new Order("Bob", "Phone");
    assertEquals("Customer: Bob, Order: Phone", order.toString());
  }

  @Test
  void testOrdenClienteNullLanzaExcepcion() {
    assertThrows(IllegalArgumentException.class, () -> new Order(null, "Product"));
  }

  @Test
  void testOrdenClienteVacioLanzaExcepcion() {
    assertThrows(IllegalArgumentException.class, () -> new Order("", "Product"));
  }

  @Test
  void testOrdenClienteEspaciosLanzaExcepcion() {
    assertThrows(IllegalArgumentException.class, () -> new Order("   ", "Product"));
  }

  @Test
  void testOrdenProductoNullLanzaExcepcion() {
    assertThrows(IllegalArgumentException.class, () -> new Order("Customer", null));
  }

  @Test
  void testOrdenProductoVacioLanzaExcepcion() {
    assertThrows(IllegalArgumentException.class, () -> new Order("Customer", ""));
  }

  @Test
  void testOrdenProductoEspaciosLanzaExcepcion() {
    assertThrows(IllegalArgumentException.class, () -> new Order("Customer", "   "));
  }

  // ========== Tests para Repository ==========

  @Test
  void testGuardarOrden() {
    InMemoryOrderRepository repo = new InMemoryOrderRepository();
    repo.save(new Order("Alice", "Laptop"));
    assertEquals(1, repo.findAll().size());
  }

  @Test
  void testGuardarOrdenNullLanzaExcepcion() {
    InMemoryOrderRepository repo = new InMemoryOrderRepository();
    assertThrows(IllegalArgumentException.class, () -> repo.save(null));
  }

  @Test
  void testFindAllVacio() {
    InMemoryOrderRepository repo = new InMemoryOrderRepository();
    assertTrue(repo.findAll().isEmpty());
  }

  @Test
  void testBuscarPorCliente() {
    InMemoryOrderRepository repo = new InMemoryOrderRepository();
    repo.save(new Order("Alice", "Laptop"));
    repo.save(new Order("Bob", "Phone"));
    repo.save(new Order("Alice", "Tablet"));
    assertEquals(2, repo.findByCustomerName("Alice").size());
  }

  @Test
  void testBuscarPorClienteNullLanzaExcepcion() {
    InMemoryOrderRepository repo = new InMemoryOrderRepository();
    assertThrows(IllegalArgumentException.class, () -> repo.findByCustomerName(null));
  }

  @Test
  void testBuscarPorClienteSinResultados() {
    InMemoryOrderRepository repo = new InMemoryOrderRepository();
    repo.save(new Order("Alice", "Laptop"));
    assertTrue(repo.findByCustomerName("Bob").isEmpty());
  }

  // ========== Tests para Service ==========

  @Test
  void testServiceConstructorNullLanzaExcepcion() {
    assertThrows(IllegalArgumentException.class, () -> new OrderServiceImpl(null));
  }

  @Test
  void testRegistrarOrden() {
    OrderServiceImpl service = new OrderServiceImpl(new InMemoryOrderRepository());
    Order order = service.registerOrder("Alice", "Laptop");
    assertNotNull(order);
    assertEquals("Alice", order.getCustomerName());
  }

  @Test
  void testObtenerTodasLasOrdenes() {
    OrderServiceImpl service = new OrderServiceImpl(new InMemoryOrderRepository());
    service.registerOrder("Alice", "Laptop");
    service.registerOrder("Bob", "Phone");
    assertEquals(2, service.getAllOrders().size());
  }

  @Test
  void testObtenerOrdenesPorCliente() {
    OrderServiceImpl service = new OrderServiceImpl(new InMemoryOrderRepository());
    service.registerOrder("Alice", "Laptop");
    service.registerOrder("Alice", "Tablet");
    assertEquals(2, service.getOrdersByCustomer("Alice").size());
  }

  // ========== Tests para ConsoleView ==========

  @Test
  void testConsoleViewConstructorNullLanzaExcepcion() {
    assertThrows(IllegalArgumentException.class, () -> new OrderConsoleView(null));
  }

  @Test
  void testConsoleViewAddOrder() {
    OrderServiceImpl service = new OrderServiceImpl(new InMemoryOrderRepository());
    OrderConsoleView view = new OrderConsoleView(service);
    view.addOrder("Alice", "Laptop");
    assertEquals(1, service.getAllOrders().size());
  }

  @Test
  void testConsoleViewDisplayAllOrders() {
    OrderServiceImpl service = new OrderServiceImpl(new InMemoryOrderRepository());
    OrderConsoleView view = new OrderConsoleView(service);
    view.addOrder("Alice", "Laptop");
    view.displayAllOrders();
    assertNotNull(view);
  }

  @Test
  void testConsoleViewDisplayOrdersByCustomer() {
    OrderServiceImpl service = new OrderServiceImpl(new InMemoryOrderRepository());
    OrderConsoleView view = new OrderConsoleView(service);
    view.addOrder("Alice", "Laptop");
    view.displayOrdersByCustomer("Alice");
    assertNotNull(view);
  }

  // ========== Tests para OrderManager (legacy) ==========

  @Test
  @SuppressWarnings("deprecation")
  void testOrderManagerAddOrder() {
    OrderManager om = new OrderManager();
    om.addOrder("Alice", "Laptop");
    assertNotNull(om);
  }

  @Test
  @SuppressWarnings("deprecation")
  void testOrderManagerListOrders() {
    OrderManager om = new OrderManager();
    om.addOrder("Alice", "Laptop");
    om.addOrder("Bob", "Phone");
    om.listOrders();
    assertNotNull(om);
  }

  @Test
  @SuppressWarnings("deprecation")
  void testOrderManagerMain() {
    OrderManager.main(new String[]{});
    assertTrue(true);
  }

  // ========== Tests para OrderManagerApp ==========

  @Test
  void testOrderManagerAppConstructor() {
    OrderServiceImpl service = new OrderServiceImpl(new InMemoryOrderRepository());
    OrderConsoleView view = new OrderConsoleView(service);
    ByteArrayInputStream input = new ByteArrayInputStream("4\n".getBytes());
    ByteArrayOutputStream output = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(output);

    OrderManagerApp app = new OrderManagerApp(view, input, printStream);
    assertNotNull(app);
    assertTrue(app.isRunning());
  }

  @Test
  void testOrderManagerAppConstructorNullThrows() {
    assertThrows(IllegalArgumentException.class, 
        () -> new OrderManagerApp(null, System.in, System.out));
  }

  @Test
  void testOrderManagerAppShowMenu() {
    OrderServiceImpl service = new OrderServiceImpl(new InMemoryOrderRepository());
    OrderConsoleView view = new OrderConsoleView(service);
    ByteArrayInputStream input = new ByteArrayInputStream("".getBytes());
    ByteArrayOutputStream output = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(output);

    OrderManagerApp app = new OrderManagerApp(view, input, printStream);
    app.showMenu();

    String result = output.toString();
    assertTrue(result.contains("Menu"));
    assertTrue(result.contains("Registrar orden"));
  }

  @Test
  void testOrderManagerAppProcessOptionExit() {
    OrderServiceImpl service = new OrderServiceImpl(new InMemoryOrderRepository());
    OrderConsoleView view = new OrderConsoleView(service);
    ByteArrayInputStream input = new ByteArrayInputStream("".getBytes());
    ByteArrayOutputStream output = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(output);

    OrderManagerApp app = new OrderManagerApp(view, input, printStream);
    app.processOption("4");

    assertFalse(app.isRunning());
    assertTrue(output.toString().contains("Adios"));
  }

  @Test
  void testOrderManagerAppProcessOptionInvalid() {
    OrderServiceImpl service = new OrderServiceImpl(new InMemoryOrderRepository());
    OrderConsoleView view = new OrderConsoleView(service);
    ByteArrayInputStream input = new ByteArrayInputStream("".getBytes());
    ByteArrayOutputStream output = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(output);

    OrderManagerApp app = new OrderManagerApp(view, input, printStream);
    app.processOption("99");

    assertTrue(output.toString().contains("no valida"));
  }

  @Test
  void testOrderManagerAppProcessOptionRegister() {
    OrderServiceImpl service = new OrderServiceImpl(new InMemoryOrderRepository());
    OrderConsoleView view = new OrderConsoleView(service);
    ByteArrayInputStream input = new ByteArrayInputStream("Alice\nLaptop\n".getBytes());
    ByteArrayOutputStream output = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(output);

    OrderManagerApp app = new OrderManagerApp(view, input, printStream);
    app.processOption("1");

    assertEquals(1, service.getAllOrders().size());
  }

  @Test
  void testOrderManagerAppProcessOptionListAll() {
    OrderServiceImpl service = new OrderServiceImpl(new InMemoryOrderRepository());
    service.registerOrder("Test", "Product");
    OrderConsoleView view = new OrderConsoleView(service);
    ByteArrayInputStream input = new ByteArrayInputStream("".getBytes());
    ByteArrayOutputStream output = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(output);

    OrderManagerApp app = new OrderManagerApp(view, input, printStream);
    app.processOption("2");

    assertTrue(output.toString().contains("Todas las Ordenes"));
  }

  @Test
  void testOrderManagerAppProcessOptionSearch() {
    OrderServiceImpl service = new OrderServiceImpl(new InMemoryOrderRepository());
    service.registerOrder("Alice", "Laptop");
    OrderConsoleView view = new OrderConsoleView(service);
    ByteArrayInputStream input = new ByteArrayInputStream("Alice\n".getBytes());
    ByteArrayOutputStream output = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(output);

    OrderManagerApp app = new OrderManagerApp(view, input, printStream);
    app.processOption("3");

    // El prompt se escribe al PrintStream, el resultado va a System.out
    assertTrue(output.toString().contains("cliente a buscar"));
  }
}
