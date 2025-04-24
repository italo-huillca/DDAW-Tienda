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
