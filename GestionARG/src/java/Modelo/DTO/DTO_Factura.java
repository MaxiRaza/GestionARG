package Modelo.DTO;

public class DTO_Factura {

    private int id_factura;
    private String fecha, sucursal, cliente, usuario, forma_de_pago, total;
    private float descuento;

    public DTO_Factura() {
    }

    public DTO_Factura(int id_factura, String fecha, String sucursal, String cliente, String usuario, String forma_de_pago, String total, float descuento) {
        this.id_factura = id_factura;
        this.fecha = fecha;
        this.sucursal = sucursal;
        this.cliente = cliente;
        this.usuario = usuario;
        this.forma_de_pago = forma_de_pago;
        this.total = total;
        this.descuento = descuento;
    }

    public int getId_factura() {
        return id_factura;
    }

    public void setId_factura(int id_factura) {
        this.id_factura = id_factura;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getSucursal() {
        return sucursal;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getForma_de_pago() {
        return forma_de_pago;
    }

    public void setForma_de_pago(String forma_de_pago) {
        this.forma_de_pago = forma_de_pago;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public float getDescuento() {
        return descuento;
    }

    public void setDescuento(float descuento) {
        this.descuento = descuento;
    }
    
}
