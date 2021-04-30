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
import model.Usuario;
import viewHelpers.UsuarioViewHelper;
import model.TipoUsuario;
import viewHelpers.LoginViewHelper;

public class AlterarUsuarioAdminAction extends HttpServlet {
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
				Campo[] campos = UsuarioViewHelper.getAlterarUsuarioActionCampos(req);

				FachadaUsuario fachada = new FachadaUsuario();

				if(fachada.validarCampos(campos, false)) {
			        long id = Long.parseLong(campos[0].getValor());
					String nome = campos[1].getValor();
					String email = campos[2].getValor();
			        int status = Integer.parseInt(campos[3].getValor());
			        TipoUsuario tipoUsuario = new TipoUsuario(Long.parseLong(campos[4].getValor()), new Date(), "", "");

		        	Usuario usuario = new Usuario(id, new Date(), nome, email, status, "", tipoUsuario);

		        	fachada.update(usuario);
		        
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
