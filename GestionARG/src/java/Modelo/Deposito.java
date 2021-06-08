package Modelo;

public class Deposito {

    private int id_deposito, id_sucursal;
    private String ubicacion;

    public Deposito(int id_deposito, int id_sucursal, String ubicacion) {
        this.id_deposito = id_deposito;
        this.id_sucursal = id_sucursal;
        this.ubicacion = ubicacion;
    }

    public int getId_deposito() {
        return id_deposito;
    }

    public void setId_deposito(int id_deposito) {
        this.id_deposito = id_deposito;
    }

    public int getId_sucursal() {
        return id_sucursal;
    }

    public void setId_sucursal(int id_sucursal) {
        this.id_sucursal = id_sucursal;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Deposito() {
    }

}
