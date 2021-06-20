package viewHelpers;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import utils.Campo;
import model.Autor;
import model.Categoria;
import model.Editora;
import model.GrupoPrecificacao;
import model.Livro;

public class LivroViewHelper implements IViewHelper<Livro> {
	public static Campo[] getListagemLivrosCamposHeader(HttpServletRequest req) {
		Campo[] campos = new Campo[1];
		campos[0] = new Campo(0, req.getParameter("term"), true, "", true, "titulo");
		return campos;
	}

	public Campo[] listagemCampos(HttpServletRequest req) {
		Campo[] campos = new Campo[16];

		String resultadosPorPagina = "10";

		if (req.getParameter("resultadosPorPagina") != null && req.getParameter("resultadosPorPagina").matches("^[0-9]+$")) {
			resultadosPorPagina = req.getParameter("resultadosPorPagina");
		}

		campos[0] = new Campo(0, req.getParameter("titulo"), true, "", true, "titulo");
		campos[1] = new Campo(1, req.getParameter("autor"), true, "", true, "autorId");
		campos[2] = new Campo(1, req.getParameter("editora"), true, "", true, "idEditora");
		campos[3] = new Campo(0, req.getParameter("isbn"), true, "", true, "isbn");
		campos[4] = new Campo(0, req.getParameter("codigoBarras"), true, "", true, "codigoBarras");		
		campos[5] = new Campo(1, req.getParameter("status"), true, "", true, "livros.status");
		campos[6] = new Campo(0, req.getParameter("edicao"), true, "", true, "edicao");
		campos[7] = new Campo(1, req.getParameter("ano"), true, "", true, "ano");
		campos[8] = new Campo(1, req.getParameter("numeroPaginas"), true, "", true, "numeroPaginas");
		campos[9] = new Campo(555, req.getParameter("altura"), true, "", true, "altura");
		campos[10] = new Campo(555, req.getParameter("peso"), true, "", true, "peso");
		campos[11] = new Campo(555, req.getParameter("profundidade"), true, "", true, "profundidade");
		campos[12] = new Campo(555, req.getParameter("preco"), true, "", true, "preco");
		campos[13] = new Campo(1, req.getParameter("grupoPrecificacao"), true, "", true, "idGrupoPrecificacao");
		campos[14] = new Campo(555, req.getParameter("largura"), true, "", true, "largura");
		campos[15] = new Campo(999, resultadosPorPagina, true, "", true, "resultadosPorPagina");

		return campos;
	}

	public static Campo[] getListagemLivrosCamposDetalhada(HttpServletRequest req) {
		Campo[] campos = new Campo[8];

		campos[0] = new Campo(0, req.getParameter("titulo"), true, "", true, "titulo");
		campos[1] = new Campo(1, req.getParameter("autor"), true, "", true, "autorId");
		campos[2] = new Campo(1, req.getParameter("editora"), true, "", true, "idEditora");
		campos[3] = new Campo(1, req.getParameter("categoria"), true, "", true, "livros_categorias.idCategoria");
		campos[4] = new Campo(0, req.getParameter("isbn"), true, "", true, "isbn");	
		campos[5] = new Campo(0, req.getParameter("edicao"), true, "", true, "edicao");
		campos[6] = new Campo(1, req.getParameter("ano"), true, "", true, "ano");
		campos[7] = new Campo(555, req.getParameter("preco"), true, "", true, "preco");

		return campos;
	}

	public static Campo[] getListagemEstoqueCampos(HttpServletRequest req) {
		Campo[] campos = new Campo[7];

		String custo = req.getParameter("custo");
		if (custo != null) {
			custo = custo.replace(',', '.');
		}

		campos[0] = new Campo(1, req.getParameter("fornecedor"), true, "", true, "fornecedores.id");
		campos[1] = new Campo(1, req.getParameter("usuarioResponsavel"), true, "", true, "livros_estoque.idUsuarioAdmin");
		campos[2] = new Campo(3, req.getParameter("dataEntrada"), true, "", true, "livros_estoque.dataEntrada");
		campos[3] = new Campo(1, req.getParameter("tipoMovimentacao"), true, "", true, "livros_estoque.tipoMovimentacao");
		campos[4] = new Campo(1, custo, true, "", true, "livros_estoque.custo");
		campos[5] = new Campo(1, req.getParameter("id"), true, "", true, "livros_estoque.livroId");
		campos[6] = new Campo(1, req.getParameter("clienteResponsavel"), true, "", true, "livros_estoque.idCliente");

		return campos;
	}

	public Campo[] alterarStatusCampos(HttpServletRequest req) {
		Campo[] campos = new Campo[2];

		campos[0] = new Campo(1, req.getParameter("id"), true, "", true, "id");
		campos[1] = new Campo(1, req.getParameter("status"), true, "", true, "status");

		return campos;
	}

	public Campo[] cadastroCampos(HttpServletRequest req) {
		Campo[] campos = new Campo[18];

		campos[0] = new Campo(0, req.getParameter("titulo"), true, "", true, "titulo");
		campos[1] = new Campo(1, req.getParameter("autor"), true, "", true, "autor");
		campos[2] = new Campo(1, req.getParameter("editora"), true, "", true, "editora");
		campos[3] = new Campo(0, req.getParameter("capa"), true, "", false, "capa");
		campos[4] = new Campo(1, req.getParameter("ano"), true, "", true, "ano");
		campos[5] = new Campo(1, req.getParameter("isbn"), true, "", true, "isbn");
		campos[6] = new Campo(1, req.getParameter("numeroPaginas"), true, "", true, "numeroPaginas");
		campos[7] = new Campo(0, req.getParameter("sinopse"), true, "", true, "sinopse");
		campos[8] = new Campo(555, req.getParameter("altura"), true, "", true, "altura");
		campos[9] = new Campo(555, req.getParameter("peso"), true, "", true, "peso");
		campos[10] = new Campo(555, req.getParameter("profundidade"), true, "", true, "profundidade");
		campos[11] = new Campo(555, req.getParameter("preco"), true, "", true, "preco");
		campos[12] = new Campo(1, req.getParameter("codigoBarras"), true, "", true, "codigoBarras");
		campos[13] = new Campo(1, req.getParameter("status"), true, "", true, "status");
		campos[14] = new Campo(1, req.getParameter("grupoPrecificacao"), true, "", true, "grupoPrecificacao");
		campos[15] = new Campo(0, req.getParameter("edicao"), true, "", false, "edicao");

		campos[16] = new Campo(6, req.getParameter("arrIdCategoria"), true, "", false, "arrIdCategoria");
		campos[17] = new Campo(555, req.getParameter("largura"), true, "", true, "largura");
		
		System.out.println("a capa VVV");
		System.out.println(campos[3].getValor());

		return campos;
	}

	public Campo[] alterarCampos(HttpServletRequest req) {
		Campo[] campos = new Campo[19]; 

		campos[0] = new Campo(0, req.getParameter("titulo"), true, "", true, "titulo");
		campos[1] = new Campo(0, req.getParameter("autor"), true, "", true, "autor");
		campos[2] = new Campo(0, req.getParameter("editora"), true, "", true, "editora");
		campos[3] = new Campo(0, req.getParameter("capa"), true, "", false, "capa");
		campos[4] = new Campo(1, req.getParameter("ano"), true, "", true, "ano");
		campos[5] = new Campo(1, req.getParameter("isbn"), true, "", true, "isbn");
		campos[6] = new Campo(1, req.getParameter("numeroPaginas"), true, "", true, "numeroPaginas");
		campos[7] = new Campo(0, req.getParameter("sinopse"), true, "", true, "sinopse");
		campos[8] = new Campo(555, req.getParameter("altura"), true, "", true, "altura");
		campos[9] = new Campo(555, req.getParameter("peso"), true, "", true, "peso");
		campos[10] = new Campo(555, req.getParameter("profundidade"), true, "", true, "profundidade");
		campos[11] = new Campo(555, req.getParameter("preco"), true, "", true, "preco");
		campos[12] = new Campo(1, req.getParameter("codigoBarras"), true, "", true, "codigoBarras");
		campos[13] = new Campo(1, req.getParameter("id"), true, "", true, "id");
		campos[14] = new Campo(1, req.getParameter("grupoPrecificacao"), true, "", true, "grupoPrecificacao");
		campos[15] = new Campo(0, req.getParameter("edicao"), true, "", false, "edicao");

		campos[16] = new Campo(6, req.getParameter("arrIdCategoria"), true, "", false, "arrIdCategoria");
		campos[17] = new Campo(6, req.getParameter("removerCategorias"), true, "", false, "removerCategorias");
		campos[18] = new Campo(555, req.getParameter("largura"), true, "", true, "largura");


		return campos;
	}

	public static Campo[] getCadastrarEstoqueActionCampos(HttpServletRequest req) {
		Campo[] campos = new Campo[5];

		campos[0] = new Campo(1, req.getParameter("idLivro"), true, "", true, "idLivro");
		campos[1] = new Campo(1, req.getParameter("quantidade"), true, "", true, "quantidade");
		campos[2] = new Campo(555, req.getParameter("custo").replace(',', '.'), true, "", true, "custo");
		campos[3] = new Campo(1, req.getParameter("fornecedor"), true, "", true, "fornecedor");
		campos[4] = new Campo(3, req.getParameter("dataEntrada"), true, "", true, "dataEntrada");

		return campos;
	}

	public static Campo[] getInativacaoCampos(HttpServletRequest req) {
		Campo[] campos = new Campo[3];

		campos[0] = new Campo(1, req.getParameter("id"), true, "", true, "id");
		campos[1] = new Campo(1, req.getParameter("categoriaInativacao"), true, "", true, "categoriaInativacao");
		campos[2] = new Campo(0, req.getParameter("justificativa"), true, "", true, "justificativa");

		return campos;
	}
	
	public static Campo[] getAtivacaoCampos(HttpServletRequest req) {
		Campo[] campos = new Campo[3];

		campos[0] = new Campo(1, req.getParameter("id"), true, "", true, "id");
		campos[1] = new Campo(1, req.getParameter("categoriaAtivacao"), true, "", true, "categoriaAtivacao");
		campos[2] = new Campo(0, req.getParameter("justificativa"), true, "", true, "justificativa");

		return campos;
	}

	public static Campo[] getSolicitacoesAtivacaoCampos(HttpServletRequest req) {
		Campo[] campos = new Campo[4];

		String resultadosPorPagina = "10";

		if (req.getParameter("resultadosPorPagina") != null && req.getParameter("resultadosPorPagina").matches("^[0-9]+$")) {
			resultadosPorPagina = req.getParameter("resultadosPorPagina");
		}

		campos[0] = new Campo(0, req.getParameter("titulo"), true, "", true, "titulo");
		campos[1] = new Campo(1, req.getParameter("categoria"), true, "", true, "idCategoria");
		campos[2] = new Campo(1, req.getParameter("idUsuario"), true, "", true, "idUsuario");
		campos[3] = new Campo(999, resultadosPorPagina, true, "", true, "resultadosPorPagina");

		return campos;
	}

	public static Categoria[] createCategoriasFromStrings(String id) throws Exception {
		if (id.equals("")) return null;
		String[] arrCategoriasIdsStr = id.split(",");

		if (arrCategoriasIdsStr.length > 0) {
			Categoria[] categorias = new Categoria[arrCategoriasIdsStr.length];
			
			for (int i = 0; i < categorias.length; i++) {
				System.out.println(arrCategoriasIdsStr[i]);
			}

			for (int i = 0; i < categorias.length; i++) {
				categorias[i] = new Categoria(Long.parseLong(arrCategoriasIdsStr[i]), new Date(), "");
			}

			return categorias;
		} else {
			return null;
		}
	}

	public static Categoria[] createCategoriasRemovidasFromStrings(String ids) {
		if (ids.equals("")) return null;
		String[] arrCategoriasIds = ids.split(",");

		if (arrCategoriasIds.length > 0) {
			Categoria[] categorias = new Categoria[arrCategoriasIds.length];

			for (int i = 0; i < categorias.length; i++) {
				categorias[i] = new Categoria(Long.parseLong(arrCategoriasIds[i]), new Date(), "");
			}

			return categorias;
		} else {		
			return null;
		}
	}

	public Livro instancia(Campo[] campos) {
		try {
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
	    	return livro;
	    } catch(Exception e) {
	    	e.printStackTrace();
	    	return null;
	    }
	}
	
}