<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="Inicio.jsp"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="dao.UsuarioDao"%>
<%@ page import="entidades.Cliente"%>


<!DOCTYPE html>
<html>

<head>

<link rel="stylesheet" href="./css/EstiloInformacion.css" />
<link rel="stylesheet" href="./css/normalize.css" />
<link rel="icon" type="image/x-icon" href="./images/favicon.ico">

<style>
table {
	width: 50%;
	border: 1px solid;
}
</style>

<!--  estilo tabla -->
<link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.10.19/css/jquery.dataTables.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>
<script type="text/javascript">
	$(document).ready( function( ){
		$('#table_id').DataTable();
	});
</script>
</head>

<body>


	<form action="SIDatos" method="GET" style="display: none"
		id="buscarDatos" name="buscarDatos"></form>

	<%
		if (request.getAttribute("DatosUsuario") == null && request.getAttribute("mensajeError") == null) {
	%>

	<script type="text/javascript">
		document.getElementById('buscarDatos').submit();
	</script>

	<%
		}

		Cliente cl = new Cliente();

		if (request.getAttribute("DatosUsuario") != null) {
			cl = (Cliente) request.getAttribute("DatosUsuario");
		}
	%>




	<h2>Informacion del usuario</h2>

	<%
		if (request.getAttribute("DatosUsuario") != null) {
	%>


	<table id='table-id' class='display' border="1" style="margin: 0 auto;">

		<tr>
			<td>Nombre:</td>
			<td><%=cl.getNombre()%></td>
		</tr>

		<tr>
			<td>Apellido:</td>
			<td><%=cl.getApellido()%></td>
		</tr>

		<tr>
			<td>Dni:</td>
			<td><%=cl.getDni()%></td>
		</tr>

		<tr>
			<td>CUIL:</td>
			<td><%=cl.getCuil()%></td>
		</tr>

		<tr>
			<td>Sexo:</td>
			<td><%=cl.getSexo().getDescripcion()%></td>
		</tr>

		<tr>
			<td>Nacionalidad:</td>
			<td><%=cl.getNacionalidad().getNombre()%></td>
		</tr>

		<tr>
			<td>Fecha de nacimiento:</td>
			<td><%=cl.getFecha_nacimiento()%></td>
		</tr>

		<tr>
			<td>Direccion:</td>
			<td><%=cl.getDireccion().getDireccion()%></td>
		</tr>

		<tr>
			<td>Localidad:</td>
			<td><%=cl.getDireccion().getIdLocalidad()%></td>
		</tr>

		<tr>
			<td>Correo electronico:</td>
			<td><%=cl.getCorreo_electronico()%></td>
		</tr>

		<tr>
			<td>Telefono:</td>
			<td><%=cl.getTelefono()%></td>
		</tr>


	</table>
	<%
		}
	%>

</body>
</html>