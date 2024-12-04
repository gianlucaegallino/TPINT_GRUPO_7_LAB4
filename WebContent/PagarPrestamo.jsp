<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.text.DecimalFormat"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="entidades.Prestamo" %>
<%@ page import="entidades.Cuenta" %>
<%@ page import="java.util.Map"%>

<%
    String usuario = (String) session.getAttribute("usuarioLogeado");
    if (usuario == null) {
        usuario = "Invitado";
    }

    // Variables de ejemplo para cuotas y estado de pago
    int totalCuotas = 12;
    double cuotaMensual = 500.00; // Monto de ejemplo por cuota
    Map<Integer, String> cuotasPagadas = new HashMap<>(); // Almacena cuotas pagadas con fecha
    cuotasPagadas.put(1, "2024-01-15");
    cuotasPagadas.put(3, "2024-03-15");
    cuotasPagadas.put(6, "2024-06-15");

    // Generar listado de cuotas
    ArrayList<Integer> cuotasPendientes = new ArrayList<>();
    for (int i = 1; i <= totalCuotas; i++) {
        if (!cuotasPagadas.containsKey(i)) {
            cuotasPendientes.add(i);
        }
    }
%>
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
		<form action="SITransferir" method="GET" style="display: none"
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
		<form action="PagarPrestamo.jsp" method="post">
			<label for="deudaSeleccionada">Seleccionar deuda a pagar:</label> <select
				id="deudaSeleccionada" name="deudaSeleccionada" required>
				<% 
				
				ArrayList<Prestamo> deudasPendientes = (ArrayList<Prestamo>) request.getAttribute("prestamos");

				
				if (deudasPendientes != null) { for (Prestamo deuda : deudasPendientes) { 
				 
					let totalSaldar = deuda.getcuotasrestantes * deuda.costocuota;
					let proximacuota = deuda.fechaoriginal + (meses-cuotasrestantes)
				%>
				<option value="<%= deuda.getCuotasRestantes() %>"
				data-costocuota="<%= deuda.getCostoCuota() %>"
				data-totalsaldar="<%= totalsaldar %>"
				data-proxcuota="<%= proxcuota %>"
				
				>[DEUDA]</option>
				<% 		} 
					}
				%>
			</select> 
			<script defer>
					let deudaSeleccionada = document.querySelector("#deudaSeleccionada");
					let cuotasrestantes = document.querySelector("#cuotasrestantes");
					let costocuota = document.querySelector("#costocuota");
					let totalsaldar = document.querySelector("#totalsaldar");
					let proxcuota = document.querySelector("#proxcuota");

					let rest = deudaSeleccionada.selectedOptions[0].value;
					let cost = deudaSeleccionada.selectedOptions[0].getAttribute("data-costocuota");
					let tot = deudaSeleccionada.selectedOptions[0].getAttribute("data-totalsaldar");
					let prox = deudaSeleccionada.selectedOptions[0].getAttribute("data-proxcuota");

					if (saldo != "") {
						contadorSaldo.value = saldo;

					} else {
						contadorSaldo.value = null;
					}

					selectCuenta.addEventListener("change", function() {
						let saldo = selectCuenta.selectedOptions[0].getAttribute("data-saldo");
						
						if (saldo != "") {
							contadorSaldo.value = saldo;

						} else {
							contadorSaldo.value = null;
							
						}
					});
				</script>
			
			<label for="cuotasrestantes">Cuotas Restantes:</label> <input
				class="balance-display" id="cuotasrestantes" name="cuotasrestantes"
				readonly> 
			<label for="costocuota">Costo por cuota:</label> <input
				class="balance-display" id="costocuota" name="costocuota" readonly>
			<label for="totalsaldar">Total a saldar:</label> <input
				class="balance-display" id="totalsaldar" name="totalsaldar" readonly>
			<label for="proxcuota">Vencimiento Proxima Cuota:</label> <input
				class="balance-display" id="proxcuota" name="proxcuota" readonly>


			<label for="cuenta">Selecciona una cuenta</label> <select id="cuenta"
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
						<label for="proxcuota">Seleccione cantidad cuotas a pagar:</label> <input
				class="balance-display" id="proxcuota" name="proxcuota" readonly>
				


			<button type="submit">Pagar Cuotas Seleccionadas</button>
		</form>

	</div>
</body>
</html>
