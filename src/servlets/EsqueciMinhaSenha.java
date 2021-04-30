package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import viewHelpers.LoginViewHelper;

public class EsqueciMinhaSenha extends HttpServlet {
	private static final long serialVersionUID = 12;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LoginViewHelper lvh = new LoginViewHelper();
		req.setAttribute("headerHTML", lvh.getHeader(req, resp, 2));
		req.getRequestDispatcher("conta/esqueciMinhaSenha.jsp").forward(req, resp);
	}
}
