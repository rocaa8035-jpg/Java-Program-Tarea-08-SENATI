package models;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionDAO {
    
    //Metodo de conexion: static y  retorna un Connection
    public static Connection conectarMariaDB() {
        
        //--- JDBC - Java Database Connectivity ---
        //Declarar la variable para la conexion
        Connection conexion;
        try {
            //Cargar el controlador de la BD
            Class.forName("org.mariadb.jdbc.Driver");
            //Parametros de conexion
            String cadena = "jdbc:mariadb://127.0.0.1:3306/bd302";
            String usuario = "root";
            String clave = "";
            //Abrir la conexion y asignar
            conexion = DriverManager.getConnection(cadena,usuario,clave);
        } catch (Exception e) {
            conexion = null;
        }
        return conexion;
    }
}
