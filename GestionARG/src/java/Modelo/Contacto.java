package Modelo;

public class Contacto {

    private int id_contacto;
    private String telefono, correo;

    public Contacto() {
    }

    public Contacto(int id_contacto, String telefono, String mail) {
        this.id_contacto = id_contacto;
        this.telefono = telefono;
        this.correo = mail;
    }

    public int getId_contacto() {
        return id_contacto;
    }

    public void setId_contacto(int id_contacto) {
        this.id_contacto = id_contacto;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

}