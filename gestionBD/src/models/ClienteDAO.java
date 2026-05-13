package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Clase ClienteDAO (Data Access Object)
 * Centraliza las operaciones de base de datos para la entidad Cliente.
 */
public class ClienteDAO {
    
    public Cliente getBuscarPorId(int idBuscar) {
        
        Cliente cliente = new Cliente();
        
        try {
            // 1. Establece la conexión con MariaDB usando la clase de utilidad ConexionDAO
            Connection cnx = ConexionDAO.conectarMariaDB();
            // 2. Prepara la sentencia SQL con un parámetro (?) para evitar Inyección SQL
            PreparedStatement ps = cnx.prepareStatement("select * from cliente where id = ?");
            // 3. Asigna el valor del ID al primer parámetro de la consulta
            ps.setInt(1,idBuscar);
            // 4. Ejecuta la consulta y guarda el resultado en un ResultSet
            ResultSet rs = ps.executeQuery(); // SELECT
            // 5. Mueve el cursor a la primera fila de resultados
            Boolean existeFila = rs.next();
            if (existeFila) {
                // Si existe, extrae los datos de las columnas y los asigna al objeto Cliente
                cliente.setId(rs.getInt("id"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setNumRuc(rs.getString("numruc"));
                cliente.setDireccion(rs.getString("direccion"));
                cliente.setTelefono(rs.getString("telefono"));
            } else {
                // Si no hay resultados, inicializa el objeto con valores por defecto
                cliente.setId(0);
                cliente.setNombre("");
                cliente.setNumRuc("");
                cliente.setDireccion("");
                cliente.setTelefono("");
            }
        } catch (Exception e) {
            // Imprime el error en consola en caso de fallo en la conexión o SQL
            e.printStackTrace();
        }
        //Retornar el Cliente
        return cliente;
    }
    
    public void setActualizar(Cliente cliente) {
        try {
            //Establecer la conexion
            Connection cnx =  ConexionDAO.conectarMariaDB();
            //Preparar la instruccion SQL
            PreparedStatement ps = cnx.prepareStatement("update cliente set nombre = ?, numruc = ?, direccion = ?, telefono = ? where id=?");
            //Pasar el valor al parametro SQL
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getNumRuc());
            ps.setString(3, cliente.getDireccion());
            ps.setString(4, cliente.getTelefono());
            ps.setInt(5, cliente.getId());
            //Ejecutar la instruccion SQL
            ps.executeUpdate(); // INSERT - UPDATE - DELETE
        }
        catch(Exception e) {
        
            e.printStackTrace();
        }
    }
    
    public int setInsertar(Cliente cliente) {
        int nuevoId;
        try {
            // -- Parte 1: Actualizacion
            //Establecer la conexion
            Connection cnx =  ConexionDAO.conectarMariaDB();
            //Preparar la instruccion SQL
            PreparedStatement ps = cnx.prepareStatement("insert into cliente (nombre, numruc, direccion, telefono) values (?,?,?,?)");
            //Pasar el valor al parametro SQL
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getNumRuc());
            ps.setString(3, cliente.getDireccion());
            ps.setString(4, cliente.getTelefono());
            //Ejecutar la instruccion SQL
            ps.executeUpdate(); // INSERT - UPDATE - DELETE
            
            // -- Parte 2: Recoger el nuevo id
            //Preparar la instruccion SQL
            ps = cnx.prepareStatement("select max(id) as nuevoId from cliente");
            //Ejecutar la instruccion SQL
            ResultSet rs = ps.executeQuery();
            //Desplazar el puntero de registros
            boolean existeFila = rs.next();
            //Verificar si hay fila
            if(existeFila) {
                //Leer los valores de la fila
                nuevoId = rs.getInt("nuevoId");
            } else {
                //Valor predeterminado
                nuevoId = 0;
            }   
        }
        catch(Exception e) {
            nuevoId = -1;
        }
        
        return nuevoId;
    }
    
    public void setEliminar(int idEliminar) {
        try {
            //Establecer la conexion
            Connection cnx =  ConexionDAO.conectarMariaDB();
            //Preparar la instruccion SQL
            PreparedStatement ps = cnx.prepareStatement("delete from cliente where id=?");
            //Pasar el valor al parametro SQL
            ps.setInt(1, idEliminar);
            //Ejecutar la instruccion SQL
            ps.executeUpdate(); // INSERT - UPDATE - DELETE
        }
        catch(Exception e) {
        
            e.printStackTrace();
        }
    }
}
