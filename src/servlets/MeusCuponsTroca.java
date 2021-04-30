package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import facades.FachadaPedido;
import model.CupomTroca;
import utils.ResultadosBusca;
import viewHelpers.LoginViewHelper;

public class MeusCuponsTroca extends HttpServlet {
	private static final long serialVersionUID = 12;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LoginViewHelper lvh = new LoginViewHelper();
		if(!lvh.isAuthorized(req, resp, 2)){
			resp.sendRedirect("/trabalho-les/home");
		}else{
			req.setCharacterEncoding("UTF-8");
			resp.setCharacterEncoding("UTF-8");

			FachadaPedido fachada = new FachadaPedido();

			ResultadosBusca registros = fachada.listagemCuponsTroca(lvh.getUsuarioLogadoId(req, resp));
			req.setAttribute("registros", registros.getResultados());

			System.out.println(registros.getResultados().size());
			req.setAttribute("cliente", "cliente");

        	req.setAttribute("headerHTML", lvh.getHeader(req, resp, 2));
			req.getRequestDispatcher("cupom/listagemCuponsTroca.jsp").forward(req, resp);
		}
	}
}