package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import facades.FachadaLivro;
import model.Livro;
import model.Categoria;
import viewHelpers.LoginViewHelper;

public class DetalhesProduto extends HttpServlet {
	private static final long serialVersionUID = 12;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LoginViewHelper lvh = new LoginViewHelper();
		
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");
		try {
			FachadaLivro fachada = new FachadaLivro();

			if(req.getParameter("id").matches("^[0-9]+$")) {
	        	Livro livro = fachada.selectSingle(Long.parseLong(req.getParameter("id")));

	        	int estoque = fachada.contaEstoque(livro);
	        	System.out.println("estoque = "+ estoque);
	        	
	        	req.setAttribute("registro", livro);
	        	req.setAttribute("estoque", estoque);
	        	req.setAttribute("headerHTML", lvh.getHeader(req, resp, 2));
        		req.getRequestDispatcher("livro/detalhesProduto.jsp").forward(req, resp);
	        } else {
	        	//retorna com os dados invalidos
	        }
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
	}
}
