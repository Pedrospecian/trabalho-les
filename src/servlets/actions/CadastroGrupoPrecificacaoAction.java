package servlets.actions;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.Campo;

import facades.FachadaGrupoPrecificacao;
import model.GrupoPrecificacao;

import viewHelpers.GrupoPrecificacaoViewHelper;
import viewHelpers.LoginViewHelper;

public class CadastroGrupoPrecificacaoAction extends HttpServlet {
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
				GrupoPrecificacaoViewHelper vh = new GrupoPrecificacaoViewHelper();
				Campo[] campos = vh.cadastroCampos(req);

				FachadaGrupoPrecificacao fachada = new FachadaGrupoPrecificacao();

				if(fachada.validarCampos(campos)) {
		        	GrupoPrecificacao gp = vh.instancia(campos);

		        	fachada.insert(gp, LoginViewHelper.getLogInfo(req, resp));
		        	resp.sendRedirect("/trabalho-les/listagemGruposPrecificacao");
		        } else {
	    	       
		        }
	    	} catch(Exception e) {
	    		e.printStackTrace();
	    	} 
	    }
	}
}
