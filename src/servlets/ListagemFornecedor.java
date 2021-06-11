package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import viewHelpers.LoginViewHelper;
import viewHelpers.FornecedorViewHelper;
import facades.FachadaFornecedor;
import utils.ResultadosBusca;
import utils.Campo;

public class ListagemFornecedor extends HttpServlet {
	private static final long serialVersionUID = 12;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LoginViewHelper lvh = new LoginViewHelper();
		if(!lvh.isAuthorized(req, resp, 1)){
			resp.sendRedirect("/trabalho-les/home");
		}else{
			req.setCharacterEncoding("UTF-8");
			resp.setCharacterEncoding("UTF-8");
			Campo[] campos = FornecedorViewHelper.getListagemFornecedorCampos(req);
			FachadaFornecedor fachada = new FachadaFornecedor();
			ResultadosBusca resultados = fachada.select(campos);
			req.setAttribute("headerHTML", lvh.getHeader(req, resp, 1));
			req.setAttribute("registros", resultados.getResultados());
			req.setAttribute("campos", campos);
			req.getRequestDispatcher("fornecedor/listagemFornecedor.jsp").forward(req, resp);
		}
	}
}
