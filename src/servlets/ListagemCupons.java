package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import viewHelpers.LoginViewHelper;
import viewHelpers.CupomDescontoViewHelper;
import facades.FachadaCupomDesconto;
import utils.Campo;
import utils.ResultadosBusca;

public class ListagemCupons extends HttpServlet {
	private static final long serialVersionUID = 12;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LoginViewHelper lvh = new LoginViewHelper();
		if(!lvh.isAuthorized(req, resp, 5)){
			resp.sendRedirect("/trabalho-les/home");
		}else{
			req.setCharacterEncoding("UTF-8");
			resp.setCharacterEncoding("UTF-8");
			Campo[] campos = CupomDescontoViewHelper.getListagemCuponsCampos(req);

            FachadaCupomDesconto fachada = new FachadaCupomDesconto();
            ResultadosBusca resultadosBusca = fachada.select(campos);  
			req.setAttribute("registros", resultadosBusca.getResultados());
			req.setAttribute("campos", campos);
			req.setAttribute("headerHTML", lvh.getHeader(req, resp, 5));
			req.getRequestDispatcher("cupom/listagemCupons.jsp").forward(req, resp);
		}
	}
}
