<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="entidades.Prestamo"%>
<%@ page import="entidades.Cliente"%>
<%@ include file="InicioUsuarioBanco.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Autorizar Préstamos | Banco Liberación</title>
<link rel="stylesheet" href="./css/estiloAutorizarPrestamos.css">
<link rel="stylesheet" href="./css/normalize.css" />
<link rel="icon" type="image/x-icon" href="./images/favicon.ico">
</head>
<body class="bodyAutorizar">
    <div class="containerAutorizar">
        <h2>Autorizar Solicitudes de Préstamos</h2>
        
        <%
            String mensajeExito = (String) request.getAttribute("mensajeExito");
            String mensajeError = (String) request.getAttribute("mensajeError");
        %>

        <% if (mensajeExito != null && !mensajeExito.isEmpty()) { %>
            <p style="color: green;"><%= mensajeExito %></p>
        <% } %>

        <% if (mensajeError != null && !mensajeError.isEmpty()) { %>
            <p style="color: red;"><%= mensajeError %></p>
        <% } %>
        <hr>
        <form action="SlAutorizarPrestamo" method="POST">
        	<input type="hidden" name="action" value="buscarPrestamoPendientes" />
        	<input type="submit" value="Mostrar Todos los prestamos pendientes" name="btnMostrarPrestamosPendientes">
        </form>

        <table class="tablaPrestamos" style="display: block;">
            <thead>
                <tr>
                    <th>N° Préstamo</th>
                    <th>DNI del Cliente</th>
                    <th>Cliente</th>
                    <th>CBU</th>
                    <th>Fecha</th>
                    <th>Estado</th>
                    <th>Importe Solicitado</th>
                    <th>Cuotas</th>
                    <th>Interés Anual</th>
                    <th>Valor de Cuota</th>
                    <th>Importe con intereses</th>
                    <th>Pagos Restantes</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <%
					String tablaHTML = (String) request.getAttribute("tablaHTML");
				%>
				<% if (tablaHTML != null && !tablaHTML.isEmpty()) { %>
			        <%= tablaHTML %>
			    <% } %>
			         
            </tbody>
        </table>
    </div>
</body>
</html>