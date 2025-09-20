# Mini Store - Java OOP Project

This project is a simple console-based store management application built with Java. It demonstrates key Object-Oriented Programming (OOP) principles such as abstraction, encapsulation, inheritance, and polymorphism. The user interface is handled through `JOptionPane` dialog boxes.

## Features

- **Add Products**: Add new products to the inventory, specifying the type (Food or Appliance), name, price, and stock.
- **List Inventory**: Display a complete list of all products, including their price, stock, and a type-specific description.
- **Purchase Products**: Simulate a customer purchase by selecting a product and quantity. The stock is updated accordingly.
- **View Statistics**: Show basic inventory statistics, such as the most and least expensive products.
- **Search Products**: Find products by entering a full or partial name.
- **Exit and Final Ticket**: Display the total amount of all purchases made during the session upon exiting.

## Project Structure

The project follows a layered architecture to separate concerns, making the code cleaner, more scalable, and easier to maintain.

- `src/main/java/org/example/`
  - `MiniStore.java`: The main entry point of the application. It initializes the service and the view.
  - **`model/`**: Contains the data classes.
    - `Producto.java`: An abstract base class for all products, with shared attributes like `nombre` (name) and `precio` (price).
    - `Alimento.java`: A subclass representing food items.
    - `Electrodomestico.java`: A subclass representing appliances.
  - **`service/`**: Contains the business logic.
    - `ITiendaService.java`: An interface defining the contract for all store operations (adding, listing, buying, etc.).
    - `TiendaServiceImpl.java`: The concrete implementation of the service interface. It manages the product list, stock, and sales calculations.
  - **`view/`**: Contains the user interface logic.
    - `MenuPrincipal.java`: Handles all user interactions through `JOptionPane` dialogs, presenting the menu and processing user input.

## Core OOP Concepts Implemented

- **Abstraction**: `Producto` is an abstract class that defines a general template for what a product is, without providing a complete implementation.
- **Inheritance**: `Alimento` and `Electrodomestico` inherit common properties and methods from `Producto`.
- **Polymorphism**: The `getDescripcion()` method is overridden in each subclass to provide a specific description for that product type.
- **Encapsulation**: Data (attributes) in the model and service layers are kept private, with access controlled through public getters and setters.

## How to Run

1.  Compile all `.java` files.
2.  Run the `main` method in the `MiniStore.java` class.
3.  The application will launch a series of `JOptionPane` dialogs to guide you through the menu options.

## Technologies Used

- **Java**: Core programming language.
- **Java Swing (`JOptionPane`)**: For the simple graphical user interface.
