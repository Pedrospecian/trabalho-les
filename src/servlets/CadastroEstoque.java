package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import viewHelpers.LoginViewHelper;
import facades.FachadaFornecedor;
import utils.Campo;
import utils.ResultadosBusca;

public class CadastroEstoque extends HttpServlet {
	private static final long serialVersionUID = 12;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LoginViewHelper lvh = new LoginViewHelper();
		if(!lvh.isAuthorized(req, resp, 1)){
			resp.sendRedirect("/trabalho-les/home");
		}else{
			req.setCharacterEncoding("UTF-8");
			resp.setCharacterEncoding("UTF-8");

			FachadaFornecedor fachada = new FachadaFornecedor();
			Campo[] campo = {new Campo(1, "1", true, "", true, "status")};
			ResultadosBusca resultados = fachada.select(campo);

			req.setAttribute("idLivro", req.getParameter("id"));
			req.setAttribute("headerHTML", lvh.getHeader(req, resp, 1));
			req.setAttribute("fornecedores", resultados.getResultados());
			req.getRequestDispatcher("livro/cadastroEstoque.jsp").forward(req, resp);
		}
	}
}
