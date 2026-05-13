package models;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionDAO {
    
    public static Connection conectarMariaDB() {
        
        Connection conexion;
        
        try {
            
            Class.forName("org.mariadb.jdbc.Driver");
            
            String cadena = "jdbc:mariadb://127.0.0.1:3306/bd302";
            String usuario = "root";
            String clave = "";
            
            conexion = DriverManager.getConnection(cadena,usuario,clave);
            
        } catch (Exception e) {
            conexion = null;
        }
        return conexion;
    }
}
