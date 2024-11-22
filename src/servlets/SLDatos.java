package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;

import dao.UsuarioDao;

@WebServlet("/SLDatos")
public class SLDatos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public SLDatos() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("btn")!=null) {
		
		Cookie[] cookies = request.getCookies();
		
		String nom = cookies[0].getValue();
		
		String password = request.getParameter("password");
		
		UsuarioDao udao = new UsuarioDao();
		udao.obtenerUsuarioporLogin(nom, password);
		
		request.setAttribute("DatosUsuario", udao);
		
		RequestDispatcher rd= request.getRequestDispatcher("/InformacionCliente.jsp");
		rd.forward(request, response);
		
	}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}