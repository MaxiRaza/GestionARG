package Modelo.DTO;

public class DTO_Detalle_Factura {
        
        private int id_detalle_factura, id_factura;
        private String producto, marca, categoria;
        private float importe, cantidad;

    public DTO_Detalle_Factura() {
    }

    public DTO_Detalle_Factura(int id_detalle_factura, int id_factura, String producto, String marca, String categoria, float importe, float cantidad) {
        this.id_detalle_factura = id_detalle_factura;
        this.id_factura = id_factura;
        this.producto = producto;
        this.marca = marca;
        this.categoria = categoria;
        this.importe = importe;
        this.cantidad = cantidad;
    }

    public int getId_detalle_factura() {
        return id_detalle_factura;
    }

    public void setId_detalle_factura(int id_detalle_factura) {
        this.id_detalle_factura = id_detalle_factura;
    }

    public int getId_factura() {
        return id_factura;
    }

    public void setId_factura(int id_factura) {
        this.id_factura = id_factura;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
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

    public float getImporte() {
        return importe;
    }

    public void setImporte(float importe) {
        this.importe = importe;
    }

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

}
