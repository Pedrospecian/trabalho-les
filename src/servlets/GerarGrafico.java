package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import viewHelpers.LoginViewHelper;
import viewHelpers.PedidoViewHelper;
import utils.Campo;
import utils.ItemGrafico;
import facades.FachadaPedido;

public class GerarGrafico extends HttpServlet {
	private static final long serialVersionUID = 12;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LoginViewHelper lvh = new LoginViewHelper();
		if(!lvh.isAuthorized(req, resp, 5)){
			resp.sendRedirect("/trabalho-les/home");
		}else{
			req.setCharacterEncoding("UTF-8");
			resp.setCharacterEncoding("UTF-8");

			FachadaPedido fachada = new FachadaPedido();

			Campo[] campos = PedidoViewHelper.getGerarGraficoCampos(req);

			if(fachada.validarCampos(campos)) {
				ArrayList<ItemGrafico> itens = fachada.gerarGrafico(campos);

				req.setAttribute("dataInicio", campos[0].getValor());
				req.setAttribute("dataFim", campos[1].getValor());
				req.setAttribute("tipo", campos[2].getValor());
				req.setAttribute("itens", itens);
				req.setAttribute("headerHTML", lvh.getHeader(req, resp, 5));
				req.getRequestDispatcher("admin/graficoVendas.jsp").forward(req, resp);
			} else {
				//resp.sendRedirect("/trabalho-les/home");
			}
		}
	}
}
