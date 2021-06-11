package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import facades.FachadaLivro;
import utils.Campo;
import utils.ResultadosBusca;
import viewHelpers.LivroViewHelper;
import viewHelpers.LoginViewHelper;

public class Busca extends HttpServlet {
	private static final long serialVersionUID = 12;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {		
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		LoginViewHelper lvh = new LoginViewHelper();

		Campo[] campos = LivroViewHelper.getListagemLivrosCamposHeader(req);		
		
		FachadaLivro fachada = new FachadaLivro();
        ResultadosBusca resultadosBusca = fachada.select(campos);
        ResultadosBusca catResultados = fachada.getCategorias();
        ResultadosBusca ediResultados = fachada.getEditoras();
		ResultadosBusca autResultados = fachada.getAutores();

        req.setAttribute("registros", resultadosBusca.getResultados());
        req.setAttribute("campos", campos);
		req.setAttribute("autores", autResultados.getResultados());
		req.setAttribute("editoras", ediResultados.getResultados());
		req.setAttribute("categorias", catResultados.getResultados());
		req.setAttribute("headerHTML", lvh.getHeader(req, resp, 2));
		req.getRequestDispatcher("front/busca.jsp").forward(req, resp);
	}
}
