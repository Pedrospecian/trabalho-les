package servlets.actions;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import facades.FachadaCupomTroca;
import viewHelpers.LoginViewHelper;

public class ConfirmarRecebimentoTroca extends HttpServlet {
	private static final long serialVersionUID = 12;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LoginViewHelper lvh = new LoginViewHelper();
		if(!lvh.isAuthorized(req, resp, 1)){
			resp.sendRedirect("/trabalho-les/home");
		}else{
			resp.setContentType("text/html");
			req.setCharacterEncoding("UTF-8");
			resp.setCharacterEncoding("UTF-8");

			FachadaCupomTroca fachada = new FachadaCupomTroca();

			fachada.confirmarRecebimentoTroca(Long.parseLong(req.getParameter("id")), req.getParameter("retornarEstoque") != null, LoginViewHelper.getLogInfo(req, resp));

		    resp.sendRedirect("/trabalho-les/listagemSolicitacoesTroca");
	    }
	}
}
