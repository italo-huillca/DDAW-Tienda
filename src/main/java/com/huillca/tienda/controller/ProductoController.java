package com.huillca.tienda.controller;

import com.huillca.tienda.dao.ProductoDAO;
import com.huillca.tienda.model.Producto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/productos")
public class ProductoController extends HttpServlet {
    private ProductoDAO productoDAO;

    @Override
    public void init() {
        productoDAO = new ProductoDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Listar productos
        List<Producto> productos = productoDAO.listar();
        request.setAttribute("productos", productos);
        request.getRequestDispatcher("/WEB-INF/views/productos.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Agregar producto
        String nombre = request.getParameter("nombre");
        double precio = Double.parseDouble(request.getParameter("precio"));

        Producto producto = new Producto();
        producto.setNombre(nombre);
        producto.setPrecio(precio);

        productoDAO.agregar(producto);

        // Redirigir para listar productos
        response.sendRedirect("productos");
    }
}



1.6 JSP - productos.jsp (Vista)
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Lista de Productos</title>
    <style>
        table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
            padding: 8px;
        }
    </style>
</head>
<body>
    <h2>Productos</h2>

    <!-- Formulario para agregar producto -->
    <form action="productos" method="post">
        <label>Nombre:</label>
        <input type="text" name="nombre" required><br>
        <label>Precio:</label>
        <input type="number" step="0.01" name="precio" required><br>
        <input type="submit" value="Agregar Producto">
    </form>

    <!-- Lista de productos -->
    <h3>Lista de Productos</h3>
    <table>
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Precio</th>
        </tr>
        <c:forEach var="producto" items="${productos}">
            <tr>
                <td>${producto.id}</td>
                <td>${producto.nombre}</td>
                <td>${producto.precio}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>




