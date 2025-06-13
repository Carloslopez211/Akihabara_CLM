package util;

//Setup de los datos 
import java.util.List;

import dao.ProductoDAO;
import model.Productootaku;

public class SetupDatos {

 public static void cargarDatosIniciales() {
     ProductoDAO dao = new ProductoDAO();
     List<Productootaku> productos = dao.obtenerTodosLosProductos();

     if (productos.isEmpty()) {
         System.out.println("Base de datos vacía. Insertando productos de ejemplo...");

         Productootaku p1 = new Productootaku("Figura de Anya Forger", "Figura", 59.95, 8);
         Productootaku p2 = new Productootaku("Manga Chainsaw Man Vol.1", "Manga", 9.99, 20);
         Productootaku p3 = new Productootaku("Póster Studio Ghibli Colección", "Póster", 15.50, 15);

         dao.agregarProducto(p1);
         dao.agregarProducto(p2);
         dao.agregarProducto(p3);

         System.out.println("Productos de ejemplo insertados correctamente.");
     } else {
         System.out.println("La base de datos ya contiene productos. No se insertó nada.");
     }
 }

 public static void main(String[] args) {
     cargarDatosIniciales();
 }
}
