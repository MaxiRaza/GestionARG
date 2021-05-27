package Modelo;

public class Proveedor {

    private int id_proveedor, id_contacto, id_tipo_proveedor, id_clasificacion, id_marca;
    private String nombre, direccion, cuit;

    public Proveedor() {
    }

    public Proveedor(int id_proveedor, int id_contacto, int id_tipo_proveedor, int id_clasificacion, int id_marca, String nombre, String direccion, String cuit) {
        this.id_proveedor = id_proveedor;
        this.id_contacto = id_contacto;
        this.id_tipo_proveedor = id_tipo_proveedor;
        this.id_clasificacion = id_clasificacion;
        this.id_marca = id_marca;
        this.nombre = nombre;
        this.direccion = direccion;
        this.cuit = cuit;
    }

    public int getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public int getId_contacto() {
        return id_contacto;
    }

    public void setId_contacto(int id_contacto) {
        this.id_contacto = id_contacto;
    }

    public int getId_tipo_proveedor() {
        return id_tipo_proveedor;
    }

    public void setId_tipo_proveedor(int id_tipo_proveedor) {
        this.id_tipo_proveedor = id_tipo_proveedor;
    }

    public int getId_clasificacion() {
        return id_clasificacion;
    }

    public void setId_clasificacion(int id_clasificacion) {
        this.id_clasificacion = id_clasificacion;
    }

    public int getId_marca() {
        return id_marca;
    }

    public void setId_marca(int id_marca) {
        this.id_marca = id_marca;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }
    
}
