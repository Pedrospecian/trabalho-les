package servlets;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import facades.FachadaCliente;
import model.Cliente;
import utils.Campo;
import viewHelpers.LoginViewHelper;

public class ExcluirCliente extends HttpServlet {
	private static final long serialVersionUID = 12;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LoginViewHelper lvh = new LoginViewHelper();
		if(!lvh.isAuthorized(req, resp, 1)){
			resp.sendRedirect("/trabalho-les/home");
		}else{
			FachadaCliente fachada = new FachadaCliente();
			Campo[] campos = new Campo[1];
			campos[0] = new Campo(1, req.getParameter("id"), true, "", true, "id");

			if(fachada.validarCampos(campos)) {            
	        	Cliente cliente = new Cliente(Long.parseLong(campos[0].getValor()), new Date(), null, "", 0, new Date(), null, null, 0, null, null, null, null);
	        	fachada.delete(cliente);
	        	req.setAttribute("headerHTML", lvh.getHeader(req, resp, 1));
	        	resp.sendRedirect("/trabalho-les/listagemClientes");
	        } else {
	        	//retorna com os dados invalidos
	        }
	    }
	}
}
