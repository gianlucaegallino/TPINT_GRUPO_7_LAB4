<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="Inicio.jsp" %>
<%@ page import="java.util.ArrayList"%>
<%@ page import="dao.UsuarioDao" %>
<%@ page import="entidades.Cliente" %>
    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<link rel="stylesheet" href="normalize.css" />
<link rel="StyleSheet" href="EstiloInformacion.css" type="text/css"/>
<link rel="icon" type="image/x-icon" href="/images/favicon.ico">

<style>
table {
  width: 50%;
  border: 1px solid;
}
</style>
</head>

<body>

<form method="get" action="SLDatos">
<input type="password" name="cont" value="password">
<input type="submit" name="btn" value="Mostrar Datos" />

</form>

<%

Cliente cl = new Cliente();

if(request.getAttribute("DatosUsuario")!=null)
    {
	
	cl = (Cliente) request.getAttribute("DatosUsuario");
	
    }

%>



<h2>Informacion del usuario</h2>

<table border="1" style="margin: 0 auto;">

  <tr>
    <td> Nombre: </td>
    <td> <% cl.getNombre(); %> </td>
  </tr>
  
  <tr>
    <td> Apellido: </td>
    <td> <% cl.getApellido(); %> </td>
  </tr>
  
  <tr>
    <td> Fecha de nacimiento: </td>
    <td> <% cl.getFecha_nacimiento(); %> </td>
  </tr>

   <tr>
    <td> Correo electronico: </td>
    <td> <% cl.getCorreo_electronico(); %> </td>
  </tr>
  
   <tr>
    <td> Telefono: </td>
    <td> <% cl.getTelefono(); %> </td>
  </tr>
  
   <tr>
    <td> Estado de Cuenta: </td>
    <td> <% cl.getEstado(); %> </td>
  </tr>
</table>


</body>
</html>