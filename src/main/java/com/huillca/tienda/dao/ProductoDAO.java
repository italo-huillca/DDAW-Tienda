package com.huillca.tienda.dao;

import com.huillca.tienda.model.Producto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

private Connection getConnection() throws SQLException {
    try {
        // Cargar el driver de SQLite explícitamente
        Class.forName("org.sqlite.JDBC");
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }
    // Ruta absoluta para Linux
    String url = "jdbc:sqlite:/home/xdork/dev/NetBens Proyects/tienda/productos.db";
    return DriverManager.getConnection(url);
}

    // Listar todos los productos
    public List<Producto> listar() {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM productos";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Producto producto = new Producto();
                producto.setId(rs.getInt("id"));
                producto.setNombre(rs.getString("nombre"));
                producto.setPrecio(rs.getDouble("precio"));
                productos.add(producto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productos;
    }

    // Agregar un producto
    public void agregar(Producto producto) {
        String sql = "INSERT INTO productos (nombre, precio) VALUES (?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, producto.getNombre());
            pstmt.setDouble(2, producto.getPrecio());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
