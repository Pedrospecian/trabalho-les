package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Logout extends HttpServlet {
	private static final long serialVersionUID = 12;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
  
        Cookie cookieId = new Cookie("login_id", ""); 
		Cookie cookieNome = new Cookie("nome", "");
		Cookie cookieTipo = new Cookie("tipo", "");

        cookieId.setMaxAge(0);
		cookieNome.setMaxAge(0);  
		cookieTipo.setMaxAge(0);  

        resp.addCookie(cookieId);
        resp.addCookie(cookieNome); 
        resp.addCookie(cookieTipo);
        
        resp.sendRedirect("/trabalho-les/logoutRedirect"); 
	}
}
