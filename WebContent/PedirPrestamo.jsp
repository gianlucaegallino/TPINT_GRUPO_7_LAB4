<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.text.DecimalFormat"%>
<%@ include file="Inicio.jsp"%>
<%@ page import="entidades.Cuenta"%>
<%@ page import="java.util.ArrayList"%>

		<%
		double interesTotal=0;
		double totalConInteres=0;

		double cuotaMensual=0;
		double TASA_INTERES=0;
		double monto = 0;
		Integer cuotas = null;

		if (request.getAttribute("cuotas") != null){
			interesTotal = (double) request.getAttribute("interesTotal");
			totalConInteres = (double) request.getAttribute("totalConInteres");
			cuotaMensual = (double) request.getAttribute("cuotaMensual");
			TASA_INTERES = (double) request.getAttribute("TASA_INTERES");
			monto = (double) request.getAttribute("monto");
			cuotas = (Integer) request.getAttribute("cuotas");
		}

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
<!-- Seleccion de la cuenta -->
			<label for="cuenta">Cuenta de Deposito:</label> <select id="cuenta"
				name="cuenta" required>
				<%
					ArrayList<Cuenta> cuentas = (ArrayList<Cuenta>) request.getAttribute("cuentas");

					if (cuentas != null) {
						for (Cuenta cuenta : cuentas) {

							String tipo = (cuenta.getCuenta().getId() == 1) ? "Caja de Ahorro" : "Cuenta Corriente";
				%>

				<option value="<%=cuenta.getCbu()%>"
					data-saldo="<%=cuenta.getSaldo()%>"><%=tipo + " - CBU: " + cuenta.getCbu()%></option>

				<%
					}
					} else {
				%>

				<option value="" disabled selected>No hay cuentas
					disponibles</option>
				<%
					}
				%>
			</select>

			<button type="submit" name="submit" value="calcular">Calcular Cuota</button>
			<button type="submit" name="submit" value="solicitar">Solicitar Prestamo</button>
		</form>


		

		<% if (cuotas != null) { %>
			<div class="formulario-mensaje"
				style="<%= cuotas != null ? "display: block;" : "display: none;" %>">
			<h3>Intereses para $<%= monto %> en <%= cuotas %> cuotas:</h3>
			<p>
				Interes Anual:
				<%= new DecimalFormat("#.##").format(TASA_INTERES*12) + "%" %></p>

			<p>
				Interes Total:
				<%= new DecimalFormat("#.##").format(interesTotal) + "%" %></p>
							<p>
				Interes Mensual:
				<%= new DecimalFormat("#.##").format(TASA_INTERES) + "%" %></p>
			<p>
				Total con interes:
				<%= "$"+new DecimalFormat("#.##").format(totalConInteres) %></p>
			<p>
				Cuota Mensual:
				<%= "$"+ new DecimalFormat("#.##").format(cuotaMensual) %></p>
			</div>
		<%} %>


		
	</div>
</body>
</html>
