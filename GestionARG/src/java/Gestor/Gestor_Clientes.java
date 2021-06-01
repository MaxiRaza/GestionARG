package Gestor;

import Modelo.Cliente;
import Modelo.DTO.DTO_Cliente;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Gestor_Clientes {

    private Connection conexion;
    private String CONN = "jdbc:sqlserver://localhost:1433;databaseName=GestionARG";
    private String USER = "sa";
    private String PASS = "1234";

    private void abrirConexion() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conexion = DriverManager.getConnection(CONN, USER, PASS);
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    private void cerrarConexion() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    public void agregarCliente(Cliente c) {
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("INSERT INTO Clientes (nombre, apellido, fecha_nac,  documento, id_contacto, id_tipo_cliente, direccion, vigencia) VALUES (?,?,?,?,?,?,?,1)");
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getApellido());
            ps.setString(3, c.getFecha_nac());
            ps.setString(4, c.getDocumento());
            ps.setInt(5, c.getId_contacto());
            ps.setInt(6, c.getId_tipo_cliente());
            ps.setString(7, c.getDireccion());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Clientes.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
    }

    public ArrayList<Cliente> obtenerClientes() {
        ArrayList<Cliente> lista = new ArrayList<>();
        try {
            abrirConexion();
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT id_cliente, nombre, apellido, fecha_nac, documento, id_contacto, id_tipo_cliente, direccion FROM Clientes WHERE vigencia = 1");
            while (rs.next()) {
                Cliente c = new Cliente();
                c.setId_cliente(rs.getInt(1));
                c.setNombre(rs.getString(2));
                c.setApellido(rs.getString(3));
                c.setFecha_nac(rs.getString(4));
                c.setDocumento(rs.getString(5));
                c.setId_contacto(rs.getInt(6));
                c.setId_tipo_cliente(rs.getInt(7));
                c.setDireccion(rs.getString(8));
                lista.add(c);
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Clientes.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
        return lista;
    }

    public Cliente obtenerCliente(int id_cliente) {
        Cliente c = null;
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("SELECT id_cliente, nombre, apellido, fecha_nac, documento, id_contacto, id_tipo_cliente, direccion FROM Clientes WHERE id_cliente = ?");
            ps.setInt(1, id_cliente);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                c = new Cliente();
                c.setId_cliente(rs.getInt(1));
                c.setNombre(rs.getString(2));
                c.setApellido(rs.getString(3));
                c.setFecha_nac(rs.getString(4));
                c.setDocumento(rs.getString(5));
                c.setId_contacto(rs.getInt(6));
                c.setId_tipo_cliente(rs.getInt(7));
                c.setDireccion(rs.getString(8));
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Clientes.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
        return c;
    }

    public void actualizarCliente(Cliente c) {
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("UPDATE Clientes SET nombre = ?, apellido = ?, fecha_nac = ?, documento = ?, id_contacto = ?,  id_tipo_cliente = ?, direccion = ? WHERE id_cliente = ?");
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getApellido());
            ps.setString(3, c.getFecha_nac());
            ps.setString(4, c.getDocumento());
            ps.setInt(5, c.getId_contacto());
            ps.setInt(6, c.getId_tipo_cliente());
            ps.setString(7, c.getDireccion());
            ps.setInt(8, c.getId_cliente());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Clientes.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
    }

    public void eliminarCliente(int id_cliente) {
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("UPDATE Clientes SET vigencia = 0 WHERE id_cliente = ?");
            ps.setInt(1, id_cliente);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Clientes.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
    }
    
    public DTO_Cliente obtenerClienteDTO(int id_cliente) {
        DTO_Cliente c = null;
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("SELECT c.id_cliente, c.nombre, c.apellido, c.documento, c.fecha_nac, c.direccion, t.nombre, co.correo, co.telefono, c.id_tipo_cliente, c.id_contacto FROM Clientes c JOIN Contactos co ON c.id_contacto = co.id_contacto JOIN Tipo_Clientes t ON c.id_tipo_cliente = t.id_tipo_cliente WHERE c.id_cliente = ? ");
            ps.setInt(1, id_cliente);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                c = new DTO_Cliente();
                c.setId_cliente(rs.getInt(1));
                c.setNombre(rs.getString(2));
                c.setApellido(rs.getString(3));
                c.setDocumento(rs.getString(4));
                c.setFecha_nac(rs.getString(5));
                c.setDireccion(rs.getString(6));
                c.setTipo_cliente(rs.getString(7));
                c.setCorreo(rs.getString(8));
                c.setTelefono(rs.getString(9));
                c.setId_tipo_cliente(rs.getInt(10));
                c.setId_contacto(rs.getInt(11));
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Clientes.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
        return c;
    }

    public ArrayList<DTO_Cliente> obtenerClientesDTO() {
        ArrayList<DTO_Cliente> lista = new ArrayList<>();
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("SELECT c.id_cliente, c.nombre, c.apellido, c.documento, c.fecha_nac, c.direccion, t.nombre, co.correo, co.telefono FROM Clientes c JOIN Contactos co ON c.id_contacto = co.id_contacto JOIN Tipo_Clientes t ON c.id_tipo_cliente = t.id_tipo_cliente WHERE c.vigencia = 1");
            ResultSet rs = ps.executeQuery();
             while (rs.next()) {
                DTO_Cliente c = new DTO_Cliente();
                c.setId_cliente(rs.getInt(1));
                c.setNombre(rs.getString(2));
                c.setApellido(rs.getString(3));
                c.setDocumento(rs.getString(4));
                c.setFecha_nac(rs.getString(5));
                c.setDireccion(rs.getString(6));
                c.setTipo_cliente(rs.getString(7));
                c.setCorreo(rs.getString(8));
                c.setTelefono(rs.getString(9));
                lista.add(c);
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Clientes.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
        return lista;
    }
    
}
