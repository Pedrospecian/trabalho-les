package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import viewHelpers.LoginViewHelper;
import facades.FachadaSelect;
import model.TipoUsuario;

public class CadastroUsuarioAdmin extends HttpServlet {
	private static final long serialVersionUID = 12;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LoginViewHelper lvh = new LoginViewHelper();
		if(!lvh.isAuthorized(req, resp, 1)){
			resp.sendRedirect("/trabalho-les/home");
		}else{
			req.setCharacterEncoding("UTF-8");
			resp.setCharacterEncoding("UTF-8");

			FachadaSelect fachadaSel = new FachadaSelect();
			ArrayList<TipoUsuario> tiposusuario = fachadaSel.getOpcoesSelect(10);

			req.setAttribute("headerHTML", lvh.getHeader(req, resp, 1));
			req.setAttribute("tiposusuario", tiposusuario);
			req.getRequestDispatcher("usuarioadmin/cadastroUsuarioAdmin.jsp").forward(req, resp);
		}
	}
}
