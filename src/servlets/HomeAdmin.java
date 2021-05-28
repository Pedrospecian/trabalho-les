package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import viewHelpers.LoginViewHelper;

public class HomeAdmin extends HttpServlet {
	private static final long serialVersionUID = 12;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LoginViewHelper lvh = new LoginViewHelper();
		if(!lvh.isAuthorized(req, resp, 5)){
			resp.sendRedirect("/trabalho-les/home");
        }else{
			req.setCharacterEncoding("UTF-8");
			resp.setCharacterEncoding("UTF-8");
			req.setAttribute("headerHTML", lvh.getHeader(req, resp, 5));
			System.out.println("o status");
			System.out.println(lvh.isAuthorized(req, resp, 3));
			req.setAttribute("isAdmin", String.valueOf(lvh.isAuthorized(req, resp, 3)));
			req.setAttribute("isGerenteVendas", String.valueOf(lvh.isAuthorized(req, resp, 4)));
			req.getRequestDispatcher("admin/homeAdmin.jsp").forward(req, resp);
		}
	}
}
