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
import model.Livro;
import model.LivroEstoque;
import model.Fornecedor;
import model.Usuario;
import viewHelpers.LivroViewHelper;
import viewHelpers.LoginViewHelper;

public class CadastrarEstoqueAction extends HttpServlet {
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
				Campo[] campos = LivroViewHelper.getCadastrarEstoqueActionCampos(req);

				FachadaLivro fachada = new FachadaLivro();

				if(fachada.validarCampos(campos)) {
			        int quantidade = Integer.parseInt(campos[1].getValor());
			        double custo = Double.parseDouble(campos[2].getValor());
			        Date dataEntrada = new SimpleDateFormat("yyyy-MM-dd").parse(campos[4].getValor());
			        Fornecedor fornecedor = new Fornecedor(Long.parseLong(campos[3].getValor()), new Date(), "", "", 1, null, null);
			        Livro livro = new Livro(Long.parseLong(campos[0].getValor()), new Date());
			        
		        	LivroEstoque livroEstoque = new LivroEstoque((long)1, new Date(), quantidade, custo, dataEntrada, fornecedor, livro, 1);
		        	livroEstoque.setUsuarioResponsavel(new Usuario(lvh.getUsuarioLogadoId(req, resp), null, "") );
		        	fachada.insertEstoque(livroEstoque, LoginViewHelper.getLogInfo(req, resp));
		        	resp.sendRedirect("/trabalho-les/listagemEstoque?id=" + campos[0].getValor());
		        } else {
		        	//retorna com os dados invalidos
		        }
	    	} catch(Exception e) {
	    		e.printStackTrace();
	    	} 
	    }
	}
}
