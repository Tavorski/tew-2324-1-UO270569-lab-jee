package com.tew.Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HolaMundoVista
 */
@WebServlet(name = "HolaMundoVista", urlPatterns = { "/HolaMundoVista" })
public class HolaMundoVistaServlet extends HttpServlet {
	private static final long serialVersionUID = 2L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HolaMundoVistaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<HTML>");
		out.println("<HEAD><TITLE>Hola Mundo!</TITLE></HEAD>");
		out.println("<BODY>");

		// SALUDO
		String nombre = (String) getServletContext().getAttribute("nombre");
		if ( nombre != null ){
			 out.println("<br>Hola "+nombre+"<br>");
			}
		out.println("Bienvenido a mi primera página web!");
		
		// VISTA: LISTA DE VISITANTES
		Vector listado = (Vector) getServletContext().getAttribute("listado");
		out.println("<br>");
		out.println("Contigo, hoy me han visitado:<br>");
		for ( int i = 0 ; i < listado.size() ; i++ ){
		 out.println("<br>"+(String)listado.elementAt(i));
		}
		out.println("<a href=\"index.html\">volver</a>");

		// CONTADOR DE VISITAS TOTALES AL SERVELT INDEPENDIENTEMENTE DE LA SESION
		Integer contador= (Integer) getServletContext().getAttribute("contador");
		if ( contador == null ){
		 contador = new Integer(0);
		}
		
		// VISTA: CONTADOR DE VISITAS TOTALES
		out.println("<br><br>" + contador +" visitas");
		
		out.println("</BODY></HTML>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
