package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import facades.FachadaTroca;
import model.Cliente;
import model.ItemCarrinho;
import utils.Campo;
import viewHelpers.PedidoViewHelper;
import viewHelpers.LoginViewHelper;

public class SolicitarTroca extends HttpServlet {
	private static final long serialVersionUID = 12;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LoginViewHelper lvh = new LoginViewHelper();
		if(!lvh.isAuthorized(req, resp, 2)){
			resp.sendRedirect("/trabalho-les/home");
		}else{
			req.setCharacterEncoding("UTF-8");
			resp.setCharacterEncoding("UTF-8");

			FachadaTroca fachada = new FachadaTroca();

			Campo[] campos = PedidoViewHelper.getSolicitarTrocaCampos(req);

			ItemCarrinho itemCarrinho = new ItemCarrinho(
					Long.parseLong(campos[0].getValor()),
					null,
					null,
					0,
					new Cliente(lvh.getUsuarioLogadoId(req, resp), null, null)
			);
			
			itemCarrinho.setQuantidadeItensTrocados(Integer.parseInt(campos[1].getValor()));

			fachada.solicitarTroca(itemCarrinho, LoginViewHelper.getLogInfo(req, resp));

        	req.setAttribute("headerHTML", lvh.getHeader(req, resp, 2));
			req.getRequestDispatcher("pedido/trocaSolicitada.jsp").forward(req, resp);
		}
	}
}
