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
	
	public String insert(Livro livro) {
		try {
			LivroDAO dao = new LivroDAO();
			dao.insert(livro);

			return "Livro inserido com sucesso!";
		}catch(Exception e) {
			e.printStackTrace();
			return "Erro de validação. Tente novamente.";
		}
	}

	public String delete(Livro livro) {
		LivroDAO dao = new LivroDAO();

		dao.delete(livro.getId());

		return "Livro excluído com sucesso!";
	}

	public String update(Livro livro) {
		LivroDAO dao = new LivroDAO();
		dao.update(livro);

		return "Livro alterado com sucesso!";
	}

	public void  updatePreco(Livro livro, boolean gerenteVendas) {
		LivroDAO dao = new LivroDAO();
		dao.updatePreco(livro, gerenteVendas);
	}
	
	public String updateStatus(Livro livro) {
		LivroDAO dao = new LivroDAO();
		dao.updateStatus(livro);

		return "Status de livro alterado com sucesso!";
	}

	public void insertEstoque(LivroEstoque livroEstoque) {
		try {
			LivroDAO dao = new LivroDAO();
			dao.insertEstoque(livroEstoque);

			dao.alteraPreco( livroEstoque.getLivro().getId() );

			//return "Entrada de estoque no livro inserida com sucesso!";
		}catch(Exception e) {
			e.printStackTrace();
			//return "Erro de validação. Tente novamente.";
		}		
	}

	public void inserirSolicitacaoInativacaoLivro(SolicitacaoInativacaoLivro sol) {
		LivroDAO dao = new LivroDAO();
		
		dao.inserirSolicitacaoInativacaoLivro(sol);		
	}
	
	public void inserirSolicitacaoAtivacaoLivro(SolicitacaoAtivacaoLivro sol) {
		LivroDAO dao = new LivroDAO();
		
		dao.inserirSolicitacaoAtivacaoLivro(sol);		
	}

	public void concluirInativacao(long idLivro, int aceite) {
		LivroDAO dao = new LivroDAO();
		
		dao.concluirInativacao(idLivro, aceite);
	}
	
	public void concluirAtivacao(long idLivro, int aceite) {
		LivroDAO dao = new LivroDAO();
		
		dao.concluirAtivacao(idLivro, aceite);
	}

	public void deleteCategorias(Categoria[] categorias, long idLivro) {
		try {	
			LivroDAO dao = new LivroDAO();
			dao.deleteCategorias(categorias, idLivro);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
