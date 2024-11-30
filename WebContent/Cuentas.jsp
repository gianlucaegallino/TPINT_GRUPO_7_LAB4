<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="InicioUsuarioBanco.jsp"%>
<%@ page import="entidades.Cuenta"%>
<%@ page import="java.util.ArrayList"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<link rel="stylesheet" href="./css/EstiloCuentas.css" />
<link rel="stylesheet" href="./css/normalize.css" />
<link rel="icon" type="image/x-icon" href="./images/favicon.ico">
<title>Gesti�n de Clientes | Banco Liberaci�n</title>
</head>
<body class="bodyInicio">

	<article class="menu-gestiones">
		<a href="Cuentas.jsp" class="botonGestion">Agregar</a> <a
			href="EditarEliminarCuenta.jsp" class="botonGestion">Editar/Eliminar</a>
		<a href="ListarCuenta.jsp" class="botonGestion">Listar</a>

		<!--   </form>-->
	</article>



	<!-- Formulario para agregar cuenta -->
	<div class="formulario-agregar" id="formularioAgregar"
		style="display: none;">
		<h2>Agregar Cuenta</h2>
		<form action="SlCuentas" method=get name=formCuentas>
			<label for="DNICliente">DNI Cliente:</label> <input type="number"
				id="DNICliente" name="DNICliente" placeholder="Ingrese DNI" required />

			<label for="fechaCreacion">Fecha de Creaci�n:</label> <input
				type="date" id="fechaCreacion" name="fechaCreacion" required /> <label
				for="tipoCuenta">Tipo de Cuenta:</label> <select id="tipoCuenta"
				name="tipoCuenta" required>
				<option value=1>Caja de Ahorro</option>
				<option value=2>Cuenta Corriente</option>
			</select> <label for="cbu">CBU:</label> <input type="text" id="cbu" name="cbu"
				placeholder="Ingrese CBU" required /> <label for="saldo">Saldo:</label>
			<input type="text" id="saldo" name="saldo" value="10000" disabled />

			<input type="submit" value="Guardar Cuenta" name=btnGuardar
				onClick="confirmar()" class="btnSubmit">

			<script type="text/javascript">
			    function confirmar(){
			    	let response = confirm ("Esta seguro que quiere continuar?")
				    if (response){
				      document.formCuentas.submit();
				    }
			    }
			</script>
		</form>
		<% if(request.getAttribute("cantFilas")=="1")
					{
				%>
		Se ha agregado Correctamente.
		<%} %>
		<% if(request.getAttribute("cantFilas")=="0")
					{
				%>
		Hubo un problema al agregar.
		<%} %>
	</div>






	<script defer src="./js/goToPage.js"></script>






</body>
</html>