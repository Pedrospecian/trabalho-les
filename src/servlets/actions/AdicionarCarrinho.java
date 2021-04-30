package servlets.actions;

import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.Campo;
import facades.FachadaPedido;
import model.Cliente;
import model.Livro;
import model.ItemCarrinho;
import model.Carrinho;
import viewHelpers.PedidoViewHelper;
import viewHelpers.UsuarioViewHelper;
import viewHelpers.LoginViewHelper;

public class AdicionarCarrinho extends HttpServlet {
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
				Campo[] campos = PedidoViewHelper.getAdicionarCarrinhoCampos(req);

				FachadaPedido fachada = new FachadaPedido();

				if(fachada.validarCampos(campos)) {
			        Livro livro = new Livro(Long.parseLong(campos[0].getValor()), new Date(), "", null, null, null, "", "", 0, "", 0, 0, 0, 0, "", 0, "", null, "");
					int quantidade = Integer.parseInt(campos[1].getValor());
			        Cliente cliente = new Cliente(lvh.getUsuarioLogadoId(req, resp), new Date(), null, "", 0, null, null, null, 0, null, null, null, null);

			        ItemCarrinho itemCarrinho = new ItemCarrinho((long)1, new Date(), livro, quantidade, cliente);

			        //Carrinho carrinho = fachada.selectCarrinho(lvh.getUsuarioLogadoId(req, resp));

		        	fachada.adicionarCarrinho(itemCarrinho, 0);
		        
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
