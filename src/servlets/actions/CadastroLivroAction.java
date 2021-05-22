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
import model.Autor;
import model.Editora;
import model.GrupoPrecificacao;
import model.Categoria;
import viewHelpers.LivroViewHelper;
import viewHelpers.UsuarioViewHelper;
import viewHelpers.LoginViewHelper;

public class CadastroLivroAction extends HttpServlet {
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
				Campo[] campos = LivroViewHelper.getCadastroLivroActionCampos(req);

				FachadaLivro fachada = new FachadaLivro();

				if(fachada.validarCampos(campos)) {
					String titulo = campos[0].getValor();
			        int status = Integer.parseInt(campos[13].getValor());
			        Autor autor = new Autor(Long.parseLong(campos[1].getValor()), new Date(), "", "");
			        Editora editora = new Editora(Long.parseLong(campos[2].getValor()), new Date(), "", "");
			        Categoria[] categorias = LivroViewHelper.createCategoriasFromStrings(campos[16].getValor());
			        String capa = campos[3].getValor();
			        String ano = campos[4].getValor();
			        String isbn = campos[5].getValor();
			        int numeroPaginas = Integer.parseInt(campos[6].getValor());
			        String sinopse = campos[7].getValor();
			        double altura = Double.parseDouble(campos[8].getValor());
			        double peso = Double.parseDouble(campos[9].getValor());
			        double profundidade = Double.parseDouble(campos[10].getValor());
			        double preco = Double.parseDouble(campos[11].getValor());
			        double largura = Double.parseDouble(campos[17].getValor());
			        String codigoBarras = campos[12].getValor();
			        GrupoPrecificacao grupoPrecificacao = new GrupoPrecificacao(Long.parseLong(campos[14].getValor()), new Date(), "", 1, 1);
			        String edicao = campos[15].getValor();
			        
		        	Livro livro = new Livro((long)1, new Date(), titulo, autor, editora, categorias, ano, isbn, numeroPaginas, sinopse, altura, peso, profundidade, preco, codigoBarras, status, capa, grupoPrecificacao, edicao);
		        	livro.setLargura(largura);
		        	
		        	fachada.insert(livro);
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
