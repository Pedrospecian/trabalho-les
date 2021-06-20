package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import facades.FachadaCliente;
import facades.FachadaFornecedor;
import facades.FachadaLivro;
import facades.FachadaUsuario;
import model.Livro;
import utils.Campo;
import utils.ResultadosBusca;
import viewHelpers.LivroViewHelper;
import viewHelpers.LoginViewHelper;

public class ListagemEstoque extends HttpServlet {
	private static final long serialVersionUID = 12;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoginViewHelper lvh = new LoginViewHelper();
        if(!lvh.isAuthorized(req, resp, 1)){
            resp.sendRedirect("/trabalho-les/home");
        }else{
            req.setCharacterEncoding("UTF-8");
            resp.setCharacterEncoding("UTF-8");
            
            Campo[] campos = LivroViewHelper.getListagemEstoqueCampos(req);

            FachadaLivro fachada = new FachadaLivro();
            ResultadosBusca resultadosBusca = fachada.selectEstoque(Long.parseLong(req.getParameter("id")), campos);
            Livro livro = fachada.selectSingle(Long.parseLong(req.getParameter("id")));

            int resultadosPorPagina = 10;

            if (req.getParameter("resultadosPorPagina") != null && req.getParameter("resultadosPorPagina").matches("^[0-9]+$")) {
                resultadosPorPagina = Integer.parseInt(req.getParameter("resultadosPorPagina"));
            }

            FachadaFornecedor fachadaf = new FachadaFornecedor();
            ResultadosBusca fornecedores = fachadaf.select(null);

            FachadaUsuario fachadau = new FachadaUsuario();
            ResultadosBusca usuarios = fachadau.select(null);

            FachadaCliente fachadac = new FachadaCliente();
            ResultadosBusca clientes = fachadac.select(null);

            req.setAttribute("registros", resultadosBusca.getResultados());
            req.setAttribute("resultadosPorPagina", resultadosPorPagina);
            req.setAttribute("campos", campos);
            req.setAttribute("livro", livro);
            req.setAttribute("headerHTML", lvh.getHeader(req, resp, 1));
            req.setAttribute("fornecedores", fornecedores.getResultados());
            req.setAttribute("clientes", clientes.getResultados());
            req.setAttribute("usuarios", usuarios.getResultados());
            req.getRequestDispatcher("livro/listagemEstoque.jsp").forward(req, resp);
        }
	}
}
