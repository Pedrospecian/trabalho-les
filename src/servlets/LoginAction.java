package servlets;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import facades.FachadaCliente;
import model.Cliente;

public class LoginAction extends HttpServlet {
	private static final long serialVersionUID = 12;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		FachadaCliente fachada = new FachadaCliente();
		Cliente cliente = fachada.recuperaClienteLogin(req.getParameter("email"), req.getParameter("senha"));

		if (cliente == null) {
			resp.sendRedirect("/trabalho-les/login"); 
		} else {
			Cookie cookieId=new Cookie("login_id", String.valueOf(cliente.getId())); 
			Cookie cookieNome=new Cookie("nome", String.valueOf(cliente.getNome()));
			Cookie cookieTipo=new Cookie("tipo", "cliente");  

			cookieId.setMaxAge(28800);
			cookieNome.setMaxAge(28800);  
			cookieTipo.setMaxAge(28800);  

            resp.addCookie(cookieId);
            resp.addCookie(cookieNome); 
            resp.addCookie(cookieTipo); 
            //req.setAttribute("headerHTML", lvh.getHeader(req, resp, 1));
            resp.sendRedirect("/trabalho-les/home");
		}
	}
}
