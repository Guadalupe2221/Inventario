import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Inventario {

    // Un HashMap para almacenar los productos y su cantidad
    private Map<String, Integer> productos;

    public Inventario() {
        productos = new HashMap<>();
    }

    // Método para agregar stock a un producto existente o nuevo
    public void agregarProducto(String nombre, int cantidad) {
        if (productos.containsKey(nombre)) {
            productos.put(nombre, productos.get(nombre) + cantidad);
        } else {
            productos.put(nombre, cantidad);
        }
        System.out.println("Producto agregado/actualizado con éxito.");
    }

    // Método para retirar stock de un producto
    public boolean retirarProducto(String nombre, int cantidad) {
        if (productos.containsKey(nombre) && productos.get(nombre) >= cantidad) {
            productos.put(nombre, productos.get(nombre) - cantidad);
            System.out.println("Producto retirado con éxito.");
            return true;
        } else {
            System.out.println("Error: No hay suficiente stock o el producto no existe.");
            return false;
        }
    }

    // Método para verificar si un producto está disponible
    public boolean estaDisponible(String nombre) {
        return productos.containsKey(nombre) && productos.get(nombre) > 0;
    }

    // Método para imprimir el inventario
    public void mostrarInventario() {
        System.out.println("\nInventario actual:");
        if (productos.isEmpty()) {
            System.out.println("El inventario está vacío.");
        } else {
            for (Map.Entry<String, Integer> entry : productos.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue() + " unidades");
            }
        }
    }

    // Método principal que incluye un menú interactivo
    public static void main(String[] args) {
        Inventario inventario = new Inventario();
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println("\n--- Menú de Inventario ---");
            System.out.println("1. Agregar producto");
            System.out.println("2. Retirar producto");
            System.out.println("3. Verificar disponibilidad de un producto");
            System.out.println("4. Mostrar inventario");
            System.out.println("5. Salir");
            System.out.print("Selecciona una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea después de la opción

            switch (opcion) {
                case 1:
                    System.out.print("Ingresa el nombre del producto: ");
                    String nombreAgregar = scanner.nextLine();
                    System.out.print("Ingresa la cantidad: ");
                    int cantidadAgregar = scanner.nextInt();
                    inventario.agregarProducto(nombreAgregar, cantidadAgregar);
                    break;

                case 2:
                    System.out.print("Ingresa el nombre del producto: ");
                    String nombreRetirar = scanner.nextLine();
                    System.out.print("Ingresa la cantidad a retirar: ");
                    int cantidadRetirar = scanner.nextInt();
                    inventario.retirarProducto(nombreRetirar, cantidadRetirar);
                    break;

                case 3:
                    System.out.print("Ingresa el nombre del producto: ");
                    String nombreVerificar = scanner.nextLine();
                    if (inventario.estaDisponible(nombreVerificar)) {
                        System.out.println("El producto " + nombreVerificar + " está disponible.");
                    } else {
                        System.out.println("El producto " + nombreVerificar + " no está disponible.");
                    }
                    break;

                case 4:
                    inventario.mostrarInventario();
                    break;

                case 5:
                    salir = true;
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("Opción no válida. Por favor, selecciona una opción del 1 al 5.");
                    break;
            }
        }

        scanner.close();
    }
}