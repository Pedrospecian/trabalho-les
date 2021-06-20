package servlets.actions;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.Campo;
import facades.FachadaConfiguracoes;
import model.Configuracao;
import viewHelpers.ConfiguracoesViewHelper;
import viewHelpers.LoginViewHelper;

public class AlterarConfiguracoes extends HttpServlet {
	private static final long serialVersionUID = 12;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LoginViewHelper lvh = new LoginViewHelper();
		if(!lvh.isAuthorized(req, resp, 1)){
			resp.sendRedirect("/trabalho-les/home");
		}else{
			req.setCharacterEncoding("UTF-8");
			resp.setCharacterEncoding("UTF-8");
			resp.setContentType("text/html");
			try {
				ConfiguracoesViewHelper vh = new ConfiguracoesViewHelper();
				Campo[] campos = vh.alterarCampos(req);

				FachadaConfiguracoes fachada = new FachadaConfiguracoes();

				if(fachada.validarCampos(campos)) {
					Configuracao[] configuracoes = new Configuracao[campos.length];

					for (int i = 0; i < configuracoes.length; i++) {
						configuracoes[i] = new Configuracao((long)1, campos[i].getNome(), campos[i].getValor(), null);
					}

	        		fachada.updateConfiguracoes(configuracoes, LoginViewHelper.getLogInfo(req, resp));
		        
	        		resp.sendRedirect("/trabalho-les/configuracoes");
		        } else {
		        	//retorna com os dados invalidos
		        }
	    	} catch(Exception e) {
	    		e.printStackTrace();
	    	}
    	}
	}
}
