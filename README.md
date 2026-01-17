# Order Management System - Proyecto de Calidad de Software

## Descripción

Sistema para registrar y procesar órdenes, asociando un cliente con los productos ordenados.

Este proyecto es una plantilla base que contiene **problemas de calidad intencionales** para ser identificados y corregidos como ejercicio de mejora de código.

## Estructura del Proyecto

```
src/
├── main/
│   └── java/
│       └── com/
│           └── qualityproject/
│               └── OrderManager.java    # Clase principal con problemas de calidad
└── test/
    └── java/
        └── com/
            └── qualityproject/
                └── OrderManagerTest.java # Pruebas unitarias (por implementar)
```

## Problemas de Calidad a Identificar

El código inicial (`OrderManager.java`) contiene los siguientes problemas que deben ser encontrados y corregidos:

### 1. Mala Cohesión
- La lógica de procesamiento de órdenes está mezclada con el manejo de datos
- Una sola clase maneja múltiples responsabilidades

### 2. Nombres Ambiguos
- Los métodos no reflejan claramente su propósito
- Las variables no son descriptivas

### 3. Falta de Pruebas
- No se asegura que los datos ingresados sean consistentes
- No hay validación de entradas
- Las pruebas unitarias no están implementadas

### 4. Sin Modularidad
- Todo el código está en una sola clase
- No hay separación de responsabilidades (SRP)
- No hay abstracción de entidades (Customer, Order, Product)

### 5. Problemas Adicionales
- Uso de `List` sin especificar el tipo genérico (raw types)
- Acoplamiento directo a `System.out.println` (dificulta testing)
- No hay manejo de errores
- No hay encapsulamiento apropiado de datos

## Requisitos

- Java 17 o superior
- Maven 3.6 o superior

## Ejecución

### Compilar
```bash
mvn compile
```

### Ejecutar
```bash
mvn exec:java -Dexec.mainClass="com.qualityproject.OrderManager"
```

### Ejecutar Tests
```bash
mvn test
```

## Objetivo del Ejercicio

Refactorizar el código aplicando principios de:
- **SOLID**: Especialmente SRP (Single Responsibility Principle)
- **Clean Code**: Nombres significativos, funciones pequeñas
- **Testing**: Pruebas unitarias con JUnit 5
- **Modularidad**: Separación en clases con responsabilidades claras
