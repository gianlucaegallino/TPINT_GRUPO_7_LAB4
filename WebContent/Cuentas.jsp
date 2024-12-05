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
<title>Gestión de Clientes | Banco Liberación</title>
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
		<% 
		    String mensaje = (String) request.getAttribute("mensaje");
		    String mensajeError = (String) request.getAttribute("mensajeError");
		
		    if (mensaje != null && !mensaje.isEmpty()) {
		%>
		    <p style="color: green;">${mensaje}</p>
		<% 
		    }
		
		    if (mensajeError != null && !mensajeError.isEmpty()) {
		%>
		    <p style="color: red;">${mensajeError}</p>
		<% 
		    }
		%>
		<form action="SlCuentas" method="POST" name=formCuentas>
			<input type="hidden" name="action" value="AgregarCuentas" />
			<label for="DNICliente">DNI Cliente:</label> <input type="number"
				id="DNICliente" name="DNICliente" placeholder="Ingrese DNI" required />

			<label for="fechaCreacion">Fecha de Creación:</label> <input
				type="date" id="fechaCreacion" name="fechaCreacion" required /> <label
				for="tipoCuenta">Tipo de Cuenta:</label> <select id="tipoCuenta"
				name="tipoCuenta" required>
				<option value=1>Caja de Ahorro</option>
				<option value=2>Cuenta Corriente</option>
			</select> <label for="cbu">CBU:</label> <input type="text" id="cbu" name="cbu"
				placeholder="Ingrese CBU" maxlength="22" minlength="22" required /> <label for="saldo">Saldo:</label>
			<input type="text" id="saldo" name="saldo" value="10000" disabled />

			<input type="submit" value="Guardar Cuenta" name=btnGuardar
				onClick="confirmar()" class="btnSubmit">

			<script type="text/javascript">
		    function confirmar(){
		    	let response = confirm ("Esta seguro que quiere continuar?");
			    if (response === true){
			      if(document.formCuentas.checkValidity() === true){
			    	  document.formCuentas.submit();
			      }
			    }else{
			    	  event.preventDefault();
			      }
		    }
			</script>
		</form>
	</div>
	<script defer src="./js/goToPage.js"></script>
</body>
</html>