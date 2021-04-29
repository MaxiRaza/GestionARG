package Gestor;

import Modelo.Contacto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Gestor_Contactos {

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

    public void agregarContacto(Contacto c) {
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("INSERT INTO Contactos (telefono, correo) VALUES (?,?)");
            ps.setString(1, c.getTelefono());
            ps.setString(2, c.getCorreo());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Contactos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
    }

    public ArrayList<Contacto> obtenerContactos() {
        ArrayList<Contacto> lista = new ArrayList<>();
        try {
            abrirConexion();
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT id_contacto, telefono, correo FROM Contactos");
            while (rs.next()) {
                Contacto c = new Contacto();
                c.setId_contacto(rs.getInt(1));
                c.setTelefono(rs.getString(2));
                c.setCorreo(rs.getString(3));
                lista.add(c);
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Contactos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
        return lista;
    }

    public Contacto obtenerContacto(int id_contacto) {
        Contacto c = null;
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("SELECT id_contacto, telefono, correo FROM Contactos WHERE id_contacto = ?");
            ps.setInt(1, id_contacto);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                c = new Contacto();
                c.setId_contacto(rs.getInt(1));
                c.setTelefono(rs.getString(2));
                c.setCorreo(rs.getString(3));
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Contactos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
        return c;
    }

    public void actualizarContacto(Contacto c) {
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("UPDATE Contactos SET telefono = ?, correo = ? WHERE id_contacto = ?");
            ps.setString(1, c.getTelefono());
            ps.setString(2, c.getCorreo());
            ps.setInt(3, c.getId_contacto());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Contactos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
    }

    public void eliminarContacto(int id_contacto) {
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("DELETE Contactos WHERE id_contacto = ?");
            ps.setInt(1, id_contacto);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Contactos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
    }
}
