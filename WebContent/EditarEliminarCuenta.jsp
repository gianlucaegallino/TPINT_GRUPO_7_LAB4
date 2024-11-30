<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="InicioUsuarioBanco.jsp"%>
<%@ page import="entidades.Cuenta"%>
<%@ page import="java.util.ArrayList"%>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="/css/EstiloCuentas.css">
<link rel="stylesheet" href="/css/normalize.css" />
<link rel="icon" type="image/x-icon" href="/images/favicon.ico">
<title>Gestión de Cuenta | Banco Liberación</title>
</head>
<body class="bodyInicio">
	<article class="menu-gestiones">
		<a href="Cuentas.jsp" class="botonGestion">Agregar</a> <a
			href="EditarEliminarCuenta.jsp" class="botonGestion">Editar/Eliminar</a>
		<a href="ListarCuenta.jsp" class="botonGestion">Listar</a>
	</article>

	<!-- Formulario para editar/eliminar cuenta -->
	<div class="formulario-editar-eliminar" id="formularioEditarEliminar"
		style="display: none">
		<h2>Editar/Eliminar Cuenta</h2>
		<form action="SlCuentas" method="post">
			<label for="cbuBuscar">CBU de Cuenta:</label> <input type="text"
				id="cbuBuscar" name="cbuBuscar"
				placeholder="Ingrese N° de CBU para buscar" required> <input
				type="submit" value="Buscar Cuenta" name="btnBuscar2" />
		</form>

		<%
        ArrayList<Cuenta> listaC = null;
        if (request.getAttribute("listaCuenta") != null) {
            listaC = (ArrayList<Cuenta>) request.getAttribute("listaCuenta");
        }
        %>

		<!-- Tabla para mostrar los datos de la cuenta -->
		<table class="tabla-cuentas" id="tablaCuentas">
			<thead>
				<tr>
					<th>ID Cliente</th>
					<th>Fecha de Creación</th>
					<th>Tipo de Cuenta</th>
					<th>N° Cuenta</th>
					<th>CBU</th>
					<th>Saldo</th>
					<th>Acciones</th>
					<!-- Nueva columna para acciones -->
				</tr>
			</thead>
			<tbody>

				<%
                if (listaC != null) {
                    for (Cuenta cuenta : listaC) {
                %>
				<tr>
					<td><%=cuenta.getCliente_id()%></td>
					<td><%=cuenta.getFecha_creacion()%></td>
					<td><%=cuenta.getTipo_cuenta_id()%></td>
					<td><%=cuenta.getNumero_cuenta()%></td>
					<td><%=cuenta.getCbu()%></td>
					<td><%=cuenta.getSaldo()%></td>
					<td>
						<!-- Formulario para cada cuenta con acciones -->

						<form action="SlCuentas" method="post">
							<input type="hidden" name="idCuenta"
								value="<%=cuenta.getNumero_cuenta()%>" /> <input type="hidden"
								name="cbu" value="<%=cuenta.getCbu()%>" />

							<!-- Campos para modificar CBU y saldo -->
							<input type="text" name="cbuModificar"
								value="<%=cuenta.getCbu()%>" placeholder="Nuevo CBU"> <input
								type="text" name="saldoModificar" value="<%=cuenta.getSaldo()%>"
								placeholder="Nuevo Saldo"> <input type="submit"
								value="Modificar" name="botonModificar" onClick="confirmar()">
						</form> <!-- Formulario para eliminar la cuenta -->
						<form action="SlCuentas" method="post">
							<input type="hidden" name="cbu" value="<%=cuenta.getCbu()%>" />
							<input type="submit" value="Eliminar" name="Eliminar"
								onClick="confirmar()">
						</form>

					</td>
				</tr>
				<%
                    }
                }
                %>
			</tbody>
		</table>

		<!-- Mostrar mensaje de éxito o error -->
		<%
        String mensaje = (String) request.getAttribute("mensaje");
        if (mensaje != null) {
        %>
		<p class="mensaje"><%=mensaje%></p>
		<%
        }
        %>

	</div>
	<script defer src="./js/goToPage.js"></script>
	<script type="text/javascript">
        function confirmar() {
            let response = confirm("Esta seguro que quiere continuar?");
            if (response) {
                document.formCuentas.submit();
            }
        }
    </script>
</body>
</html>