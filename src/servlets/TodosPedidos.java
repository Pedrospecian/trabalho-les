package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import facades.FachadaPedido;
import facades.FachadaCliente;
import model.Cliente;
import utils.ResultadosBusca;
import viewHelpers.LoginViewHelper;
import viewHelpers.PedidoViewHelper;
import utils.Campo;

public class TodosPedidos extends HttpServlet {
	private static final long serialVersionUID = 12;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LoginViewHelper lvh = new LoginViewHelper();
		if(!lvh.isAuthorized(req, resp, 1)){
			resp.sendRedirect("/trabalho-les/home");
		}else{
			req.setCharacterEncoding("UTF-8");
			resp.setCharacterEncoding("UTF-8");

			FachadaCliente fachadaCliente = new FachadaCliente();
			FachadaPedido fachadaPedido = new FachadaPedido();
			PedidoViewHelper vh = new PedidoViewHelper();
			Campo[] camposBusca = vh.listagemCampos(req);

			Cliente cliente = null;
			ResultadosBusca resultadosBusca;
			ResultadosBusca todosClientes = fachadaCliente.select(new Campo[0]);

			if (req.getParameter("cliente") != null) {
				cliente = fachadaCliente.selectSingle(Long.parseLong(req.getParameter("cliente")), false);
				resultadosBusca = fachadaPedido.selectPedidosPorCliente(Long.parseLong(req.getParameter("cliente")), camposBusca);
			} else {
				resultadosBusca = fachadaPedido.select(camposBusca);
			}

			String[] linksPaginacao = null;

			req.setAttribute("cliente", cliente);
			req.setAttribute("registros", resultadosBusca.getResultados());
	        req.setAttribute("linksPaginacao", linksPaginacao);
	        req.setAttribute("headerHTML", lvh.getHeader(req, resp, 1));
	        req.setAttribute("clientes", todosClientes.getResultados());
	        req.setAttribute("campos", camposBusca);
			req.getRequestDispatcher("pedido/listagemPedidosAdmin.jsp").forward(req, resp);
		}
	}
}
