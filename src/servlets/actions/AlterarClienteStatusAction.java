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

public class AlterarClienteStatusAction extends HttpServlet {
	private static final long serialVersionUID = 12;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LoginViewHelper lvh = new LoginViewHelper();
		if(!lvh.isAuthorized(req, resp, 1)){
			resp.sendRedirect("/trabalho-les/home");
		}else{
			resp.setContentType("text/html");
			try {
				Campo[] campos = ClienteViewHelper.getAlterarClienteStatusActionCampos(req);

				FachadaCliente fachada = new FachadaCliente();

				if(fachada.validarCampos(campos)) {
			        long id = Long.parseLong(campos[0].getValor());
					int status = Integer.parseInt(campos[1].getValor());
		        
		        	Cliente cliente = new Cliente(id, new Date(), null, "", 1, new Date(), null, null, status, null, null, null, null);
		        	fachada.updateStatus(cliente);
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
