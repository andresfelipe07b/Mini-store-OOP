package org.example.view;

import org.example.model.Alimento;
import org.example.model.Electrodomestico;
import org.example.model.Producto;
import org.example.service.ITiendaService;

import javax.swing.*;

public class MenuPrincipal {
    private ITiendaService tiendaService;

    public MenuPrincipal(ITiendaService tiendaService) {
        this.tiendaService = tiendaService;
    }

    public void mostrarMenu() {
        String opcion;
        do {
            opcion = JOptionPane.showInputDialog(null,
                    "=== MENÚ MINI TIENDA ===\n"
                            + "1. Agregar producto\n"
                            + "2. Listar inventario\n"
                            + "3. Comprar producto\n"
                            + "4. Mostrar estadísticas\n"
                            + "5. Buscar producto\n"
                            + "6. Salir\n\n"
                            + "Seleccione una opción:");

            if (opcion == null) {
                break; // Salir si el usuario cierra el diálogo
            }

            switch (opcion) {
                case "1":
                    agregarProducto();
                    break;
                case "2":
                    listarInventario();
                    break;
                case "3":
                    comprarProducto();
                    break;
                case "4":
                    mostrarEstadisticas();
                    break;
                case "5":
                    buscarProducto();
                    break;
                case "6":
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida.");
            }
        } while (!"6".equals(opcion));

        JOptionPane.showMessageDialog(null, tiendaService.obtenerTicketFinal() + "\n¡Gracias por su visita!");
    }

    private void agregarProducto() {
        String tipo = JOptionPane.showInputDialog("¿Qué tipo de producto desea agregar? (Alimento / Electrodomestico)");
        if (tipo == null || tipo.trim().isEmpty()) return;

        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del producto:");
        if (nombre == null || nombre.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El nombre no puede estar vacío.");
            return;
        }

        try {
            double precio = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el precio:"));
            if (precio <= 0) {
                JOptionPane.showMessageDialog(null, "El precio debe ser positivo.");
                return;
            }

            int stock = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el stock inicial:"));
            if (stock < 0) {
                JOptionPane.showMessageDialog(null, "El stock no puede ser negativo.");
                return;
            }

            Producto producto;
            if (tipo.equalsIgnoreCase("Alimento")) {
                producto = new Alimento(nombre, precio);
            } else if (tipo.equalsIgnoreCase("Electrodomestico")) {
                producto = new Electrodomestico(nombre, precio);
            } else {
                JOptionPane.showMessageDialog(null, "Tipo de producto no válido.");
                return;
            }

            tiendaService.agregarProducto(producto, stock);
            JOptionPane.showMessageDialog(null, "Producto agregado con éxito.");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Formato de número no válido.");
        }
    }

    private void listarInventario() {
        String inventario = tiendaService.listarInventario();
        JOptionPane.showMessageDialog(null, inventario);
    }

    private void comprarProducto() {
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del producto a comprar:");
        if (nombre == null || nombre.trim().isEmpty()) return;

        try {
            int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad:"));
            if (cantidad <= 0) {
                JOptionPane.showMessageDialog(null, "La cantidad debe ser positiva.");
                return;
            }

            String resultado = tiendaService.comprarProducto(nombre, cantidad);
            JOptionPane.showMessageDialog(null, resultado);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Formato de número no válido.");
        }
    }

    private void mostrarEstadisticas() {
        String estadisticas = tiendaService.obtenerEstadisticas();
        JOptionPane.showMessageDialog(null, estadisticas);
    }

    private void buscarProducto() {
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre (o parte del nombre) del producto a buscar:");
        if (nombre == null || nombre.trim().isEmpty()) return;

        String resultado = tiendaService.buscarProducto(nombre);
        JOptionPane.showMessageDialog(null, resultado);
    }
}
