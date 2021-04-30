package model;

import java.util.Date;

public class Pais extends EntidadeDominio {

	private String descricao;

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
	
	public Pais(long id, Date dataCadastro, String descricao) {
		super(id, dataCadastro);
		this.descricao = descricao;
	}

}
