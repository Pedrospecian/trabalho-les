package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import viewHelpers.LoginViewHelper;
import facades.FachadaLivro;
import facades.FachadaGrupoPrecificacao;
import utils.ResultadosBusca;
import utils.Campo;

public class CadastroLivro extends HttpServlet {
	private static final long serialVersionUID = 12;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LoginViewHelper lvh = new LoginViewHelper();
		if(!lvh.isAuthorized(req, resp, 1)){
			resp.sendRedirect("/trabalho-les/home");
		}else{
			req.setCharacterEncoding("UTF-8");
			resp.setCharacterEncoding("UTF-8");

			FachadaLivro fachadaLivro = new FachadaLivro();
			FachadaGrupoPrecificacao fachadaGrupoPrecificacao = new FachadaGrupoPrecificacao();

			ResultadosBusca catResultados = fachadaLivro.getCategorias();
			ResultadosBusca ediResultados = fachadaLivro.getEditoras();
			ResultadosBusca autResultados = fachadaLivro.getAutores();
			Campo[] campos = new Campo[1];
			campos[0] = new Campo(1, "1", true, "", false, "status");
			ResultadosBusca gpResultados = fachadaGrupoPrecificacao.select(campos);

			req.setAttribute("headerHTML", lvh.getHeader(req, resp, 1));
			req.setAttribute("categorias", catResultados.getResultados());
			req.setAttribute("autores", autResultados.getResultados());
			req.setAttribute("editoras", ediResultados.getResultados());
			req.setAttribute("gruposPrecificacao", gpResultados.getResultados());
			req.getRequestDispatcher("livro/cadastroLivro.jsp").forward(req, resp); 
		}
	}
}
