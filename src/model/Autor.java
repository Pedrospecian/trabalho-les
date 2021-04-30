package model;

import java.util.Date;

public class Autor extends EntidadeDominio {

	private String nome;
	private String resumo;

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return this.nome;
	}

	public void setResumo(String resumo) {
		this.resumo = resumo;
	}
	
	public String getResumo() {
		return this.resumo;
	}
	
	public Autor(long id, Date dataCadastro, String nome, String resumo) {
		super(id, dataCadastro);
		this.nome = nome;
		this.resumo = resumo;
	}

}
