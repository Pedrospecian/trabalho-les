package model;

import java.util.Date;

public class Catbase extends EntidadeDominio {

	private String nome;
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public Catbase(long id, Date dataCadastro, String nome) {
		super(id, dataCadastro);
		this.nome = nome;
	}

}
