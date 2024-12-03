<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.text.DecimalFormat"%>
<%@ include file="Inicio.jsp"%>

		<%
		double interesTotal = (double) request.getAttribute("interesTotal");
		double totalConInteres = (double) request.getAttribute("totalConInteres");
		double cuotaMensual = (double) request.getAttribute("cuotaMensual");
		double TASA_INTERES = (double) request.getAttribute("TASA_INTERES");
		double monto = (double) request.getAttribute("monto");
		Integer cuotas = (Integer) request.getAttribute("cuotas");
		%>

<!-- MAIN -->

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link
	href="https://fonts.googleapis.com/css2?family=Nunito:wght@400;600;700&display=swap"
	rel="stylesheet">


<link rel="stylesheet" href="./css/estiloPrestamo.css">
<link rel="stylesheet" href="./css/normalize.css" />
<link rel="icon" type="image/x-icon" href="./images/favicon.ico">
<title>Pedir Prestamo | Banco Liberacion</title>
</head>
<body class="bodyPrestamo">
	<div class="containerPrestamo">
	
		<form action="SIPedirPrestamo" method="GET" style="display: none"id="formCargarListas"></form>

		<%
			if (request.getAttribute("mensajeCarga") != "Cargadas") {
		%>

		<script type="text/javascript">
			document.getElementById('formCargarListas').submit();
		</script>

		<%
			}
		%>
		
		<h2>Pedir Prestamo</h2>


		<form action="SIPedirPrestamo" method="POST">
			<label for="monto">Monto:</label> 
			<input type="number" id="monto" name="monto" required> 
			<label for="cuotas">Cantidad de Cuotas:</label> 
			<input type="number" id="cuotas" name="cuotas" required>
			<label for="cuenta">Cuenta de Deposito:</label> 
			<select id="cuenta" name="cuenta" required>
				<option value="caja_ahorro">Caja de Ahorro</option>
				<option value="cuenta_corriente">Cuenta Corriente</option>
			</select>

			<button type="submit" name="submit" value="calcular">Calcular Cuota</button>
			<button type="submit" name="submit" value="solicitar">Solicitar Prestamo</button>
		</form>


		<div class="formulario-mensaje"
			style="<%= request.getAttribute("interesTotal") != null && !request.getAttribute("interesTotal").toString().isEmpty() ? "display: block;" : "display: none;" %>">
		<h3>Intereses para $<%= request.getAttribute("monto") %> en <%= request.getAttribute("cuotas") %> cuotas:</h3>
		<p>
			Interes Anual:
			<%= (request.getAttribute("TASA_INTERES") * 100) + "%" %></p>
		<p>
			Interes Total:
			<%= new DecimalFormat("#.##").format(request.getAttribute("interesTotal")) + "%" %></p>
		<p>
			Cuota Mensual:
			<%= new DecimalFormat("#.##").format(request.getAttribute("cuotaMensual")) %></p>
		</div>
		
		
		


		
	</div>
</body>
</html>
