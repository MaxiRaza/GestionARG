package Modelo;

public class Deposito {

    private int id_deposito;
    private String ubicacion;

    public Deposito() {
    }

    public Deposito(int id_deposito, String ubicacion) {
        this.id_deposito = id_deposito;
        this.ubicacion = ubicacion;
    }

    public int getId_deposito() {
        return id_deposito;
    }

    public void setId_deposito(int id_deposito) {
        this.id_deposito = id_deposito;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

}
