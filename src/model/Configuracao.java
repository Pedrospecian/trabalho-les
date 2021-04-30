package model;

import java.util.Date;

public class Configuracao {

	private String descricao;
	private String valor;
	private Date dataAlteracao;
	private long id;

	public void setId(long id) {
		this.id = id;
	}
	
	public long getId() {
		return this.id;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return this.descricao;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
	
	public String getValor() {
		return this.valor;
	}
	
	public void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}
	
	public Date getDataAlteracao() {
		return this.dataAlteracao;
	}
	
	public Configuracao(long id, String descricao, String valor, Date dataAlteracao) {
		this.id = id;
		this.descricao = descricao;
		this.valor = valor;
		this.dataAlteracao = dataAlteracao;
	}
}
