package servlets.actions;

import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.Campo;

import facades.FachadaUsuario;
import model.TipoUsuario;
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
				Campo[] campos = UsuarioViewHelper.getCadastroUsuarioActionCampos(req);

				FachadaUsuario fachada = new FachadaUsuario();

			    String email = campos[3].getValor();

				if(fachada.validarCampos(campos, true) && fachada.validaEmailExistente(email)) {
					String nome = campos[2].getValor();
			        int status = Integer.parseInt(campos[4].getValor());
			        String senha = campos[0].getValor();
			        TipoUsuario tipoUsuario = new TipoUsuario(Long.parseLong(campos[5].getValor()), new Date(), "", "");

		        	Usuario usuario = new Usuario((long)1, new Date(), nome, email, status, senha, tipoUsuario);

		        	fachada.insert(usuario);
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
