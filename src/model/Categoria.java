package model;

import java.util.Date;

public class Categoria extends EntidadeDominio {

	private String nome;

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public Categoria(long id, Date dataCadastro, String nome) {
		super(id, dataCadastro);
		this.nome = nome;
	}

}
