package model;

import java.util.Date;

public class Estado extends EntidadeDominio {

	private String descricao;	
	private Pais pais;
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
	
	public void setPais(Pais pais) {
		this.pais = pais;
	}
	
	public Pais getPais() {
		return this.pais;
	}
	
	public Estado(long id, Date dataCadastro, String descricao, Pais pais) {
		super(id, dataCadastro);
		this.descricao = descricao;
		this.pais = pais;
	}
}
