package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import entidad.Usuario;
import interfaces.DaoUsuarios;
import persistencia.DaoUsuarioMySql;

/**
 * Servlet implementation class PrimerServlet
 */
@WebServlet("/usuarios/login")
public class PrimerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	private DaoUsuarios daoUsuario = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrimerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		daoUsuario = new DaoUsuarioMySql();
		boolean validado;
		
		Usuario aux = new Usuario(-1,request.getParameter("usuario"),request.getParameter("password"));
		validado=daoUsuario.obtener(aux);

		JSONObject json = new JSONObject();
		json.put("validado", validado);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(json.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		daoUsuario = new DaoUsuarioMySql();
		boolean validado;
		
		Usuario aux = new Usuario(-1,request.getParameter("usuario"),request.getParameter("password"));
		validado=daoUsuario.obtener(aux);

		JSONObject json = new JSONObject();
		json.put("validado", validado);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(json.toString());
	}

}
