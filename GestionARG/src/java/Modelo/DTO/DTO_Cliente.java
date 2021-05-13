package Modelo.DTO;

public class DTO_Cliente {

    private int id_cliente, id_tipo_cliente, id_contacto;
    private String nombre, apellido, fecha_nac, direccion, tipo_cliente, correo, telefono, documento;

    public DTO_Cliente() {
    }

    public DTO_Cliente(int id_usuario, int id_tipo_cliente, int id_contacto, String nombre, String apellido, String fecha_nac, String direccion, String tipo_cliente, String correo, String telefono, String documento) {
        this.id_cliente = id_usuario;
        this.id_tipo_cliente = id_tipo_cliente;
        this.id_contacto = id_contacto;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fecha_nac = fecha_nac;
        this.direccion = direccion;
        this.tipo_cliente = tipo_cliente;
        this.correo = correo;
        this.telefono = telefono;
        this.documento = documento;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getId_tipo_cliente() {
        return id_tipo_cliente;
    }

    public void setId_tipo_cliente(int id_tipo_cliente) {
        this.id_tipo_cliente = id_tipo_cliente;
    }

    public int getId_contacto() {
        return id_contacto;
    }

    public void setId_contacto(int id_contacto) {
        this.id_contacto = id_contacto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getFecha_nac() {
        return fecha_nac;
    }

    public void setFecha_nac(String fecha_nac) {
        this.fecha_nac = fecha_nac;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTipo_cliente() {
        return tipo_cliente;
    }

    public void setTipo_cliente(String tipo_cliente) {
        this.tipo_cliente = tipo_cliente;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

}
