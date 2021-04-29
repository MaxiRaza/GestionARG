package Gestor;

import Modelo.Categoria;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Gestor_Categorias {

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

    public void agregarCategorias(Categoria c) {
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("INSERT INTO Categorias (nombre) VALUES (?)");
            ps.setString(1, c.getNombre());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Categorias.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
    }

    public ArrayList<Categoria> obtenerCategorias() {
        ArrayList<Categoria> lista = new ArrayList<>();
        try {
            abrirConexion();
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT id_categoria, nombre FROM Categorias");
            while (rs.next()) {
                Categoria c = new Categoria();
                c.setId_categoria(rs.getInt(1));
                c.setNombre(rs.getString(2));
                lista.add(c);
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Categorias.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
        return lista;
    }

    public Categoria obtenerCategoria(int id_categoria) {
        Categoria c = null;
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("SELECT id_categoria, nombre FROM Categorias WHERE id_categoria = ?");
            ps.setInt(1, id_categoria);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                c = new Categoria();
                c.setId_categoria(rs.getInt(1));
                c.setNombre(rs.getString(2));
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Categorias.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
        return c;
    }

    public void actualizarCategoria(Categoria c) {
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("UPDATE Categorias SET nombre = ? WHERE id_categoria = ?");
            ps.setString(1, c.getNombre());
            ps.setInt(2, c.getId_categoria());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Categorias.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
    }

    public void eliminarCategoria(int id_categoria) {
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("DELETE Categorias WHERE id_categoria = ?");
            ps.setInt(1, id_categoria);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Categorias.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
    }
}
