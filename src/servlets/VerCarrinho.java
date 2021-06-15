package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import viewHelpers.PedidoViewHelper;
import model.Carrinho;
import facades.FachadaCarrinho;
import facades.FachadaPedido;
import viewHelpers.LoginViewHelper;

public class VerCarrinho extends HttpServlet {
	private static final long serialVersionUID = 12;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LoginViewHelper lvh = new LoginViewHelper();
		if(!lvh.isAuthorized(req, resp, 2)){
			resp.sendRedirect("/trabalho-les/home");
		}else{
			req.setCharacterEncoding("UTF-8");
			resp.setCharacterEncoding("UTF-8");

			FachadaCarrinho fachada = new FachadaCarrinho();

			Carrinho carrinho = fachada.selectCarrinho(lvh.getUsuarioLogadoId(req, resp));
			double valorTotal = PedidoViewHelper.calcularTotalCarrinho(carrinho);

			req.setAttribute("carrinho", carrinho);
			req.setAttribute("valorTotal", valorTotal);

        	req.setAttribute("headerHTML", lvh.getHeader(req, resp, 2));
			req.getRequestDispatcher("front/carrinho.jsp").forward(req, resp);
		}
	}
}
