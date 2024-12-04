<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.text.DecimalFormat"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="java.time.format.DateTimeFormatter"%>
<%@ page import="java.time.Instant"%>
<%@ page import="java.time.LocalDateTime"%>
<%@ page import="java.time.ZoneOffset"%>
<%@ page import="entidades.Prestamo"%>
<%@ page import="entidades.Cuenta"%>
<%@ include file="Inicio.jsp"%>

<!-- Incluimos el inicio -->

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link
	href="https://fonts.googleapis.com/css2?family=Nunito:wght@400;600;700&display=swap"
	rel="stylesheet" />
<!-- Normalize.css -->

<link rel="stylesheet" href="./css/estiloPagoPrestamo.css" />
<link rel="stylesheet" href="./css/normalize.css" />
<link rel="icon" type="image/x-icon" href="./images/favicon.ico">
<title>Pagar Prestamo | Banco Liberacion</title>
</head>
<body class="bodyPrestamo">
	<div class="containerPrestamo">
		<form action="SIPagarPrestamo" method="GET" style="display: none"
			id="formCargarListas"></form>

		<%
			if (request.getAttribute("mensajeCarga") != "Cargadas") {
		%>

		<script type="text/javascript">
			document.getElementById('formCargarListas').submit();
		</script>

		<%
			}
		%>


		<h3>Deudas a Pagar</h3>
		<form action="SIPagarPrestamo" method="POST">
			<label for="deudaSeleccionada">Seleccionar deuda a pagar:</label> <select
				id="deudaSeleccionada" name="deudaSeleccionada" required>
				<%
					ArrayList<Prestamo> deudasPendientes = (ArrayList<Prestamo>) request.getAttribute("prestamos");
					System.out.println(deudasPendientes);

					if (deudasPendientes != null) {
						for (Prestamo deuda : deudasPendientes) {

							double totalSaldar = deuda.getCuotasRestantes() * deuda.getMontoMensual();
							int cuotaspagas = deuda.getPlazoMeses() - deuda.getCuotasRestantes();
							//Armamos una instancia calendario para sumar meses y determinar el proximo pago.
							Calendar cal = Calendar.getInstance();
							cal.setTime(deuda.getFecha());
							cal.add(Calendar.MONTH, cuotaspagas + 1);
							//Lo convertimos en un instant, para darle el formato que queremos
							Instant instant = cal.getTime().toInstant();
							//Convertimos el instant a localdatetime con un offset utc (irrelevante, solo queremos la fecha)
							LocalDateTime ldt = instant.atOffset(ZoneOffset.UTC).toLocalDateTime();
							//formateamos el localdatetime para tener la hora deseada
							String proximacuota = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(ldt);
				%>
				<option value="<%=deuda.getCuotasRestantes()%>"
					data-costocuota="<%=deuda.getMontoMensual()%>"
					data-totalsaldar="<%=totalSaldar%>"
					data-proxcuota="<%=proximacuota%>"
					data-iddeuda="<%=deuda.getId()%>">Prestamo a
					<%=deuda.getPlazoMeses()%> meses por
					<%=deuda.getImporteConIntereses()%> al CBU
					<%=deuda.getCbu_cuenta()%></option>
				<%
					}
					} else {
				%>

				<option value="" disabled selected>No hay prestamos a pagar</option>
				<%
					}
				%>

			</select> <label for="cuotasrestantes">Cuotas Restantes:</label> <input
				class="balance-display" id="cuotasrestantes" name="cuotasrestantes"
				readonly> <label for="costocuota">Costo por cuota:</label> <input
				class="balance-display" id="costocuota" name="costocuota" readonly>
			<label for="totalsaldar">Total a saldar:</label> <input
				class="balance-display" id="totalsaldar" name="totalsaldar" readonly>
			<label for="proxcuota">Vencimiento Proxima Cuota:</label> <input
				class="balance-display" id="proxcuota" name="proxcuota" readonly>

			<input class="balance-display" id="iddeuda" name=iddeuda readonly
				style="display: none">

			<script defer>
				let deudaSeleccionada = document
						.querySelector("#deudaSeleccionada");
				let cuotasrestantes = document
						.querySelector("#cuotasrestantes");
				let costocuota = document.querySelector("#costocuota");
				let totalsaldar = document.querySelector("#totalsaldar");
				let proxcuota = document.querySelector("#proxcuota");
				let iddeuda = document.querySelector("#iddeuda");

				let rest = deudaSeleccionada.selectedOptions[0].value;
				let cost = deudaSeleccionada.selectedOptions[0]
						.getAttribute("data-costocuota");
				let tot = deudaSeleccionada.selectedOptions[0]
						.getAttribute("data-totalsaldar");
				let prox = deudaSeleccionada.selectedOptions[0]
						.getAttribute("data-proxcuota");
				let id = deudaSeleccionada.selectedOptions[0]
						.getAttribute("data-iddeuda");

				if (rest != "") {
					cuotasrestantes.value = rest;

				} else {
					cuotasrestantes.value = null;
				}

				if (cost != "") {
					costocuota.value = Number(cost).toFixed(2);

				} else {
					costocuota.value = null;
				}

				if (tot != "") {
					totalsaldar.value = Number(tot).toFixed(2);;

				} else {
					totalsaldar.value = null;
				}

				if (prox != "") {
					proxcuota.value = prox;

				} else {
					proxcuota.value = null;
				}

				if (id != "") {
					iddeuda.value = id;

				} else {
					iddeuda.value = null;
				}

				deudaSeleccionada.addEventListener("change", function() {
					let deudaSeleccionada = document
							.querySelector("#deudaSeleccionada");
					let cuotasrestantes = document
							.querySelector("#cuotasrestantes");
					let costocuota = document.querySelector("#costocuota");
					let totalsaldar = document.querySelector("#totalsaldar");
					let proxcuota = document.querySelector("#proxcuota");
					let iddeuda = document.querySelector("#iddeuda");

					let rest = deudaSeleccionada.selectedOptions[0].value;
					let cost = deudaSeleccionada.selectedOptions[0]
							.getAttribute("data-costocuota");
					let tot = deudaSeleccionada.selectedOptions[0]
							.getAttribute("data-totalsaldar");
					let prox = deudaSeleccionada.selectedOptions[0]
							.getAttribute("data-proxcuota");
					let id = deudaSeleccionada.selectedOptions[0]
							.getAttribute("data-iddeuda");

					if (rest != "") {
						cuotasrestantes.value = rest;

					} else {
						cuotasrestantes.value = null;
					}

					if (cost != "") {
						costocuota.value =  Number(cost).toFixed(2);

					} else {
						costocuota.value = null;
					}

					if (tot != "") {
						totalsaldar.value = Number(tot).toFixed(2);

					} else {
						totalsaldar.value = null;
					}

					if (prox != "") {
						proxcuota.value = prox;

					} else {
						proxcuota.value = null;
					}

					if (id != "") {
						iddeuda.value = id;

					} else {
						iddeuda.value = null;
					}
				});
			</script>

			<label for="cuenta">Selecciona una cuenta para pagar:</label> <select
				id="cuenta" name="cuenta" required>
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
			</select> <label for="saldo">Saldo disponible en cuenta:</label> <input
				class="balance-display" id="saldo" name="saldo" readonly>
			<script defer>
				let selectCuenta = document.querySelector("#cuenta");
				let contadorSaldo = document.querySelector("#saldo");

				let saldo = selectCuenta.selectedOptions[0]
						.getAttribute("data-saldo");

				if (saldo != "") {
					contadorSaldo.value = Number(saldo).toFixed(2);

				} else {
					contadorSaldo.value = null;
				}

				selectCuenta.addEventListener("change", function() {
					let saldo = selectCuenta.selectedOptions[0]
							.getAttribute("data-saldo");

					if (saldo != "") {
						contadorSaldo.value = Number(saldo).toFixed(2);

					} else {
						contadorSaldo.value = null;

					}
				});
			</script>
			<label for="cantcuotasapagar">Seleccione cantidad cuotas a
				pagar:</label> <input class="balance-display" id="cantcuotasapagar"
				name="cantcuotasapagar" required pattern= "^[0-9]*$" maxlength="2">



			<button type="submit">Pagar Cuotas Seleccionadas</button>
		</form>
								<div class="formulario-mensaje"
			style="<%= request.getAttribute("mensajeExito") != null && !request.getAttribute("mensajeExito").toString().isEmpty() ? "display: block;" : "display: none;" %>">
				<h3 style="color: green;"><%= request.getAttribute("mensajeExito") %></h3>
		</div>

		<div class="formulario-mensaje"
			style="<%= request.getAttribute("mensajeError") != null && !request.getAttribute("mensajeError").toString().isEmpty() ? "display: block;" : "display: none;" %>">
				<h3 style="color: red;"><%= request.getAttribute("mensajeError") %></h3>
		</div>

	</div>
</body>
</html>
