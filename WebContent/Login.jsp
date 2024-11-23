<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!-- HTML -->

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!-- Normalize.css -->
    <link rel="stylesheet" href="normalize.css">
     <!-- Estilo local -->
    <link rel="stylesheet" href="css/estilos.css">
    <link rel="icon" type="image/x-icon" href="/images/favicon.ico">
    <title>Iniciar Sesion | Banco Liberacion</title>
    
    <link href="https://fonts.googleapis.com/css2?family=Nunito:ital,wght@0,200;0,300;0,400;0,500;0,600;0,700;0,800;1,300&display=swap" rel="stylesheet">
</head>

  <%

  Cookie[] cookies = null;
  
  cookies = request.getCookies();

  //Remueve las cookies si estan presentes
  
  if( cookies != null ) {
	  for (int i = 0; i < cookies.length; i++) {
	  	cookies[i].setMaxAge(0);
	  	response.addCookie(cookies[i]);
	  }
  }
   
  
  %>

<body class="bodyRegistrarse">
    <header class="headerlogin">
    	<img src="images/logo.png" alt="Logo" class="icon">
        <a href="Inicio.jsp" style="color: #fff; text-decoration: none;">
            <span>Banco Liberacion</span>
        </a>
    </header>
    <div id="main" class="main">
        <section class="seccionRegistrarse">
            <form action="SIniciarSesion" method="post">
                <h2 class="tituloRegistrarse">Iniciar Sesion</h2>
                <div>
                    <input type="text" placeholder="Nombre Usuario" id="nomUsu" name="nomUsu">
                    <div class="error-text"></div>
                </div>
                <div>
                    <input type="password" placeholder="Contrasena" id="password" name="password">
                    <div class="error-text"></div>
                </div>
                <div>
                    <input class="boton" type="submit" value="Iniciar Sesion" name="btnInicioSesion">
                </div>
            </form>
				
				<% if(request.getAttribute("mensaje")=="error")
					{
				%>
						Usuario o Contrasenia incorrecta!
				<%} %>
				
        </section>
    </div>
</html>