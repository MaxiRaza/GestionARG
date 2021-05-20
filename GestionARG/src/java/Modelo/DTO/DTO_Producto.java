package Modelo.DTO;

public class DTO_Producto {

    private int id_producto, id_categoria, id_marca, id_deposito;
    private String codigo, nombre, fecha_fab, fecha_ven, descripcion, categoria, marca, deposito;
    private float precio, stock;

    public DTO_Producto() {
    }

    public DTO_Producto(int id_producto, int id_categoria, int id_marca, int id_deposito, String codigo, String nombre, String fecha_fab, String fecha_ven, String descripcion, String categoria, String marca, String deposito, float precio, float stock) {
        this.id_producto = id_producto;
        this.id_categoria = id_categoria;
        this.id_marca = id_marca;
        this.id_deposito = id_deposito;
        this.codigo = codigo;
        this.nombre = nombre;
        this.fecha_fab = fecha_fab;
        this.fecha_ven = fecha_ven;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.marca = marca;
        this.deposito = deposito;
        this.precio = precio;
        this.stock = stock;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public int getId_marca() {
        return id_marca;
    }

    public void setId_marca(int id_marca) {
        this.id_marca = id_marca;
    }

    public int getId_deposito() {
        return id_deposito;
    }

    public void setId_deposito(int id_deposito) {
        this.id_deposito = id_deposito;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha_fab() {
        return fecha_fab;
    }

    public void setFecha_fab(String fecha_fab) {
        this.fecha_fab = fecha_fab;
    }

    public String getFecha_ven() {
        return fecha_ven;
    }

    public void setFecha_ven(String fecha_ven) {
        this.fecha_ven = fecha_ven;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getDeposito() {
        return deposito;
    }

    public void setDeposito(String deposito) {
        this.deposito = deposito;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public float getStock() {
        return stock;
    }

    public void setStock(float stock) {
        this.stock = stock;
    }
    
}
