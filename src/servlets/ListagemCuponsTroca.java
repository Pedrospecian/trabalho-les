package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import facades.FachadaPedido;
import model.Pedido;
import utils.ResultadosBusca;
import viewHelpers.LoginViewHelper;

public class ListagemCuponsTroca extends HttpServlet {
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

			ResultadosBusca registros = fachada.listagemCuponsTroca(34);
			System.out.println(registros.getResultados().size());

			System.out.println(registros.getResultados().get(0).getClass());
			req.setAttribute("registros", registros.getResultados());
			req.setAttribute("headerHTML", lvh.getHeader(req, resp, 1));
			req.getRequestDispatcher("cupom/listagemCuponsTroca.jsp").forward(req, resp);
		}
	}
}
