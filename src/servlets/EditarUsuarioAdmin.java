package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Usuario;
import model.TipoUsuario;
import utils.Campo;
import facades.FachadaUsuario;
import facades.FachadaSelect;
import viewHelpers.LoginViewHelper;

public class EditarUsuarioAdmin extends HttpServlet {
	private static final long serialVersionUID = 12;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LoginViewHelper lvh = new LoginViewHelper();
		if(!lvh.isAuthorized(req, resp, 1)){
			resp.sendRedirect("/trabalho-les/home");
		}else{
			req.setCharacterEncoding("UTF-8");
			resp.setCharacterEncoding("UTF-8");
			FachadaUsuario fachada = new FachadaUsuario();
			Campo[] campos = new Campo[1];
			campos[0] = new Campo(1, req.getParameter("id"), true, "", true, "id");

			if(fachada.validarCampos(campos, false)) {
				Usuario usuario = fachada.selectSingle(Long.parseLong(campos[0].getValor()));

				FachadaSelect fachadaSel = new FachadaSelect();
				ArrayList<TipoUsuario> tiposusuario = fachadaSel.getOpcoesSelect(10);

				req.setAttribute("usuario", usuario);
				req.setAttribute("headerHTML", lvh.getHeader(req, resp, 1));
				req.setAttribute("tiposusuario", tiposusuario);
				req.getRequestDispatcher("usuarioadmin/editarUsuarioAdmin.jsp").include(req, resp); 
			} else {

			}
		}
	}
}
