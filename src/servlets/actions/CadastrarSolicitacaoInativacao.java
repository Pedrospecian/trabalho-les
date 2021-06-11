package servlets.actions;

import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.Campo;
import facades.FachadaLivro;
import facades.FachadaPedido;
import model.Cliente;
import model.Livro;
import model.ItemCarrinho;
import model.Carrinho;
import viewHelpers.LivroViewHelper;
import viewHelpers.UsuarioViewHelper;
import viewHelpers.LoginViewHelper;
import model.CategoriaInativacao;
import model.SolicitacaoInativacaoLivro;
import model.Usuario;

public class CadastrarSolicitacaoInativacao extends HttpServlet {
	private static final long serialVersionUID = 12;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LoginViewHelper lvh = new LoginViewHelper();
		if(!lvh.isAuthorized(req, resp, 1)){
			resp.sendRedirect("/trabalho-les/home");
		}else{
			req.setCharacterEncoding("UTF-8");
			resp.setCharacterEncoding("UTF-8");
			resp.setContentType("text/html");
			try {
				Campo[] campos = LivroViewHelper.getInativacaoCampos(req);

				FachadaLivro fachada = new FachadaLivro();

				if(fachada.validarCampos(campos)) {
					Livro livro = new Livro(Long.parseLong(campos[0].getValor()), null);
					CategoriaInativacao categoria = new CategoriaInativacao(Long.parseLong(campos[1].getValor()), null, "");
			        SolicitacaoInativacaoLivro sol = new SolicitacaoInativacaoLivro((long)1, null, categoria, campos[2].getValor(), livro, new Usuario(lvh.getUsuarioLogadoId(req, resp), null));
					
		        	fachada.inserirSolicitacaoInativacaoLivro(sol, LoginViewHelper.getLogInfo(req, resp));

		        	//System.out.println(sol.getJustificativa());
		        
	        		resp.sendRedirect("/trabalho-les/listagemLivros");
		        } else {
		        	//retorna com os dados invalidos
		        }
	    	} catch(Exception e) {
	    		e.printStackTrace();
	    	}
	    }
	}
}
