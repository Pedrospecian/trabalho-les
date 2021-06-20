package servlets.actions;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import facades.FachadaTroca;
import viewHelpers.LoginViewHelper;

public class DecidirPedidoTroca extends HttpServlet {
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

			FachadaTroca fachada = new FachadaTroca();

			if (req.getParameter("id").matches("^[0-9]+$") && req.getParameter("autorizar").matches("^[0-9]$")) {
				fachada.decidirPedidoTroca(Long.parseLong(req.getParameter("id")), Integer.parseInt(req.getParameter("autorizar")), LoginViewHelper.getLogInfo(req, resp));

		   		resp.sendRedirect("/trabalho-les/listagemSolicitacoesTroca");
			}		
	    }
	}
}
