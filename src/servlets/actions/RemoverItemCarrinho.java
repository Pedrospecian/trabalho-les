package servlets.actions;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import facades.FachadaCarrinho;
import viewHelpers.LoginViewHelper;

public class RemoverItemCarrinho extends HttpServlet {
	private static final long serialVersionUID = 12;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LoginViewHelper lvh = new LoginViewHelper();
		if(!lvh.isAuthorized(req, resp, 2)){
			resp.sendRedirect("/trabalho-les/home");
		}else{
			req.setCharacterEncoding("UTF-8");
			resp.setCharacterEncoding("UTF-8");
			resp.setContentType("text/html");
			try {
				FachadaCarrinho fachada = new FachadaCarrinho();

				if(req.getParameter("id").matches("^[0-9]+$")) {
		        	fachada.removerItemCarrinho(Long.parseLong(req.getParameter("id")), LoginViewHelper.getLogInfo(req, resp));
		        
	        		resp.sendRedirect("/trabalho-les/carrinho");
		        } else {
		        	//retorna com os dados invalidos
		        }
	    	} catch(Exception e) {
	    		e.printStackTrace();
	    	}
	    }
	}
}
