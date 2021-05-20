package Modelo.DTO;

public class DTO_Encargo {
        
    private int id_encargo, id_proveedor, id_producto, id_detalle_encargo;
    private String fecha, proveedor, producto;
    private float importe, cantidad;

    public DTO_Encargo() {
    }

    public DTO_Encargo(int id_encargo, int id_proveedor, int id_producto, int id_detalle_encargo, String fecha, String proveedor, String producto, float importe, float cantidad) {
        this.id_encargo = id_encargo;
        this.id_proveedor = id_proveedor;
        this.id_producto = id_producto;
        this.id_detalle_encargo = id_detalle_encargo;
        this.fecha = fecha;
        this.proveedor = proveedor;
        this.producto = producto;
        this.importe = importe;
        this.cantidad = cantidad;
    }

    public int getId_encargo() {
        return id_encargo;
    }

    public void setId_encargo(int id_encargo) {
        this.id_encargo = id_encargo;
    }

    public int getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public int getId_detalle_encargo() {
        return id_detalle_encargo;
    }

    public void setId_detalle_encargo(int id_detalle_encargo) {
        this.id_detalle_encargo = id_detalle_encargo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
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
