package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import viewHelpers.LoginViewHelper;
import facades.FachadaCliente;

public class MinhaConta extends HttpServlet {
	private static final long serialVersionUID = 12;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LoginViewHelper lvh = new LoginViewHelper();
		if(!lvh.isAuthorized(req, resp, 2)){
			resp.sendRedirect("/trabalho-les/home");
		}else{
			req.setCharacterEncoding("UTF-8");
			resp.setCharacterEncoding("UTF-8");

			FachadaCliente fachada = new FachadaCliente();

			ArrayList notificacoes = fachada.selectNotificacao(lvh.getUsuarioLogadoId(req, resp));

			fachada.deleteNotificacoes(lvh.getUsuarioLogadoId(req, resp));

			req.setAttribute("headerHTML", lvh.getHeader(req, resp, 2));
			req.setAttribute("notificacoes", notificacoes);
			req.getRequestDispatcher("conta/minhaConta.jsp").include(req, resp); 
		}
	}
}
