package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import facades.FachadaCliente;
import model.Cliente;
import viewHelpers.LoginViewHelper;
import utils.Campo;
import utils.ResultadosBusca;
import viewHelpers.PedidoViewHelper;

public class ListagemPedidosAdmin extends HttpServlet {
	private static final long serialVersionUID = 12;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LoginViewHelper lvh = new LoginViewHelper();
		if(!lvh.isAuthorized(req, resp, 1)){
			resp.sendRedirect("/trabalho-les/home");
		}else{
			req.setCharacterEncoding("UTF-8");
			resp.setCharacterEncoding("UTF-8");
			PedidoViewHelper vh = new PedidoViewHelper();

			Campo[] camposBusca = vh.listagemCampos(req);


			FachadaCliente fachadaCliente = new FachadaCliente();
			Cliente cliente = fachadaCliente.selectSingle(Long.parseLong(req.getParameter("cliente")), false);

			ResultadosBusca todosClientes = fachadaCliente.select(null);

			req.setAttribute("cliente", cliente);
			req.setAttribute("headerHTML", lvh.getHeader(req, resp, 1));

	        req.setAttribute("clientes", todosClientes.getResultados());
	        req.setAttribute("campos", camposBusca);
			req.getRequestDispatcher("pedido/listagemPedidosAdmin.jsp").forward(req, resp);
		}
	}
}
