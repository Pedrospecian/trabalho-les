package viewHelpers;

import java.util.Date;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import utils.Campo;
import model.Categoria;
import model.Livro;
import utils.ResultadosBusca;

public class LivroViewHelper {
	public static Campo[] getListagemLivrosCampos(HttpServletRequest req) {
		Campo[] campos = new Campo[9];

		/*campos[0] = new Campo(0, req.getParameter("titulo"), true, "", true, "titulo");
		campos[1] = new Campo(0, req.getParameter("autor"), true, "", true, "autor");
		campos[2] = new Campo(0, req.getParameter("editora"), true, "", true, "editora");
		campos[3] = new Campo(0, req.getParameter("categoria"), true, "", true, "categoria");
		campos[4] = new Campo(1, req.getParameter("ano"), true, "", true, "ano");
		campos[5] = new Campo(1, req.getParameter("isbn"), true, "", true, "isbn");
		campos[6] = new Campo(1, req.getParameter("numerodepaginas"), true, "", true, "numeroPaginas");
		campos[7] = new Campo(0, req.getParameter("sinopse"), true, "", true, "sinopse");
		campos[8] = new Campo(555, req.getParameter("altura"), true, "", true, "altura");
		campos[9] = new Campo(555, req.getParameter("peso"), true, "", true, "peso");
		campos[10] = new Campo(555, req.getParameter("profundidade"), true, "", true, "profundidade");
		campos[11] = new Campo(555, req.getParameter("preco"), true, "", true, "preco");
		campos[12] = new Campo(1, req.getParameter("codigodebarras"), true, "", true, "codigoBarras");
		campos[13] = new Campo(1, req.getParameter("status"), true, "", true, "status");
		campos[14] = new Campo(1, req.getParameter("status"), true, "", true, "grupoPrecificacao");
		campos[15] = new Campo(0, req.getParameter("status"), true, "", true, "grupoPrecificacao");*/

		return campos;
	}

	public static Campo[] getListagemEstoqueCampos(HttpServletRequest req) {
		Campo[] campos = new Campo[6];

		campos[0] = new Campo(1, req.getParameter("fornecedor"), true, "", true, "fornecedores.id");
		campos[1] = new Campo(1, req.getParameter("usuarioResponsavel"), true, "", true, "livros_estoque.usuarioResponsavel");
		campos[2] = new Campo(3, req.getParameter("dataEntrada"), true, "", true, "livros_estoque.dataEntrada");
		campos[3] = new Campo(1, req.getParameter("tipoMovimentacao"), true, "", true, "livros_estoque.tipoMovimentacao");
		campos[4] = new Campo(1, req.getParameter("custo"), true, "", true, "livros_estoque.custo");
		campos[5] = new Campo(1, req.getParameter("id"), true, "", true, "livros_estoque.livroId");

		return campos;
	}

	/*
	data entrada
	usuario responsavel
	fornecedor
	tipomovimentacao
	custo
	id
	*/

	public static Campo[] getAlterarLivroStatusActionCampos(HttpServletRequest req) {
		Campo[] campos = new Campo[2];

		campos[0] = new Campo(1, req.getParameter("id"), true, "", true, "id");
		campos[1] = new Campo(1, req.getParameter("status"), true, "", true, "status");

		return campos;
	}

	public static Campo[] getCadastroLivroActionCampos(HttpServletRequest req) {
		Campo[] campos = new Campo[17];

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
		System.out.println("a capa VVV");
		System.out.println(campos[3].getValor());

		return campos;
	}

	public static Campo[] getAlterarLivroActionCampos(HttpServletRequest req) {
		Campo[] campos = new Campo[18]; 

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


		return campos;
	}

	public static String[] createLinksPaginacao(Campo campo, ResultadosBusca resultadosBusca) {
        int resultadosPorPagina = LivroViewHelper.getResultadosPorPagina(campo);
        int linksPaginacaoCount = LivroViewHelper.linksPaginacaoCount(resultadosBusca, resultadosPorPagina);

        return LivroViewHelper.getLinksPaginacao(linksPaginacaoCount, resultadosPorPagina);
    }

	public static int getResultadosPorPagina(Campo campo) {
		if (campo == null || campo.getValor() == null || campo.getValor().equals("") || campo.getValor().matches("^[0-9]+$") == false) {
            return 10;
        } else {
        	return Integer.parseInt(campo.getValor());
        }
	}

	public static int linksPaginacaoCount(ResultadosBusca resultadosBusca, int resultadosPorPagina) {
		return (int)Math.ceil((double)resultadosBusca.getContagemTotal() / (double)resultadosPorPagina);
	}

	public static String[] getLinksPaginacao(int linksPaginacaoCount, int resultadosPorPagina) {
		String[] linksPaginacao = new String[linksPaginacaoCount];

        for (int i = 0; i < linksPaginacao.length; i++) {
        	linksPaginacao[i] = "paginaAtual=" + (i + 1) + "&resultadosPorPagina=" + resultadosPorPagina;
        }

        return linksPaginacao;
	}

	public static Campo[] getCadastrarEstoqueActionCampos(HttpServletRequest req) {
		Campo[] campos = new Campo[5];

		campos[0] = new Campo(1, req.getParameter("idLivro"), true, "", true, "idLivro");
		campos[1] = new Campo(1, req.getParameter("quantidade"), true, "", true, "quantidade");
		campos[2] = new Campo(555, req.getParameter("custo"), true, "", true, "custo");
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

	public static Categoria[] createCategoriasFromStrings(String id) throws Exception {
		if (id.equals("")) return null;
		String[] arrCategoriasIdsStr = id.split(",");

		if (arrCategoriasIdsStr.length > 0) {
			Categoria[] categorias = new Categoria[arrCategoriasIdsStr.length];

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

	
}