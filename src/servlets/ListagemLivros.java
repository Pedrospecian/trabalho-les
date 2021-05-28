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
import viewHelpers.UsuarioViewHelper;
import viewHelpers.LoginViewHelper;

public class ListagemLivros extends HttpServlet {
	private static final long serialVersionUID = 12;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                req.setCharacterEncoding("UTF-8");
                resp.setCharacterEncoding("UTF-8");
                LoginViewHelper lvh = new LoginViewHelper();
                if(!lvh.isAuthorized(req, resp, 5)){
                        resp.sendRedirect("/trabalho-les/home");
                }else{
                        Campo[] campos = LivroViewHelper.getListagemLivrosCampos(req);

                        FachadaLivro fachada = new FachadaLivro();
                        ResultadosBusca resultadosBusca = fachada.select(campos);

                        req.setAttribute("registros", resultadosBusca.getResultados());
                        req.setAttribute("total", resultadosBusca.getContagemTotal());
                        req.setAttribute("campos", campos);
                        req.setAttribute("headerHTML", lvh.getHeader(req, resp, 5));
                        
                        req.setAttribute("isGerenteVendas", String.valueOf(lvh.isAuthorized(req, resp, 4)));

        		req.getRequestDispatcher("livro/listagemLivros.jsp").forward(req, resp);
                }
	}
}
