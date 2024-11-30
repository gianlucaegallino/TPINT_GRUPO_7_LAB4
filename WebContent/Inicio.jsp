<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%

  Cookie[] cookies = null;
  
  cookies = request.getCookies();
  
  String name = null;
  String surname = null;
  
  //Agarra los valores de las cookies

  if(cookies != null && cookies.length > 1) { // si hay mas cookies que la JSESSIONID, que es seteada automaticamente
		  for (int i = 0; i < cookies.length; i++) {
			
		  	if (cookies[i].getName().equals("NombrePersona")){
		  		name = cookies[i].getValue();
		  	}
		  	if (cookies[i].getName().equals("ApellidoPersona")){
		  		surname = cookies[i].getValue();
		  	}
		  }
	  } else {
		  //Agarra los valores de la request, como un backup (las cookies tardan en cargar)
		  name = (String) request.getAttribute("NombrePersona");
		  surname = (String) request.getAttribute("ApellidoPersona");
	  }
   
  
  %>

<!-- HTML -->

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link
	href="https://fonts.googleapis.com/css2?family=Nunito:wght@400;600;700&display=swap"
	rel="stylesheet" />

<!-- Estilo Local -->
<link rel="stylesheet" href="./css/estiloInicioUs.css" />
<link rel="stylesheet" href="./css/normalize.css" />
<link rel="icon" type="image/x-icon" href="./images/favicon.ico">
<title>Inicio | Banco Liberacion</title>
</head>

<body class="bodyInicio">
	<header class="headerInicio">
		<div class="logo">
			<a href="Inicio.jsp"> <img src="images/logo.png" alt="Logo"
				class="icon" />
			</a>
		</div>
		<div class="namecard">
			<img src="images/fotoUsuario.jpg" class="pfp">
			<p class="line1"><%= name + " " + surname %></p>
			<p class="line2">
				<a href="Login.jsp" id="cierre">Cerrar Sesion</a>
			</p>
		</div>

	</header>
	<article class="menus">

		<input class="botonInicio" id="botonHistorial" type="submit"
			value="Historial de Movimientos"
			onClick="window.location.href='HistorialdeMovimientos.jsp'" /> <input
			class="botonInicio" id="botonTransferir" type="submit"
			value="Transferir" onClick="window.location.href='Transferir.jsp'" />


		<input class="botonInicio" id="botonPedirPrestamo" type="submit"
			value="Pedir Prestamo"
			onClick="window.location.href='PedirPrestamo.jsp'" /> <input
			class="botonInicio" id="botonPagarPrestamo" type="submit"
			value="Pago de Prestamo"
			onClick="window.location.href='PagarPrestamo.jsp'" /> <input
			class="botonInicio" id="botonInformacionPersonal" type="submit"
			value="Informacion Personal"
			onClick="window.location.href='InformacionCliente.jsp'" />

	</article>
	<hr />
	<script defer src="./js/goToPage.js"></script>
</body>
</html>
