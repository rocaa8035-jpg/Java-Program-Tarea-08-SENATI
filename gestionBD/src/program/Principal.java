package program;

import java.util.Scanner;
import models.Cliente;
import models.ClienteDAO;

public class Principal {
    
    public static void main(String[] args) {
        
        Scanner t = new Scanner(System.in); // Objeto Scanner para capturar la entrada del usuario por consola
        
        ClienteDAO clienteDAO = new ClienteDAO();
        
        String opcion;
        int id;
        
        do { // Bucle infinito para mantener el programa en ejecución hasta que se elija '0'
            Cliente c = new Cliente(); // Se instancia un nuevo objeto Cliente en cada iteración del menú
            
            // Impresión del Menú de Opciones
            System.out.print("\nMenu Principal"
                           + "\n=============="
                           + "\n1 - Buscar"
                           + "\n2 - Editar"
                           + "\n3 - Nuevo"
                           + "\n4 - Eliminar"
                           + "\n0 - Salir"
                           + "\nOpcion: ");

            opcion = t.next(); // Captura la opción seleccionada por el usuario
            
            if (opcion.equals("1")) { // LÓGICA DE OPCIÓN 1: BUSCAR CLIENTE
                
                try {

                    System.out.print("Id: ");
                    id = t.nextInt();// Lee el ID a buscar
                    
                    c = clienteDAO.getBuscarPorId(id); // Llama al método del DAO para buscar en la base de datos
                    
                    if (c.getId()==0) { // Valida si el objeto retornado tiene un ID válido
                        
                        System.out.println("Cliente no encontrado");
                        
                    } else {
                        // Imprime los detalles del cliente encontrado usando un formato de cadena
                        System.out.println("================="); 
                        System.out.printf("Id: %d - Nombre: %s - NumRuc: %s - Direccion: %s - Telefono: %s",
                                c.getId(), c.getNombre(), c.getNumRuc(), c.getDireccion(), c.getTelefono());

                        Thread.sleep(5000); // Pausa la ejecución 5 segundos para que el usuario pueda leer la info
                    }
                    
                } catch (Exception e) {
                
                    e.printStackTrace(); // Imprime el error en consola en caso de fallo en la conexión o SQL
                }
                
            } else if (opcion.equals("2")) { // LÓGICA DE OPCIÓN 2: EDITAR CLIENTE

                try {

                    System.out.print("Id: ");
                    id = t.nextInt();
                    
                    t.nextLine(); // Limpiamos el buffer para quitar el 'Enter' que dejó nextInt()

                    c = clienteDAO.getBuscarPorId(id); // Primero verifica si existe
                    
                    if (c.getId()==0) {
                        
                        System.out.println("Cliente no encontrado");
                        
                    } else {
                        
                        System.out.println("================="); // Muestra los datos actuales
                        System.out.printf("Id: %d - Nombre: %s - NumRuc: %s - Direccion: %s - Telefono: %s",
                                c.getId(), c.getNombre(), c.getNumRuc(), c.getDireccion(), c.getTelefono());

                        // Pide los nuevos datos y los asigna al objeto 'c'
                        System.out.print("\n=================\nNuevo nombre (ENTER para mantener): "); 
                        String nombre = t.nextLine().trim(); // .trim() elimina espacios accidentales

                        if (!nombre.isEmpty()) {
                            c.setNombre(nombre);
                        } 
                        // El 'else' no es necesario porque si está vacío, simplemente no actualizamos el objeto

                        System.out.print("Nuevo numRuc (ENTER para mantener): ");
                        String ruc = t.nextLine().trim();
                        if (!ruc.isEmpty()) {
                            c.setNumRuc(ruc);
                        }

                        System.out.print("Nueva direccion (ENTER para mantener): ");
                        String direccion = t.nextLine().trim();
                        if (!direccion.isEmpty()) {
                            c.setDireccion(direccion);
                        }

                        System.out.print("Nuevo telefono (ENTER para mantener): ");
                        String telefono = t.nextLine().trim();
                        if (!telefono.isEmpty()) {
                            c.setTelefono(telefono);
                        }

                        clienteDAO.setActualizar(c); // Persiste los cambios en la base de datos
                    }
                    
                } catch (Exception e) {
                
                    e.printStackTrace();
                }
                
            } else if (opcion.equals("3")) { // LÓGICA DE OPCIÓN 3: NUEVO CLIENTE (INSERTAR)
                
                try {

                    t.nextLine(); // Limpiamos el buffer para quitar el 'Enter' que dejó nextInt()
                    System.out.print("=================\nNuevo nombre: ");
                    c.setNombre(t.nextLine());
                    System.out.print("Nuevo numRuc: ");
                    c.setNumRuc(t.nextLine());
                    System.out.print("Nueva direccion: ");
                    c.setDireccion(t.nextLine());
                    System.out.print("Nuevo telefono: ");
                    c.setTelefono(t.nextLine());

                    id = clienteDAO.setInsertar(c); // Inserta el registro y recupera el ID generado

                    System.out.println("Nuevo id: " + id);

                    Thread.sleep(3000);
                    
                } catch (Exception e) {
                    
                    e.printStackTrace();
                }
                
            } else if (opcion.equals("4")) { // LÓGICA DE OPCIÓN 4: ELIMINAR CLIENTE
                
                try {
                    System.out.print("Id: ");
                    id = t.nextInt();

                    clienteDAO.setEliminar(id); // Elimina el registro por su ID
                    
                } catch (Exception e) {
                    
                    e.printStackTrace();
                }
                
            } else if (opcion.equals("0")) { // SALIR DEL PROGRAMA
                
                break; // Rompe el bucle 'do-while'
                
            } else { // VALIDACIÓN DE OPCIONES INCORRECTAS
                
                System.out.println("Opcion no aceptable");
                
            }
        
        } while(true);
        
        t.close(); // Desactivar el Scanner
    }
}
