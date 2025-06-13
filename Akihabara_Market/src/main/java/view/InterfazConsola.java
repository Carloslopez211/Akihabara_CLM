package view;

import java.util.List;
import java.util.Scanner;

import model.Productootaku;

public class InterfazConsola {
    private final Scanner scanner = new Scanner(System.in);

    public void mostrarMenuPrincipal() {
        System.out.println("\n=== AKIHABARA INVENTARIO ===");
        System.out.println("1. Añadir nuevo producto");
        System.out.println("2. Consultar producto por ID");
        System.out.println("3. Ver todos los productos");
        System.out.println("4. Actualizar producto");
        System.out.println("5. Eliminar producto");
        System.out.println("6. Asistente IA");
        System.out.println("7. Salir");
        System.out.print("Selecciona una opción: ");
    }

    public void mostrarSubmenuIA() {
        System.out.println("\n--- Asistente IA ---");
        System.out.println("1. Generar descripción de producto");
        System.out.println("2. Sugerir categoría para nuevo producto");
        System.out.println("3. Volver al menú principal");
        System.out.print("Selecciona una opción: ");
    }

    public int leerOpcionMenu() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public String leerLinea() {
        return scanner.nextLine();
    }

    public Productootaku leerDatosProductoNuevo() {
        System.out.println("\n--- Añadir nuevo producto ---");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Categoría (Figura, Manga, Póster, etc.): ");
        String categoria = scanner.nextLine();

        System.out.print("Precio: ");
        double precio = Double.parseDouble(scanner.nextLine());

        System.out.print("Stock: ");
        int stock = Integer.parseInt(scanner.nextLine());

        return new Productootaku(nombre, categoria, precio, stock);
    }

    public int leerIdProducto() {
        System.out.print("Introduce el ID del producto: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public Productootaku leerDatosProductoActualizado(int idExistente) {
        System.out.println("\n--- Actualizar producto ---");
        System.out.print("Nuevo nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Nueva categoría: ");
        String categoria = scanner.nextLine();

        System.out.print("Nuevo precio: ");
        double precio = Double.parseDouble(scanner.nextLine());

        System.out.print("Nuevo stock: ");
        int stock = Integer.parseInt(scanner.nextLine());

        return new Productootaku(idExistente, nombre, categoria, precio, stock);
    }

    public void mostrarProducto(Productootaku p) {
        if (p != null) {
            System.out.println(String.format("ID: %d | Nombre: %s | Categoría: %s | Precio: $%.2f | Stock: %d",
                    p.getId(), p.getNombre(), p.getCategoria(), p.getPrecio(), p.getStock()));
        } else {
            System.out.println("Producto no encontrado.");
        }
    }

    public void mostrarListaProductos(List<Productootaku> productos) {
        System.out.println("\n--- Productos en Inventario ---");
        if (productos.isEmpty()) {
            System.out.println("No hay productos registrados.");
        } else {
            for (Productootaku p : productos) {
                mostrarProducto(p);
            }
        }
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}
