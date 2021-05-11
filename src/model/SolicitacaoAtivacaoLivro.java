package model;

import java.util.Date;

public class SolicitacaoAtivacaoLivro extends EntidadeDominio {

	private CategoriaAtivacao categoria;
	private String justificativa;
	private Livro livro;

	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
	}
	
	public String getJustificativa() {
		return this.justificativa;
	}

	public void setCategoria(CategoriaAtivacao categoria) {
		this.categoria = categoria;
	}
	
	public CategoriaAtivacao getCategoria() {
		return this.categoria;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}
	
	public Livro getLivro() {
		return this.livro;
	}
	
	public SolicitacaoAtivacaoLivro(long id, Date dataCadastro, CategoriaAtivacao categoria, String justificativa, Livro livro) {
		super(id, dataCadastro);
		this.categoria = categoria;
		this.justificativa = justificativa;
		this.livro = livro;
	}

}
