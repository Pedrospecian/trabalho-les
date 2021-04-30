package servlets.api;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.ResultadosBusca;
import facades.FachadaPedido;

import model.CupomDesconto;

public class ConsultaPrecoCupomDesconto extends HttpServlet {
	private static final long serialVersionUID = 12;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");
		
		FachadaPedido fachadaPedido = new FachadaPedido();

		double valorCupomDesconto = 0;

		CupomDesconto cupom = fachadaPedido.encontraCupomDesconto(req.getParameter("cupomDesconto"));

		if (cupom != null) {
			valorCupomDesconto = cupom.getValor();
		}

		PrintWriter out = resp.getWriter();  
        out.println(valorCupomDesconto);
		
		
	}
}
