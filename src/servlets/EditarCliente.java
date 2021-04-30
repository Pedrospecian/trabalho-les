package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cliente;
import utils.Campo;
import facades.FachadaCliente;
import viewHelpers.LoginViewHelper;

public class EditarCliente extends HttpServlet {
	private static final long serialVersionUID = 12;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LoginViewHelper lvh = new LoginViewHelper();
		if(!lvh.isAuthorized(req, resp, 1)){
			resp.sendRedirect("/trabalho-les/home");
		}else{
			req.setCharacterEncoding("UTF-8");
			resp.setCharacterEncoding("UTF-8");
			FachadaCliente fachada = new FachadaCliente();
			Campo[] campos = new Campo[1];
			campos[0] = new Campo(1, req.getParameter("id"), true, "", true, "id");

			if(fachada.validarCampos(campos)) {
				Cliente cliente = fachada.selectSingle(Long.parseLong(campos[0].getValor()), false);

				req.setAttribute("cliente", cliente);
				req.setAttribute("actionForm", "alterarClienteAction");
				req.setAttribute("headerHTML", lvh.getHeader(req, resp, 1));
				req.getRequestDispatcher("cliente/editarCliente.jsp").include(req, resp); 
			} else {

			}
		}
	}
}
