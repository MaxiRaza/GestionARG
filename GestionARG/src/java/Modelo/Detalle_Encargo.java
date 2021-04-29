package Modelo;

public class Detalle_Encargo {

    private int id_detalle_encargo, id_encargo, id_producto;
    private float importe, cantidad;

    public Detalle_Encargo() {
    }

    public Detalle_Encargo(int id_detalle_encargp, int id_encargo, int id_producto, float importe, float cantidad) {
        this.id_detalle_encargo = id_detalle_encargp;
        this.id_encargo = id_encargo;
        this.id_producto = id_producto;
        this.importe = importe;
        this.cantidad = cantidad;
    }

    public int getId_detalle_encargo() {
        return id_detalle_encargo;
    }

    public void setId_detalle_encargo(int id_detalle_encargo) {
        this.id_detalle_encargo = id_detalle_encargo;
    }

    public int getId_encargo() {
        return id_encargo;
    }

    public void setId_encargo(int id_encargo) {
        this.id_encargo = id_encargo;
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

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

}
