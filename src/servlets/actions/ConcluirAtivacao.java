package servlets.actions;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.Campo;
import facades.FachadaCliente;
import facades.FachadaLivro;
import model.Cliente;
import viewHelpers.ClienteViewHelper;
import viewHelpers.UsuarioViewHelper;
import viewHelpers.LoginViewHelper;

public class ConcluirAtivacao extends HttpServlet {
	private static final long serialVersionUID = 12;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LoginViewHelper lvh = new LoginViewHelper();
		if(!lvh.isAuthorized(req, resp, 3)){
			resp.sendRedirect("/trabalho-les/homeAdmin");
		}else{
			resp.setContentType("text/html");

			FachadaLivro fachada = new FachadaLivro();

			fachada.concluirAtivacao(Long.parseLong(req.getParameter("id")), Integer.parseInt(req.getParameter("aceite")), LoginViewHelper.getLogInfo(req, resp));

		    resp.sendRedirect("/trabalho-les/livrosPendentesAtivacao");
	    }
	}
}
