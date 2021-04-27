package Modelo;

public class Forma_de_Pago {

    private int id_forma_de_pago;
    private String nombre;

    public Forma_de_Pago() {
    }

    public Forma_de_Pago(int id_forma_de_pago, String nombre) {
        this.id_forma_de_pago = id_forma_de_pago;
        this.nombre = nombre;
    }

    public int getId_forma_de_pago() {
        return id_forma_de_pago;
    }

    public void setId_forma_de_pago(int id_forma_de_pago) {
        this.id_forma_de_pago = id_forma_de_pago;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
