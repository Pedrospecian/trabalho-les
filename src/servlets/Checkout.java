package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Carrinho;
import model.Cliente;
import model.TipoEndereco;
import model.TipoResidencia;
import model.FuncaoEndereco;
import model.TipoLogradouro;
import model.Bandeira;
import viewHelpers.PedidoViewHelper;
import facades.FachadaCarrinho;
import facades.FachadaCliente;
import facades.FachadaSelect;
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
			FachadaCliente fachadaCliente = new FachadaCliente();
			FachadaCarrinho fachadaCarrinho = new FachadaCarrinho();

			Carrinho carrinho = fachadaCarrinho.selectCarrinho(lvh.getUsuarioLogadoId(req, resp));

			if (carrinho == null) {
				resp.sendRedirect("/trabalho-les/home");
			} else {
				double valorTotal = PedidoViewHelper.calcularTotalCarrinho(carrinho);

				Cliente cliente = fachadaCliente.selectSingle(lvh.getUsuarioLogadoId(req, resp), true);

				FachadaSelect fachadaSel = new FachadaSelect();

				ArrayList<TipoEndereco> tiposendereco = fachadaSel.getOpcoesSelect(4);
				ArrayList<TipoResidencia> tiposresidencia = fachadaSel.getOpcoesSelect(5);
				ArrayList<FuncaoEndereco> funcoesendereco = fachadaSel.getOpcoesSelect(6);
				ArrayList<TipoLogradouro> tiposlogradouro = fachadaSel.getOpcoesSelect(7);
				ArrayList<Bandeira> bandeiras = fachadaSel.getOpcoesSelect(8);

				req.setAttribute("carrinho", carrinho);
				req.setAttribute("cliente", cliente);
				req.setAttribute("valorTotal", valorTotal);

        		req.setAttribute("headerHTML", lvh.getHeader(req, resp, 2));

				req.setAttribute("tiposendereco", tiposendereco);
				req.setAttribute("tiposresidencia", tiposresidencia);
				req.setAttribute("funcoesendereco", funcoesendereco);
				req.setAttribute("tiposlogradouro", tiposlogradouro);
				req.setAttribute("bandeiras", bandeiras);

				req.getRequestDispatcher("front/checkout.jsp").forward(req, resp);
			}
		}
	}
}
