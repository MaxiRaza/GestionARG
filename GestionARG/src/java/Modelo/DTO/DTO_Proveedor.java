package Modelo.DTO;

public class DTO_Proveedor {

    private int id_proveedor, id_tipo_proveedor, id_contacto, id_clasificacion, id_marca, id_categoria;
    private String nombre, cuit, direccion, telefono, correo, tipo, clasificacion, marca, categoria;

    public DTO_Proveedor() {
    }

    public DTO_Proveedor(int id_proveedor, int id_tipo_proveedor, int id_contacto, int id_clasificacion, int id_marca, int id_categoria, String nombre, String cuit, String direccion, String telefono, String correo, String tipo, String clasificacion, String marca, String categoria) {
        this.id_proveedor = id_proveedor;
        this.id_tipo_proveedor = id_tipo_proveedor;
        this.id_contacto = id_contacto;
        this.id_clasificacion = id_clasificacion;
        this.id_marca = id_marca;
        this.id_categoria = id_categoria;
        this.nombre = nombre;
        this.cuit = cuit;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
        this.tipo = tipo;
        this.clasificacion = clasificacion;
        this.marca = marca;
        this.categoria = categoria;
    }

    public int getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public int getId_tipo_proveedor() {
        return id_tipo_proveedor;
    }

    public void setId_tipo_proveedor(int id_tipo_proveedor) {
        this.id_tipo_proveedor = id_tipo_proveedor;
    }

    public int getId_contacto() {
        return id_contacto;
    }

    public void setId_contacto(int id_contacto) {
        this.id_contacto = id_contacto;
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

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
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

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    
}
