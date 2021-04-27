package Modelo;

public class Contacto {

    private int id_contacto;
    private String telefono, mail;

    public Contacto() {
    }

    public Contacto(int id_contacto, String telefono, String mail) {
        this.id_contacto = id_contacto;
        this.telefono = telefono;
        this.mail = mail;
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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

}