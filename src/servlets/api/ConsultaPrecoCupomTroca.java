package servlets.api;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.ResultadosBusca;
import facades.FachadaCupomTroca;
import facades.FachadaPedido;
import viewHelpers.LoginViewHelper;

import model.CupomTroca;

public class ConsultaPrecoCupomTroca extends HttpServlet {
	private static final long serialVersionUID = 12;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");
		LoginViewHelper lvh = new LoginViewHelper();
		
		FachadaCupomTroca fachada = new FachadaCupomTroca();

		double valorCupomTroca = 0;

		CupomTroca cupom = fachada.encontraCupomTroca(req.getParameter("cupomTroca"), lvh.getUsuarioLogadoId(req, resp));

		if (cupom != null) {
			valorCupomTroca = cupom.getValor();
		}

		PrintWriter out = resp.getWriter();  
        out.println(valorCupomTroca);
		
		
	}
}
