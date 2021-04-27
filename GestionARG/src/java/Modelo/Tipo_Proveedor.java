package Modelo;

public class Tipo_Proveedor {

    private int id_tipo_proveedor;
    private String nombre;

    public Tipo_Proveedor() {
    }

    public Tipo_Proveedor(int id_tipo_proveedor, String nombre) {
        this.id_tipo_proveedor = id_tipo_proveedor;
        this.nombre = nombre;
    }

    public int getId_tipo_proveedor() {
        return id_tipo_proveedor;
    }

    public void setId_tipo_proveedor(int id_tipo_proveedor) {
        this.id_tipo_proveedor = id_tipo_proveedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
