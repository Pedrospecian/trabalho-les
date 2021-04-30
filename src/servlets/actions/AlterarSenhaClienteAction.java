package servlets.actions;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.Campo;
import facades.FachadaCliente;
import model.Cliente;
import viewHelpers.ClienteViewHelper;
import viewHelpers.UsuarioViewHelper;
import viewHelpers.LoginViewHelper;

public class AlterarSenhaClienteAction extends HttpServlet {
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
				Campo[] campos = ClienteViewHelper.getAlterarSenhaClienteActionCampos(req);

				FachadaCliente fachada = new FachadaCliente();

				if(fachada.validarCampos(campos) && fachada.validarSenha(campos) && fachada.validarSenhaExistente(campos[3].getValor(), Long.parseLong(campos[2].getValor()))) {
					long id = Long.parseLong(campos[2].getValor());
			        String senha = campos[0].getValor();
			        
		        	Cliente cliente = new Cliente(id, new Date(), null, "", senha);

		        	fachada.updateSenha(cliente);
		        
	        		resp.sendRedirect("/trabalho-les/listagemClientes");
		        } else {
		        	//retorna com os dados invalidos
		        }
	    	} catch(Exception e) {
	    		e.printStackTrace();
	    	}
	    }
	}
}
