package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import facades.FachadaPedido;
import utils.ResultadosBusca;
import viewHelpers.LoginViewHelper;

public class ListagemSolicitacoesTroca extends HttpServlet {
	private static final long serialVersionUID = 12;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LoginViewHelper lvh = new LoginViewHelper();
		if(!lvh.isAuthorized(req, resp, 1)){
			resp.sendRedirect("/trabalho-les/home");
		}else{
			req.setCharacterEncoding("UTF-8");
			resp.setCharacterEncoding("UTF-8");

			FachadaPedido fachada = new FachadaPedido();

			ResultadosBusca resultadosBusca = fachada.selectSolicitacoesTroca();
			req.setAttribute("registros", resultadosBusca.getResultados());
			req.setAttribute("headerHTML", lvh.getHeader(req, resp, 1));
			req.getRequestDispatcher("pedido/listagemSolicitacoesTroca.jsp").forward(req, resp);
		}
	}
}
