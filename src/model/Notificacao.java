package model;

import java.util.Date;

public class Notificacao extends EntidadeDominio {

	private String descricao;
	private String cor;
	private long idCliente;

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return this.descricao;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}
	
	public String getCor() {
		return this.cor;
	}

	public void setIdCliente(long idCliente) {
		this.idCliente = idCliente;
	}
	
	public long getIdCliente() {
		return this.idCliente;
	}
	
	public Notificacao(long id, Date dataCadastro, String descricao, long idCliente, String cor) {
		super(id, dataCadastro);
		this.descricao = descricao;
		this.idCliente = idCliente;
		this.cor = cor;
	}

}
