package model;

import java.util.Date;

public class Bairro extends EntidadeDominio {

	private String descricao;
	private Cidade cidade;
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
	
	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
	
	public Cidade getCidade() {
		return this.cidade;
	}
	
	public Bairro(long id, Date dataCadastro, String descricao, Cidade cidade) {
		super(id, dataCadastro);
		this.descricao = descricao;
		this.cidade = cidade;
	}

}
