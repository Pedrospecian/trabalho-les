package model;

import java.util.Date;

public class CategoriaAtivacao extends EntidadeDominio {

	private String nome;

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public CategoriaAtivacao(long id, Date dataCadastro, String nome) {
		super(id, dataCadastro);
		this.nome = nome;
	}

}
