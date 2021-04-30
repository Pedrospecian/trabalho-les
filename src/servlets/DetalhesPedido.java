package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Cliente;
import model.Pedido;
import viewHelpers.LoginViewHelper;
import facades.FachadaPedido;
import facades.FachadaCliente;

public class DetalhesPedido extends HttpServlet {
	private static final long serialVersionUID = 12;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LoginViewHelper lvh = new LoginViewHelper();
		if(!lvh.isAuthorized(req, resp, 2)){
			resp.sendRedirect("/trabalho-les/home");
		}else{
			req.setCharacterEncoding("UTF-8");
			resp.setCharacterEncoding("UTF-8");		

			if (req.getParameter("id").matches("^[0-9]+$") ) {

				FachadaPedido fachada = new FachadaPedido();
				FachadaCliente fachadaCliente = new FachadaCliente();
				Pedido pedido = fachada.selectSingle(Long.parseLong(req.getParameter("id")));

				if (pedido.getCliente().getId() == lvh.getUsuarioLogadoId(req, resp)) {
					req.setAttribute("pedido", pedido);
					req.setAttribute("cliente", true);
					req.setAttribute("headerHTML", lvh.getHeader(req, resp, 2));
					req.getRequestDispatcher("pedido/detalhesPedido.jsp").forward(req, resp);
				} else {
					resp.sendRedirect("/trabalho-les/home");
				}
				/*if (req.getParameter("admin") != null) {
			    	req.getRequestDispatcher("pedido/detalhesPedidoAdmin.jsp").forward(req, resp);
				} else {*/
				//}
			}
		}
	}
}
