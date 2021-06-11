package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import facades.FachadaPedido;
import model.Pedido;
import utils.Campo;
import utils.ResultadosBusca;
import viewHelpers.LoginViewHelper;
import viewHelpers.CupomDescontoViewHelper;

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

			Campo[] campos = CupomDescontoViewHelper.getListagemCuponsTrocaCampos(req);

			FachadaPedido fachada = new FachadaPedido();

			ResultadosBusca registros = fachada.listagemCuponsTroca(campos);
			req.setAttribute("registros", registros.getResultados());
			req.setAttribute("headerHTML", lvh.getHeader(req, resp, 1));
			req.setAttribute("campos", campos);
			req.setAttribute("linkPedido", "pedidoAdmin");
			req.getRequestDispatcher("cupom/listagemCuponsTroca.jsp").forward(req, resp);
		}
	}
}
