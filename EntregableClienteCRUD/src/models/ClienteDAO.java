package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ClienteDAO {
    
    //METODOS CRUD: CREATE / READ / UPDATE / DELETE
    public Cliente getBuscarPorId(int idBuscar) {
    
        //Instanciar un Cliente
        Cliente cliente = new Cliente();
        
        try {
            //Establecer la conexion
            Connection cnx = ConexionDAO.conectarMariaDB();
            //Preparar la instruccion SQL
            PreparedStatement ps = cnx.prepareStatement("select * from cliente where id = ?");
            //Pasar el valor al parametro SQL
            ps.setInt(1,idBuscar);
            //Ejecutar la instruccion SQL
            ResultSet rs = ps.executeQuery(); // SELECT
            //Desplazar el puntero de registros
            Boolean existeFila = rs.next();
            if (existeFila) {
                //Leer los valores de la fila en Cliente
                cliente.setId(rs.getInt("id"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setNumRuc(rs.getString("numruc"));
                cliente.setDireccion(rs.getString("direccion"));
                cliente.setTelefono(rs.getString("telefono"));
            } else {
                //Asignar valores predeterminados
                cliente.setId(0);
                cliente.setNombre("");
                cliente.setNumRuc("");
                cliente.setDireccion("");
                cliente.setTelefono("");
            }
        } catch (Exception e) {
            //Asignar valores predeterminados
                cliente.setId(0);
                cliente.setNombre(" - error de conexion -");
                cliente.setNumRuc("");
                cliente.setDireccion("");
                cliente.setTelefono("");
        }
        //Retornar el Cliente
        return cliente;
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
        catch(Exception e) {}
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
        catch(Exception e) {}
    }
}
