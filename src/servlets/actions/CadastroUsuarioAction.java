package servlets.actions;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.Campo;

import facades.FachadaUsuario;
import model.Usuario;
import viewHelpers.UsuarioViewHelper;
import viewHelpers.LoginViewHelper;

public class CadastroUsuarioAction extends HttpServlet {
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
				UsuarioViewHelper vh = new UsuarioViewHelper();
				Campo[] campos = vh.cadastroCampos(req);

				FachadaUsuario fachada = new FachadaUsuario();

				if(fachada.validarCampos(campos, true) && fachada.validaEmailExistente(campos[3].getValor())) {
					Usuario usuario = vh.instancia(campos);

		        	fachada.insert(usuario, LoginViewHelper.getLogInfo(req, resp));
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
