package models;

public class Cliente {
    
    //Propiedades
    private int id;
    private String nombre;
    private String numRuc;
    private String direccion;
    private String telefono;
    
    //Constructores
    public Cliente() {}
    
    public Cliente(int id, String nombre, String numRuc, String direccion, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.numRuc = numRuc;
        this.direccion = direccion;
        this.telefono = telefono;
    }
    
    //Encapsulamiento
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNumRuc() {
        return numRuc;
    }

    public void setNumRuc(String numRuc) {
        this.numRuc = numRuc;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    //Propiedades
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
