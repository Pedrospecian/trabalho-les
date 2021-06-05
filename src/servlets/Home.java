package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import facades.FachadaLivro;
import model.Livro;
import viewHelpers.LoginViewHelper;
import utils.Log;

public class Home extends HttpServlet {
	private static final long serialVersionUID = 12;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LoginViewHelper lvh = new LoginViewHelper();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

        FachadaLivro fachada = new FachadaLivro();
        ArrayList<Livro> livros = fachada.selectHome();

        System.out.println(livros.size());

        req.setAttribute("livros", livros);
        req.setAttribute("headerHTML", lvh.getHeader(req, resp, 2));

		req.getRequestDispatcher("/index.jsp").forward(req, resp);
	}
}
