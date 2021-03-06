package servlets.actions;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.Campo;

import facades.FachadaCupomDesconto;
import model.CupomDesconto;

import viewHelpers.CupomDescontoViewHelper;
import viewHelpers.LoginViewHelper;

public class CadastroCupomDescontoAction extends HttpServlet {
	private static final long serialVersionUID = 12;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LoginViewHelper lvh = new LoginViewHelper();
		if(!lvh.isAuthorized(req, resp, 5)){
			resp.sendRedirect("/trabalho-les/home");
		}else{
			req.setCharacterEncoding("UTF-8");
			resp.setCharacterEncoding("UTF-8");
			resp.setContentType("text/html");
			try {
				CupomDescontoViewHelper vh = new CupomDescontoViewHelper();
				Campo[] campos = vh.cadastroCampos(req);

				FachadaCupomDesconto fachada = new FachadaCupomDesconto();

				if(fachada.validarCampos(campos)) {
		        	CupomDesconto cupom = vh.instancia(campos);

		        	fachada.insert(cupom, LoginViewHelper.getLogInfo(req, resp));
		        	resp.sendRedirect("/trabalho-les/listagemCupons");
		        } else {
	    	       
		        }
	    	} catch(Exception e) {
	    		e.printStackTrace();
	    	} 
	    }
	}
}
