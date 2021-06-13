package servlets;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import facades.FachadaUsuario;
import model.Usuario;

public class LoginAdminAction extends HttpServlet {
	private static final long serialVersionUID = 12;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		FachadaUsuario fachada = new FachadaUsuario();
		Usuario usuario = fachada.recuperaUsuarioLogin(req.getParameter("email"), req.getParameter("senha"));

		if (usuario == null) {
			resp.sendRedirect("/trabalho-les/loginAdmin"); 
		} else {
			Cookie cookieId=new Cookie("login_id", String.valueOf(usuario.getId())); 
			Cookie cookieNome=new Cookie("nome", String.valueOf(usuario.getNome()));
			Cookie cookieTipo=new Cookie("tipo", usuario.getTipoUsuario().getNome());  

            resp.addCookie(cookieId);
            resp.addCookie(cookieNome); 
            resp.addCookie(cookieTipo);
            resp.sendRedirect("/trabalho-les/homeAdmin");
		}
	}
}
