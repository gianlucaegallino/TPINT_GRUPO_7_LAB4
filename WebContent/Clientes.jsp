<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="entidades.Usuario" %>
<%@ page import="java.util.ArrayList" %>
<%@ include file="InicioUsuarioBanco.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet" href="./css/normalize.css" />
    <link rel="stylesheet" href="./css/EstiloClientes.css">
    <link rel="icon" type="image/x-icon" href="/images/favicon.ico">
	<title>Gesti�n de Clientes | Banco Liberaci�n</title>
</head>
<body class="bodyInicio">
	
	<!-- Contenedor espec�fico para los botones de gesti�n en esta vista -->
	<article class="menu-gestiones-clientes">
	    <a href="SIClientes" class="boton-gestion-cliente">Agregar</a>      
        <input class="boton-gestion-cliente" id="botonEditarEliminarC" type="submit" value="Editar/Eliminar" onClick="window.location.href='ClientesModificarEliminar.jsp'"/>
        <input class="boton-gestion-cliente" id="botonListarC" type="submit" value="Listar" onClick="window.location.href='ClientesListar.jsp'"/>
    </article>
	<script defer src="js/clientes.js"></script>
</body>
</html>