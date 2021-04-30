package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Carrinho;
import model.Cliente;
import viewHelpers.PedidoViewHelper;
import facades.FachadaPedido;
import facades.FachadaCliente;
import viewHelpers.LoginViewHelper;

public class Checkout extends HttpServlet {
	private static final long serialVersionUID = 12;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		LoginViewHelper lvh = new LoginViewHelper();
		if(!lvh.isAuthorized(req, resp, 2)){
			resp.sendRedirect("/trabalho-les/home");
		}else{
			FachadaPedido fachadaPedido = new FachadaPedido();
			FachadaCliente fachadaCliente = new FachadaCliente();

			Carrinho carrinho = fachadaPedido.selectCarrinho(lvh.getUsuarioLogadoId(req, resp));

			if (carrinho == null) {
				resp.sendRedirect("/trabalho-les/home");
			} else {
				double valorTotal = PedidoViewHelper.calcularTotalCarrinho(carrinho);

				Cliente cliente = fachadaCliente.selectSingle(lvh.getUsuarioLogadoId(req, resp), true);

				req.setAttribute("carrinho", carrinho);
				req.setAttribute("cliente", cliente);
				req.setAttribute("valorTotal", valorTotal);

        		req.setAttribute("headerHTML", lvh.getHeader(req, resp, 2));
				req.getRequestDispatcher("front/checkout.jsp").forward(req, resp);
			}
		}
	}
}
