package servlets.actions;

import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.Campo;
import facades.FachadaCliente;
import facades.FachadaConfiguracoes;
import model.CartaoCredito;
import model.Cliente;
import model.Configuracao;
import model.Endereco;
import model.Documento;
import model.TipoCliente;
import viewHelpers.ConfiguracoesViewHelper;
import viewHelpers.UsuarioViewHelper;
import viewHelpers.LoginViewHelper;

public class ResetarConfiguracoes extends HttpServlet {
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
				FachadaConfiguracoes fachada = new FachadaConfiguracoes();
	    		fachada.resetarConfiguracoes(LoginViewHelper.getLogInfo(req, resp));
	        
	    		resp.sendRedirect("/trabalho-les/configuracoes");
	    	} catch(Exception e) {
	    		e.printStackTrace();
	    	}
	    }
	}
}
