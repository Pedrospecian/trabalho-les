package servlets.actions;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.Campo;
import facades.FachadaGrupoPrecificacao;
import model.GrupoPrecificacao;
import viewHelpers.GrupoPrecificacaoViewHelper;
import viewHelpers.UsuarioViewHelper;
import viewHelpers.LoginViewHelper;

public class AlterarGrupoPrecificacaoStatusAction extends HttpServlet {
	private static final long serialVersionUID = 12;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LoginViewHelper lvh = new LoginViewHelper();
		if(!lvh.isAuthorized(req, resp, 5)){
			resp.sendRedirect("/trabalho-les/home");
		}else{
			resp.setContentType("text/html");

		    resp.sendRedirect("/trabalho-les/listagemGruposPrecificacao");
			try {
				Campo[] campos = GrupoPrecificacaoViewHelper.getAlterarGrupoPrecificacaoStatusActionCampos(req);

				FachadaGrupoPrecificacao fachada = new FachadaGrupoPrecificacao();

				if(fachada.validarCampos(campos)) {
			        long id = Long.parseLong(campos[0].getValor());
					int status = Integer.parseInt(campos[1].getValor());
		        
		        	GrupoPrecificacao grupoPrecificacao = new GrupoPrecificacao(id, new Date(), "", 0, status);
		        	fachada.updateStatus(grupoPrecificacao, LoginViewHelper.getLogInfo(req, resp));
		        } else {
		        	//retorna com os dados invalidos
		        }
	    	} catch(Exception e) {
	    		e.printStackTrace();
	    	}
    	}
	}
}
