package model;

import java.util.Date;

public class Cidade extends EntidadeDominio {

	private String descricao;
	private Estado estado;
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
	
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	public Estado getEstado() {
		return this.estado;
	}
	
	public Cidade(long id, Date dataCadastro, String descricao, Estado estado) {
		super(id, dataCadastro);
		this.descricao = descricao;
		this.estado = estado;
	}

}
