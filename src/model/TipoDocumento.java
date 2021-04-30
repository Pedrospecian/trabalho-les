package model;

import java.util.Date;

public class TipoDocumento extends EntidadeDominio {

	private String nome;
	private String descricao;
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return this.nome;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
	
	public TipoDocumento(long id, Date dataCadastro, String nome, String descricao) {
		super(id, dataCadastro);
		this.nome = nome;
		this.descricao = descricao;
	}

}
