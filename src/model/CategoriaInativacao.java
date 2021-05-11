package model;

import java.util.Date;

public class CategoriaInativacao extends EntidadeDominio {

	private String nome;

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public CategoriaInativacao(long id, Date dataCadastro, String nome) {
		super(id, dataCadastro);
		this.nome = nome;
	}

}
