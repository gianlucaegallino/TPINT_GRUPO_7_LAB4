<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="InicioUsuarioBanco.jsp"%>
<%@ page import="entidades.Cliente"%>
<%@ page import="java.util.ArrayList" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registro de Usuario | Banco Liberación</title>
<link rel="StyleSheet" href="./css/EstiloClientes.css">
<link rel="stylesheet" href="./css/normalize.css" />
<link rel="icon" type="image/x-icon" href="./images/favicon.ico">
</head>
<body class="bodyInicio">

	<!-- Formulario de agregar usuario -->
	<div class="formulario-agregar" id="formularioAgregarUsuario"
		style="display: block;">
		<h2>Agregar Usuario</h2>
		<br>
		<form action="SIUsuarios" method="POST">
			<input type="hidden" name="action" value="agregarUsuario">
			<div class="form-container">
				<div class="fila" id="Username_Contrasena">
					<div class="form-element">
						<label for="UsernameUsuario">USUARIO:</label> <input type="text"
							id="UsernameUsuario" name="UsernameUsuario"
							placeholder="Ingrese un nombre de Usuario" required>
					</div>
					<div class="form-element">
						<label for="ContrasenaUsuario">CONTRASEÑA:</label> <input
							type="password" id="ContrasenaUsuario" name="ContrasenaUsuario"
							placeholder="Ingrese una contraseña" required>
					</div>
					<div class="form-element">
						<label for="ConfirmarContrasenaUsuario">CONFIRMAR
							CONTRASEÑA:</label> <input type="password"
							id="ConfirmarContrasenaUsuario" name="ConfirmarContrasenaUsuario"
							placeholder="Ingrese la contraseña de nuevo" required>
					</div>
				</div>

				<div class="fila">
					<div class="form-element">
						<label for="TipoUsuario">TIPO DE USUARIO:</label> <select
							id="TipoUsuario" name="TipoUsuario" required>
							<option value="" disabled selected>-- SELECCIONE TIPO DE
								USUARIO --</option>
							<option value="1">Administrador</option>
							<option value="2">Cliente</option>
						</select>
					</div>
				</div>
			</div>

			<div class="button-container">
				<input type="submit" value="Guardar Usuario"
					name="btnAgregarUsuario">
			</div>
		</form>
		
		<hr>
		<br>
		
		<form action="SIUsuarios" method="POST">
			<input type="hidden" name="action" value="asignarUsuario">
				<h2>Asignar Usuario a Cliente</h2>
					<div class="form-container">
						<div class="fila">
							<div class="form-element">
								<label for="AsignarCliente">Elige a que Cliente se le asignará el usuario:</label>
								<select id="AsignarUsuarioaCuenta" name="AsignarUsuarioaCuenta">
										<%
											ArrayList<Cliente> listaClientes = (ArrayList<Cliente>) request.getAttribute("listaClientes");
											if (listaClientes != null) {
						                      for (Cliente cliente : listaClientes) {
						                        %>

													<option value="<%= cliente.getIdCliente() %>"
														><%= "Cliente: " + cliente.getNombre() + " " + cliente.getApellido() + " | DNI: " + cliente.getDni() %></option>

													<% 
						                      }
						                    } else { 
						                      %>
													<option value="" disabled>No hay datos disponibles</option>

													<% } 
						                %>
										
										
										<% 
											String desplegableHTML = (String) request.getAttribute("desplegableHTML"); 
										%>
										<% if(desplegableHTML != null && !desplegableHTML.isEmpty()) { %>
											<%= desplegableHTML %>
										<% } %>
								</select>
								<div class="button-container">
									<input type="submit" value="Asignar Usuario" name="btnAsignarUsuario">
								</div>
							</div>
						</div>
					</div>
		</form>

		<div class="formulario-mensaje"
			style="<%= request.getAttribute("mensajeExito") != null && !request.getAttribute("mensajeExito").toString().isEmpty() ? "display: block;" : "display: none;" %>">
			<form>
				<h3 style="color: green;"><%= request.getAttribute("mensajeExito") %></h3>
			</form>
		</div>
		<div class="formulario-mensaje"
			style="<%= request.getAttribute("mensajeError") != null && !request.getAttribute("mensajeError").toString().isEmpty() ? "display: block;" : "display: none;" %>">
			<form>
				<h3 style="color: red;"><%= request.getAttribute("mensajeError") %></h3>
			</form>
		</div>
	</div>

	<script defer src="js/clientes.js"></script>
</body>
</html>