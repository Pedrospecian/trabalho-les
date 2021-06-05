package servlets.actions;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.Campo;
import facades.FachadaUsuario;
import model.Usuario;
import viewHelpers.UsuarioViewHelper;
import viewHelpers.LoginViewHelper;

public class AlterarSenhaUsuarioAdminAction extends HttpServlet {
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
				Campo[] campos = UsuarioViewHelper.getAlterarSenhaUsuarioActionCampos(req);

				FachadaUsuario fachada = new FachadaUsuario();

				if(fachada.validarCampos(campos, true) && fachada.validarSenhaExistente(campos[3].getValor(), Long.parseLong(campos[2].getValor()))) {
					long id = Long.parseLong(campos[2].getValor());
			        String senha = campos[0].getValor();
			        
		        	Usuario usuario = new Usuario(id, new Date(), "", "", 1, senha, null);

		        	fachada.updateSenha(usuario, LoginViewHelper.getLogInfo(req, resp));
		        
	        		resp.sendRedirect("/trabalho-les/listagemUsuariosAdmin");
		        } else {
		        	//retorna com os dados invalidos
		        }
	    	} catch(Exception e) {
	    		e.printStackTrace();
	    	}
	    }
	}
}
