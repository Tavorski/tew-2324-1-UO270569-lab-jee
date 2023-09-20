package com.tew.Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HolaMundoServlet
 */
@WebServlet(name = "HolaMundo", urlPatterns = { "/HolaMundoCordial" })
public class HolaMundoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HolaMundoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// PROCESADO DE PARAMETROS DE LA SOLICITUD HTTP
		String nombre = (String) request.getParameter("NombreUsuario");
		request.getSession().setAttribute("nombre",nombre);
		
		// VECTOR LISTADO DE USUSARIOS SALUDADOS
		Vector listado = (Vector)request.getSession().getAttribute("listado");
		if (listado == null){
		 listado = new Vector();
		}
		
		// AÑADIR COMO ATRIBUTO DE SESION
		request.getSession().setAttribute("listado",listado);

		// SALUDO
		if ( nombre != null ){
			 listado.addElement(nombre);
			}

		// CONTADOR DE VISITAS TOTALES AL SERVELT INDEPENDIENTEMENTE DE LA SESION
		Integer contador= (Integer) getServletContext().getAttribute("contador");
		if ( contador == null ){
		 contador = new Integer(0);
		}
		// Establecemos el contador como atributo del context bajo el nombre
		// contador. En caso de que ya existiera, sobreescribiría la referencia
		// existente con la nueva.
		getServletContext().setAttribute("contador",new
		Integer(contador.intValue()+1));
		
		RequestDispatcher dispatcher =
				 getServletContext().getNamedDispatcher("HolaMundoVista");
				dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
