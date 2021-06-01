package Gestor;

import Modelo.Marca;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Gestor_Marcas {

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

    public void agregarMarca(Marca m) {
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("INSERT INTO Marcas (nombre, id_categoria, vigencia) VALUES (?, ?,1)");
            ps.setString(1, m.getNombre());
            ps.setInt(2, m.getId_categoria());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Marcas.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
    }

    public ArrayList<Marca> obtenerMarcas() {
        ArrayList<Marca> lista = new ArrayList<>();
        try {
            abrirConexion();
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT id_marca, nombre, id_categoria FROM Marcas WHERE vigencia = 1");
            while (rs.next()) {
                Marca m = new Marca();
                m.setId_marca(rs.getInt(1));
                m.setNombre(rs.getString(2));
                m.setId_categoria(rs.getInt(3));
                lista.add(m);
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Marcas.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
        return lista;
    }

    public Marca obtenerMarca(int id_marca) {
        Marca m = null;
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("SELECT id_marca, nombre, id_categoria FROM Marcas WHERE id_marca = ?");
            ps.setInt(1, id_marca);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                m = new Marca();
                m.setId_marca(rs.getInt(1));
                m.setNombre(rs.getString(2));
                m.setId_categoria(rs.getInt(3));
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Marcas.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
        return m;
    }

    public void actualizarMarca(Marca m) {
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("UPDATE Marcas SET nombre = ?, id_categoria = ? WHERE id_marca = ?");
            ps.setString(1, m.getNombre());
            ps.setInt(2, m.getId_categoria());
            ps.setInt(3, m.getId_marca());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Marcas.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
    }

    public void eliminarMarca(int id_marca) {
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("UPDATE Marcas SET vigencia = 0 WHERE id_marca = ?");
            ps.setInt(1, id_marca);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Marcas.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
    }
    
    public ArrayList<Marca> obtenerMarcasFiltro(int id_categoria) {
        ArrayList<Marca> lista = new ArrayList<>();
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("SELECT id_marca, nombre, id_categoria FROM Marcas WHERE id_categoria = ? AND vigencia = 1");
            ps.setInt(1, id_categoria);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Marca m = new Marca();
                m.setId_marca(rs.getInt(1));
                m.setNombre(rs.getString(2));
                m.setId_categoria(rs.getInt(3));
                lista.add(m);
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Marcas.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
        return lista;
    }
}
