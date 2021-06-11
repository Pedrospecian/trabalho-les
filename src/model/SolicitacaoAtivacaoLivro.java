package model;

import java.util.Date;

public class SolicitacaoAtivacaoLivro extends EntidadeDominio {

	private CategoriaAtivacao categoria;
	private String justificativa;
	private Livro livro;
	private Usuario usuario;

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

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Usuario getUsuario() {
		return this.usuario;
	}
	
	public SolicitacaoAtivacaoLivro(long id, Date dataCadastro, CategoriaAtivacao categoria, String justificativa, Livro livro, Usuario usuario) {
		super(id, dataCadastro);
		this.categoria = categoria;
		this.justificativa = justificativa;
		this.livro = livro;
		this.usuario = usuario;
	}

}
