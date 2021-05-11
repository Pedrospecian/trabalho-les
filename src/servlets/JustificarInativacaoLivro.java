package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import viewHelpers.LoginViewHelper;

import java.util.ArrayList;
import facades.FachadaLivro;
import model.CategoriaInativacao;
import model.Livro;

public class JustificarInativacaoLivro extends HttpServlet {
	private static final long serialVersionUID = 12;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LoginViewHelper lvh = new LoginViewHelper();
		if(!lvh.isAuthorized(req, resp, 1)){
			resp.sendRedirect("/trabalho-les/home");
		}else{
			req.setCharacterEncoding("UTF-8");
			resp.setCharacterEncoding("UTF-8");
			FachadaLivro fachada = new FachadaLivro();

			Livro livro = fachada.selectSingle(Long.parseLong(req.getParameter("id")));

			ArrayList<CategoriaInativacao> categorias = fachada.getCategoriasInativacao();
			req.setAttribute("livro", livro);
			req.setAttribute("categorias", categorias);
			req.setAttribute("headerHTML", lvh.getHeader(req, resp, 1));
			req.getRequestDispatcher("livro/justificarInativacao.jsp").forward(req, resp);
		}
	}
}
