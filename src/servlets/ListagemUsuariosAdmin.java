package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import viewHelpers.UsuarioViewHelper;
import viewHelpers.PaginacaoViewHelper;
import facades.FachadaUsuario;
import utils.Campo;
import utils.ResultadosBusca;
import viewHelpers.LoginViewHelper;

public class ListagemUsuariosAdmin extends HttpServlet {
	private static final long serialVersionUID = 12;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		LoginViewHelper lvh = new LoginViewHelper();
		if(!lvh.isAuthorized(req, resp, 1)){
			resp.sendRedirect("/trabalho-les/home");
		}else{
	        Campo[] campos = UsuarioViewHelper.getListagemUsuariosCampos(req);

	        FachadaUsuario fachada = new FachadaUsuario();
	        ResultadosBusca resultadosBusca = fachada.select(campos);
	        String[] linksPaginacao = PaginacaoViewHelper.createLinksPaginacao(campos[4], resultadosBusca);

	        req.setAttribute("registros", resultadosBusca.getResultados());
	        req.setAttribute("total", resultadosBusca.getContagemTotal());
	        req.setAttribute("linksPaginacao", linksPaginacao);
	        req.setAttribute("campos", campos);
	        req.setAttribute("headerHTML", lvh.getHeader(req, resp, 1));
			req.getRequestDispatcher("usuarioadmin/listagemUsuariosAdmin.jsp").forward(req, resp); 
		}
	}
}
