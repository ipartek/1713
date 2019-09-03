package com.ipartek.formacion.controller.backoffice;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import com.ipartek.formacion.controller.pojo.Alert;
import com.ipartek.formacion.model.dao.UsuarioDAO;
import com.ipartek.formacion.model.pojo.Usuario;

/**
 * Servlet implementation class UsuariosController
 */
@WebServlet("/UsuariosController")
public class UsuariosController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static UsuarioDAO usuarioDAO = UsuarioDAO.getInstance();

	public static final String VIEW_INDEX = "usuarios/lista.jsp";
	public static final String VIEW_FORM = "usuarios/formulario.jsp";
	public static String view = VIEW_INDEX;

	private static final String OP_CREATE = "a23d";
	private static final String OP_UPDATE = "l78t";
	private static final String OP_DELETE = "d3ng";
	private static final String OP_LIST = "q092d";

	private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UsuariosController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String op = request.getParameter("op");
		if (op == null) {
			op = OP_LIST;
		}

		switch (op) {
		case OP_CREATE:
			create(request, response);
			break;

		case OP_UPDATE:
			update(request, response);
			break;

		case OP_DELETE:
			delete(request, response);
			break;

		default:
			listar(request, response);
			break;
		}

		request.getRequestDispatcher(view).forward(request, response);
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) {
		String sid = request.getParameter("id");
		int id = Integer.parseInt(sid);

		if (usuarioDAO.delete(id)) {
			request.setAttribute("mensaje", new Alert("success", "Registro Eliminado"));
		} else {
			request.setAttribute("mensaje", new Alert("danger", "ERROR, no se pudo eliminar"));
		}

		listar(request, response);

	}

	private void listar(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("usuarios", usuarioDAO.getAll());
		view = VIEW_INDEX;

	}

	private void update(HttpServletRequest request, HttpServletResponse response) {
		String sid = request.getParameter("id");
		String nombre = request.getParameter("nombre");
		String contra = request.getParameter("contra");

		Usuario u = new Usuario();
		u.setId(Integer.parseInt(sid));
		u.setNombre(nombre);
		u.setContra(contra);

		Set<ConstraintViolation<Usuario>> violations = validator.validate(u);
		if (violations.isEmpty()) {

			try {

				if (u.getId() == -1) {
					usuarioDAO.crear(u);
				} else {
					usuarioDAO.modificar(u);
				}
				request.setAttribute("mensaje", new Alert("success", "Registro creado con exito"));

			} catch (Exception e) {

				request.setAttribute("mensaje", new Alert("danger", "Tenemos un problema, el codigo ya existe"));
			}

		} else { // hay violaciones de las validaciones

			String mensaje = "";

			for (ConstraintViolation<Usuario> violation : violations) {
				mensaje += violation.getPropertyPath() + ": " + violation.getMessage() + "<br>";
			}
			request.setAttribute("mensaje", new Alert("warning", mensaje));
		}
		request.setAttribute("usuario", u);
		view = VIEW_FORM;

	}

	private void create(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("usuario", new Usuario());
		view = VIEW_FORM;
	}
}
