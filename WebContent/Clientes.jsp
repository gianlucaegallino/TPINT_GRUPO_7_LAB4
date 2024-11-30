<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="entidades.Usuario"%>
<%@ page import="java.util.ArrayList"%>
<%@ include file="InicioUsuarioBanco.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="./css/EstiloClientes.css">
<link rel="stylesheet" href="./css/normalize.css" />
<link rel="icon" type="image/x-icon" href="./images/favicon.ico">
<title>Gestión de Clientes | Banco Liberación</title>
</head>
<body class="bodyInicio">

	<!-- Contenedor específico para los botones de gestión en esta vista -->
	<article class="menu-gestiones-clientes">
		<a href="ClientesAgregar.jsp" class="boton-gestion-cliente">Agregar</a>
		<a href="ClientesModificarEliminar.jsp" class="boton-gestion-cliente">Editar/Eliminar</a>
		<a href="ClientesListar.jsp" class="boton-gestion-cliente">Listar</a>
	</article>
	<script defer src="js/clientes.js"></script>
</body>
</html>