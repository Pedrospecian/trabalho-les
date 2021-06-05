package servlets.actions;

import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.Campo;
import facades.FachadaGrupoPrecificacao;
import model.GrupoPrecificacao;
import model.Endereco;
import model.Documento;
import model.TipoCliente;
import viewHelpers.GrupoPrecificacaoViewHelper;
import viewHelpers.LoginViewHelper;

public class AlteraGrupoPrecificacaoAction extends HttpServlet {
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
				Campo[] campos = GrupoPrecificacaoViewHelper.getAlterarGrupoPrecificacaoActionCampos(req);

				FachadaGrupoPrecificacao fachada = new FachadaGrupoPrecificacao();

				if(fachada.validarCampos(campos)) {
					String nome = campos[1].getValor();
			        double valor = Double.parseDouble(campos[2].getValor());
			        int status = Integer.parseInt(campos[3].getValor());

		        	GrupoPrecificacao grupoPrecificacao = new GrupoPrecificacao(Long.parseLong(campos[0].getValor()), new Date(), nome, valor, status);

		        	fachada.update(grupoPrecificacao, LoginViewHelper.getLogInfo(req, resp));
		        	resp.sendRedirect("/trabalho-les/listagemGruposPrecificacao");
		        } else {
	    	       
		        }
	    	} catch(Exception e) {
	    		e.printStackTrace();
	    	}
	    }
	}
}
