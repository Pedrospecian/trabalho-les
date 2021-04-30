package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.CupomDesconto;
import utils.Campo;
import facades.FachadaCupomDesconto;
import viewHelpers.LoginViewHelper;

public class EditarCupom extends HttpServlet {
	private static final long serialVersionUID = 12;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LoginViewHelper lvh = new LoginViewHelper();
		if(!lvh.isAuthorized(req, resp, 1)){
			resp.sendRedirect("/trabalho-les/home");
		}else{
			req.setCharacterEncoding("UTF-8");
			resp.setCharacterEncoding("UTF-8");
			FachadaCupomDesconto fachada = new FachadaCupomDesconto();
			Campo[] campos = new Campo[1];
			campos[0] = new Campo(1, req.getParameter("id"), true, "", true, "id");

			if(fachada.validarCampos(campos)) {
				CupomDesconto cupom = fachada.selectSingle(Long.parseLong(campos[0].getValor()));

				req.setAttribute("cupom", cupom);
				req.setAttribute("headerHTML", lvh.getHeader(req, resp, 1));
				req.getRequestDispatcher("cupom/editarCupom.jsp").include(req, resp); 
			} else {

			}
		}
	}
}
