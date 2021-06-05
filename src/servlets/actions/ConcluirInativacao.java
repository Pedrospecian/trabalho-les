package servlets.actions;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.Campo;
import facades.FachadaCliente;
import facades.FachadaLivro;
import model.Cliente;
import viewHelpers.ClienteViewHelper;
import viewHelpers.UsuarioViewHelper;
import viewHelpers.LoginViewHelper;

public class ConcluirInativacao extends HttpServlet {
	private static final long serialVersionUID = 12;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LoginViewHelper lvh = new LoginViewHelper();
		if(!lvh.isAuthorized(req, resp, 3)){
			resp.sendRedirect("/trabalho-les/homeAdmin");
		}else{
			resp.setContentType("text/html");

			FachadaLivro fachada = new FachadaLivro();

			fachada.concluirInativacao(Long.parseLong(req.getParameter("id")), Integer.parseInt(req.getParameter("aceite")), LoginViewHelper.getLogInfo(req, resp));

		    resp.sendRedirect("/trabalho-les/livrosPendentesInativacao");

			/*try {
				Campo[] campos = ClienteViewHelper.getAlterarClienteStatusActionCampos(req);

				FachadaCliente fachada = new FachadaCliente();

				if(fachada.validarCampos(campos)) {
			        long id = Long.parseLong(campos[0].getValor());
					int status = Integer.parseInt(campos[1].getValor());
		        
		        	Cliente cliente = new Cliente(id, new Date(), null, "", 1, new Date(), null, null, status);
		        	fachada.updateStatus(cliente);
		        } else {
		        	//retorna com os dados invalidos
		        }
	    	} catch(Exception e) {
	    		e.printStackTrace();
	    	}*/
	    }
	}
}
