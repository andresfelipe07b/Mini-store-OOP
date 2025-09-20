package org.example.service;

import org.example.model.Producto;

import java.util.ArrayList;
import java.util.HashMap;

public class TiendaServiceImpl implements ITiendaService {
    private ArrayList<Producto> productos = new ArrayList<>();
    private HashMap<String, Integer> stock = new HashMap<>();
    private double totalCompras = 0.0;

    @Override
    public void agregarProducto(Producto producto, int stockInicial) {
        if (!productos.stream().anyMatch(p -> p.getNombre().equalsIgnoreCase(producto.getNombre()))) {
            productos.add(producto);
            stock.put(producto.getNombre(), stockInicial);
        }
    }

    @Override
    public String listarInventario() {
        if (productos.isEmpty()) {
            return "El inventario está vacío.";
        }
        StringBuilder sb = new StringBuilder("=== Inventario ===\n");
        for (Producto p : productos) {
            sb.append(String.format("Nombre: %s, Precio: $%.2f, Stock: %d, Desc: %s\n",
                    p.getNombre(), p.getPrecio(), stock.get(p.getNombre()), p.getDescripcion()));
        }
        return sb.toString();
    }

    @Override
    public String comprarProducto(String nombre, int cantidad) {
        Producto producto = productos.stream()
                .filter(p -> p.getNombre().equalsIgnoreCase(nombre))
                .findFirst()
                .orElse(null);

        if (producto == null) {
            return "Producto no encontrado.";
        }

        int stockActual = stock.get(nombre);
        if (cantidad > stockActual) {
            return "No hay suficiente stock.";
        }

        stock.put(nombre, stockActual - cantidad);
        double total = producto.getPrecio() * cantidad;
        totalCompras += total;
        return "Compra realizada con éxito. Total: $" + String.format("%.2f", total);
    }

    @Override
    public String obtenerEstadisticas() {
        if (productos.isEmpty()) {
            return "No hay productos para generar estadísticas.";
        }

        Producto masCaro = productos.get(0);
        Producto masBarato = productos.get(0);

        for (Producto p : productos) {
            if (p.getPrecio() > masCaro.getPrecio()) {
                masCaro = p;
            }
            if (p.getPrecio() < masBarato.getPrecio()) {
                masBarato = p;
            }
        }

        return String.format("Producto más caro: %s ($%.2f)\nProducto más barato: %s ($%.2f)",
                masCaro.getNombre(), masCaro.getPrecio(), masBarato.getNombre(), masBarato.getPrecio());
    }

    @Override
    public String buscarProducto(String nombre) {
        StringBuilder sb = new StringBuilder("Resultados de la búsqueda:\n");
        boolean encontrado = false;
        for (Producto p : productos) {
            if (p.getNombre().toLowerCase().contains(nombre.toLowerCase())) {
                sb.append(String.format("Nombre: %s, Precio: $%.2f, Stock: %d\n",
                        p.getNombre(), p.getPrecio(), stock.get(p.getNombre())));
                encontrado = true;
            }
        }
        return encontrado ? sb.toString() : "No se encontraron productos.";
    }

    @Override
    public String obtenerTicketFinal() {
        return "Total de todas las compras: $" + String.format("%.2f", totalCompras);
    }
}
