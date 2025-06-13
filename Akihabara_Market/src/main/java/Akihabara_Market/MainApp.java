package Akihabara_Market;

import java.util.List;

import dao.ProductoDAO;
import model.Productootaku;
import service.LlmService;
import util.SetupDatos;
import view.InterfazConsola;

public class MainApp {
    public static void main(String[] args) {
        InterfazConsola consola = new InterfazConsola();
        ProductoDAO dao = new ProductoDAO();

        SetupDatos.cargarDatosIniciales();

        boolean continuar = true;

        while (continuar) {
            consola.mostrarMenuPrincipal();
            int opcion = consola.leerOpcionMenu();

            switch (opcion) {
                case 1: {
                    Productootaku nuevo = consola.leerDatosProductoNuevo();
                    dao.agregarProducto(nuevo);
                    consola.mostrarMensaje("Producto añadido con éxito.");
                    break;
                }
                case 2: {
                    int id = consola.leerIdProducto();
                    Productootaku producto = dao.obtenerProductoPorId(id);
                    consola.mostrarProducto(producto);
                    break;
                }
                case 3: {
                    List<Productootaku> productos = dao.obtenerTodosLosProductos();
                    consola.mostrarListaProductos(productos);
                    break;
                }
                case 4: {
                    int id = consola.leerIdProducto();
                    Productootaku actualizado = consola.leerDatosProductoActualizado(id);
                    boolean exito = dao.actualizarProducto(actualizado);
                    consola.mostrarMensaje(exito ? "Producto actualizado." : "Error al actualizar.");
                    break;
                }
                case 5: {
                    int id = consola.leerIdProducto();
                    boolean eliminado = dao.eliminarProducto(id);
                    consola.mostrarMensaje(eliminado ? "Producto eliminado." : "No se pudo eliminar.");
                    break;
                }
                case 6: {
                    boolean seguirIA = true;
                    while (seguirIA) {
                        consola.mostrarSubmenuIA();
                        int opcionIA = consola.leerOpcionMenu();

                        switch (opcionIA) {
                            case 1: { // Generar descripción con la IA que he puesto
                                int id = consola.leerIdProducto();
                                Productootaku producto = dao.obtenerProductoPorId(id);
                                if (producto != null) {
                                    String prompt = "Genera una descripción de marketing breve y atractiva para el producto otaku: "
                                            + producto.getNombre() + " de la categoría " + producto.getCategoria() + ".";
                                    try {
                                        String respuesta = LlmService.sendPrompt(prompt);
                                        consola.mostrarMensaje("Descripción generada por IA:\n" + respuesta);
                                    } catch (Exception e) {
                                        consola.mostrarMensaje("Error al contactar la IA: " + e.getMessage());
                                    }
                                } else {
                                    consola.mostrarMensaje("Producto no encontrado.");
                                }
                                break;
                            }
                            case 2: { // Sugerir categoría con la IA
                                consola.mostrarMensaje("Introduce el nombre del nuevo producto:");
                                String nombreProducto = consola.leerLinea();
                                String prompt = "Para un producto otaku llamado '" + nombreProducto +
                                        "', sugiere una categoría adecuada de esta lista: Figura, Manga, Póster, Llavero, Ropa, Videojuego, Otro.";
                                try {
                                    String respuesta = LlmService.sendPrompt(prompt);
                                    consola.mostrarMensaje("Categoría sugerida por IA: " + respuesta);
                                } catch (Exception e) {
                                    consola.mostrarMensaje("Error al contactar la IA: " + e.getMessage());
                                }
                                break;
                            }
                            case 3: {
                                seguirIA = false;
                                break;
                            }
                            default:
                                consola.mostrarMensaje("Opción inválida. Intenta de nuevo.");
                        }
                    }
                    break;
                }
                case 7: {
                    consola.mostrarMensaje("¡Hasta luego, otaku!");
                    continuar = false;
                    break;
                }
                default:
                    consola.mostrarMensaje("Opción inválida. Intenta de nuevo.");
            }
        }
    }
}
