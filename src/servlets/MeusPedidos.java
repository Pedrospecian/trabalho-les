package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.Campo;
import utils.ResultadosBusca;
import facades.FachadaPedido;
import viewHelpers.LoginViewHelper;
import viewHelpers.PedidoViewHelper;

public class MeusPedidos extends HttpServlet {
	private static final long serialVersionUID = 12;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LoginViewHelper lvh = new LoginViewHelper();
		if(!lvh.isAuthorized(req, resp, 2)){
			resp.sendRedirect("/trabalho-les/home");
		}else{
			req.setCharacterEncoding("UTF-8");
			resp.setCharacterEncoding("UTF-8");
			
			FachadaPedido fachadaPedido = new FachadaPedido();
			
			PedidoViewHelper vh = new PedidoViewHelper();
			
			Campo[] camposBusca = vh.listagemCampos(req);

			ResultadosBusca resultadosBusca = fachadaPedido.selectPedidosPorCliente(lvh.getUsuarioLogadoId(req, resp), camposBusca);
			String linksPaginacao = "";
			
			req.setAttribute("registros", resultadosBusca.getResultados());
	        req.setAttribute("linksPaginacao", linksPaginacao);

        	req.setAttribute("headerHTML", lvh.getHeader(req, resp, 2));
			req.getRequestDispatcher("pedido/meusPedidos.jsp").forward(req, resp);
		}
	}
}
