package com.qualityproject;

import com.qualityproject.repository.InMemoryOrderRepository;
import com.qualityproject.repository.OrderRepository;
import com.qualityproject.service.OrderService;
import com.qualityproject.service.OrderServiceImpl;
import com.qualityproject.ui.OrderConsoleUI;

/**
 * Clase principal de la aplicacion.
 * Principio SRP: Solo se encarga de ensamblar dependencias e iniciar la app.
 * Aqui se realiza la inyeccion de dependencias (Composition Root).
 */
public class OrderManagerApp {

    public static void main(String[] args) {
        // Composicion de dependencias (DIP aplicado)
        OrderRepository repository = new InMemoryOrderRepository();
        OrderService service = new OrderServiceImpl(repository);
        OrderConsoleUI ui = new OrderConsoleUI(service);

        // Ejecucion de la aplicacion
        ui.addOrder("Alice", "Laptop");
        ui.addOrder("Bob", "Phone");
        ui.displayAllOrders();
    }
}
