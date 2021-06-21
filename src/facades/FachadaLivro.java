package facades;

import dao.LivroDAO;
import model.CategoriaInativacao;
import model.Categoria;
import model.CategoriaAtivacao;
import model.Livro;
import model.LivroEstoque;
import model.SolicitacaoAtivacaoLivro;
import model.SolicitacaoInativacaoLivro;
import strategies.ValidarCampos;
import utils.Campo;
import utils.ResultadosBusca;
import java.util.ArrayList;
import utils.Log;

public class FachadaLivro implements IFachada< Livro, Campo[]> {
	public boolean validarCampos(Campo[] campos) {
		ValidarCampos validarCampos = new ValidarCampos();

		boolean camposValidos = true;

		for(int i = 0; i < campos.length; i++) {
			if (validarCampos.processa(campos[i]) == false) {
				System.out.println(campos[i].getValor());
				System.out.println(campos[i].getMensagemErro());
				camposValidos = false;
			}
		}
		return camposValidos;
	}

	public ResultadosBusca select(Campo[] campos) {
		LivroDAO dao = new LivroDAO();

		dao.select(campos);
		ArrayList arrl = dao.selectVals;
		ResultadosBusca rb = new ResultadosBusca(arrl);

		return rb;
	}

	public ResultadosBusca selectDetalhado(Campo[] campos) {
		LivroDAO dao = new LivroDAO();

		dao.selectDetalhado(campos);
		ArrayList arrl = dao.selectVals;
		ResultadosBusca rb = new ResultadosBusca(arrl);

		return rb;
	}

	public ResultadosBusca getSolicitacoesAtivacao(Campo[] campos) {
		LivroDAO dao = new LivroDAO();

		dao.getSolicitacoesAtivacao(campos);
		ArrayList arrl = dao.selectVals;
		ResultadosBusca rb = new ResultadosBusca(arrl);

		return rb;
	}

	public ResultadosBusca getSolicitacoesInativacao(Campo[] campos) {
		LivroDAO dao = new LivroDAO();

		dao.getSolicitacoesInativacao(campos);
		ArrayList arrl = dao.selectVals;
		ResultadosBusca rb = new ResultadosBusca(arrl);

		return rb;
	}

	public ResultadosBusca getCategorias() {
		LivroDAO dao = new LivroDAO();
		dao.getCategorias();
		ArrayList arrl = dao.selectVals;
		ResultadosBusca rb = new ResultadosBusca(arrl);

		return rb;
	}

	public ResultadosBusca getEditoras() {
		LivroDAO dao = new LivroDAO();
		dao.getEditoras();
		ArrayList arrl = dao.selectVals;
		ResultadosBusca rb = new ResultadosBusca(arrl);

		return rb;
	}

	public ResultadosBusca getAutores() {
		LivroDAO dao = new LivroDAO();
		dao.getAutores();
		ArrayList arrl = dao.selectVals;
		ResultadosBusca rb = new ResultadosBusca(arrl);

		return rb;
	}

	public ResultadosBusca selectEstoque(long id, Campo[] campos) {
		LivroDAO dao = new LivroDAO();

		dao.selectEstoque(id, campos);
		ArrayList arrl = dao.selectVals;
		ResultadosBusca rb = new ResultadosBusca(arrl);

		return rb;
	}

	public ArrayList<Livro> selectHome() {
		LivroDAO dao = new LivroDAO();

		dao.selectHome();
		return dao.selectVals;
	}

	public ArrayList<CategoriaInativacao> getCategoriasInativacao() {
		LivroDAO dao = new LivroDAO();

		dao.getCategoriasInativacao();
		return dao.selectVals;
	}

	public ArrayList<CategoriaAtivacao> getCategoriasAtivacao() {
		LivroDAO dao = new LivroDAO();

		dao.getCategoriasAtivacao();
		return dao.selectVals;
	}

	public Livro selectSingle(long id) {
		LivroDAO dao = new LivroDAO();
		dao.selectSingle(id);
		return dao.selectSingleVal;
	}

	public int contaEstoque(Livro livro) {
		LivroDAO dao = new LivroDAO();
		return dao.contaEstoque(livro, 0);
	}
	
	public String insert(Livro livro, String usuarioResponsavel) {
		try {
			LivroDAO dao = new LivroDAO();
			dao.insert(livro);

			String categoriasIds = "";

			for (int i = 0; i < livro.getCategorias().length; i++) {
				categoriasIds += livro.getCategorias()[i].getId() + ", ";
			}

			categoriasIds = categoriasIds.substring(0, categoriasIds.length() - 2);

			Log log = new Log(usuarioResponsavel + " (admin)",
							 "Livro {"+ 
							 "id: " + livro.getId() +
							 ", titulo: " + livro.getTitulo() +
							 ", autor: " + livro.getAutor().getId() +
							 ", editora: " + livro.getEditora().getId() +
							 ", categorias: [" + categoriasIds + "]" +
							 ", ano: " + livro.getAno() +
							 ", numeroPaginas: " + livro.getNumeroPaginas() +
							 ", sinopse: '" + livro.getSinopse() + "'" +
							 ", altura: " + livro.getAltura() + 
							 ", largura: " + livro.getLargura() + 
							 ", peso: " + livro.getPeso() + 
							 ", profundidade: " + livro.getProfundidade() + 
							 ", preco: " + livro.getPreco() + 
							 ", codigoBarras: " + livro.getCodigoBarras() + 
							 ", status: " + livro.getStatus() +
							 ", capa: " + livro.getCapa() +
							 ", edicao: " + livro.getEdicao() +
							 ", grupoPrecificacao: " + livro.getGrupoPrecificacao().getId() +
							 "}",
							 "Inserção");
			log.registrar();

			return "Livro inserido com sucesso!";
		}catch(Exception e) {
			e.printStackTrace();
			return "Erro de validação. Tente novamente.";
		}
	}

	public String delete(Livro livro, String usuarioResponsavel) {
		LivroDAO dao = new LivroDAO();

		dao.delete(livro.getId());

		return "Livro excluído com sucesso!";
	}

	public String update(Livro livro, String usuarioResponsavel) {
		LivroDAO dao = new LivroDAO();
		dao.update(livro);

		String categoriasIds = "";
		
			if (livro.getCategorias() != null) {	
				for (int i = 0; i < livro.getCategorias().length; i++) {
					categoriasIds += livro.getCategorias()[i].getId() + ", ";
				}

				categoriasIds = categoriasIds.substring(0, categoriasIds.length() - 2);
			}

			Log log = new Log(usuarioResponsavel + " (admin)",
							 "Livro {"+ 
							 "id: " + livro.getId() +
							 ", titulo: " + livro.getTitulo() +
							 ", autor: " + livro.getAutor().getId() +
							 ", editora: " + livro.getEditora().getId() +
							 ", categoriasNovas: [" + categoriasIds + "]" +
							 ", ano: " + livro.getAno() +
							 ", numeroPaginas: " + livro.getNumeroPaginas() +
							 ", sinopse: '" + livro.getSinopse() + "'" +
							 ", altura: " + livro.getAltura() + 
							 ", largura: " + livro.getLargura() + 
							 ", peso: " + livro.getPeso() + 
							 ", profundidade: " + livro.getProfundidade() + 
							 ", preco: " + livro.getPreco() + 
							 ", codigoBarras: " + livro.getCodigoBarras() + 
							 ", status: " + livro.getStatus() +
							 ", capa: " + livro.getCapa() +
							 ", edicao: " + livro.getEdicao() +
							 ", grupoPrecificacao: " + livro.getGrupoPrecificacao().getId() +
							 "}",
							 "Alteração");
			log.registrar();

		return "Livro alterado com sucesso!";
	}

	public void  updatePreco(Livro livro, boolean gerenteVendas, String usuarioResponsavel) {
		LivroDAO dao = new LivroDAO();
		dao.updatePreco(livro, gerenteVendas);

		Log log = new Log(usuarioResponsavel + " (admin)",
							 "Livro {"+ 
							 "id: " + livro.getId() +
							 ", preco: " + livro.getPreco() +
							 "}",
							 "Alteração de preço");
		log.registrar();
	}
	
	public String updateStatus(Livro livro, String usuarioResponsavel) {
		LivroDAO dao = new LivroDAO();
		dao.updateStatus(livro);

		Log log = new Log(usuarioResponsavel + " (admin)",
							 "Livro {"+ 
							 "id: " + livro.getId() +
							 ", status: " + livro.getStatus() +
							 "}",
							 "Alteração de status");
		log.registrar();

		return "Status de livro alterado com sucesso!";
	}

	public void insertEstoque(LivroEstoque le, String usuarioResponsavel) {
		try {
			LivroDAO dao = new LivroDAO();
			dao.insertEstoque(le);

			Log log = new Log(usuarioResponsavel + " (admin)",
							 "LivroEstoque {"+ 
							 "id: " + le.getId() +
							 ", quantidade: " + le.getQuantidade() +
							 ", custo: " + le.getCusto() +
							 ", dataEntrada: " + le.getDataEntrada() +
							 ", fornecedor: " + le.getFornecedor().getId() +
							 ", idLivro: " + le.getLivro().getId() +
							 ", tipoMovimentacao: " + le.getTipoMovimentacao() +
							 "}",
							 "Inserção");
			log.registrar();

			double precoNovo = dao.alteraPreco( le.getLivro().getId() );

			Log log2 = new Log(usuarioResponsavel + " (admin)",
							 "Livro {"+ 
							 "id: " + le.getLivro().getId() +
							 ", preco: " + precoNovo +
							 "}",
							 "Alteração de preço via mudança de estoque");
			log2.registrar();
		}catch(Exception e) {
			e.printStackTrace();
		}		
	}

	public void inserirSolicitacaoInativacaoLivro(SolicitacaoInativacaoLivro sol, String usuarioResponsavel) {
		LivroDAO dao = new LivroDAO();
		
		dao.inserirSolicitacaoInativacaoLivro(sol);	

		Log log = new Log(usuarioResponsavel + " (admin)",
							 "SolicitacaoInativacaoLivro {"+ 
							 "id: " + sol.getId() +
							 ", CategoriaAtivacao: " + sol.getCategoria() +
							 ", justificativa: " + sol.getJustificativa() +
							 ", idLivro: " + sol.getLivro().getId()
							 +"}",
							 "Inserção");
		log.registrar();	
	}
	
	public void inserirSolicitacaoAtivacaoLivro(SolicitacaoAtivacaoLivro sol, String usuarioResponsavel) {
		LivroDAO dao = new LivroDAO();
		
		dao.inserirSolicitacaoAtivacaoLivro(sol);

		Log log = new Log(usuarioResponsavel + " (admin)",
							 "SolicitacaoAtivacaoLivro {"+ 
							 "id: " + sol.getId() +
							 ", CategoriaAtivacao: " + sol.getCategoria() +
							 ", justificativa: " + sol.getJustificativa() +
							 ", idLivro: " + sol.getLivro().getId()
							 +"}",
							 "Inserção");
		log.registrar();
	}

	public void concluirInativacao(long idLivro, int aceite, String usuarioResponsavel) {
		LivroDAO dao = new LivroDAO();
		
		dao.concluirInativacao(idLivro, aceite);

		Log log = new Log(usuarioResponsavel + " (admin)",
							 "ConclusaoInativacao {"+ 
							 "idLivro: " + idLivro +
							 ", aceite: " + aceite
							 +"}",
							 "Conclusão de inativação");
		log.registrar();
	}
	
	public void concluirAtivacao(long idLivro, int aceite, String usuarioResponsavel) {
		LivroDAO dao = new LivroDAO();
		
		dao.concluirAtivacao(idLivro, aceite);

		Log log = new Log(usuarioResponsavel + " (admin)",
							 "ConclusaoInativacao {"+ 
							 "idLivro: " + idLivro +
							 ", aceite: " + aceite
							 +"}",
							 "Conclusão de ativação");
		log.registrar();
	}

	public void deleteCategorias(Categoria[] categorias, long idLivro, String usuarioResponsavel) {
		try {	
			LivroDAO dao = new LivroDAO();
			dao.deleteCategorias(categorias, idLivro);

			String categoriasIds = "";

			for (int i = 0; i < categorias.length; i++) {
				categoriasIds += categorias[i].getId() + ", ";
			}

			categoriasIds = categoriasIds.substring(0, categoriasIds.length() - 2);

			Log log = new Log(usuarioResponsavel + " (admin)",
							 "Livro {"+ 
							 "id: " + idLivro +
							 ", categoriasRemovidas: [" + categoriasIds + "]" +
							 "}",
							 "Remoção de associação com categorias");
			log.registrar();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
