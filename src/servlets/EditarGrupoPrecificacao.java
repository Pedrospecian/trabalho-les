package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.GrupoPrecificacao;
import utils.Campo;
import facades.FachadaGrupoPrecificacao;
import viewHelpers.LoginViewHelper;

public class EditarGrupoPrecificacao extends HttpServlet {
	private static final long serialVersionUID = 12;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LoginViewHelper lvh = new LoginViewHelper();
		if(!lvh.isAuthorized(req, resp, 5)){
			resp.sendRedirect("/trabalho-les/home");
		}else{
			req.setCharacterEncoding("UTF-8");
			resp.setCharacterEncoding("UTF-8");
			FachadaGrupoPrecificacao fachada = new FachadaGrupoPrecificacao();
			Campo[] campos = new Campo[1];
			campos[0] = new Campo(1, req.getParameter("id"), true, "", true, "id");

			if(fachada.validarCampos(campos)) {
				GrupoPrecificacao grupoPrecificacao = fachada.selectSingle(Long.parseLong(campos[0].getValor()));

				req.setAttribute("grupoPrecificacao", grupoPrecificacao);
				req.setAttribute("headerHTML", lvh.getHeader(req, resp, 5));
				req.getRequestDispatcher("grupoprecificacao/editarGrupoPrecificacao.jsp").forward(req, resp);
			}
		}
	}
}
