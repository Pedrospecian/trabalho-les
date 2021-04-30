package model;

import java.util.Date;

public class GrupoPrecificacao extends EntidadeDominio {

	private String nome;
	private double porcentagem;
	private int status;

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return this.nome;
	}

	public void setPorcentagem(double porcentagem) {
		this.porcentagem = porcentagem;
	}
	
	public double getPorcentagem() {
		return this.porcentagem;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	public int getStatus() {
		return this.status;
	}
	
	public GrupoPrecificacao(long id, Date dataCadastro, String nome, double porcentagem, int status) {
		super(id, dataCadastro);
		this.nome = nome;
		this.porcentagem = porcentagem;
		this.status = status;
	}

}
