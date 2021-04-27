package Modelo;

public class Detalle_Factura {

    private int id_deatalle_factura, cantidad, id_factura, id_producto;
    private float importe;

    public Detalle_Factura() {
    }

    public Detalle_Factura(int id_deatalle_factura, int cantidad, int id_factura, int id_producto, float importe) {
        this.id_deatalle_factura = id_deatalle_factura;
        this.cantidad = cantidad;
        this.id_factura = id_factura;
        this.id_producto = id_producto;
        this.importe = importe;
    }

    public int getId_deatalle_factura() {
        return id_deatalle_factura;
    }

    public void setId_deatalle_factura(int id_deatalle_factura) {
        this.id_deatalle_factura = id_deatalle_factura;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getId_factura() {
        return id_factura;
    }

    public void setId_factura(int id_factura) {
        this.id_factura = id_factura;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public float getImporte() {
        return importe;
    }

    public void setImporte(float importe) {
        this.importe = importe;
    }

}
