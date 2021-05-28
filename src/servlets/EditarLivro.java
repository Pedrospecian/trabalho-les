package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import facades.FachadaGrupoPrecificacao;
import facades.FachadaLivro;
import model.Categoria;
import model.Livro;
import utils.Campo;
import utils.ResultadosBusca;
import viewHelpers.LoginViewHelper;

public class EditarLivro extends HttpServlet {
	private static final long serialVersionUID = 12;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LoginViewHelper lvh = new LoginViewHelper();
		if(!lvh.isAuthorized(req, resp, 5)){
			resp.sendRedirect("/trabalho-les/home");
		}else{
			req.setCharacterEncoding("UTF-8");
			resp.setCharacterEncoding("UTF-8");

			FachadaLivro fachadaLivro = new FachadaLivro();
			FachadaGrupoPrecificacao fachadaGrupoPrecificacao = new FachadaGrupoPrecificacao();
			
			Livro livro = fachadaLivro.selectSingle(Long.parseLong(req.getParameter("id")));
			ResultadosBusca catResultados = fachadaLivro.getCategorias();
			ResultadosBusca ediResultados = fachadaLivro.getEditoras();
			ResultadosBusca autResultados = fachadaLivro.getAutores();
			Campo[] campos = new Campo[1];
			campos[0] = new Campo(1, "1", true, "", false, "status");
			ResultadosBusca gpResultados = fachadaGrupoPrecificacao.select(campos);

			req.setAttribute("livro", livro);

			Categoria[] categoriasAssociadas = livro.getCategorias();
			ArrayList<Categoria> categoriasTodas = catResultados.getResultados();
			ArrayList<Categoria> categoriasDisponiveis = new ArrayList<Categoria>();

			for (int i = 0; i < categoriasTodas.size(); i++) {
				boolean entra = true;
				//comentar esse for para teste de repeticao de categorias
				for (int j = 0; j < categoriasAssociadas.length; j++) {
					if (categoriasTodas.get(i).getId() == categoriasAssociadas[j].getId()) {
						entra = false;
						break;
					}
				}

				if (entra) {
					categoriasDisponiveis.add(categoriasTodas.get(i));
				}
			}

			req.setAttribute("categorias", categoriasDisponiveis);

			req.setAttribute("autores", autResultados.getResultados());
			req.setAttribute("editoras", ediResultados.getResultados());
			req.setAttribute("gruposPrecificacao", gpResultados.getResultados());
			req.setAttribute("headerHTML", lvh.getHeader(req, resp, 5));
			req.getRequestDispatcher("livro/editarLivro.jsp").forward(req, resp);
		}
	}
}
