package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import facades.FachadaLivro;
import facades.FachadaUsuario;
import utils.ResultadosBusca;
import viewHelpers.LoginViewHelper;
import model.CategoriaAtivacao;

public class LivrosPendentesAtivacao extends HttpServlet {
	private static final long serialVersionUID = 12;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LoginViewHelper lvh = new LoginViewHelper();
		if(!lvh.isAuthorized(req, resp, 3)){
			resp.sendRedirect("/trabalho-les/homeAdmin");
		}else{	
			req.setCharacterEncoding("UTF-8");
			resp.setCharacterEncoding("UTF-8");

			FachadaLivro fachada = new FachadaLivro();
			ResultadosBusca registros = fachada.getSolicitacoesAtivacao(null);
			ArrayList<CategoriaAtivacao> categorias = fachada.getCategoriasAtivacao();

			FachadaUsuario fachadau = new FachadaUsuario();
			ResultadosBusca usuarios = fachadau.select(null);

			req.setAttribute("headerHTML", lvh.getHeader(req, resp, 1));
			req.setAttribute("registros", registros.getResultados());
			req.setAttribute("categorias", categorias);
			req.setAttribute("usuarios", usuarios.getResultados());
			req.getRequestDispatcher("livro/listagemLivrosPendentesAtivacao.jsp").forward(req, resp);
		}
	}
}
