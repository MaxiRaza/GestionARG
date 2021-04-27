package Modelo;

public class Factura {

    private int id_factura, id_sucursal, id_cliente, id_usuario, id_forma_de_pago;
    private String fecha;
    private float descuento;

    public Factura() {
    }

    public Factura(int id_factura, int id_sucursal, int id_cliente, int id_usuario, int id_forma_de_pago, String fecha, float descuento) {
        this.id_factura = id_factura;
        this.id_sucursal = id_sucursal;
        this.id_cliente = id_cliente;
        this.id_usuario = id_usuario;
        this.id_forma_de_pago = id_forma_de_pago;
        this.fecha = fecha;
        this.descuento = descuento;
    }

    public int getId_factura() {
        return id_factura;
    }

    public void setId_factura(int id_factura) {
        this.id_factura = id_factura;
    }

    public int getId_sucursal() {
        return id_sucursal;
    }

    public void setId_sucursal(int id_sucursal) {
        this.id_sucursal = id_sucursal;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getId_forma_de_pago() {
        return id_forma_de_pago;
    }

    public void setId_forma_de_pago(int id_forma_de_pago) {
        this.id_forma_de_pago = id_forma_de_pago;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public float getDescuento() {
        return descuento;
    }

    public void setDescuento(float descuento) {
        this.descuento = descuento;
    }
    
}
