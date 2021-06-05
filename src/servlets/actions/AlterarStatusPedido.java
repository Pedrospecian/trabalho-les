package servlets.actions;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.Campo;
import facades.FachadaPedido;
import model.Pedido;
import viewHelpers.PedidoViewHelper;
import viewHelpers.UsuarioViewHelper;
import viewHelpers.LoginViewHelper;

public class AlterarStatusPedido extends HttpServlet {
	private static final long serialVersionUID = 12;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LoginViewHelper lvh = new LoginViewHelper();
		if(!lvh.isAuthorized(req, resp, 1)){
			resp.sendRedirect("/trabalho-les/home");
		}else{
			resp.setContentType("text/html");
			req.setCharacterEncoding("UTF-8");
			resp.setCharacterEncoding("UTF-8");

		    
			try {
				Campo[] campos = PedidoViewHelper.getAlterarPedidoStatusActionCampos(req);

				FachadaPedido fachada = new FachadaPedido();

				if(fachada.validarCampos(campos)) {
			        long id = Long.parseLong(campos[0].getValor());
		        
		        	Pedido pedido = new Pedido(id, new Date(), null, 1, null, 0, null, null, null, null, 0, 0, "");
		        	fachada.updateStatus(pedido, LoginViewHelper.getLogInfo(req, resp));
		        } else {
		        	//retorna com os dados invalidos
		        }

		        resp.sendRedirect("/trabalho-les/todosPedidos");
	    	} catch(Exception e) {
	    		e.printStackTrace();
	    	}
	    }
	}
}
